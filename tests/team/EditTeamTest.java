package com.softserveinc.ita.volleymanagementtests.tests.team;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.softserveinc.ita.volleymanagementtests.criterias.Specification;
import com.softserveinc.ita.volleymanagementtests.domain.models.Team;
import com.softserveinc.ita.volleymanagementtests.domain.repositories.DALTools;
import com.softserveinc.ita.volleymanagementtests.domain.repositories
.PlayerRepository;
import com.softserveinc.ita.volleymanagementtests.domain.repositories
.PlayerTestObjects;
import com.softserveinc.ita.volleymanagementtests.domain.repositories
.TeamRepository;
import com.softserveinc.ita.volleymanagementtests.domain.repositories
.TeamTestObjects;
import com.softserveinc.ita.volleymanagementtests.pages.EditTeamPage;
import com.softserveinc.ita.volleymanagementtests.pages.HomePage;
import com.softserveinc.ita.volleymanagementtests.pages.TeamDetailPage;
import com.softserveinc.ita.volleymanagementtests.tests.TestsConstants;
import com.softserveinc.ita.volleymanagementtests.tools.WebDriverUtils;

/**
 * @author Alla Prykhodchenko
 *              This test-case for checking edit team functionality
 *              of the VolleyManagement application.
 *
 */
public class EditTeamTest {
    /**
     * Specification object for soft-assert report create.
     */
    private Specification specification;
    /**
     * Main page object of testing application.
     */
    private HomePage homePage;
    /**
     * Page, which shown team details.
     */
    private TeamDetailPage teamDetailPage;
    /**
     * Edit team page.
     */
    private EditTeamPage editTeamPage;
    /**
     * Valid team object with all fields.
     */
    private Team team;
    /**
     * Valid team object with required fields.
     */
    private Team teamReqFields;
    /**
     * Existing team database.
     */
    private TeamRepository repository;

    /**
     * This method performs the operations necessary to run the test.
     */
    @BeforeClass
    public final void setUp() {
        WebDriverUtils.load(TestsConstants.HOME_PAGE);
        homePage = new HomePage();
        DALTools.startConnection(TestsConstants.CONNECT_DB_STRING);
        PlayerRepository.insertPlayer(PlayerTestObjects.getValidPlayer());
        PlayerRepository.insertPlayer(PlayerTestObjects
                .getValidPlayerWithRequiredFields());
        team = TeamTestObjects.getValidTeam();
        teamReqFields = TeamTestObjects.getValidTeamWithRequiredFields();
    }

    /**
     * This test check edit team with all fields functionality.
     */
    @Test
    public final void editTeam() {
        specification = Specification.get();
        teamDetailPage = homePage.getNavigationPage().getTeamListPage()
                .showTeamInfo();
        editTeamPage = teamDetailPage.pressEditButton();
        editTeamPage.getData().setName(team);
        specification.For(editTeamPage.getData().getNameInput())
        .isContain(team.getTeamName())
        .next();
        editTeamPage.getData().setCaptain(team);
        specification.For(editTeamPage.getData().getCaptainInput())
        .isContain(team.getCaptain().toString())
        .next();
        editTeamPage.getData().setCoach(team);
        specification.For(editTeamPage.getData().getCoachInput())
        .isContain(team.getCoach())
        .next();
        editTeamPage.getData().setaAhievements(team);
        specification.For(editTeamPage.getData().getaAhievementsInput())
        .isContain(team.getAchievements())
        .next();
        editTeamPage.pressSave();
        specification.For(repository)
        .teamPresent(team);
        specification.check();
    }
    /**
     * This test check edit team with required fields functionality.
     */
    @Test
    public final void editTeamRequiredFields() {
        specification = Specification.get();
        teamDetailPage = homePage.getNavigationPage().getTeamListPage()
                .showTeamInfo();
        editTeamPage = teamDetailPage.pressEditButton();
        editTeamPage.getData().setName(teamReqFields);
        specification.For(editTeamPage.getData().getNameInput())
        .isContain(teamReqFields.getTeamName())
        .next();
        editTeamPage.getData().setCaptain(teamReqFields);
        specification.For(editTeamPage.getData().getCaptainInput())
        .isContain(teamReqFields.getCaptain().toString())
        .next();
        editTeamPage.getData().getCoachInput().clear();
        editTeamPage.getData().getaAhievementsInput().clear();
        editTeamPage.pressSave();
        specification.For(repository)
        .teamPresent(teamReqFields);
        specification.check();
    }
    /**
     * This method performs the operations necessary after the test execution.
     */
    @AfterClass
    public final void tearDown() {
        //WebDriverUtils.stop();
        PlayerRepository.deleteLastPlayer();
        PlayerRepository.deleteLastPlayer();
    }
}
