package com.softserveinc.ita.volleymanagementtests.tests.player;

import java.util.List;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.softserveinc.ita.volleymanagementtests.criterias.Specification;
import com.softserveinc.ita.volleymanagementtests.domain.models.Player;
import com.softserveinc.ita.volleymanagementtests.domain.repositories.DALTools;
import com.softserveinc.ita.volleymanagementtests.domain.repositories.PlayerRepository;
import com.softserveinc.ita.volleymanagementtests.domain.repositories.PlayerTestObjects;
import com.softserveinc.ita.volleymanagementtests.pages.EditPlayerPage;
import com.softserveinc.ita.volleymanagementtests.pages.HomePage;
import com.softserveinc.ita.volleymanagementtests.pages.PlayerDetailPage;
import com.softserveinc.ita.volleymanagementtests.tests.RollbackPreconditions;
import com.softserveinc.ita.volleymanagementtests.tests.SetPreconditions;
import com.softserveinc.ita.volleymanagementtests.tests.TestsConstants;
import com.softserveinc.ita.volleymanagementtests.tools.WebDriverUtils;
/**
 * @author Aleksandr Zaitsev
 * Test verifies the player's height input of the edit player page.
 */
public class ValidateHeightFieldEdit {
    /**
     * Specification object for soft-assert report create.
     */
    private Specification specification;
    /**
     * an instance of the main page of the web site.
     */
    private HomePage homePage;
    /**
     * an instance of the page with the player's details.
     */
    private PlayerDetailPage playerDetailPage;
    /**
     * an instance of the player's edit page.
     */
    private EditPlayerPage editPlayerPage;
    /**
     * the list of invalid type of player's height (string).
     */
    private List<String> invalidHeightAsStrings;
    /**
     * valid player - player with valid data.
     */
    private Player validPlayer;
    /**
     * the list of players with invalid height (numeric).
     */
    private List<Player> invalidHeightPlayers;
    /**
     * the list of players with valid height (numeric).
     */
    private List<Player> validHeightPlayers;
    /**
     * Existing player database.
     */
    private PlayerRepository repository;
    /**.
     * playersQuantityBeforeDelete holds quantity of players before
     * player creation for checking the quantity after test operations.
     */
    private int playersInDBBeforCreation;
    /**
     * playersData will save the players in set up method in his current state
     * to restore them after test will complete in tear down method.
     */
    private List<Player> playersData;
    /**
     * This method performs the operations necessary to run the test.
     */
    @BeforeClass
    public final void setUp() {
        WebDriverUtils.load(TestsConstants.HOME_PAGE);
        DALTools.startConnection(TestsConstants.CONNECT_DB_STRING);
        playersData = SetPreconditions.forValidationPlayerFildsTest();
        homePage = new HomePage();
        specification = Specification.get();
        validPlayer = PlayerTestObjects.getValidPlayer();
        PlayerRepository.insertPlayer(validPlayer);
        invalidHeightAsStrings = PlayerTestObjects.getInValidType();
        invalidHeightPlayers = PlayerTestObjects.getInvalidHeightPlayers();
        validHeightPlayers = PlayerTestObjects.getValidHeightPlayers();
        playersInDBBeforCreation = PlayerRepository.countAllPlayers();
    }
    /**
     * This method performs the operations necessary after the test execution.
     */
    @AfterClass
    public final void tearDown() {
        RollbackPreconditions.forValidationPlayerFildsTest(playersData);
    }
    /**
     * The player shouldn't be save in the database.
     * The validation messages should be shown as it is wrong type entered
     * (need numeric - entered strings)
     */
    @Test
    public final void heightFieldStringdNegativeTest() {
        editPlayerPage = homePage.getNavigationPage().getPlayersPage()
                .showPlayerInfo(validPlayer).pressEditButton();
            for (String invalidHeight : invalidHeightAsStrings) {
                editPlayerPage.getData().getHeightInput().clear();
                editPlayerPage.getData().getHeightInput().type(invalidHeight);
                editPlayerPage.unsuccessfulPressSave();

                specification
                .For(editPlayerPage.getData().getErrorMessageLabelHeight())
                .valueMatch(TestsConstants
                        .VALIDATION_ERROR_TEXT_WRONG_TYPE_NUMBER)
                .next()
                .For(repository).numberOfPlayersHasNotChanged(
                        playersInDBBeforCreation)
                .playerPresent(validPlayer);
            }
            specification.check();
    }
    /**
     * The player shouldn't be save in the database.
     * The validation messages should be shown as it is wrong type of the
     * numbers are entered (not between 10-250).
     */
    @Test
    public final void heightFieldNumbNegativeTest() {
        editPlayerPage = homePage.getNavigationPage().getPlayersPage()
                .showPlayerInfo(validPlayer).pressEditButton();
            for (Player player : invalidHeightPlayers) {
                editPlayerPage.getData().getHeightInput().clear();
                editPlayerPage.getData().setHeight(player);
                editPlayerPage.unsuccessfulPressSave();

                specification
                .For(editPlayerPage.getData().getErrorMessageLabelHeight())
                .valueMatch(TestsConstants.
                        VALIDATION_ERROR_TEXT_WRONG_LENGTH_HEIGHT)
                .next()
                .For(repository).numberOfPlayersHasNotChanged(
                        playersInDBBeforCreation)
                .playerNotPresent(player)
                .playerPresent(validPlayer);
            }
            specification.check();
    }
    /**
     * Valid height are entered.
     * The player should be saved in the database.
     */
    @Test(dependsOnMethods = {"heightFieldStringdNegativeTest",
            "heightFieldNumbNegativeTest"}, alwaysRun = true)
    public final void heightFieldPositiveTest() {
        editPlayerPage = homePage.getNavigationPage().getPlayersPage()
                .showPlayerInfo(validPlayer).pressEditButton();
        for (Player player : validHeightPlayers) {
            editPlayerPage.getData().getHeightInput().clear();
            editPlayerPage.getData().setHeight(player);
            editPlayerPage.pressSave();
            playerDetailPage = homePage.getNavigationPage().getPlayersPage()
                    .showPlayerInfo();
            editPlayerPage = playerDetailPage.pressEditButton();

            specification
            .For(repository).numberOfPlayersHasNotChanged(
                    playersInDBBeforCreation)
            .playerPresent(player)
            .playerNotPresent(validPlayer);
        }
        specification.check();
    }
}
