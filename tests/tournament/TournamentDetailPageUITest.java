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
import com.softserveinc.ita.volleymanagementtests.pages.HomePage;
import com.softserveinc.ita.volleymanagementtests.pages.TournamentDetailsPage;
import com.softserveinc.ita.volleymanagementtests.pages.TournamentListPage;
import com.softserveinc.ita.volleymanagementtests.tests.RollbackPreconditions;
import com.softserveinc.ita.volleymanagementtests.tests.SetPreconditions;
import com.softserveinc.ita.volleymanagementtests.tests.TestsConstants;
import com.softserveinc.ita.volleymanagementtests.tools.WebDriverUtils;
/**
 * This class check UI elements on the detail tournament page.
 * @author Artem Pychenko
 *
 */
public class TournamentDetailPageUITest {
	/**
	 * Specification object for soft-assert report create.
	 */
	private Specification specification;
	/**
	 * Object of HomePage class.
	 */
	private HomePage homePage;
	/**
	 * Object of TournamentListPage class.
	 */
	private TournamentListPage tournamentListPage;
	/**
	 * Object of TournamentDetailPage class.
	 */
	private TournamentDetailsPage detailTournamentPage;
	/**
	 * Object of Tournament class.
	 */
	private Tournament expectedTournament;
	/**
	    * TournamentsData will save the tournament in set up method in his current state
	    * to restore them after test will complete in tear down method.
	    */
	    private List<Tournament> tournamentData;
	/**
	 * This method performs the operations necessary to run the test.
	 */
	@BeforeClass
	public final void setUp() {
		DALTools.startConnection(TestsConstants.CONNECT_DB_STRING);
	 	tournamentData = SetPreconditions.forValidationTournamentFieldsTest();
        DALTools.clearTable(TestsConstants.TOURNAMENT_TABLE);
		expectedTournament = TournamentTestObjects.getValidTournament();
		TournamentRepository.insertTournament(expectedTournament);
		WebDriverUtils.load(TestsConstants.HOME_PAGE);
		homePage = new HomePage();
		tournamentListPage = homePage.getNavigationPage().getTournamentListPage();
		detailTournamentPage = tournamentListPage.showTournamentInfo();
		specification = Specification.get();
	}

	/**
     * Check UI element on the page.
     */
    @Test
    public final void checkUIElements() {
    	specification.For(detailTournamentPage.getPageTitleLabel())
    				 .valueMatch(TestsConstants.EDIT_TOURNAMENT_TITLE_LABEL)
    				 .isVisible()
    				 .next()
    				 
    				 .For(detailTournamentPage.getEditButton())
    				 .textMatch(TestsConstants.EDIT_BUTTON)
    				 .isVisible()
    				 .next()
    				 
    				 .For(detailTournamentPage.getTournamentNameLabel())
		 			 .isVisible()
		 			 .valueMatch(TestsConstants.TOURNAMENT_NAME_LABEL)
		 		     .next()
		 				
		 			 .For(detailTournamentPage.getDescriptionLabel())
		 			 .isVisible()
		 			 .valueMatch(TestsConstants.TOURNAMENT_DESCRIPTION_LABEL)
		 			 .next()
		 				
		 			 .For(detailTournamentPage.getSeasonLabel())
		 			 .isVisible()
		 			 .valueMatch(TestsConstants.TOURNAMENT_SEASON_LABEL)
		 			 .next()

		 			 .For(detailTournamentPage.getStartAndEndOfRegistrationLabel())
		 			 .isVisible()
		 			 .valueMatch(TestsConstants.TOURNAMENT_TERMS_APPLYING_LABEL)
		 			 .next()
		 			 
		 			 .For(detailTournamentPage.getStartAndEndOfTournamentLabel())
		 			 .isVisible()
		 			 .valueMatch(TestsConstants.TOURNAMENT_START_END_LABEL)
		 			 .next()
		 			 
		 			 .For(detailTournamentPage.getStartAndEndOfTransferLabel())
		 			 .isVisible()
		 			 .valueMatch(TestsConstants.TOURNAMENT_START_END_TRANSFER_PERIOD_LABEL)
		 			 .next()
		 			 
		 			 .For(detailTournamentPage.getSchemeLabel())
		 			 .isVisible()
		 			 .valueMatch(TestsConstants.TOURNAMENT_SCHEME_LABEL)
		 			 .next()


		 			.For(detailTournamentPage.getReturnButton())
   				    .textMatch(TestsConstants.RETURN_BUTTON)
   				    .isVisible()
   				    .next()
   				    
   				    .For(detailTournamentPage.getDeleteButton())
   				    .textMatch(TestsConstants.DELETE_BUTTON)
   				    .isVisible()
   				    .next(); 
    }
    /**
     * This method performs the operations necessary to stop the test and close
     * browser.
     */
    @AfterClass
    public final void tearDown() {
    	RollbackPreconditions.forValidationTournamentFieldsTest(tournamentData);
    }
}
