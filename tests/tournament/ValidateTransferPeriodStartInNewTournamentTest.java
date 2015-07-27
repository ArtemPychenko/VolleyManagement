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
import com.softserveinc.ita.volleymanagementtests.pages.NewTournamentPage;
import com.softserveinc.ita.volleymanagementtests.tests.RollbackPreconditions;
import com.softserveinc.ita.volleymanagementtests.tests.SetPreconditions;
import com.softserveinc.ita.volleymanagementtests.tests.TestsConstants;
import com.softserveinc.ita.volleymanagementtests.tools.WebDriverUtils;
/**
 * This test case validate transfer period start field in new tournament page.
 * @author Dmytro Maslov.
 */
public class ValidateTransferPeriodStartInNewTournamentTest {
    /** Specification object for soft-assert report create.*/
    private Specification specification;
    /** Main page object of testing application.*/
    private HomePage homePage;
    /**New tournament page.*/
    private NewTournamentPage newTournamentPage;
    /**Valid tournament.*/
    private Tournament newTournament;
    /**Contain tournament repository.*/
    private TournamentRepository repository;
    /**Tournament count in DB.*/
    private int tournamentInDB;
    /**Collection don't valid date.*/
    private List<String> newDateList;
    /**Collection tournament.*/
    private List<Tournament> saveTournamentInBD;
    /**This method performs the operations necessary to run the test.*/
    @BeforeClass
    public final void setUp() {
        DALTools.startConnection(TestsConstants.CONNECT_DB_STRING);
        saveTournamentInBD = SetPreconditions
                .forValidationTournamentFieldsTest();
        tournamentInDB = TournamentRepository.countAllTournaments();
        specification = Specification.get();
        WebDriverUtils.load(TestsConstants.HOME_PAGE);
        homePage = new HomePage();
    }
    /**Don't correct date type and value.*/
    @Test
    public final void dontCorrectDate() {
        newTournament = TournamentTestObjects.getValidTournament();
        newDateList = TournamentTestObjects.getInvalidTypeForDate();
        for (String date : newDateList) {
            newTournamentPage = homePage.getNavigationPage()
                    .getTournamentListPage().openCreateTournamentPage();
            newTournamentPage.getData().setTournamentName(newTournament);
            newTournamentPage.getData().setDescription(newTournament);
            newTournamentPage.getData().setSeason(newTournament);
            newTournamentPage.getData().setScheme(newTournament);
            newTournamentPage.getData().setLinkToReglamentInput(newTournament);
            newTournamentPage.getData().setApplyingStartInput(newTournament);
            newTournamentPage.getData().setApplyingEndInput(newTournament);
            newTournamentPage.getData().setTournamentStartInput(newTournament);
            newTournamentPage.getData().setTournamentEndInput(newTournament);
            newTournamentPage.getData().setTransferStartInput(date);
            newTournamentPage.getData().setTransferEndInput(newTournament);
            newTournamentPage.pressSaveWithoutReturnPage();
            specification.For(repository)
                    .numberOfTournamentsHasNotChanged(tournamentInDB).next();
        }
        specification.check();
    }
    /**Valid date.*/
    @Test
    public final void validDate() {
        // Transfer start = Transfer end
        newTournament = TournamentTestObjects.getValidTournament();
        newTournament.setTransferStart(newTournament.getTransferEnd());
        newTournamentPage = homePage.getNavigationPage().getTournamentListPage()
                .openCreateTournamentPage();
        newTournamentPage.getData().setAllFields(newTournament);
        newTournamentPage.pressSaveWithoutReturnPage();
        specification.For(repository)
                .numberOfTournamentsHasIncreased(tournamentInDB).next();
        tournamentInDB = TournamentRepository.countAllTournaments();
        // Tournament Start < Transfer Start < Tournament End
        newTournament.setTransferStart(TournamentTestObjects
                .getLargeDate(newTournament.getGamesStart()));
        newTournamentPage = homePage.getNavigationPage().getTournamentListPage()
                .openCreateTournamentPage();
        newTournamentPage.getData().setAllFields(newTournament);
        newTournamentPage.pressSaveWithoutReturnPage();
        specification.For(repository)
                .numberOfTournamentsHasIncreased(tournamentInDB);
        specification.check();
    }
    /**This method performs the operations necessary after the test execution.*/
    @AfterClass
    public final void tearDown() {
        RollbackPreconditions
                .forValidationTournamentFieldsTest(saveTournamentInBD);
    }

}
