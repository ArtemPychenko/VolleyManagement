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
import com.softserveinc.ita.volleymanagementtests.pages.EditTournamentPage;
import com.softserveinc.ita.volleymanagementtests.pages.TournamentListPage;
import com.softserveinc.ita.volleymanagementtests.tests.RollbackPreconditions;
import com.softserveinc.ita.volleymanagementtests.tests.SetPreconditions;
import com.softserveinc.ita.volleymanagementtests.tests.TestsConstants;
import com.softserveinc.ita.volleymanagementtests.tools.WebDriverUtils;

/**
 * This class validates the coach's name field through edit team page.
 * @author Aleksandr Zaitsev
 *
 */
public class ValidateApplyingStartEdit {
    /**
     * an instance of the main page of the web site.
     */
    private HomePage homePage;
    /**
     * an instance of the edit player's page.
     */
    private EditTournamentPage editTournamentPage;
    /**
     * an instance of the page with the list of teams.
     */
    private TournamentListPage tournamentListPage;
    /**
     * Existing team database.
     */
    private TournamentRepository repository;
    /**
     * Specification object for soft-assert report create.
     */
    private Specification specification;
    /**
     * teamData will save the team in set up method in his current state
     * to restore them after test will complete in tear down method.
     */
    private List<Tournament> tournamentsData;
    /**
     * The team instance with valid data entered.
     */
    private Tournament validTournament;
    /**
     * The team instance.
     */
    private Tournament tournament;
    /**
     * for count tournaments in data base.
     */
    private int tournamentInDB;
    /**
     * holds invalid type of data.
     */
    private List<String> invalidDateList;
    /**
     * This method performs the operations necessary to run the test.
     */
    @BeforeClass
    public final void setUp() {
        DALTools.startConnection(TestsConstants.CONNECT_DB_STRING);
        tournamentsData = SetPreconditions.forValidationTournamentFieldsTest();
        WebDriverUtils.load(TestsConstants.HOME_PAGE);
        homePage = new HomePage();
        specification = Specification.get();
        validTournament = TournamentTestObjects.getValidTournament();
        TournamentRepository.insertTournament(validTournament);
        tournamentInDB = TournamentRepository.countAllTournaments();
    }
    /**
     * This method performs the operations necessary after the test
     * execution.
     */
    @AfterClass
    public final void tearDown() {
        RollbackPreconditions.forValidationTournamentFieldsTest(
                tournamentsData);
    }
    /**
     * Test verifies that if invalid data in applying start is entered
     * the tournament will be not added in data base.
     */
    @Test
    public final void invalidDate() {
        tournament = TournamentTestObjects.getValidTournament();
        invalidDateList = TournamentTestObjects.getInvalidTypeForDate();
        for (String date: invalidDateList) {
            tournamentListPage = homePage.getNavigationPage()
                    .getTournamentListPage();
            editTournamentPage = tournamentListPage.showTournamentInfo(
                    validTournament)
                    .pressEditButton();
            editTournamentPage.getData().setApplyingStartInput(date);
            editTournamentPage.pressSaveWithoutReturnPage();
            specification.For(repository)
                .numberOfTournamentsHasNotChanged(tournamentInDB)
                .isTournamentPresent(tournament);
        }
        specification.check();
    }
        /**
         * Test verifies that after correct dates are entered into the
         * applying start input - the tournament will be added in data base.
         */
        @Test
        public final void typeDate() {
            //Positive test - tournament should be saved in DB.
            //Applying start = Applying end
            tournament = TournamentTestObjects.getValidTournament();
            tournament.setApplyingPeriodStart(tournament
                    .getApplyingPeriodEnd());
            tournamentListPage = homePage.getNavigationPage()
                    .getTournamentListPage();
            editTournamentPage = tournamentListPage.showTournamentInfo(
                    validTournament)
                    .pressEditButton();
            editTournamentPage.getData().setApplyingStartInput(tournament);
            editTournamentPage.pressSave();
            specification.For(repository)
                .numberOfTournamentsHasNotChanged(tournamentInDB)
                .isTournamentPresent(tournament);
            //Positive test - tournament should be saved in DB.
            //Applying Start = current date
            tournament.setApplyingPeriodStart((TournamentTestObjects
                    .getValidTournament().getApplyingPeriodStart()));
            tournamentListPage = homePage.getNavigationPage()
                    .getTournamentListPage();
            editTournamentPage = tournamentListPage.showTournamentInfo(
                    validTournament)
                    .pressEditButton();
            editTournamentPage.getData().setApplyingStartInput(tournament);
            editTournamentPage.pressSaveWithoutReturnPage();
            specification.For(repository)
                .numberOfTournamentsHasIncreased(tournamentInDB)
                .isTournamentPresent(tournament);
            //Positive test - tournament should be saved in DB.
            //Applying Start < current date
            tournament.setApplyingPeriodStart(
                    TournamentTestObjects
                    .getLowerDate(tournament.getApplyingPeriodStart()));
            tournamentListPage = homePage.getNavigationPage()
                    .getTournamentListPage();
            editTournamentPage = tournamentListPage.showTournamentInfo(
                    validTournament)
                    .pressEditButton();
            editTournamentPage.getData().setApplyingStartInput(tournament);
            editTournamentPage.pressSaveWithoutReturnPage();
            specification.For(repository)
                .numberOfTournamentsHasIncreased(tournamentInDB)
                .isTournamentPresent(tournament);
            specification.check();
           }
}
