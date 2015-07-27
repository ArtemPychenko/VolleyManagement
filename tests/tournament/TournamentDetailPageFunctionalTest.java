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
import com.softserveinc.ita.volleymanagementtests.pages.NavigationPage;
import com.softserveinc.ita.volleymanagementtests.pages.TournamentDetailsPage;
import com.softserveinc.ita.volleymanagementtests.pages.TournamentListPage;
import com.softserveinc.ita.volleymanagementtests.tests.RollbackPreconditions;
import com.softserveinc.ita.volleymanagementtests.tests.SetPreconditions;
import com.softserveinc.ita.volleymanagementtests.tests.TestsConstants;
import com.softserveinc.ita.volleymanagementtests.tools.WebDriverUtils;
/**
 * Test compare fields content in database and on detail tournament page.
 * @author Artem Pychenko
 *
 */
public class TournamentDetailPageFunctionalTest {
	/**
	 * Specification object for soft-assert report create.
	 */
	private Specification specification;
	/**
	 * Object of HomePage class.
	 */
	private NavigationPage homePage;
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
     * Existing team database.
     */
	private TournamentRepository repository;
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
	        specification = Specification.get();
	 }
	 /**
	  * Test compare fields content in database and on edit tournament page.
	  */
	 @Test
	 public final void editTournamentPageUI() {
	     specification.For(repository).isTournamentPresent(expectedTournament).next();
	     WebDriverUtils.load(TestsConstants.HOME_PAGE);
	     homePage = new NavigationPage();
	     tournamentListPage = homePage.getTournamentListPage();
	     detailTournamentPage = tournamentListPage.showTournamentInfo();
	     
	     specification.For(detailTournamentPage.getTournamentNameInput())
	     			  .valueMatch(expectedTournament.getName())
	     			  .next();
	     specification.For(detailTournamentPage.getDescriptionInput())
	     			  .valueMatch(expectedTournament.getDescription())
	     			  .next();
	     specification.For(detailTournamentPage.getSeasonInput())
		              .valueMatch(expectedTournament.getSeasonForUI())
		              .next();
	     specification.For(detailTournamentPage.getStartAndEndOfRegistrationInput())
	     			  .valueMatch(expectedTournament.getApplyingPeriodStartEndForUI())
	     			  .next();
	     specification.For(detailTournamentPage.getStartAndEndOfTournamentInput())
		              .valueMatch(expectedTournament.getGamesStartEndForUI())
		              .next();
	     specification.For(detailTournamentPage.getStartAndEndOfTransferInput())
		              .valueMatch(expectedTournament.getTransferStartEndForUI())
		              .next();
	     specification.For(detailTournamentPage.getSchemeInput())
		              .valueMatch(String.valueOf(expectedTournament.getScheme()))
		              .next();
	     specification.check();
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
