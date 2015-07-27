package com.softserveinc.ita.volleymanagementtests.tests.tournament;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.softserveinc.ita.volleymanagementtests.criterias.Specification;
import com.softserveinc.ita.volleymanagementtests.domain.repositories
        .TournamentTestObjects;
import com.softserveinc.ita.volleymanagementtests.pages.HomePage;
import com.softserveinc.ita.volleymanagementtests.pages.NewTournamentPage;
import com.softserveinc.ita.volleymanagementtests.tests.TestsConstants;
import com.softserveinc.ita.volleymanagementtests.tools.WebDriverUtils;

/**
 * @author Andriy Lantukh This test-case for checking UI elements of the
 *         new tournament page of the VolleyManagement application.
 */
public class NewTournamentPageUITest {
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
    private NewTournamentPage newTournamentPage;
    /**
     * This method performs the operations necessary to run the test.
     */
    @BeforeClass
    public final void setUp() {
        WebDriverUtils.load(TestsConstants.HOME_PAGE);
        homePage = new HomePage();
        newTournamentPage = homePage.getNavigationPage()
                .getTournamentListPage().openCreateTournamentPage();
        specification = Specification.get();
    }
    /**
     * Check UI element on the page.
     */
    @Test
    public final void tournamentListPageUI() {
        
        //Title
        specification
        .For(newTournamentPage.getData().getTitleLabel())
        .isVisible()
        .valueMatch(TestsConstants.CREATE_NEW_TOURNAMENT_TITLE_LABEL)
        .next()
        //Name field label
        .For(newTournamentPage.getData().getTournamentNameLabel())
        .isVisible()
        .valueMatch(TestsConstants.TOURNAMENT_NAME_LABEL)
        .next()
        //Name field 
        .For(newTournamentPage.getData().getTournamentNameInput())
        .isVisible()
        .isEmpty()
        .next()
        //Description field label
        .For(newTournamentPage.getData().getDescriptionLabel())
        .isVisible()
        .valueMatch(TestsConstants.TOURNAMENT_DESCRIPTION_LABEL)
        .next()
        //Description field
        .For(newTournamentPage.getData().getDescriptionInput())
        .isVisible()
        .isEmpty()
        .next()
        //Season field label
        .For(newTournamentPage.getData().getSeasonLabel())
        .isVisible()
        .valueMatch(TestsConstants.TOURNAMENT_SEASON_LABEL)
        .next()
        //Season dropdown
        .For(newTournamentPage.getData().getSeasonDropdown())
        .selectedValueMatch(TournamentTestObjects
                .getValidTournament().getSeasonForUI())
        .next()
        //Applying Period Start/End fields label
        .For(newTournamentPage.getData().getApplyingTermsLabel())
        .isVisible()
        .valueMatch(TestsConstants.TOURNAMENT_TERMS_APPLYING_LABEL)
        .next()
        //Applying Period Start field
        .For(newTournamentPage.getData().getApplyingStartInput())
        .isVisible()
        // TODO default value
        .next()
        //Applying Period Start button
        .For(newTournamentPage.getData().getApplyingStartButton())
        .isVisible()
        .next()
         //Applying Period End field
        .For(newTournamentPage.getData().getApplyingEndInput())
        .isVisible()
        // TODO default value
        .next()
        //Applying Period End button
        .For(newTournamentPage.getData().getApplyingEndButton())
        .isVisible()
        .next()
         //Tournament Start/End fields label
        .For(newTournamentPage.getData().getTournamentTermsLabel())
        .isVisible()
        .valueMatch(TestsConstants.TOURNAMENT_START_END_LABEL)
        .next()
        //Tournament Start field
        .For(newTournamentPage.getData().getTournamentStartInput())
        .isVisible()
        // TODO default value
        .next()
        //Tournament Start button
        .For(newTournamentPage.getData().getTournamentStartButton())
        .isVisible()
        .next()
         //Tournament End field
        .For(newTournamentPage.getData().getTournamentEndInput())
        .isVisible()
        // TODO default value
        .next()
        //Tournament End button
        .For(newTournamentPage.getData().getTournamentEndButton())
        .isVisible()
        .next()
         //Transfer Start/End fields label
        .For(newTournamentPage.getData().getTransferPeriodTermsLabel())
        .isVisible()
        .valueMatch(TestsConstants.TOURNAMENT_START_END_TRANSFER_PERIOD_LABEL)
        .next()
        //Transfer Start field
        .For(newTournamentPage.getData().getTransferStartInput())
        .isVisible()
        // TODO default value
        .next()
        //Transfer Start button
        .For(newTournamentPage.getData().getTransferStartButton())
        .isVisible()
        .next()
         //Transfer End field
        .For(newTournamentPage.getData().getTransferEndInput())
        .isVisible()
        // TODO default value
        .next()
        //Transfer End button
        .For(newTournamentPage.getData().getTransferEndButton())
        .isVisible()
        .next()
         //Scheme field label
        .For(newTournamentPage.getData().getSchemeLabel())
        .isVisible()
        .valueMatch(TestsConstants.TOURNAMENT_SCHEME_LABEL)
        .next()
        //Scheme dropdown
        .For(newTournamentPage.getData().getSchemeDropdown())
        .selectedValueMatch(Integer.toString(TournamentTestObjects
                .getValidTournament().getScheme()))
        .next()
        //Regulations link field label
        .For(newTournamentPage.getData().getLinkToReglamentLabel())
        .isVisible()
        .valueMatch(TestsConstants.TOURNAMENT_REGULATIONS_LINK_LABEL)
        .next()
        //Regulations link field 
        .For(newTournamentPage.getData().getLinkToReglamentInput())
        .isVisible()
        .isEmpty()
        .next()
        //Cancel button
        .For(newTournamentPage.getCancelButton())
        .isVisible()
        .textMatch(TestsConstants.CANCEL_BUTTON_LABEL)
        .next()
        //Save button
        .For(newTournamentPage.getSaveButton())
        .isVisible()
        .textMatch(TestsConstants.SAVE_BUTTON_LABEL);
               
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
