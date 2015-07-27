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
import com.softserveinc.ita.volleymanagementtests.pages.HomePage;
import com.softserveinc.ita.volleymanagementtests.pages.NewTournamentPage;
import com.softserveinc.ita.volleymanagementtests.tests.RollbackPreconditions;
import com.softserveinc.ita.volleymanagementtests.tests.SetPreconditions;
import com.softserveinc.ita.volleymanagementtests.tests.TestsConstants;
import com.softserveinc.ita.volleymanagementtests.tools.WebDriverUtils;
/**
 * This class compare the description field through create tournament page.
 * @author Artem Pychenko
 *
 */
public class ValidateDescriptionFieldInNewTournamentPageTest {
	/**
     * Specification object for soft-assert report create.
     */
	private Specification specification;
	/**
     * an instance of the main page of the web site.
     */
	private HomePage homePage;
	/**
     * an instance of the page of new tournament creating.
     */
	private NewTournamentPage newTournamentPage;
	/**
     * an instance of the page of tournament.
     */
	private Tournament newTournament;
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
     * tournamentsData will save the team in set up method in his current state
     * to restore them after test will complete in tear down method.
     */
	private List <Tournament> tournamentInBD;
	/**
     * This method performs the operations necessary to run the test.
     */
	 @BeforeClass
	 public final void setUp() {
		 	DALTools.startConnection(TestsConstants.CONNECT_DB_STRING);
	        tournamentInBD = SetPreconditions.forValidationTournamentFieldsTest();
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
		newTournamentPage = homePage.getNavigationPage().getTournamentListPage().openCreateTournamentPage();
	    newTournament = TournamentTestObjects.getValidTournament();
	    newTournament.setDescription(TournamentTestObjects.getInvalidSizeDescription());
	    newTournamentPage.getData().setAllFields(newTournament);
	    specification.For(
	    		newTournamentPage.getData().getErrorMessageLabelDescription())
	    		.valueMatch(TestsConstants.VALIDATION_ERROR_TEXT_WRONG__DESCRIPTION_SIZE).next();
	    newTournamentPage.unsuccessfulPressSave();
	    specification.For(repository).numberOfTournamentsHasNotChanged(tournamentInDB).next();
	    specification.check();
	 }
	 /**
      * This method check functionality of description field if save button works.
      */
	 @Test
	 public final void validDescription() {
		 newTournament = TournamentTestObjects.getValidTournament();
		 newDescriptionList = TournamentTestObjects.getValidName();
		 newDescriptionList.add(TournamentTestObjects.getValidSizeDescription());
	 	for (String validDescriptions: newDescriptionList) {
	 		newTournamentPage = homePage.getNavigationPage().getTournamentListPage().openCreateTournamentPage();
	 		newTournament.setDescription(validDescriptions);
	 		newTournamentPage.getData().setAllFields(newTournament);
	 		newTournamentPage.pressSave();
	 		specification.For(repository).numberOfTournamentsHasIncreased(tournamentInDB).next()
	 					 .For(repository).isTournamentPresent(newTournament).next();
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
	    	RollbackPreconditions.forValidationTournamentFieldsTest(tournamentInBD);
	    }
	 
}
