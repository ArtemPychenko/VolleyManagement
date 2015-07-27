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
import com.softserveinc.ita.volleymanagementtests.pages.HomePage;
import com.softserveinc.ita.volleymanagementtests.pages.NewTournamentPage;
import com.softserveinc.ita.volleymanagementtests.tests.TestsConstants;
import com.softserveinc.ita.volleymanagementtests.tools.WebDriverUtils;

/**
 * @author Alla Prykhodchenko This test-case for checking create new tournament
 *         functionality of the VolleyManagement application.
 */
public class CreateNewTournamentTest {
    /**
     * Main page object of testing application.
     */
    private HomePage homePage;
    /**
     * Create new tournament page.
     */
    private NewTournamentPage newTournamentPage;
    /**
     * Valid tournament object with all fields.
     */
    private Tournament tournament;
    /**
     * Valid tournament object with required fields.
     */
    private Tournament reqFieldTournament;
    /**
     * Specification object for soft-assert report create.
     */
    private Specification specification;
    /**
     * Existing tournament database.
     */
    private TournamentRepository repository;
    /**
     * Number of tournaments with valid tournament data before test execution.
     */
    private int prevNumberOfTournament;
    /**
     * Number of tournaments with valid tournaments with required fields data
     * before test execution.
     */
    private int prevNumberOfReqFieldTournament;
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
        reqFieldTournament = TournamentTestObjects
                .getValidTournamentWithRequiredFields();
        prevNumberOfTournament = TournamentRepository
                .countTournaments(tournament);
        prevNumberOfReqFieldTournament = TournamentRepository
                .countTournaments(reqFieldTournament);
        prevcountAllTournament = TournamentRepository.countAllTournaments();
    }

    /**
     * This test for checking create new tournament functionality for tournament
     * with all tournament fields data.
     */
    @Test
    public final void createNewTournament() {
       specification = Specification.get();
       newTournamentPage = homePage.getNavigationPage()
                .getTournamentListPage()
                .openCreateTournamentPage();
        newTournamentPage.getData().setTournamentName(tournament);
        specification.For(newTournamentPage.getData()
                .getTournamentNameInput())
                .isContain(tournament.getName())
                .next();
        newTournamentPage.getData().setDescription(tournament);
        specification.For(newTournamentPage.getData()
                .getDescriptionInput())
                .isContain(tournament.getDescription())
                .next();
        newTournamentPage.getData().setSeason(tournament);
        specification.For(newTournamentPage.getData()
                .getSeasonDropdown())
                .selectedValueMatch(tournament.getSeasonForUI())
                .next();
        newTournamentPage.getData().setApplyingStartInput(tournament);
        specification.For(newTournamentPage.getData()
                .getApplyingStartInput())
                .isContain(tournament.getApplyingPeriodStartForUI())
                .next();
        newTournamentPage.getData().setApplyingEndInput(tournament);
        specification.For(newTournamentPage.getData()
                .getApplyingEndInput())
                .isContain(tournament.getApplyingPeriodEndForUI())
                .next();
        newTournamentPage.getData().setTournamentStartInput(tournament);
        specification.For(newTournamentPage.getData()
                .getTournamentStartInput())
                .isContain(tournament.getGamesStartForUI())
                .next();
        newTournamentPage.getData().setTournamentEndInput(tournament);
        specification.For(newTournamentPage.getData()
                .getTournamentEndInput())
                .isContain(tournament.getGamesEndForUI())
                .next();
        newTournamentPage.getData().setTransferStartInput(tournament);
        specification.For(newTournamentPage.getData()
                .getTransferStartInput())
                .isContain(tournament.getTransferStartForUI())
                .next();
        newTournamentPage.getData().setTransferEndInput(tournament);
        specification.For(newTournamentPage.getData()
                .getTransferEndInput())
                .isContain(tournament.getTransferEndForUI())
                .next();
        newTournamentPage.getData().setScheme(tournament);
        specification.For(newTournamentPage.getData()
                .getSchemeDropdown())
                .selectedValueMatch(String.valueOf(tournament.getScheme()))
                .next();
        newTournamentPage.getData().setLinkToReglamentInput(tournament);
        specification.For(newTournamentPage.getData()
                .getLinkToReglamentInput())
                .isContain(tournament.getRegulationsLink())
                .next();
        newTournamentPage.getSaveButton().click();
        specification.For(repository)
        .tournamentWasAdded(tournament, prevNumberOfTournament)
        .numberOfTournamentsHasIncreased(prevcountAllTournament);
        specification.check();
    }
    /**
     * This test for checking create new tournament functionality for tournament
     * with required tournament fields data.
     */
    @Test
    public final void createNewTournamentRequiredFields() {
       specification = Specification.get();
       newTournamentPage = homePage.getNavigationPage()
                .getTournamentListPage()
                .openCreateTournamentPage();
        newTournamentPage.getData().setTournamentName(reqFieldTournament);
        specification.For(newTournamentPage.getData()
                .getTournamentNameInput())
                .isContain(reqFieldTournament.getName())
                .next();
        newTournamentPage.getData().setSeason(reqFieldTournament);
        specification.For(newTournamentPage.getData()
                .getSeasonDropdown())
                .selectedValueMatch(reqFieldTournament.getSeasonForUI())
                .next();
        newTournamentPage.getData().setApplyingStartInput(reqFieldTournament);
        specification.For(newTournamentPage.getData()
                .getApplyingStartInput())
                .isContain(reqFieldTournament.getApplyingPeriodStartForUI())
                .next();
        newTournamentPage.getData().setApplyingEndInput(reqFieldTournament);
        specification.For(newTournamentPage.getData()
                .getApplyingEndInput())
                .isContain(reqFieldTournament.getApplyingPeriodEndForUI())
                .next();
        newTournamentPage.getData().setTournamentStartInput(reqFieldTournament);
        specification.For(newTournamentPage.getData()
                .getTournamentStartInput())
                .isContain(reqFieldTournament.getGamesStartForUI())
                .next();
        newTournamentPage.getData().setTournamentEndInput(reqFieldTournament);
        specification.For(newTournamentPage.getData()
                .getTournamentEndInput())
                .isContain(reqFieldTournament.getGamesEndForUI())
                .next();
        newTournamentPage.getData().setTransferStartInput(reqFieldTournament);
        specification.For(newTournamentPage.getData()
                .getTransferStartInput())
                .isContain(reqFieldTournament.getTransferStartForUI())
                .next();
        newTournamentPage.getData().setTransferEndInput(reqFieldTournament);
        specification.For(newTournamentPage.getData()
                .getTransferEndInput())
                .isContain(reqFieldTournament.getTransferEndForUI())
                .next();
        newTournamentPage.getData().setScheme(reqFieldTournament);
        specification.For(newTournamentPage.getData()
                .getSchemeDropdown())
                .selectedValueMatch(
                        String.valueOf(reqFieldTournament.getScheme()))
                .next();
        newTournamentPage.getSaveButton().click();
        specification.For(repository)
        .tournamentWasAdded(reqFieldTournament, prevNumberOfReqFieldTournament)
        .numberOfTournamentsHasIncreased(prevcountAllTournament);
        specification.check();
    }
    /**
     * This method performs the operations necessary after the test execution.
     */
    @AfterClass
    public final void tearDown() {
        //WebDriverUtils.stop();
    }
}
