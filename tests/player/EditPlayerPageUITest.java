package com.softserveinc.ita.volleymanagementtests.tests.player;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.softserveinc.ita.volleymanagementtests.criterias.Specification;
import com.softserveinc.ita.volleymanagementtests.pages.EditPlayerPage;
import com.softserveinc.ita.volleymanagementtests.pages.HomePage;
import com.softserveinc.ita.volleymanagementtests.pages.PlayerDetailPage;
import com.softserveinc.ita.volleymanagementtests.pages.PlayerListPage;
import com.softserveinc.ita.volleymanagementtests.tests.TestsConstants;
import com.softserveinc.ita.volleymanagementtests.tools.WebDriverUtils;

/** UI test for edit player page.
 * @author Dmytro Maslov */
public class EditPlayerPageUITest {
    /**Variable for specification.*/
    private Specification specification;
    /**Variable contain home page.*/
    private HomePage homePage;
    /**Contain player list page.*/
    private PlayerListPage playersListPage;
    /**Contain detail player page.*/
    private PlayerDetailPage detailPlayerPage;
    /**Contain edit player page.*/
    private EditPlayerPage editPlayerPage;
    /**Prepare test data.*/
    @BeforeClass
    public final void setUp() {
        WebDriverUtils.load(TestsConstants.HOME_PAGE);
        homePage = new HomePage();
        playersListPage = homePage.getNavigationPage().getPlayersPage();
        detailPlayerPage = playersListPage.showPlayerInfo();
        editPlayerPage = detailPlayerPage.pressEditButton();
        specification = Specification.get();
    }
    /**Test method.*/
    @Test
    public final void editPlayerPageUI() {
        specification.For(editPlayerPage.getData().getTitleLabel())
                .valueMatch(TestsConstants.EDIT_PLAYER_PAGE_LABEL).isVisible()
                .next()
        .For(editPlayerPage.getData().getFirstNameLabel()).isVisible()
                .valueMatch(TestsConstants.PLAYER_NAME_LABEL).next()
        .For(editPlayerPage.getData().getSecondNameLabel()).isVisible()
                .valueMatch(TestsConstants.PLAYER_SECOND_NAME_LABEL).next()
        .For(editPlayerPage.getData().getBirthYearLabel()).isVisible()
                .valueMatch(TestsConstants.PLAYER_BIRTH_YEAR_LABEL).next()
        .For(editPlayerPage.getData().getHeightLabel()).isVisible()
                .valueMatch(TestsConstants.PLAYER_HEIGHT_LABEL).next()
        .For(editPlayerPage.getData().getWeightLabel()).isVisible()
                .valueMatch(TestsConstants.PLAYER_WEIGHT_LABEL).next()
        .For(editPlayerPage.getCancelButton()).isVisible()
                .textMatch(TestsConstants.CANCEL_BUTTON_LABEL).next()
        .For(editPlayerPage.getSaveButton()).isVisible()
                .textMatch(TestsConstants.SAVE_BUTTON_LABEL).next();

    }
    /**after method.*/
    @AfterClass
    public final void tearDown() {
    }

}
