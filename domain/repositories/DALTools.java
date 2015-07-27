package com.softserveinc.ita.volleymanagementtests.domain.repositories;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Savepoint;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.ibatis.common.jdbc.ScriptRunner;
import com.softserveinc.ita.volleymanagementtests.tests.TestsConstants;

/**
 * DAL utility class for DB service.
 *
 * @author DP-076ATQC
 */
public final class DALTools {
    /**
     * java.sql.Connection object.
     */
    private static Connection connect = null;
    /**
     * java.sql.Statement object.
     */
    private static Statement stmt = null;
    /**
     * java.sql.ResultSet object.
     */
    private static ResultSet resultSet = null;
    /**
     * java.sql.Savepoint object.
     */
    private static Savepoint savePoint = null;
    /**
     * String for jdbc connecting to DB.
     */
    private static String connectString = null;
    /**
     * Name list of all tables in DB.
     */
    private static List<String> tableList = new ArrayList<String>();

    /**
     * Not used constructor.
     */
    private DALTools() {
    }

    /**
     * Static method for starting new DB connection.
     * @return new java.sql.Connection if Connection not exist yet.
     */
    private static Connection get() {
        if (connect == null) {
            try {
                if (connectString == null) {
                    connect = DriverManager.getConnection("jdbc:sqlserver://"
                            .concat("localhost;IntegratedSecurity=True;")
                            .concat("databaseName=")
                            .concat(TestsConstants.DB_NAME).concat("; "));
                    setTableList();
                } else {
                    connect = DriverManager.getConnection(connectString);
                    setTableList();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return connect;
    }

    /**
     * Static method for creating new SQL statement.
     * @return new java.sql.Statement if Statement not exist yet.
     * @throws SQLException - SQL exception.
     */
    private static Statement getStatement() throws SQLException {
        if (stmt == null) {
            stmt = get().createStatement();
        }
        return stmt;
    }

    /**
     * Method for starting new DB connection with certain connection string.
     * And disabling DB constraints.
     * @param inputConnectString - connection string.
     */
    public static void startConnection(final String inputConnectString) {
        connectString = inputConnectString;
        get();
        disableDBConstraints();
    }

    /**
     * Static method for closing DB connection.
     */
    public static void closeConnection() {
        if (connect != null) {
            try {
                enableDBConstraints();
                closeStatement();
                connect.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Static method for creating Name list of all tables in DB.
     * @throws SQLException - SQL exception.
     */
    private static void setTableList() throws SQLException {
        resultSet = connect.getMetaData().getTables(TestsConstants.DB_NAME,
                TestsConstants.DB_SCHEMA, null, null);
        while (resultSet.next()) {
            tableList.add(resultSet.getString("TABLE_NAME"));
        }
    }

    /**
     * JDBS method for executing SQL query.
     * @param queryStr - SQL query.
     * @throws SQLException - SQL exception.
     */
    public static void executeStatementQuery(final String queryStr)
            throws SQLException {
        resultSet = getStatement().executeQuery(queryStr);
    }

    /**
     * JDBS method for executing SQL query with DB updating.
     * @param queryStr - SQL query.
     * @throws SQLException
     */
    public static void executeStatementUpdate(final String queryStr) {
        try {
            getStatement().executeUpdate(queryStr);
        } catch (SQLException e) {
            e.printStackTrace();
            DALTools.closeStatement();
        }
    }

    /**
     * Method for executing SQL script from file.
     * @param scriptPath - SQL script file path.
     */
    public static void runSQLScript(final String scriptPath) {
        ScriptRunner runner = new ScriptRunner(get(), false, false);
        try {
            runner.runScript(new BufferedReader(new FileReader(scriptPath)));
        } catch (IOException | SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Method for dropping DB.
     */
    public static void dropDB() {
        executeStatementUpdate("DROP DATABASE ".concat(TestsConstants.DB_NAME));
    }

    /**
     * Method for clearing DB table.
     * @param tableName - table for clearing.
     */
    public static void clearTable(final String tableName) {
        executeStatementUpdate("DELETE FROM ".concat(TestsConstants.DB_NAME)
                .concat(".").concat(TestsConstants.DB_SCHEMA)
                .concat(".").concat(tableName));
        resetId(tableName, 0);
    }

    /**
     * Method for reseting auto-increment ID.
     * @param tableName - table for changes apply.
     * @param id - new id value.
     */
    public static void resetId(final String tableName, final int id) {
        executeStatementUpdate("DBCC CHECKIDENT ('"
                .concat(TestsConstants.DB_SCHEMA).concat(".")
                .concat(tableName).concat("', RESEED, ")
                .concat(String.valueOf(id)).concat(");"));
    }

    /**
     * Method for getting the max value of certain column.
     * Only integer column type allowed.
     * @param tableName - table for search.
     * @param field - column for search.
     * @return Max value of certain column.
     */
    public static int maxValue(final String tableName, final String field) {
        try {
            executeStatementQuery("SELECT MAX("
                    .concat(field).concat(") FROM ")
                    .concat(TestsConstants.DB_SCHEMA).concat(".")
                    .concat(tableName));
            resultSet.next();
            return resultSet.getInt("");
        } catch (SQLException e) {
            DALTools.closeStatement();
            e.printStackTrace();
        }
        return 0;
    }

    /**
     * Method for truncate certain table.
     * @param tableName - table for truncate.
     */
    public static void truncateTable(final String tableName) {
        clearTable(tableName);
        executeStatementUpdate("TRUNCATE TABLE ".concat(TestsConstants.DB_NAME)
                .concat(".").concat(TestsConstants.DB_SCHEMA)
                .concat(".").concat(tableName));
    }

    /**
     * Method for truncate all tables in DB.
     */
    public static void truncateDB() {
        for (String tableName : tableList) {
            truncateTable(tableName);
        }
    }

    /**
     * Static method for closing Statement.
     */
    public static void closeStatement() {
        try {
            if (stmt != null) {
                stmt.close();
                stmt = null;
            }
            if (resultSet != null) {
                resultSet.close();
                resultSet = null;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Static method for disabling DB constraints.
     */
    private static void disableDBConstraints() {
        for (String tableName : tableList) {
            executeStatementUpdate("ALTER TABLE ".concat(tableName)
                    .concat(" DISABLE TRIGGER ALL;"));
            executeStatementUpdate("ALTER TABLE ".concat(tableName)
                    .concat(" NOCHECK CONSTRAINT ALL;"));
        }
    }

    /**
     * Static method for enabling DB constraints.
     */
    private static void enableDBConstraints() {
        for (String tableName : tableList) {
            executeStatementUpdate("ALTER TABLE ".concat(tableName)
                    .concat(" ENABLE TRIGGER ALL;"));
            executeStatementUpdate("ALTER TABLE ".concat(tableName)
                    .concat(" CHECK CONSTRAINT ALL;"));
        }
    }

    /**
     * Static method for starting transaction.
     */
    public static void setTransaction() {
        try {
            connect.setTransactionIsolation(Connection
                    .TRANSACTION_READ_UNCOMMITTED);
            connect.setAutoCommit(false);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Static method for stopping transaction.
     */
    public static void stopTransaction() {
        try {
            connect.setAutoCommit(true);
            connect.setTransactionIsolation(Connection
                    .TRANSACTION_READ_COMMITTED);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Static method for committing transaction.
     */
    public static void commitTransaction() {
        try {
            connect.commit();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Static method for saving transaction rollback point.
     */
    public static void saveTransactionPoint() {
        try {
            savePoint = connect.setSavepoint();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Static method for rolling back transaction.
     */
    public static void rollbackTransaction() {
        try {
            connect.rollback();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Static method for rolling back transaction to certain save point.
     */
    public static void rollbackToSavePoint() {
        try {
            if (savePoint != null) {
                connect.rollback(savePoint);
                savePoint = null;
            } else {
                rollbackTransaction();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    /**
     * Static method for getting ResultSet.
     * @return ResultSet.
     */
    public static ResultSet getResultSet() {
        return resultSet;
    }

}
