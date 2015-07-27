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
import com.softserveinc.ita.volleymanagementtests.pages.HomePage;
import com.softserveinc.ita.volleymanagementtests.pages.NewTeamPage;
import com.softserveinc.ita.volleymanagementtests.pages.TeamListPage;
import com.softserveinc.ita.volleymanagementtests.tests.TestsConstants;
import com.softserveinc.ita.volleymanagementtests.tools.WebDriverUtils;

/**
 * @author Alla Prykhodchenko
 *              This test-case for checking create new team functionality
 *              of the VolleyManagement application.
 */
public class CreateNewTeamTest {
    /**
     * Specification object for soft-assert report create.
     */
    private Specification specification;
    /**
     * Main page object of testing application.
     */
    private HomePage homePage;
    /**
     * Page with team list.
     */
    private TeamListPage teamListPage;
    /**
     * Create new team page.
     */
    private NewTeamPage newTeamPage;
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
     * This test check create new team with all fields functionality.
     */
    @Test
    public final void createNewTeam() {
        specification = Specification.get();
        teamListPage = homePage.getNavigationPage().getTeamListPage();
        newTeamPage = teamListPage.openCreateTeamPage();
        newTeamPage.getData().setName(team);
        specification.For(newTeamPage.getData().getNameInput())
        .isContain(team.getTeamName())
        .next();
        newTeamPage.getData().setCaptain(team);
        specification.For(newTeamPage.getData().getCaptainInput())
        .isContain(team.getCaptain().toString())
        .next();
        newTeamPage.getData().setCoach(team);
        specification.For(newTeamPage.getData().getCoachInput())
        .isContain(team.getCoach())
        .next();
        newTeamPage.getData().setaAhievements(team);
        specification.For(newTeamPage.getData().getaAhievementsInput())
        .isContain(team.getAchievements())
        .next();
        newTeamPage.pressSave();
        specification.For(repository)
        .teamPresent(team);
        specification.check();
    }
    /**
     * This test check create new team with required fields functionality.
     */
    @Test
    public final void createNewTeamRequiredFields() {
        specification = Specification.get();
        teamListPage = homePage.getNavigationPage().getTeamListPage();
        newTeamPage = teamListPage.openCreateTeamPage();
        newTeamPage.getData().setName(teamReqFields);
        specification.For(newTeamPage.getData().getNameInput())
        .isContain(teamReqFields.getTeamName())
        .next();
        newTeamPage.getData().setCaptain(teamReqFields);
        specification.For(newTeamPage.getData().getCaptainInput())
        .isContain(teamReqFields.getCaptain().toString())
        .next();
        newTeamPage.pressSave();
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
