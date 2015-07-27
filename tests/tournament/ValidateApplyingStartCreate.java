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
     * This class validates the coach's name field through create team page.
     * @author Aleksandr Zaitsev
     *
     */
public class ValidateApplyingStartCreate {

        /**
         * an instance of the main page of the web site.
         */
        private HomePage homePage;
        /**
         * an instance of the page of new team creating.
         */
        private NewTournamentPage newTournamentPage;
        /**
         * Existing team database.
         */
        private TournamentRepository repository;
        /**
         * Specification object for soft-assert report create.
         */
        private Specification specification;
        /**
         * tournamentsData will save the team in set up method in his current
         * state to restore them after test will complete in tear down method.
         */
        private List<Tournament> tournamentsData;
        /**
         * The tournament instance.
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
            tournamentsData = SetPreconditions
                    .forValidationTournamentFieldsTest();
            WebDriverUtils.load(TestsConstants.HOME_PAGE);
            homePage = new HomePage();
            tournamentInDB = TournamentRepository.countAllTournaments();
            specification = Specification.get();
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
         * Test verifies that if invalid data in applying start field is entered
         * the tournament will be not saved in data base.
         */
        @Test
        public final void invalidDate() {
            tournament = TournamentTestObjects.getValidTournament();
            invalidDateList = TournamentTestObjects.getInvalidTypeForDate();
            for (String date: invalidDateList) {
                newTournamentPage = homePage.getNavigationPage()
                        .getTournamentListPage().openCreateTournamentPage();
                newTournamentPage.getData().setAllFields(tournament);
                newTournamentPage.getData().setApplyingStartInput(date);
                newTournamentPage.pressSaveWithoutReturnPage();
                specification.For(repository)
                    .numberOfTournamentsHasNotChanged(tournamentInDB)
                    .isTournamentNotPresent(tournament);

                if (TournamentRepository.isTournamentPresent(tournament)) {
                    TournamentRepository.deleteTournamentByName(tournament
                            .getName());
                }

            }
            specification.check();
        }
            /**
             * Test verifies that after correct dates are entered into the
             * applying start input - the tournament will be saved in data base.
             */
            @Test
            public final void validDate() {
                //Positive test - tournament should be saved in DB.
                //Applying start = Applying end
                tournamentInDB = TournamentRepository.countAllTournaments();
                tournament = TournamentTestObjects.getValidTournament();
                tournament.setApplyingPeriodStart(tournament
                        .getApplyingPeriodEnd());
                newTournamentPage = homePage.getNavigationPage()
                        .getTournamentListPage().openCreateTournamentPage();
                newTournamentPage.getData().setAllFields(tournament);
                newTournamentPage.pressSaveWithoutReturnPage();
                specification.For(repository)
                    .numberOfTournamentsHasIncreased(tournamentInDB)
                    .isTournamentPresent(tournament);
                //Positive test - tournament should be saved in DB.
                //Applying Start = current date
                tournament.setApplyingPeriodStart((TournamentTestObjects
                        .getValidTournament().getApplyingPeriodStart()));
                newTournamentPage = homePage.getNavigationPage()
                        .getTournamentListPage().openCreateTournamentPage();
                newTournamentPage.getData().setApplyingStartInput(tournament);
                newTournamentPage.pressSaveWithoutReturnPage();
                specification.For(repository)
                    .numberOfTournamentsHasIncreased(tournamentInDB)
                    .isTournamentPresent(tournament);
                //Positive test - tournament should be saved in DB.
                //Applying Start < current date
                tournament.setApplyingPeriodStart(
                        TournamentTestObjects
                        .getLowerDate(tournament.getApplyingPeriodStart()));
                newTournamentPage = homePage.getNavigationPage()
                        .getTournamentListPage().openCreateTournamentPage();
                newTournamentPage.getData().setAllFields(tournament);
                newTournamentPage.pressSaveWithoutReturnPage();
                specification.For(repository)
                    .numberOfTournamentsHasIncreased(tournamentInDB)
                    .isTournamentPresent(tournament);

                specification.check();
               }
    }


