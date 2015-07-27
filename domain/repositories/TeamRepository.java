package com.softserveinc.ita.volleymanagementtests.domain.repositories;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.softserveinc.ita.volleymanagementtests.domain.models.Team;
import com.softserveinc.ita.volleymanagementtests.tests.TestsConstants;

/**
 * DAL class for "Teams" table access.
 *
 * @author DP-076ATQC
 */
public final class TeamRepository {
    /**
     * StringBuilder for SQL query quick assembly.
     */
    private static StringBuilder query = new StringBuilder();
    /**
     * Not used  constructor.
     */
    private TeamRepository() {
    }

    /**
     * Method for getting teams from DB with optional query.
     * @param inputQuery - SELECT SQL query for teams.
     * @return list of Teams.
     */
    public static List<Team> getTeamsByQuery(final String inputQuery) {
        query.delete(0, query.length());
        query = query.append("SELECT * FROM dbo.Teams ")
                .append(inputQuery);
        List<Team> teamList = new ArrayList<Team>();
        try {
            DALTools.executeStatementQuery(query.toString());
            while (DALTools.getResultSet().next()) {
                Team team = new Team();
                team.setTeamName(DALTools.getResultSet()
                        .getString("Name"));
                team.setCaptain(DALTools.getResultSet()
                        .getString("CaptainId"));
                team.setCoach(DALTools.getResultSet()
                        .getString("Coach"));
                team.setAchievements(DALTools.getResultSet()
                        .getString("Achievements"));
                teamList.add(team);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            DALTools.closeStatement();
        }
        return teamList;
    }

    /**
     * Method for counting teams in DB with optional query.
     * @param inputQuery - COUNT(*) SQL query for teams.
     * @return teams quantity.
     */
    public static int countTeamsByQuery(final String inputQuery) {
        int count = 0;
        query.delete(0, query.length());
        query = query.append("SELECT  COUNT(*) FROM ")
                .append(TestsConstants.DB_NAME).append(".")
                .append(TestsConstants.DB_SCHEMA).append(".Teams ")
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
     * Method for getting ALL teams from DB.
     * @return list of Teams.
     */
    public static List<Team> getAllTeams() {
        return getTeamsByQuery("");
    }

    /**
     * Method for getting team from DB with certain Id.
     * @param id - Id of the required team.
     * @return list of Teams.
     */
    public static List<Team> getTeamsById(final int id) {
        return getTeamsByQuery("WHERE Id="
                .concat(String.valueOf(id)));
    }

    /**
     * Method for getting team from DB with certain CaptainId.
     * @param captainId - CaptainId of the required team.
     * @return list of Teams.
     */
    public static List<Team> getTeamsByCaptainId(final int captainId) {
        return getTeamsByQuery("WHERE CaptainId="
                .concat(String.valueOf(captainId)));
    }

    /**
     * Method for getting team from DB with certain Name.
     * @param name - Name of the required team.
     * @return list of Teams.
     */
    public static List<Team> getTeamsByName(final String name) {
        return getTeamsByQuery("WHERE Name='"
                .concat(name).concat("'"));
    }

    /**
     * Method for getting team from DB with certain Coach.
     * @param coach - Coach of the required team.
     * @return list of Teams.
     */
    public static List<Team> getTeamsByCoach(final String coach) {
        return getTeamsByQuery("WHERE Coach='"
                .concat(coach).concat("'"));
    }

    /**
     * Method for getting team from DB with certain Achievements.
     * @param achievements - Achievements of the required team.
     * @return list of Teams.
     */
    public static List<Team> getTeamsByAchievements(final String achievements) {
        return getTeamsByQuery("WHERE Achievements='"
                .concat(achievements).concat("'"));
    }

    /**
     * Method for counting  all teams in DB.
     * @return teams quantity.
     */
    public static int countAllTeams() {
        return countTeamsByQuery("");
    }

    /**
     * Method for making the universal SELECT/COUNT query for certain Team.
     * @param team - Team for parse.
     * @return String - universal query.
     */
    public static String makeQuery(final Team team) {
        query.delete(0, query.length());
        query.append("WHERE Name='").append(team.getTeamName())
                .append("' AND CaptainId=")
                .append(team.getCaptain().intValue());

        if (team.getCoach() != null) {
            query.append(" AND Coach='").append(team.getCoach()).append("'");
        }
        if (team.getAchievements() != null) {
            query.append(" AND Achievements='").append(team.getAchievements())
                    .append("'");
        }
        query.append(";");
        return query.toString();
    }

    /**
     * Method for counting  certain teams in DB.
     * @param team - Team for COUNT.
     * @return teams quantity.
     */
    public static int countTeams(final Team team) {
        return countTeamsByQuery(makeQuery(team));
    }

    /**
     * Method for inserting certain teams in DB.
     * @param team - Team for insert.
     */
    public static void insertTeam(final Team team) {
        query.delete(0, query.length());
        query.append("INSERT INTO ")
                .append(TestsConstants.DB_NAME).append(".")
                .append(TestsConstants.DB_SCHEMA)
                .append(".Teams (Name, CaptainId,")
                .append(" Coach, Achievements) VALUES ('")
                .append(team.getTeamName()).append("', ")
                .append(team.getCaptain().intValue()).append(", '")
                .append(team.getCoach()).append("', '")
                .append(team.getAchievements()).append("');");
        DALTools.executeStatementUpdate(query.toString());
    }

    /**
     * Method for inserting the list of teams in DB.
     * @param teamList - Teams for insert.
     */
    public static void insertTeamList(final List<Team> teamList) {
        for (Team team : teamList) {
            insertTeam(team);
        }
    }

    /**
     * Method for check. Is certain team present in DB?
     * @param team - Teams for search.
     * @return boolean answer on question is Team Present?.
     */
    public static boolean isTeamPresent(final Team team) {
        if (countTeams(team) >= 1) {
            return true;
        }
        return false;
    }

    /**
     * Method for check. Is team with the certain achievements present in DB?
     * @param achievements - the sign of counting teams.
     * @return boolean answer on question is Team Present By Achievements?.
     */
     public static boolean isTeamPresentByAchievements(
             final String achievements) {
         if (countTeamsByQuery("WHERE Achievements='".concat(achievements)
                 .concat("';")) >= 1) {
             return true;
         }
         return false;
     }

    /**
     * Method for deleting teams from DB with optional query.
     * @param inputQuery - DELETE SQL query for teams.
     */
    public static void deleteTeamByQuery(final String inputQuery) {
        query.delete(0, query.length());
        DALTools.executeStatementUpdate(query.append("DELETE FROM ")
                .append(TestsConstants.DB_NAME).append(".")
                .append(TestsConstants.DB_SCHEMA).append(".Teams ")
                .append(inputQuery).toString());
    }

    /**
     * Method for deleting teams from DB with certain Name.
     * @param name - the sign of deleting team.
     */
    public static void deleteTeamByName(final String name) {
        deleteTeamByQuery("WHERE Name='".concat(name).concat("'"));
    }

    /**
     * Method for deleting certain team from DB.
     * @param team - certain team for delete.
     */
    public static void deleteTeam(final Team team) {
        deleteTeamByQuery(makeQuery(team));
    }

    /**
     * Method for deleting team with MAX Id from DB.
     */
    public static void deleteLastTeam() {
        int maxId = DALTools.maxValue("Teams", "Id");
        deleteTeamByQuery("WHERE Id="
                .concat(String.valueOf(maxId)));
        DALTools.resetId("Teams", (maxId - 1));
    }

}
