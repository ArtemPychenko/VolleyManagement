package com.softserveinc.ita.volleymanagementtests.tests.tournament;

import java.util.List;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.softserveinc.ita.volleymanagementtests.criterias.Specification;
import com.softserveinc.ita.volleymanagementtests.domain.models.Tournament;
import com.softserveinc.ita.volleymanagementtests.domain.repositories.DALTools;
import com.softserveinc.ita.volleymanagementtests.domain.repositories.TournamentRepository;
import com.softserveinc.ita.volleymanagementtests.domain.repositories.TournamentTestObjects;
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
public class DeleteTournamentTest {
    /**
     * Specification object for soft-assert report create.
     */
    private Specification specification;
    /**
     * Existing tournament database.
     */
    private TournamentRepository repository;
    /**.
     * tornamentsQuantityBeforeDelete holds quantity of tournaments before
     * delete.
     */
    private int tornamentsQuantityBeforeDelete;
    /**
     * an instance of the main page of the web site.
     */
    private HomePage homePage;
    /**
     * valid tournament - tournament with valid data.
     */
    private Tournament validTournament;
    /**
     * playersData will save the tournaments in set up method in his current
     * state
     * to restore them after test will complete in tear down method.
     */
    private List<Tournament> tournamentsData;
    /**
     * This method performs the operations necessary to run the test.
     */
    @BeforeClass
    public final void setUp() {
        WebDriverUtils.load(TestsConstants.HOME_PAGE);
        DALTools.startConnection(TestsConstants.CONNECT_DB_STRING);
        tournamentsData = SetPreconditions.forValidationTournamentFieldsTest();
        specification = Specification.get();
        homePage = new HomePage();
        validTournament = TournamentTestObjects.getValidTournament();
        TournamentRepository.insertTournament(validTournament);
        tornamentsQuantityBeforeDelete = TournamentRepository
                .countAllTournaments();

    }
    /**
     * This method performs the operations necessary after the test execution.
     */
    @AfterClass
    public final void tearDown() {
        RollbackPreconditions.forValidationTournamentFieldsTest(
                tournamentsData);
    }
    /**
     * This test verifies the text of confirmation dialog page (
     * delete tournament).
     */
   @Test
    public final void deleteTeamTextMessageTest() {
       specification.For(
       homePage.getNavigationPage().getTournamentListPage().showTournamentInfo(
               TournamentTestObjects.getValidTournament())
       .pressDeleteButton())
       .textDeletePlayerMatch(TestsConstants.DELETE_TOURNAMENT_LABEL)
       .buttonYesMatch(TestsConstants.DELETE_BUTTON_YES_LABEL)
       .buttonNoMatch(TestsConstants.DELETE_BUTTON_NO_LABEL)
       .next().check();
       new ConfirmDialogPage().clickNo();

       specification.For(repository)
               .numberOfTournamentsHasNotChanged(tornamentsQuantityBeforeDelete)
               .next()
               .For(repository)
               .isTournamentPresent(validTournament)
               .next()
               .check();
    }
   /**
    * This test verifies the delete tournament functionality. If cancel the
    * deleting - tournament will stay in database.
    */
   @Test
   public final void deleteTeamTestNotConfirm() {
      homePage.getNavigationPage().getTournamentListPage().showTournamentInfo(
              TournamentTestObjects.getValidTournament())
      .pressDeleteButton().clickNo();

      specification.For(repository)
              .numberOfTournamentsHasNotChanged(tornamentsQuantityBeforeDelete)
              .next()
              .For(repository)
              .isTournamentPresent(validTournament)
              .next()
              .check();
   }
   /**
    * This test verifies the delete tournament functionality. If confirm the
    * deleting - tournament will be deleted from database.
    */
   @Test(dependsOnMethods = {"deleteTeamTestNotConfirm"}, alwaysRun = true)
   public final void deleteTeamTestConfirm() {
       homePage.getNavigationPage().getTournamentListPage().showTournamentInfo(
               TournamentTestObjects.getValidTournament())
      .pressDeleteButton().clickYes();

      specification.For(repository)
              .numberOfTournamentsHasDecreased(tornamentsQuantityBeforeDelete)
              .next()
              .For(repository)
              .isTournamentNotPresent(validTournament)
              .next()
              .check();

   }
}
