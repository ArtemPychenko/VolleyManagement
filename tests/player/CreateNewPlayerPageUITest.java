package com.softserveinc.ita.volleymanagementtests.tests.player;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.softserveinc.ita.volleymanagementtests.criterias.Specification;
import com.softserveinc.ita.volleymanagementtests.pages.HomePage;
import com.softserveinc.ita.volleymanagementtests.pages.NewPlayerPage;
import com.softserveinc.ita.volleymanagementtests.pages.PlayerListPage;
import com.softserveinc.ita.volleymanagementtests.tests.TestsConstants;
import com.softserveinc.ita.volleymanagementtests.tools.WebDriverUtils;
/**
 * UI test for create new player page.
 * @author Dmytro Maslov
 *
 */
public class CreateNewPlayerPageUITest {
    /**Variable for specification.*/
    private Specification specification;
    /**Contain home page.*/
    private HomePage homePage;
    /**Contain player list page.*/
    private PlayerListPage playersListPage;
    /**Contain new player page.*/
    private NewPlayerPage newPlayerPage;
    /**Before class.*/
    @BeforeClass
    public final void setUp() {
        WebDriverUtils.load(TestsConstants.HOME_PAGE);
        homePage = new HomePage();
        playersListPage = homePage.getNavigationPage().getPlayersPage();
        specification = Specification.get();
    }
    /**Test class.*/
    @Test
    public final void newPlayerPageUI() {
        newPlayerPage = playersListPage.openCreatePlayerPage();
        specification.For(newPlayerPage.getData().getTitleLabel())
                .valueMatch(TestsConstants.ADD_NEW_PLAYER_LABEL).isVisible()
                .next()
        .For(newPlayerPage.getData().getFirstNameLabel()).isVisible()
                .valueMatch(TestsConstants.PLAYER_NAME_LABEL).next()
        .For(newPlayerPage.getData().getFirstNameInput()).isVisible().isEmpty()
                .next()
        .For(newPlayerPage.getData().getSecondNameLabel()).isVisible()
                .valueMatch(TestsConstants.PLAYER_SECOND_NAME_LABEL).next()
        .For(newPlayerPage.getData().getSecondNameInput()).isVisible().isEmpty()
                .next()
        .For(newPlayerPage.getData().getBirthYearLabel()).isVisible()
                .valueMatch(TestsConstants.PLAYER_BIRTH_YEAR_LABEL).next()
        .For(newPlayerPage.getData().getBirthYearInput()).isVisible().isEmpty()
                .next()
        .For(newPlayerPage.getData().getHeightLabel()).isVisible()
                .valueMatch(TestsConstants.PLAYER_HEIGHT_LABEL).next()
        .For(newPlayerPage.getData().getHeightInput()).isVisible().isEmpty()
                .next()
        .For(newPlayerPage.getData().getWeightLabel()).isVisible()
                .valueMatch(TestsConstants.PLAYER_WEIGHT_LABEL).next()
        .For(newPlayerPage.getData().getWeightInput()).isVisible().isEmpty()
                .next()
        .For(newPlayerPage.getCancelButton()).isVisible()
                .textMatch(TestsConstants.CANCEL_BUTTON_LABEL).next()
        .For(newPlayerPage.getSaveButton()).isVisible()
                .textMatch(TestsConstants.SAVE_BUTTON_LABEL).next();
    }
    /**After method.*/
    @AfterClass
    public final void tearDown() {
    }

}
