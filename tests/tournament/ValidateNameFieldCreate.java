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
import com.softserveinc.ita.volleymanagementtests.pages.TournamentListPage;
import com.softserveinc.ita.volleymanagementtests.tests.RollbackPreconditions;
import com.softserveinc.ita.volleymanagementtests.tests.SetPreconditions;
import com.softserveinc.ita.volleymanagementtests.tests.TestsConstants;
import com.softserveinc.ita.volleymanagementtests.tools.WebDriverUtils;

    /**
     * This class validates the coach's name field through create team page.
     * @author Aleksandr Zaitsev
     *
     */
public class ValidateNameFieldCreate {

        /**
         * an instance of the main page of the web site.
         */
        private HomePage homePage;
        /**
         * an instance of the page of new team creating.
         */
        private NewTournamentPage newTournamentPage;
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
         * tournamentsData will save the team in set up method in his current
         * state to restore them after test will complete in tear down method.
         */
        private List<Tournament> tournamentsData;
        /**
         * The tournament instance.
         */
        private Tournament tournament;
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
        
        @Test
        public final void validateTeamsNameField() {
            
            /**
             * The tournament shouldn't be save in the database, because of
             * field was made empty.
             * The validation message should appear.
             */ 
            tournament = TournamentTestObjects.getInvalidEmptyTournamentName();
            tournamentListPage = homePage.getNavigationPage()
                    .getTournamentListPage();
            newTournamentPage = tournamentListPage.openCreateTournamentPage();
            newTournamentPage.getData().setAllFields(tournament);
            newTournamentPage = newTournamentPage.unsuccessfulPressSave();
               
            specification
            .For(newTournamentPage.getData().getErrorMessageLabelName())
            .valueMatch(TestsConstants
                    .VALIDATION_ERROR_TEXT_EMPTY_NAME_FIELD)
            .next()
            .For(repository)
            .isTournamentNotPresent(tournament);
                           
            if (TournamentRepository.isTournamentPresent(tournament)) {
                TournamentRepository.deleteTournamentByName(tournament
                        .getName());
            }
            /**
             * The tournament shouldn't be save in the database, because of 60 symbols
             * is max but 61 are entered.
             * The validation message should appear.
             */
            tournament = TournamentTestObjects.getInvalidTooBigTournamentName();
            tournamentListPage = homePage.getNavigationPage()
                    .getTournamentListPage();
            newTournamentPage = tournamentListPage.openCreateTournamentPage();
            newTournamentPage.getData().setAllFields(tournament);
            newTournamentPage = newTournamentPage.unsuccessfulPressSave();

            specification
            .For(newTournamentPage.getData().getErrorMessageLabelName())
            .valueMatch(TestsConstants.VALIDATION_ERROR_TEXT_TOO_BIG_NAME)
            .next()
            .For(repository)
            .isTournamentNotPresent(tournament);

            if (TournamentRepository.isTournamentPresent(tournament)) {
                TournamentRepository.deleteTournamentByName(tournament
                        .getName());
            }
            tournamentListPage = TournamentListPage.refreshTournamentList();
            /**
             * Validation field with valid values.
             * The tournament should be save in the database.
             */
            final List<Tournament> validTournaments
            = TournamentTestObjects.getValidTournamentsName();
            for (Tournament tournament : validTournaments) {
                newTournamentPage = tournamentListPage
                        .openCreateTournamentPage();
                newTournamentPage.getData().setAllFields(tournament);
                tournamentListPage = newTournamentPage.pressSave();

                specification
                .For(repository)
                .isTournamentPresent(tournament);

                if (TournamentRepository.isTournamentPresent(tournament)) {
                    TournamentRepository.deleteTournamentByName(tournament
                            .getName());
                }
            }
            specification.check();
        }
    }


