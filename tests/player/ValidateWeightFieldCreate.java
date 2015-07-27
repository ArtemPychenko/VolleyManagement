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
import com.softserveinc.ita.volleymanagementtests.pages.HomePage;
import com.softserveinc.ita.volleymanagementtests.pages.NewPlayerPage;
import com.softserveinc.ita.volleymanagementtests.tests.RollbackPreconditions;
import com.softserveinc.ita.volleymanagementtests.tests.SetPreconditions;
import com.softserveinc.ita.volleymanagementtests.pages.PlayerListPage;
import com.softserveinc.ita.volleymanagementtests.tests.TestsConstants;
import com.softserveinc.ita.volleymanagementtests.tools.WebDriverUtils;
/**
 * @author Aleksandr Zaitsev
 * Test verifies the player's weight input of the create player page.
 */
public class ValidateWeightFieldCreate {
    /**
     * Specification object for soft-assert report create.
     */
    private Specification specification;
    /**
     * an instance of the main page of the web site.
     */
    private HomePage homePage;
    /**
     * an instance of the page with the list of players.
     */
    private PlayerListPage playersListPage;
    /**
     * an instance of the page of new player creating.
     */
    private NewPlayerPage newPlayerPage;
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
        playersListPage = homePage.getNavigationPage().getPlayersPage();
        newPlayerPage = playersListPage.openCreatePlayerPage();
        validPlayer = PlayerTestObjects.getValidPlayer();
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
    public final void weightFieldStringdNegativeTest() {
        newPlayerPage = homePage.getNavigationPage().getPlayersPage()
                .openCreatePlayerPage();
            for (String invalidWeight : invalidWeightAsStrings) {
                newPlayerPage.getData().setAllFields(validPlayer);
                newPlayerPage.getData().getWeightInput().clear();
                newPlayerPage.getData().getWeightInput().type(invalidWeight);
                newPlayerPage.unsuccessfulPressSave();

                specification
                .For(newPlayerPage.getData().getErrorMessageLabelWeight())
                .valueMatch(TestsConstants
                        .VALIDATION_ERROR_TEXT_WRONG_TYPE_NUMBER)
                .next()
                .For(repository).numberOfPlayersHasNotChanged(
                        playersInDBBeforCreation)
                .playerNotPresent(validPlayer);

                if (PlayerRepository.isPlayerPresent(validPlayer)) {
                    PlayerRepository.deletePlayerByLastName(
                            validPlayer.getLastName());
                }
            }
            specification.check();
    }
    /**
     * The player shouldn't be save in the database.
     * The validation messages should be shown as it is wrong type of the
     * numbers are entered (not between 10-500).
     */
    @Test
    public final void weightFieldNumbNegativeTest() {
        newPlayerPage = homePage.getNavigationPage().getPlayersPage()
                .openCreatePlayerPage();
            for (Player player : invalidWeightPlayers) {
                newPlayerPage.getData().setAllFields(player);
                newPlayerPage.unsuccessfulPressSave();

                specification
                .For(newPlayerPage.getData().getErrorMessageLabelWeight())
                .valueMatch(TestsConstants
                        .VALIDATION_ERROR_TEXT_WRONG_LENGTH_WEIGHT)
                .next()
                .For(repository).numberOfPlayersHasNotChanged(
                        playersInDBBeforCreation)
                .playerNotPresent(player);

                if (PlayerRepository.isPlayerPresent(player)) {
                    PlayerRepository.deletePlayerByLastName(
                            player.getLastName());
                }
            }
            specification.check();
    }
    /**
     * Valid weight are entered.
     * The player should be saved in the database.
     */
    @Test(dependsOnMethods = { "weightFieldStringdNegativeTest",
    "weightFieldNumbNegativeTest" }, alwaysRun = true)
    public final void weightFieldPositiveTest() {
        for (Player player : validWeightPlayers) {
            newPlayerPage.getData().setAllFields(player);
            newPlayerPage.pressSave();
            newPlayerPage = homePage.getNavigationPage().getPlayersPage()
                    .openCreatePlayerPage();

            specification.For(repository).numberOfPlayersHasIncreased(
                    playersInDBBeforCreation)
            .playerPresent(player);

            if (PlayerRepository.isPlayerPresent(player)) {
                PlayerRepository.deletePlayerByLastName(
                        player.getLastName());
            }
        }
        specification.check();
    }
}
