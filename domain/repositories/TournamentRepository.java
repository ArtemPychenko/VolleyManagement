package com.softserveinc.ita.volleymanagementtests.domain.repositories;

import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.softserveinc.ita.volleymanagementtests.domain.models.Tournament;
import com.softserveinc.ita.volleymanagementtests.tests.TestsConstants;

/**
 * DAL class for "Tournaments" table access.
 *
 * @author DP-076ATQC
 */
public final class TournamentRepository {
    /**
     * StringBuilder for SQL query quick assembly.
     */
    private static StringBuilder query = new StringBuilder();
    /**
     * StringBuilder for SQL query quick assembly.
     */
    private static final short SEASON_DELTA = 1900;
    /**
     * Not used constructor.
     */
    private TournamentRepository() {
    }

    /**
     * Method for getting tournaments from DB with optional query.
     * @param inputQuery - SELECT SQL query for tournaments.
     * @return list of Tournaments.
     */
    public static List<Tournament> getTournamentsByQuery(
            final String inputQuery) {
        query.delete(0, query.length());
        query = query.append("SELECT * FROM dbo.Tournaments ")
                .append(inputQuery);
        List<Tournament> tournamentList = new ArrayList<Tournament>();
        try {
            DALTools.executeStatementQuery(query.toString());
            while (DALTools.getResultSet().next()) {
                Tournament tournament = new Tournament();
                tournament.setName(DALTools.getResultSet()
                        .getString("Name"));
                tournament.setScheme(DALTools.getResultSet()
                        .getShort("Scheme"));
                tournament.setSeason(DALTools.getResultSet()
                        .getShort("Season") + SEASON_DELTA);
                tournament.setDescription(DALTools.getResultSet()
                        .getString("Description"));
                tournament.setRegulationsLink(DALTools.getResultSet()
                        .getString("RegulationsLink"));
                tournament.setApplyingPeriodStart(DALTools.getResultSet()
                        .getDate("ApplyingPeriodStart"));
                tournament.setApplyingPeriodEnd(DALTools.getResultSet()
                        .getDate("ApplyingPeriodEnd"));
                tournament.setGamesStart(DALTools.getResultSet()
                        .getDate("GamesStart"));
                tournament.setGamesEnd(DALTools.getResultSet()
                        .getDate("GamesEnd"));
                tournament.setTransferStart(DALTools.getResultSet()
                        .getDate("TransferStart"));
                tournament.setTransferEnd(DALTools.getResultSet()
                        .getDate("TransferEnd"));
                tournamentList.add(tournament);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            DALTools.closeStatement();
        }
        return tournamentList;
    }

    /**
     * Method for counting tournaments in DB with optional query.
     * @param inputQuery - COUNT(*) SQL query for tournaments.
     * @return tournaments quantity.
     */
    public static int countTournamentsByQuery(final String inputQuery) {
        int count = 0;
        query.delete(0, query.length());
        query = query.append("SELECT  COUNT(*) FROM ")
                .append(TestsConstants.DB_NAME).append(".")
                .append(TestsConstants.DB_SCHEMA).append(".Tournaments ")
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
     * Method for getting ALL tournaments from DB.
     * @return list of Tournaments.
     */
    public static List<Tournament> getAllTournaments() {
        return getTournamentsByQuery("");
    }

    /**
     * Method for getting tournament from DB with certain Id.
     * @param id - Id of the required tournament.
     * @return list of Tournaments.
     */
    public static List<Tournament> getTournamentsById(final int id) {
        return getTournamentsByQuery("WHERE Id="
                .concat(String.valueOf(id)));
    }

    /**
     * Method for getting tournaments from DB with certain Name.
     * @param name - Name of the required tournaments.
     * @return list of Tournaments.
     */
    public static List<Tournament> getTournamentsByName(final String name) {
        return getTournamentsByQuery("WHERE Name='"
                .concat(name).concat("'"));
    }

    /**
     * Method for getting tournaments from DB with certain Scheme.
     * @param scheme - Scheme of the required tournaments.
     * @return list of Tournaments.
     */
    public static List<Tournament> getTournamentsByScheme(final short scheme) {
        return getTournamentsByQuery("WHERE Scheme="
                .concat(String.valueOf(scheme)));
    }

    /**
     * Method for getting tournaments from DB with certain Season.
     * @param season - Season of the required tournaments.
     * @return list of Tournaments.
     */
    public static List<Tournament> getTournamentsBySeason(final short season) {
        return getTournamentsByQuery("WHERE Season="
                .concat(String.valueOf(season - SEASON_DELTA)));
    }

    /**
     * Method for getting tournaments from DB with certain Description.
     * @param description - Description of the required tournaments.
     * @return list of Tournaments.
     */
    public static List<Tournament> getTournamentsByDescription(
            final String description) {
        return getTournamentsByQuery("WHERE Description='".concat(description)
                .concat("'"));
    }

    /**
     * Method for getting tournaments from DB with certain RegulationsLink.
     * @param regulationsLink - RegulationsLink of the required tournaments.
     * @return list of Tournaments.
     */
    public static List<Tournament> getTournamentsByRegulationsLink(
            final String regulationsLink) {
        return getTournamentsByQuery("WHERE RegulationsLink='"
                .concat(regulationsLink).concat("'"));
    }

    /**
     * Method for getting tournaments from DB with certain ApplyingPeriodStart.
     * @param applyingPeriodStart - ApplyingPeriodStart
     *                              of the required tournaments.
     * @return list of Tournaments.
     */
    public static List<Tournament> getTournamentsByApplyingPeriodStart(
            final Date applyingPeriodStart) {
        return getTournamentsByQuery("WHERE ApplyingPeriodStart='"
                .concat(applyingPeriodStart.toString()).concat("'"));
    }

    /**
     * Method for getting tournaments from DB with certain ApplyingPeriodEnd.
     * @param applyingPeriodEnd - ApplyingPeriodEnd of the required tournaments.
     * @return list of Tournaments.
     */
    public static List<Tournament> getTournamentsByApplyingPeriodEnd(
            final Date applyingPeriodEnd) {
        return getTournamentsByQuery("WHERE ApplyingPeriodEnd='"
                .concat(applyingPeriodEnd.toString()).concat("'"));
    }

    /**
     * Method for getting tournaments from DB with certain GamesStart.
     * @param gamesStart - GamesStart of the required tournaments.
     * @return list of Tournaments.
     */
    public static List<Tournament> getTournamentsByGamesStart(
            final Date gamesStart) {
        return getTournamentsByQuery("WHERE GamesStart='"
                .concat(gamesStart.toString()).concat("'"));
    }

    /**
     * Method for getting tournaments from DB with certain GamesEnd.
     * @param gamesEnd - GamesEnd of the required tournaments.
     * @return list of Tournaments.
     */
    public static List<Tournament> getTournamentsByGamesEnd(
            final Date gamesEnd) {
        return getTournamentsByQuery("WHERE GamesEnd='"
                .concat(gamesEnd.toString()).concat("'"));
    }

    /**
     * Method for getting tournaments from DB with certain TransferStart.
     * @param transferStart - TransferStart of the required tournaments.
     * @return list of Tournaments.
     */
    public static List<Tournament> getTournamentsByTransferStart(
            final Date transferStart) {
        return getTournamentsByQuery("WHERE TransferStart='"
                .concat(transferStart.toString()).concat("'"));
    }

    /**
     * Method for getting tournaments from DB with certain TransferEnd.
     * @param transferEnd - TransferEnd of the required tournaments.
     * @return list of Tournaments.
     */
    public static List<Tournament> getTournamentsByTransferEnd(
            final Date transferEnd) {
        return getTournamentsByQuery("WHERE TransferEnd='"
                .concat(transferEnd.toString()).concat("'"));
    }

    /**
     * Method for counting  all tournaments in DB.
     * @return tournaments quantity.
     */
    public static int countAllTournaments() {
        return countTournamentsByQuery("");
    }

    /**
     * Method making the universal SELECT/COUNT query for certain Tournament.
     * @param tournament - Tournament for parse.
     * @return String - universal query.
     */
    private static String makeQuery(final Tournament tournament) {
        query.delete(0, query.length());
        query.append("WHERE Name='").append(tournament.getName())
        .append("' AND Scheme=").append(tournament.getScheme())
        .append(" AND Season=").append(tournament.getSeason() - SEASON_DELTA)
        .append(" AND ApplyingPeriodStart='")
        .append(tournament.getApplyingPeriodStart())
        .append("' AND ApplyingPeriodEnd='")
        .append(tournament.getApplyingPeriodEnd())
        .append("' AND GamesStart='").append(tournament.getGamesStart())
        .append("' AND GamesEnd='").append(tournament.getGamesEnd())
        .append("' AND TransferStart='").append(tournament.getTransferStart())
        .append("' AND TransferEnd='").append(tournament.getTransferEnd())
        .append("'");

        if (tournament.getDescription() != null) {
            query.append(" AND Description='")
            .append(tournament.getDescription()).append("'");
        }
        if (tournament.getRegulationsLink() != null) {
            query.append(" AND RegulationsLink='")
            .append(tournament.getRegulationsLink()).append("'");
        }
        query.append(";");
        return query.toString();
    }

    /**
     * Method for counting  certain tournaments in DB.
     * @param tournament - Tournament for COUNT.
     * @return tournaments quantity.
     */
    public static int countTournaments(final Tournament tournament) {
        return countTournamentsByQuery(makeQuery(tournament));
    }

    /**
     * Method for inserting certain tournaments in DB.
     * @param tournament - Tournament for insert.
     */
    public static void insertTournament(final Tournament tournament) {
        query.delete(0, query.length());
        query.append("INSERT INTO ")
        .append(TestsConstants.DB_NAME).append(".")
        .append(TestsConstants.DB_SCHEMA)
        .append(".Tournaments (Name, Scheme, Season, Description,")
        .append(" RegulationsLink, ApplyingPeriodStart, ApplyingPeriodEnd,")
        .append(" GamesStart, GamesEnd, TransferStart, TransferEnd) VALUES ('")
        .append(tournament.getName()).append("', ")
        .append(tournament.getScheme()).append(", ")
        .append(tournament.getSeason() - SEASON_DELTA).append(", '")
        .append(tournament.getDescription()).append("', '")
        .append(tournament.getRegulationsLink()).append("', '")
        .append(tournament.getApplyingPeriodStart()).append("', '")
        .append(tournament.getApplyingPeriodEnd()).append("', '")
        .append(tournament.getGamesStart()).append("', '")
        .append(tournament.getGamesEnd()).append("', '")
        .append(tournament.getTransferStart()).append("', '")
        .append(tournament.getTransferEnd()).append("');");
        DALTools.executeStatementUpdate(query.toString());
    }

    /**
     * Method for inserting the list of tournaments in DB.
     * @param tournamentList - Tournaments for insert.
     */
    public static void insertTournamentList(
            final List<Tournament> tournamentList) {
        for (Tournament tournament : tournamentList) {
            insertTournament(tournament);
        }
    }

    /**
     * Method for check. Is certain tournament present in DB?
     * @param tournament - Tournaments for search.
     * @return boolean answer on question is Tournament Present?.
     */
    public static boolean isTournamentPresent(final Tournament tournament) {
        if (countTournaments(tournament) >= 1) {
            return true;
        }
        return false;
    }

    /**
     * Checker. Is tournament with the certain description present in DB?
     * @param description - the sign of counting tournaments.
     * @return boolean answer on question is Tournament Present By description?
     */
    public static boolean isTournamentPresentByDescription(
            final String description) {
        if (countTournamentsByQuery("WHERE Description='".concat(description)
                .concat("';")) >= 1) {
            return true;
        }
        return false;
    }

    /**
     * Method for deleting tournaments from DB with optional query.
     * @param inputQuery - DELETE SQL query for tournaments.
     */
    public static void deleteTournamentByQuery(final String inputQuery) {
        query.delete(0, query.length());
        DALTools.executeStatementUpdate(query.append("DELETE FROM ")
                .append(TestsConstants.DB_NAME).append(".")
                .append(TestsConstants.DB_SCHEMA).append(".Tournaments ")
                .append(inputQuery).toString());
    }

    /**
     * Method for deleting certain tournament from DB.
     * @param tournament - certain tournament for delete.
     */
    public static void deleteTournament(final Tournament tournament) {
        deleteTournamentByQuery(makeQuery(tournament));
    }


    /**
     * Method for deleting tournaments from DB with certain Name.
     * @param name - the sign of deleting tournament.
     */
    public static void deleteTournamentByName(final String name) {
        deleteTournamentByQuery("WHERE Name='".concat(name).concat("'"));
    }

    /**
     * Method for deleting tournaments from DB with certain Season.
     * @param season - the sign of deleting tournament.
     */
    public static void deleteTournamentSeason(final short season) {
        deleteTournamentByQuery("WHERE Season='"
                .concat(String.valueOf(season - SEASON_DELTA)).concat("'"));
    }

    /**
     * Method for deleting tournaments from DB with certain Description.
     * @param description - the sign of deleting tournament.
     */
    public static void deleteTournamentByDescription(final String description) {
        deleteTournamentByQuery("WHERE Description='"
                .concat(description).concat("'"));
    }

    /**
     * Method for deleting tournament with MAX Id from DB.
     */
    public static void deleteLastTournament() {
        int maxId = DALTools.maxValue("Tournaments", "Id");
        deleteTournamentByQuery("WHERE Id="
                .concat(String.valueOf(maxId)));
        DALTools.resetId("Tournaments", (maxId - 1));
    }

}
