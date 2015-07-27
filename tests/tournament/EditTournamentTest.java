package com.softserveinc.ita.volleymanagementtests.tests.tournament;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.softserveinc.ita.volleymanagementtests.criterias.Specification;
import com.softserveinc.ita.volleymanagementtests.domain.models.Tournament;
import com.softserveinc.ita.volleymanagementtests.domain.repositories.DALTools;
import com.softserveinc.ita.volleymanagementtests.domain.repositories
.TournamentRepository;
import com.softserveinc.ita.volleymanagementtests.domain.repositories
.TournamentTestObjects;
import com.softserveinc.ita.volleymanagementtests.pages.EditTournamentPage;
import com.softserveinc.ita.volleymanagementtests.pages.HomePage;
import com.softserveinc.ita.volleymanagementtests.tests.TestsConstants;
import com.softserveinc.ita.volleymanagementtests.tools.WebDriverUtils;

/**
 * @author Alla Prykhodchenko This test-case for checking edit tournament
 *         functionality of the VolleyManagement application.
 */
public class EditTournamentTest {
    /**
     * Main page object of testing application.
     */
    private HomePage homePage;
    /**
     * Tournament edit page.
     */
    private EditTournamentPage editPage;
    /**
     * Valid tournament object with all fields.
     */
    private Tournament tournament;
    /**
     * Valid tournament object with required fields.
     */
    private Tournament tournamentForEdition;
    /**
     * Specification object for soft-assert report create.
     */
    private Specification specification;
    /**
     * Existing tournament database.
     */
    private TournamentRepository repository;
    /**
     * Number of tournaments with tournament data before test execution.
     */
    private int prevNumberOfTournament;
    /**
     * Number of tournaments with tournament for edition data before
     * test execution.
     */
    private int prevNumberOftournamentForEdition;
    /**
     * Number of tournaments in database before test execution.
     */
    private int prevcountAllTournament;

    /**
     * This method performs the operations necessary to run the test.
     */
    @BeforeClass
    public final void setUp() {
        WebDriverUtils.load(TestsConstants.HOME_PAGE);
        homePage = new HomePage();
        DALTools.startConnection(TestsConstants.CONNECT_DB_STRING);
        tournament = TournamentTestObjects.getValidTournament();
        tournamentForEdition = TournamentTestObjects
                .getValidTournamentWithRequiredFields();
        TournamentRepository.insertTournament(tournamentForEdition);
        prevNumberOfTournament = TournamentRepository
                .countTournaments(tournament);
        prevNumberOftournamentForEdition = TournamentRepository
                .countTournaments(tournamentForEdition);
        prevcountAllTournament = TournamentRepository.countAllTournaments();
    }

    /**
     * This test for checking edit tournament functionality for tournament
     * with all tournament fields data.
     */
    @Test
    public final void editTournament() {
       specification = Specification.get();
       editPage = homePage.getNavigationPage().getTournamentListPage()
               .showTournamentInfo(tournamentForEdition)
               .pressEditButton();
       editPage.getData().setTournamentName(tournament);
        specification.For(editPage.getData()
                .getTournamentNameInput())
                .isContain(tournament.getName())
                .next();
        editPage.getData().setDescription(tournament);
        specification.For(editPage.getData()
                .getDescriptionInput())
                .isContain(tournament.getDescription())
                .next();
        editPage.getData().setSeason(tournament);
        specification.For(editPage.getData()
                .getSeasonDropdown())
                .selectedValueMatch(tournament.getSeasonForUI())
                .next();
        editPage.getData().setApplyingStartInput(tournament);
        specification.For(editPage.getData()
                .getApplyingStartInput())
                .isContain(tournament.getApplyingPeriodStartForUI())
                .next();
        editPage.getData().setApplyingEndInput(tournament);
        specification.For(editPage.getData()
                .getApplyingEndInput())
                .isContain(tournament.getApplyingPeriodEndForUI())
                .next();
        editPage.getData().setTournamentStartInput(tournament);
        specification.For(editPage.getData()
                .getTournamentStartInput())
                .isContain(tournament.getGamesStartForUI())
                .next();
        editPage.getData().setTournamentEndInput(tournament);
        specification.For(editPage.getData()
                .getTournamentEndInput())
                .isContain(tournament.getGamesEndForUI())
                .next();
        editPage.getData().setTransferStartInput(tournament);
        specification.For(editPage.getData()
                .getTransferStartInput())
                .isContain(tournament.getTransferStartForUI())
                .next();
        editPage.getData().setTransferEndInput(tournament);
        specification.For(editPage.getData()
                .getTransferEndInput())
                .isContain(tournament.getTransferEndForUI())
                .next();
        editPage.getData().setScheme(tournament);
        specification.For(editPage.getData()
                .getSchemeDropdown())
                .selectedValueMatch(String.valueOf(tournament.getScheme()))
                .next();
        editPage.getData().setLinkToReglamentInput(tournament);
        specification.For(editPage.getData()
                .getLinkToReglamentInput())
                .isContain(tournament.getRegulationsLink())
                .next();
        editPage.pressSave();
        specification.For(repository)
        .tournamentWasAdded(tournament, prevNumberOfTournament)
        .tournamentWasDeleted(tournamentForEdition,
                prevNumberOftournamentForEdition)
        .numberOfTournamentsHasNotChanged(prevcountAllTournament);
        specification.check();
    }
    /**
     * This method performs the operations necessary after the test execution.
     */
    @AfterClass
    public final void tearDown() {
        TournamentRepository.deleteLastTournament();
        //WebDriverUtils.stop();
    }
}
