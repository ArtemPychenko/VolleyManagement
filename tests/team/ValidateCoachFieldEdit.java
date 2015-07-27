package com.softserveinc.ita.volleymanagementtests.tests.team;

import java.util.List;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.softserveinc.ita.volleymanagementtests.criterias.Specification;
import com.softserveinc.ita.volleymanagementtests.domain.models.Team;
import com.softserveinc.ita.volleymanagementtests.domain.repositories.DALTools;
import com.softserveinc.ita.volleymanagementtests.domain.repositories.TeamRepository;
import com.softserveinc.ita.volleymanagementtests.domain.repositories.TeamTestObjects;
import com.softserveinc.ita.volleymanagementtests.pages.HomePage;
import com.softserveinc.ita.volleymanagementtests.pages.EditTeamPage;
import com.softserveinc.ita.volleymanagementtests.pages.TeamListPage;
import com.softserveinc.ita.volleymanagementtests.tests.RollbackPreconditions;
import com.softserveinc.ita.volleymanagementtests.tests.SetPreconditions;
import com.softserveinc.ita.volleymanagementtests.tests.TestsConstants;
import com.softserveinc.ita.volleymanagementtests.tools.WebDriverUtils;

/**
 * This class validates the coach's name field through edit team page.
 * @author Aleksandr Zaitsev
 *
 */
public class ValidateCoachFieldEdit {
    /**
     * an instance of the main page of the web site.
     */
    private HomePage homePage;
    /**
     * an instance of the edit player's page.
     */
    private EditTeamPage editTeamPage;
    /**
     * an instance of the page with the list of teams.
     */
    private TeamListPage teamListPage;
    /**
     * Existing team database.
     */
    private TeamRepository repository;
    /**
     * Specification object for soft-assert report create.
     */
    private Specification specification;
    /**
     * teamData will save the team in set up method in his current state
     * to restore them after test will complete in tear down method.
     */
    private List<Team> teamData;
    /**
     * The team instance with valid data entered.
     */
    private Team validTeam;
    /**
     * The team instance.
     */
    private Team team;
    /**
     * This method performs the operations necessary to run the test.
     */
    @BeforeClass
    public final void setUp() {
        DALTools.startConnection(TestsConstants.CONNECT_DB_STRING);
        teamData = SetPreconditions.forValidationTeamFildsTest();
        WebDriverUtils.load(TestsConstants.HOME_PAGE);
        homePage = new HomePage();
        specification = Specification.get();
        validTeam = TeamTestObjects.getValidTeam();
        TeamRepository.insertTeam(validTeam);
    }
    /**
     * This method performs the operations necessary after the test
     * execution.
     */
    @AfterClass
    public final void tearDown() {
        RollbackPreconditions.forValidationTeamFildsTest(teamData);
    }
    /**
     * The team shouldn't be save in the database, because of 60 symbols
     * is max but 61 are entered.
     * The validation message should appear.
     */
    @Test
    public final void validateCoachField() {

        team = TeamTestObjects.getInvalidTooBigCoachName();
        teamListPage = homePage.getNavigationPage().getTeamListPage();
        editTeamPage = teamListPage.showTeamInfo(validTeam)
                .pressEditButton();
        editTeamPage.getData().setAllFields(team);
        editTeamPage.unsuccessfulPressSave();

        specification
        .For(editTeamPage.getData().getErrorMessageLabelCoach())
        .valueMatch(TestsConstants.VALIDATION_ERROR_TEXT_TOO_BIG_NAME)
        .next()
        .For(repository)
        .teamNotPresent(team);

        if (TeamRepository.isTeamPresent(team)) {
            TeamRepository.deleteTeamByName(team.getTeamName());
        }
        teamListPage = TeamListPage.refreshTeamList();
        /**
         * Validation field with valid values.
         * The team should be save in the database.
         */
        final List<Team> validTeams
        = TeamTestObjects.getValidCoachNames();
        for (Team team : validTeams) {
            editTeamPage = teamListPage.showTeamInfo(validTeam)
                    .pressEditButton();
            editTeamPage.getData().setAllFields(team);
            editTeamPage.pressSave();

            specification
            .For(repository)
            .teamPresent(team);

            if (TeamRepository.isTeamPresent(team)) {
                TeamRepository.deleteTeamByName(team.getTeamName());
            }
            teamListPage = TeamListPage.refreshTeamList();
        }
        /**
         * Validation field with invalid values (special symbols,
         * not letters).
         * The team should be save in the database.
         */
        final List<Team> invalidTeams
        = TeamTestObjects.getInvalidCoachNames();
        for (Team team : invalidTeams) {
            editTeamPage = teamListPage.showTeamInfo(validTeam)
                    .pressEditButton();
            editTeamPage.getData().setAllFields(team);
            editTeamPage.pressSave();

            specification
            .For(editTeamPage.getData().getErrorMessageLabelCoach())
            .valueMatch(TestsConstants.VALIDATION_ERROR_TEXT_WRONG_TYPE_NAME)
            .next()
            .For(repository)
            .teamNotPresent(team);

            if (TeamRepository.isTeamPresent(team)) {
                TeamRepository.deleteTeamByName(team.getTeamName());
            }
            teamListPage = TeamListPage.refreshTeamList();
        }
        specification.check();
    }
}


