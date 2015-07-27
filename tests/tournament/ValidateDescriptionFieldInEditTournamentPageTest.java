package  com.softserveinc.ita.volleymanagementtests.tests.tournament;
import java.util.List;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.softserveinc.ita.volleymanagementtests.criterias.Specification;
import com.softserveinc.ita.volleymanagementtests.domain.models.Tournament;
import com.softserveinc.ita.volleymanagementtests.domain.repositories.DALTools;
import com.softserveinc.ita.volleymanagementtests.domain.repositories.TournamentRepository;
import com.softserveinc.ita.volleymanagementtests.domain.repositories.TournamentTestObjects;
import com.softserveinc.ita.volleymanagementtests.pages.EditTournamentPage;
import com.softserveinc.ita.volleymanagementtests.pages.HomePage;
import com.softserveinc.ita.volleymanagementtests.tests.RollbackPreconditions;
import com.softserveinc.ita.volleymanagementtests.tests.SetPreconditions;
import com.softserveinc.ita.volleymanagementtests.tests.TestsConstants;
import com.softserveinc.ita.volleymanagementtests.tools.WebDriverUtils;
/**
 * This class validate's the description field through edit tournament page.
 * @author Artem Pychenko
 *
 */
public class ValidateDescriptionFieldInEditTournamentPageTest {
	/**
     * Specification object for soft-assert report create.
     */
	private Specification specification;
	/**
     * an instance of the main page of the web site.
     */
	private HomePage homePage;
	/**
     * an instance of the page of edit tournament.
     */
	private EditTournamentPage editTournamentPage;
	/**
     * an instance of the page of tournament.
     */
	private Tournament tournament;
	/**
     * Existing team database.
     */
	private TournamentRepository repository;
	/**
     * Variable used for count amount of tournaments.
     */
	private int tournamentInDB;
	/**
     * String list.
     */
	private List <String> newDescriptionList;
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
	        tournament = TournamentTestObjects.getValidTournament();
	        TournamentRepository.insertTournament(tournament);
	        tournamentInDB = TournamentRepository.countAllTournaments();
	        specification = Specification.get();
	        WebDriverUtils.load(TestsConstants.HOME_PAGE);
		    homePage = new HomePage();
		    
	    }
	 /**
      * This method check functionality of description field if save button doesn't work.
      */
	 @Test
	 public final void dontCorrectSize() {
		editTournamentPage = homePage
				.getNavigationPage().getTournamentListPage()
				.showTournamentInfo().pressEditButton();
	    editTournamentPage.getData().setDescription(TournamentTestObjects.getInvlidDescriptionSize());
	    editTournamentPage.unsuccessfulPressSave();
	    specification.For(
	    		editTournamentPage.getData().getErrorMessageLabelDescription())
	    		.valueMatch(TestsConstants.VALIDATION_ERROR_TEXT_WRONG__DESCRIPTION_SIZE).next()
	    		.For(repository).numberOfTournamentsHasNotChanged(tournamentInDB).next()
	    		.For(tournament).tournamentMatch(TournamentRepository
	    		.getTournamentsByName(tournament.getName()).get(0));
	    specification.check();
	 }
	 /**
      * This method check functionality of description field if save button works.
      */
	 @Test
	 public final void validDescription() {
		 newDescriptionList = TournamentTestObjects.getValidName();
		 newDescriptionList.add(TournamentTestObjects.getValidSizeDescription());
	 	for (String validDescription: newDescriptionList) {
	 		editTournamentPage = homePage
					.getNavigationPage().getTournamentListPage()
					.showTournamentInfo().pressEditButton();
	 		editTournamentPage.getData().setDescription(validDescription);
	 		editTournamentPage.pressSave();
	 		specification.For(repository).numberOfTournamentsHasIncreased(tournamentInDB).next();
	 		specification.For(repository).tournamentPresentByDescription(validDescription);
	 		tournamentInDB = TournamentRepository.countAllTournaments();
	 	}
	 	specification.check();
	    }
	    /**
	 	 * This method performs the operations necessary after the test
	 	 * execution.
	 	 */
	    @AfterClass
	    public final void tearDown() {
	    	RollbackPreconditions.forValidationTournamentFieldsTest(tournamentData);
	    }
	 
}
