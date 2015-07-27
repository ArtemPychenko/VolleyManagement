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
 * Test verifies the player's weight input of the edit player page.
 */
public class ValidateWeightFieldEdit {
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
     * the list of invalid type of player's weight (string).
     */
    private List<String> invalidWeightAsStrings;
    /**
     * valid player - player with valid data.
     */
    private Player validPlayer;
    /**
     * the list of players with invalid weight (numeric).
     */
    private List<Player> invalidWeightPlayers;
    /**
     * the list of players with valid weight (numeric).
     */
    private List<Player> validWeightPlayers;
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
        invalidWeightAsStrings = PlayerTestObjects.getInValidType();
        invalidWeightPlayers = PlayerTestObjects.getInvalidWeightPlayers();
        validWeightPlayers = PlayerTestObjects.getValidWeightPlayers();
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
            for (String invalidWeight : invalidWeightAsStrings) {
                editPlayerPage.getData().getWeightInput().clear();
                editPlayerPage.getData().getWeightInput().type(invalidWeight);
                editPlayerPage.unsuccessfulPressSave();

                specification
                .For(editPlayerPage.getData().getErrorMessageLabelWeight())
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
     * numbers are entered (not between 10-500).
     */
    @Test
    public final void heightFieldNumbNegativeTest() {
        editPlayerPage = homePage.getNavigationPage().getPlayersPage()
                .showPlayerInfo(validPlayer).pressEditButton();
            for (Player player : invalidWeightPlayers) {
                editPlayerPage.getData().getWeightInput().clear();
                editPlayerPage.getData().setWeight(player);
                editPlayerPage.unsuccessfulPressSave();

                specification
                .For(editPlayerPage.getData().getErrorMessageLabelWeight())
                .valueMatch(TestsConstants
                        .VALIDATION_ERROR_TEXT_WRONG_LENGTH_WEIGHT)
                .next()
                .For(repository).numberOfPlayersHasNotChanged(
                        playersInDBBeforCreation)
                .playerNotPresent(player)
                .playerPresent(validPlayer);
            }
            specification.check();
    }
    /**
     * Valid weight are entered.
     * The player should be saved in the database.
     */
    @Test(dependsOnMethods = {"heightFieldNumbNegativeTest",
            "heightFieldStringdNegativeTest"}, alwaysRun = true)
    public final void heightFieldPositiveTest() {
        editPlayerPage = homePage.getNavigationPage().getPlayersPage()
                .showPlayerInfo(validPlayer).pressEditButton();
        for (Player player : validWeightPlayers) {
            editPlayerPage.getData().getWeightInput().clear();
            editPlayerPage.getData().setWeight(player);
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
