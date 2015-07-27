package com.softserveinc.ita.volleymanagementtests.tests;

import java.util.List;

import com.softserveinc.ita.volleymanagementtests.domain.models.Player;
import com.softserveinc.ita.volleymanagementtests.domain.models.Team;
import com.softserveinc.ita.volleymanagementtests.domain.models.Tournament;
import com.softserveinc.ita.volleymanagementtests.domain.repositories.DALTools;
import com.softserveinc.ita.volleymanagementtests.domain.repositories
                                                           .PlayerRepository;
import com.softserveinc.ita.volleymanagementtests.domain.repositories
                                                           .PlayerTestObjects;
import com.softserveinc.ita.volleymanagementtests.domain.repositories
                                                             .TeamRepository;
import com.softserveinc.ita.volleymanagementtests.domain.repositories
                                                             .TeamTestObjects;
import com.softserveinc.ita.volleymanagementtests.domain.repositories
                                                        .TournamentRepository;
import com.softserveinc.ita.volleymanagementtests.domain.repositories
                                                        .TournamentTestObjects;
import com.softserveinc.ita.volleymanagementtests.pages.HomePage;
import com.softserveinc.ita.volleymanagementtests.pages.NavigationPage;
import com.softserveinc.ita.volleymanagementtests.pages.PlayerListPage;
import com.softserveinc.ita.volleymanagementtests.pages.SearchString;
import com.softserveinc.ita.volleymanagementtests.pages.TeamListPage;
import com.softserveinc.ita.volleymanagementtests.pages.TournamentListPage;

/**
 * This class contain static methods for setting up test preconditions.
 *
 * @author DP-076ATQC
 */
public final class SetPreconditions {

    /**
     * The list of player reads from DB.
     * Uses for save "Players" table state before test.
     */
    private static List<Player> playerListDB;
    /**
     * The list of player reads from DB.
     * Uses for save "Teams" table state before test.
     */
    private static List<Team> teamListDB;
    /**
     * The list of player reads from DB.
     * Uses for save "Tournaments" table state before test.
     */
    private static List<Tournament> tournamentListDB;
    /**
     * Page quantity for paging tests.
     */
    private static final short PAGES_FOR_TEST = 5;
    /**
     * Season year from the past.
     */
    private static final short PAST_SEASON = 1900;
    /**
     * Season year from the future.
     */
    private static final short FUTURE_SEASON = 2100;

    /**
     * Not used constructor.
     */
    private SetPreconditions() {
    }

    /**
     * This method sets PreCondition for Paging functionality test.
     * Change FirstName of first player on "AAAAATest".
     * And insert this new Player.
     * @return new PlayerListPage.
     */
    public static PlayerListPage forPlayerPagingTest() {
        playerListDB = PlayerRepository.getAllPlayers();
        addTestPlayerPages(PAGES_FOR_TEST);
        PlayerRepository
        .insertPlayer(new Player("AAAAATest", readLastNameUI(0)));
        return PlayerListPage.refreshPlayerList();
    }

    /**
     * This method sets PreCondition for Paging functionality test.
     * And insert new team with name "AAAAATest".
     * @return new TeamListPage.
     */
    public static TeamListPage forTeamPagingTest() {
        teamListDB = TeamRepository.getAllTeams();
        addTestTeamPages(PAGES_FOR_TEST);
        TeamRepository
        .insertTeam(new Team("AAAAASHTA", PlayerRepository.getLastId()));
        return new NavigationPage().getTeamListPage();
    }

    /**
     * This method sets PreCondition for Paging functionality test.
     * And insert new tournaments:
     *   - with name "AAAAATestTournament" and seson 2100 year.
     *   - with name "AAAAATestTournament" and seson 1900 year.
     * @return new TournamentListPage.
     */
    public static TournamentListPage forTournamentPagingTest() {
        tournamentListDB = TournamentRepository.getAllTournaments();
        addTestTournamentPages(PAGES_FOR_TEST);
        TournamentRepository
        .insertTournament(TournamentTestObjects
                .getValidTournament("AAAAATestTournament", PAST_SEASON));
        TournamentRepository
        .insertTournament(TournamentTestObjects
                .getValidTournament("AAAAATestTournament", FUTURE_SEASON));
        return new NavigationPage().getTournamentListPage();
    }

    /**
     * This method sets PreCondition for Search player functionality test.
     * Positive script.
     * @return new SearchString.
     */
    public static SearchString forSearchPlayerPositive() {
        playerListDB = PlayerRepository.getAllPlayers();
        DALTools.clearTable("Players");
        addTestPlayerPages(2);
        PlayerRepository
        .insertPlayer(new Player("AAAAATest", readLastNameUI(0)));
        return PlayerListPage.refreshPlayerList().getSearchString();
    }

    /**
     * This method sets PreCondition for Search player functionality test.
     * Negative script.
     * @return new SearchString.
     */
    public static SearchString forSearchPlayerNegative() {
        playerListDB = PlayerRepository.getAllPlayers();
        DALTools.clearTable("Players");
        addTestPlayerPages(2);
        return new NavigationPage().getPlayersPage().getSearchString();
    }

    /**
     * This method sets PreCondition for Search team functionality test.
     * Positive script.
     * @return new SearchString.
     */
    public static SearchString forSearchTeamPositive() {
        teamListDB = TeamRepository.getAllTeams();
        DALTools.clearTable("Teams");
        addTestTeamPages(2);
        return new NavigationPage().getTeamListPage().getSearchString();
    }

    /**
     * This method sets PreCondition for Search team functionality test.
     * Negative script.
     * @return new SearchString.
     */
    public static SearchString forSearchTeamNegative() {
        teamListDB = TeamRepository.getAllTeams();
        DALTools.clearTable("Teams");
        addTestTeamPages(2);
        return new NavigationPage().getTeamListPage().getSearchString();
    }

    /**
     * This method sets PreCondition for Search tournament functionality test.
     * Positive script.
     * @return new SearchString.
     */
    public static SearchString forSearchTournamentPositive() {
        tournamentListDB = TournamentRepository.getAllTournaments();
        DALTools.clearTable("Tournaments");
        addTestTournamentPages(2);
        return new NavigationPage().getTournamentListPage().getSearchString();
    }

    /**
     * This method sets PreCondition for Search tournament functionality test.
     * Negative script.
     * @return new SearchString.
     */
    public static SearchString forSearchTournamentNegative() {
        tournamentListDB = TournamentRepository.getAllTournaments();
        DALTools.clearTable("Tournaments");
        addTestTournamentPages(2);
        return new NavigationPage().getTournamentListPage().getSearchString();
    }

    /**
     * This method sets PreCondition for Validation Player Fields test.
     * @return full player list from DB.
     */
    public static List<Player> forValidationPlayerFildsTest() {
        List<Player> playersData = PlayerRepository.getAllPlayers();
        DALTools.clearTable(TestsConstants.PLAYER_TABLE);
        return playersData;
    }

    /**
     * This method sets PreCondition for Validation Team Fields test.
     * @return full team list from DB.
     */
    public static List<Team> forValidationTeamFildsTest() {
        List<Team> teamsData = TeamRepository.getAllTeams();
        DALTools.clearTable(TestsConstants.TEAM_TABLE);
        return teamsData;
    }
    /**
     * This method sets PreCondition for Validation Tournament Fields test.
     * @return full team list from DB.
     */
    public static List<Tournament> forValidationTournamentFieldsTest() {
        List<Tournament> tournamentData = TournamentRepository
                .getAllTournaments();
        DALTools.clearTable(TestsConstants.TOURNAMENT_TABLE);
        return tournamentData;
    }

    /**
     * Getter of player last name with define index.
     * @param playerIndex index of player, that last name needed.
     * @return String with player LastName.
     */
    private static String readLastNameUI(final int playerIndex) {
        return new HomePage()
                .getNavigationPage()
                .getPlayersPage()
                .getPlayerNameList()
                .get(playerIndex)
                .split("\\s")[0];
    }

    /**
     * Method for add test Players to get required page quantity.
     * Test player model - FirstName* LastName*,
     * where * - char counter, for example "AAR"
     * @param pagesQuantity  - required page quantity for test.
     */
    public static void addTestPlayerPages(final int pagesQuantity) {
        int requiredPlayersToAdd = (TestsConstants
                .ITEMS_PER_PAGE * (pagesQuantity - 1) + 1)
                - PlayerRepository.countAllPlayers();
        PlayerRepository
        .insertPlayerList(PlayerTestObjects
                .getTestPlayerList(requiredPlayersToAdd));
    }

    /**
     * Add test Teams to get required page quantity.
     * Test team model - TestTeam*,
     * where * - char counter, for example "AAR"
     * @param pagesQuantity  - required page quantity for test.
     */
    public static void addTestTeamPages(final int pagesQuantity) {
        int requiredTeamsToAdd = (TestsConstants
                .ITEMS_PER_PAGE * (pagesQuantity - 1) + 1)
                - TeamRepository.countAllTeams();
        TeamRepository
        .insertTeamList(TeamTestObjects
                .getTestTeamList(requiredTeamsToAdd));
    }

    /**
     * Add test Tournaments to get required page quantity.
     * Test tournament model - TestTournament*,
     * where * - char counter, for example "AAR"
     * @param pagesQuantity  - required page quantity for test.
     */
    public static void addTestTournamentPages(final int pagesQuantity) {
        int requiredTournamentsToAdd = (TestsConstants
                .ITEMS_PER_PAGE * (pagesQuantity - 1) + 1)
                - TournamentRepository.countAllTournaments();
        TournamentRepository
        .insertTournamentList(TournamentTestObjects
                .getTestTournamentList(requiredTournamentsToAdd));
    }

    /**
     * playerListDB getter.
     * @return "Players" table state before test.
     */
    public static List<Player> getPlayerListDB() {
        return playerListDB;
    }

    /**
     * playerListDB setter.
     * @param playerList of player.
     */
    public static void setPlayerListDB(final List<Player> playerList) {
        playerListDB = playerList;
    }

    /**
     * teamListDB getter.
     * @return "Teams" table state before test.
     */
    public static List<Team> getTeamListDB() {
        return teamListDB;
    }

    /**
     * teamListDB setter.
     * @param teamList of team.
     */
    public static void setTeamListDB(final List<Team> teamList) {
        teamListDB = teamList;
    }

    /**
     * tournamentListDB getter.
     * @return "Tournaments" table state before test.
     */
    public static List<Tournament> getTournamentListDB() {
        return tournamentListDB;
    }

    /**
     * tournamentListDB setter.
     * @param tournamentList of tournament.
     */
    public static void setTournamentListDB(
            final List<Tournament> tournamentList) {
        tournamentListDB = tournamentList;
    }

}
