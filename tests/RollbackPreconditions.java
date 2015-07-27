package com.softserveinc.ita.volleymanagementtests.tests;

import java.util.List;

import com.softserveinc.ita.volleymanagementtests.domain.models.Player;
import com.softserveinc.ita.volleymanagementtests.domain.models.Team;
import com.softserveinc.ita.volleymanagementtests.domain.models.Tournament;
import com.softserveinc.ita.volleymanagementtests.domain.repositories.DALTools;
import com.softserveinc.ita.volleymanagementtests.domain.repositories
                                                        .PlayerRepository;
import com.softserveinc.ita.volleymanagementtests.domain.repositories
                                                           .TeamRepository;
import com.softserveinc.ita.volleymanagementtests.domain.repositories
                                                         .TournamentRepository;

/**
 * This class contain static methods for
 * rolling back DB state after test execution.
 *
 * @author DP-076ATQC
 */
public final class RollbackPreconditions {

    /**
     * Not used constructor.
     */
    private RollbackPreconditions() {
    }

    /**
     * Method roll back PreCondition for functionality test with DB changes.
     * Restores Players Table contains".
     */
    public static void forPlayersTable() {
        DALTools.clearTable(TestsConstants.PLAYER_TABLE);
        PlayerRepository.insertPlayerList(SetPreconditions.getPlayerListDB());
        SetPreconditions.setPlayerListDB(null);
    }

    /**
     * Method roll back PreCondition for functionality test with DB changes.
     * Restores Teams Table contains".
     */
    public static void forTeamsTable() {
        DALTools.clearTable(TestsConstants.TEAM_TABLE);
        TeamRepository.insertTeamList(SetPreconditions.getTeamListDB());
        SetPreconditions.setTeamListDB(null);
    }

    /**
     * Method roll back PreCondition for functionality test with DB changes.
     * Restores Tournaments Table contains".
     */
    public static void forTournamentsTable() {
        DALTools.clearTable(TestsConstants.TOURNAMENT_TABLE);
        TournamentRepository.insertTournamentList(
                SetPreconditions.getTournamentListDB());
        SetPreconditions.setTournamentListDB(null);
    }

    /**
     * Method roll back PreCondition for functionality test with DB changes.
     * @param playersData - player list for DB restore
     */
    public static void forValidationPlayerFildsTest(
            final List<Player> playersData) {
        DALTools.clearTable(TestsConstants.PLAYER_TABLE);
        PlayerRepository.insertPlayerList(playersData);
    }

    /**
     * Method roll back PreCondition for functionality test with DB changes.
     * @param teamsData - team list for DB restore
     */
    public static void forValidationTeamFildsTest(final List<Team> teamsData) {
        DALTools.clearTable(TestsConstants.TEAM_TABLE);
        TeamRepository.insertTeamList(teamsData);
    }
    /**
     * Method roll back PreCondition for functionality test with DB changes.
     * @param tournamentData - team list for DB restore
     */
    public static void forValidationTournamentFieldsTest(
            final List<Tournament> tournamentData) {
        DALTools.clearTable(TestsConstants.TOURNAMENT_TABLE);
        TournamentRepository.insertTournamentList(tournamentData);
    }
}
