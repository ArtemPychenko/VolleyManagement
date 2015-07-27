package com.softserveinc.ita.volleymanagementtests.tests.tournament;

import java.util.List;

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
import com.softserveinc.ita.volleymanagementtests.tests.RollbackPreconditions;
import com.softserveinc.ita.volleymanagementtests.tests.SetPreconditions;
import com.softserveinc.ita.volleymanagementtests.tests.TestsConstants;
import com.softserveinc.ita.volleymanagementtests.tools.WebDriverUtils;

/**
 * This test-case for checking UI elements of the
 * edit tournament page of the VolleyManagement application.
 * This test-case dooesn't check data in fields.
 * Date in fields checks in 
 * @author Andriy Lantukh 
 *         
 */
public class EditTournamentPageUITest {
    /**
     * Specification object for soft-assert report create.
     */
    private Specification specification;
    /**
     * Main page object of testing application.
     */
    private HomePage homePage;
    /**
     * New tournament page.
     */
    private EditTournamentPage editTournamentPage;
    /**
     * tournamentsData will save the tournaments in set up method 
     * in their current state to restore them after test 
     * will complete in tear down method.
     */
    private List<Tournament> tournamentsData;
    /**
     * The tournament instance with valid data entered.
     */
    private Tournament validTournament;
    
    /**
     * This method performs the operations necessary to run the test.
     */
    @BeforeClass
    public final void setUp() {
        DALTools.startConnection(TestsConstants.CONNECT_DB_STRING);
        tournamentsData = SetPreconditions.forValidationTournamentFieldsTest();
        validTournament = TournamentTestObjects.getValidTournament();
        TournamentRepository.insertTournament(validTournament);
        WebDriverUtils.load(TestsConstants.HOME_PAGE);
        homePage = new HomePage();
        editTournamentPage = homePage.getNavigationPage()
                .getTournamentListPage().showTournamentInfo(validTournament)
                .pressEditButton();
        specification = Specification.get();
    }
    /**
     * Check UI element on the page.
     */
    @Test
    public final void tournamentEditPageUI() {
        
        //Title
        specification
        .For(editTournamentPage.getData().getTitleLabel())
        .isVisible()
        .valueMatch(TestsConstants.EDIT_TOURNAMENT_TITLE_LABEL)
        .next()
        //Name field label
        .For(editTournamentPage.getData().getTournamentNameLabel())
        .isVisible()
        .valueMatch(TestsConstants.TOURNAMENT_NAME_LABEL)
        .next()
        //Name field 
        .For(editTournamentPage.getData().getTournamentNameInput())
        .isVisible()
        .next()
        //Description field label
        .For(editTournamentPage.getData().getDescriptionLabel())
        .isVisible()
        .valueMatch(TestsConstants.TOURNAMENT_DESCRIPTION_LABEL)
        .next()
        //Description field
        .For(editTournamentPage.getData().getDescriptionInput())
        .isVisible()
        .next()
        //Season field label
        .For(editTournamentPage.getData().getSeasonLabel())
        .isVisible()
        .valueMatch(TestsConstants.TOURNAMENT_SEASON_LABEL)
        .next()
        //Applying Period Start/End fields label
        .For(editTournamentPage.getData().getApplyingTermsLabel())
        .isVisible()
        .valueMatch(TestsConstants.TOURNAMENT_TERMS_APPLYING_LABEL)
        .next()
        //Applying Period Start field
        .For(editTournamentPage.getData().getApplyingStartInput())
        .isVisible()
        // TODO default value
        .next()
        //Applying Period Start button
        .For(editTournamentPage.getData().getApplyingStartButton())
        .isVisible()
        .next()
         //Applying Period End field
        .For(editTournamentPage.getData().getApplyingEndInput())
        .isVisible()
        // TODO default value
        .next()
        //Applying Period End button
        .For(editTournamentPage.getData().getApplyingEndButton())
        .isVisible()
        .next()
         //Tournament Start/End fields label
        .For(editTournamentPage.getData().getTournamentTermsLabel())
        .isVisible()
        .valueMatch(TestsConstants.TOURNAMENT_START_END_LABEL)
        .next()
        //Tournament Start field
        .For(editTournamentPage.getData().getTournamentStartInput())
        .isVisible()
        .next()
        //Tournament Start button
        .For(editTournamentPage.getData().getTournamentStartButton())
        .isVisible()
        .next()
         //Tournament End field
        .For(editTournamentPage.getData().getTournamentEndInput())
        .isVisible()
        .next()
        //Tournament End button
        .For(editTournamentPage.getData().getTournamentEndButton())
        .isVisible()
        .next()
         //Transfer Start/End fields label
        .For(editTournamentPage.getData().getTransferPeriodTermsLabel())
        .isVisible()
        .valueMatch(TestsConstants.TOURNAMENT_START_END_TRANSFER_PERIOD_LABEL)
        .next()
        //Transfer Start field
        .For(editTournamentPage.getData().getTransferStartInput())
        .isVisible()
        .next()
        //Transfer Start button
        .For(editTournamentPage.getData().getTransferStartButton())
        .isVisible()
        .next()
         //Transfer End field
        .For(editTournamentPage.getData().getTransferEndInput())
        .isVisible()
        .next()
        //Transfer End button
        .For(editTournamentPage.getData().getTransferEndButton())
        .isVisible()
        .next()
         //Scheme field label
        .For(editTournamentPage.getData().getSchemeLabel())
        .isVisible()
        .valueMatch(TestsConstants.TOURNAMENT_SCHEME_LABEL)
        .next()
        //Regulations link field label
        .For(editTournamentPage.getData().getLinkToReglamentLabel())
        .isVisible()
        .valueMatch(TestsConstants.TOURNAMENT_REGULATIONS_LINK_LABEL)
        .next()
        //Regulations link field 
        .For(editTournamentPage.getData().getLinkToReglamentInput())
        .isVisible()
        .next()
        //Cancel button
        .For(editTournamentPage.getCancelButton())
        .isVisible()
        .textMatch(TestsConstants.CANCEL_BUTTON_LABEL)
        .next()
        //Save button
        .For(editTournamentPage.getSaveButton())
        .isVisible()
        .textMatch(TestsConstants.SAVE_BUTTON_LABEL);
               
        specification.check();
    }
    
    /**
     * This method performs the operations necessary after the test execution.
     */
    @AfterClass
    public final void tearDown() {
        RollbackPreconditions
            .forValidationTournamentFieldsTest(tournamentsData);
        //DALTools.closeConnection();
        //WebDriverUtils.stop();
    }
}

