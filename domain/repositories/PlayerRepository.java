package com.softserveinc.ita.volleymanagementtests.domain.repositories;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.softserveinc.ita.volleymanagementtests.domain.models.Player;
import com.softserveinc.ita.volleymanagementtests.tests.TestsConstants;

/**
 * DAL class for "Players" table access.
 *
 * @author DP-076ATQC
 */
public final class PlayerRepository {
    /**
     * StringBuilder for SQL query quick assembly.
     */
    private static StringBuilder query = new StringBuilder();
    /**
     * Not used constructor.
     */
    private PlayerRepository() {
    }

    /**
     * Method for getting players from DB with optional query.
     * @param inputQuery - SELECT SQL query for players.
     * @return list of Players.
     */
    public static List<Player> getPlayersByQuery(final String inputQuery) {
        query.delete(0, query.length());
        query = query.append("SELECT * FROM dbo.Players ")
                .append(inputQuery);
        List<Player> playerList = new ArrayList<Player>();
        try {
            DALTools.executeStatementQuery(query.toString());
            while (DALTools.getResultSet().next()) {
                Player player = new Player();
                player.setFirstName(DALTools.getResultSet()
                        .getString("FirstName"));
                player.setLastName(DALTools.getResultSet()
                        .getString("LastName"));
                player.setBirthYear(DALTools.getResultSet()
                        .getString("BirthYear"));
                player.setHeight(DALTools.getResultSet().getString("Height"));
                player.setWeight(DALTools.getResultSet().getString("Weight"));
                playerList.add(player);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            DALTools.closeStatement();
        }
        return playerList;
    }

    /**
     * Method for counting players in DB with optional query.
     * @param inputQuery - COUNT(*) SQL query for players.
     * @return players quantity.
     */
    public static int countPlayersByQuery(final String inputQuery) {
        int count = 0;
        query.delete(0, query.length());
        query = query.append("SELECT  COUNT(*) FROM ")
                .append(TestsConstants.DB_NAME).append(".")
                .append(TestsConstants.DB_SCHEMA).append(".Players ")
                .append(inputQuery);
        try {
            DALTools.executeStatementQuery(query.toString());
            if (DALTools.getResultSet().next()) {
                count = DALTools.getResultSet().getInt("");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DALTools.closeStatement();
        }
        return count;
    }

    /**
     * Method for getting ALL players from DB.
     * @return list of Players.
     */
    public static List<Player> getAllPlayers() {
        return getPlayersByQuery("");
    }

    /**
     * Method for getting player from DB with certain Id.
     * @param id - Id of the required player.
     * @return list of Players.
     */
    public static List<Player> getPlayersById(final int id) {
        return getPlayersByQuery("WHERE Id="
                .concat(String.valueOf(id)));
    }

    /**
     * Method for getting players from DB with certain TeamId.
     * @param teamId - TeamId of the required players.
     * @return list of Players.
     */
    public static List<Player> getPlayersByTeamId(final int teamId) {
        return getPlayersByQuery("WHERE TeamId="
                .concat(String.valueOf(teamId)));
    }

    /**
     * Method for getting players from DB with certain FirstName.
     * @param firstName - FirstName of the required players.
     * @return list of Players.
     */
    public static List<Player> getPlayersByFirstName(final String firstName) {
        return getPlayersByQuery("WHERE FirstName='"
                .concat(firstName).concat("'"));
    }

    /**
     * Method for getting players from DB with certain LastName.
     * @param lastName - LastName of the required players.
     * @return list of Players.
     */
    public static List<Player> getPlayersByLastName(final String lastName) {
        return getPlayersByQuery("WHERE LastName='"
                .concat(lastName).concat("'"));
    }

    /**
     * Method for getting players from DB with certain BirthYear.
     * @param birthYear - BirthYear of the required players.
     * @return list of Players.
     */
    public static List<Player> getPlayersByBirthYear(final int birthYear) {
        return getPlayersByQuery("WHERE BirthYear="
                .concat(String.valueOf(birthYear)));
    }

    /**
     * Method for getting players from DB with certain Height.
     * @param height - Height of the required players.
     * @return list of Players.
     */
    public static List<Player> getPlayersByHeight(final int height) {
        return getPlayersByQuery("WHERE Height="
                .concat(String.valueOf(height)));
    }

    /**
     * Method for getting players from DB with certain Weight.
     * @param weight - Weight of the required players.
     * @return list of Players.
     */
    public static List<Player> getPlayersByWeight(final int weight) {
        return getPlayersByQuery("WHERE Weight="
                .concat(String.valueOf(weight)));
    }

    /**
     * Method for counting  all players in DB.
     * @return players quantity.
     */
    public static int countAllPlayers() {
        return countPlayersByQuery("");
    }

    /**
     * Method for making the universal SELECT/COUNT query for certain Player.
     * @param player - Player for parse.
     * @return String - universal query.
     */
    private static String makeQuery(final Player player) {
        query.delete(0, query.length());
        query.append("WHERE FirstName='").append(player.getFirstName())
        .append("' AND LastName='").append(player.getLastName()).append("'");

        if (player.getBirthYear() != null) {
            query.append(" AND BirthYear=").append(player.getBirthYear());
        }
        if (player.getHeight() != null) {
            query.append(" AND Height=").append(player.getHeight());
        }
        if (player.getWeight() != null) {
            query.append(" AND Weight=").append(player.getWeight());
        }
        query.append(";");
        return query.toString();
    }

    /**
     * Method for counting  certain players in DB.
     * @param player - Player for COUNT.
     * @return players quantity.
     */
    public static int countPlayers(final Player player) {
        return countPlayersByQuery(makeQuery(player));
    }

    /**
     * Method for inserting certain players in DB.
     * @param player - Player for insert.
     */
    public static void insertPlayer(final Player player) {
        query.delete(0, query.length());
        query.append("INSERT INTO ")
        .append(TestsConstants.DB_NAME).append(".")
        .append(TestsConstants.DB_SCHEMA)
        .append(".Players (FirstName, LastName, TeamId,")
        .append(" BirthYear, Height, Weight) VALUES ('")
        .append(player.getFirstName()).append("', '")
        .append(player.getLastName()).append("', ")
        .append(player.getTeamId()).append(", ")
        .append(player.getBirthYear()).append(", ")
        .append(player.getHeight()).append(", ")
        .append(player.getWeight()).append(");");
        DALTools.executeStatementUpdate(query.toString());
    }

    /**
     * Method for inserting the list of players in DB.
     * @param playerList - Players for insert.
     */
    public static void insertPlayerList(final List<Player> playerList) {
        for (Player player : playerList) {
            insertPlayer(player);
        }
    }

    /**
     * Method for check. Is certain player present in DB?
     * @param player - Players for search.
     * @return boolean answer on question is Player Present?.
     */
    public static boolean isPlayerPresent(final Player player) {
        if (countPlayers(player) >= 1) {
            return true;
        }
        return false;
    }

    /**
     * Method for deleting players from DB with optional query.
     * @param inputQuery - DELETE SQL query for players.
     */
    public static void deletePlayerByQuery(final String inputQuery) {
        query.delete(0, query.length());
        DALTools.executeStatementUpdate(query.append("DELETE FROM ")
                .append(TestsConstants.DB_NAME).append(".")
                .append(TestsConstants.DB_SCHEMA).append(".Players ")
                .append(inputQuery).toString());
    }

    /**
     * Method for deleting certain player from DB.
     * @param player - certain player for delete.
     */
    public static void deletePlayer(final Player player) {
        deletePlayerByQuery(makeQuery(player));
    }

    /**
     * Method for deleting players from DB with certain LastName.
     * @param lastName - the sign of deleting player.
     */
    public static void deletePlayerByLastName(final String lastName) {
        deletePlayerByQuery("WHERE LastName='".concat(lastName).concat("'"));
    }

    /**
     * Method for deleting players from DB with certain FirstName.
     * @param firstName - the sign of deleting player.
     */
    public static void deletePlayerByFirstName(final String firstName) {
        deletePlayerByQuery("WHERE FirstName='".concat(firstName).concat("'"));
    }

    /**
     * Method for deleting players from DB with certain FullName.
     * @param firstName - the sign of deleting player.
     * @param lastName - the sign of deleting player.
     */
    public static void deletePlayerByFullName(final String firstName,
            final String lastName) {
        deletePlayerByQuery("WHERE FirstName='".concat(firstName)
                .concat("' AND LastName='").concat(lastName).concat("'"));
    }

    /**
     * Method for get MAX player Id from DB.
     * @return MAX player Id from DB.
     */
    public static int getLastId() {
        return DALTools.maxValue("Players", "Id");
    }

    /**
     * Method for deleting player with MAX Id from DB.
     */
    public static void deleteLastPlayer() {
        int maxId = DALTools.maxValue("Players", "Id");
        deletePlayerByQuery("WHERE Id="
                .concat(String.valueOf(maxId)));
        DALTools.resetId("Players", (maxId - 1));
    }

}
