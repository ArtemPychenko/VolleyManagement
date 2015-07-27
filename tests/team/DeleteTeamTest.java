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
import com.softserveinc.ita.volleymanagementtests.pages.ConfirmDialogPage;
import com.softserveinc.ita.volleymanagementtests.pages.HomePage;
import com.softserveinc.ita.volleymanagementtests.tests.RollbackPreconditions;
import com.softserveinc.ita.volleymanagementtests.tests.SetPreconditions;
import com.softserveinc.ita.volleymanagementtests.tests.TestsConstants;
import com.softserveinc.ita.volleymanagementtests.tools.WebDriverUtils;
/**
 * @author Aleksandr Zaitsev
 * test verifies the delete team functionality.
 */
public class DeleteTeamTest {
    /**
     * Specification object for soft-assert report create.
     */
    private Specification specification;
    /**
     * Existing team database.
     */
    private TeamRepository repository;
    /**.
     * teamsQuantityBeforeDelete holds quantity of teams before delete.
     */
    private int teamsQuantityBeforeDelete;
    /**
     * an instance of the main page of the web site.
     */
    private HomePage homePage;
    /**
     * valid player - player with valid data.
     */
    private Team validTeam;
    /**
     * teamsData will save the players in set up method in his current state
     * to restore them after test will complete in tear down method.
     */
    private List<Team> teamsData;
    /**
     * This method performs the operations necessary to run the test.
     */
    @BeforeClass
    public final void setUp() {
        WebDriverUtils.load(TestsConstants.HOME_PAGE);
        DALTools.startConnection(TestsConstants.CONNECT_DB_STRING);
        teamsData = SetPreconditions.forValidationTeamFildsTest();
        specification = Specification.get();
        homePage = new HomePage();
        validTeam = TeamTestObjects.getValidTeam();
        TeamRepository.insertTeam(validTeam);
        teamsQuantityBeforeDelete = TeamRepository.countAllTeams();

    }
    /**
     * This method performs the operations necessary after the test execution.
     */
    @AfterClass
    public final void tearDown() {
        RollbackPreconditions.forValidationTeamFildsTest(teamsData);
        //WebDriverUtils.stop();
    }
    /**
     * This test verifies the text of confirmation dialog page (delete team).
     */
   @Test
    public final void deleteTeamTextMessageTest() {
       specification.For(
       homePage.getNavigationPage().getTeamListPage().showTeamInfo(
               TeamTestObjects.getValidTeam())
       .pressDeleteButton())
       .textDeletePlayerMatch(TestsConstants.DELETE_TEAM_LABEL)
       .buttonYesMatch(TestsConstants.DELETE_BUTTON_YES_LABEL)
       .buttonNoMatch(TestsConstants.DELETE_BUTTON_NO_LABEL)
       .next().check();
       new ConfirmDialogPage().clickNo();

       specification.For(repository)
               .numberOfTeamsHasNotChanged(teamsQuantityBeforeDelete)
               .next()
               .For(repository)
               .teamPresent(validTeam)
               .next()
               .check();
    }
   /**
    * This test verifies the delete team functionality. If cancel the
    * deleting - team will stay in database.
    */
   @Test
   public final void deleteTeamTestNotConfirm() {
      homePage.getNavigationPage().getTeamListPage().showTeamInfo(
              TeamTestObjects.getValidTeam())
      .pressDeleteButton().clickNo();

      specification.For(repository)
              .numberOfTeamsHasNotChanged(teamsQuantityBeforeDelete)
              .next()
              .For(repository)
              .teamPresent(validTeam)
              .next()
              .check();
   }
   /**
    * This test verifies the delete team functionality. If confirm the
    * deleting - team will be deleted from database.
    */
   @Test(dependsOnMethods = {"deleteTeamTestNotConfirm"}, alwaysRun = true)
   public final void deleteTeamTestConfirm() {
       homePage.getNavigationPage().getTeamListPage().showTeamInfo(
               TeamTestObjects.getValidTeam())
      .pressDeleteButton().clickYes();

      specification.For(repository)
              .numberOfTeamsHasDecreased(teamsQuantityBeforeDelete)
              .next()
              .For(repository)
              .teamNotPresent(validTeam)
              .next()
              .check();

   }
}
