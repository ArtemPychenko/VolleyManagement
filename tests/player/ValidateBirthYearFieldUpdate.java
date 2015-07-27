package com.softserveinc.ita.volleymanagementtests.tests.player;

import java.util.List;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.softserveinc.ita.volleymanagementtests.criterias.Specification;
import com.softserveinc.ita.volleymanagementtests.domain.models.Player;
import com.softserveinc.ita.volleymanagementtests.domain.repositories.DALTools;
import com.softserveinc.ita.volleymanagementtests.domain.repositories
        .PlayerRepository;
import com.softserveinc.ita.volleymanagementtests.domain.repositories
        .PlayerTestObjects;
import com.softserveinc.ita.volleymanagementtests.pages.EditPlayerPage;
import com.softserveinc.ita.volleymanagementtests.pages.PlayerDetailPage;
import com.softserveinc.ita.volleymanagementtests.pages.PlayerListPage;
import com.softserveinc.ita.volleymanagementtests.tests.RollbackPreconditions;
import com.softserveinc.ita.volleymanagementtests.tests.SetPreconditions;
import com.softserveinc.ita.volleymanagementtests.tests.TestsConstants;
import com.softserveinc.ita.volleymanagementtests.tools.WebDriverUtils;

/**
 * This class does validation birth year field through edit functionality.
 * It doesn't do validation location validation error message on the page.
 * @author Andriy Lantukh
 *
 */
public class ValidateBirthYearFieldUpdate {
    /**
     * an instance of the page with the list of players.
     */
    private PlayerListPage playersListPage;
    /**
     * an instance of the page with the player's details.
     */
    private PlayerDetailPage playerDetailPage;
    /**
     * an instance of the player's edit page.
     */
    private EditPlayerPage editPlayerPage;
    /**
     *  database access.
     */
    private PlayerRepository repository;
    /**
     *  an specification for check.
     */
    private Specification specification;
    /**
     * valid player - player with valid data.
     */
    private Player validPlayer;
    /**
     * playersData will save the players in set up method in his current state
     * to restore them after test will complete in tear down method.
     */
    private List<Player> playersData;
    /**.
     * playersQuantityBeforeDelete holds quantity of players before
     * player creation for checking the quantity after test operations.
     */
    private int playersInDBBeforCreation;
    /**
     * This method performs the operations necessary to run the test.
     */
    @BeforeClass
    public final void setUp() {
        DALTools.startConnection(TestsConstants.CONNECT_DB_STRING);
        playersData = SetPreconditions.forValidationPlayerFildsTest();
        WebDriverUtils.load(TestsConstants.HOME_PAGE);
        specification = Specification.get();
        validPlayer = PlayerTestObjects.getValidPlayer();
    }
    /**
     * The player shouldn't be save in the database for invalid player.
     * The validation messages should be shown as it is wrong type entered.
     * 
     * If valid player are entered.
     * The player should be saved in the database.
     */   
    @Test
    public final void validateBirthYearField() {
        
     // Validation field with invalid data (out of range). 
        final List<Player> invalidPlayers 
                = PlayerTestObjects.getInvalidYearPlayers();
        for (Player player : invalidPlayers) {
            if (!PlayerRepository.isPlayerPresent(validPlayer)) {
                PlayerRepository.insertPlayer(validPlayer);
            }
            playersListPage = PlayerListPage.refreshPlayerList();
            playerDetailPage = playersListPage
                    .showPlayerInfo(validPlayer);
            editPlayerPage = playerDetailPage.pressEditButton();
            editPlayerPage.getData().setAllFields(player);
            editPlayerPage.unsuccessfulPressSave();
            
            specification
            .For(editPlayerPage.getData().getErrorMessageBirthYear())
            .valueMatch(TestsConstants
                    .VALIDATION_ERROR_TEXT_WRONG_YEAR)
            .next()
            .For(repository)
            .playerNotPresent(player)
            .playerPresent(validPlayer);
            
            if (PlayerRepository.isPlayerPresent(player)) {
                PlayerRepository.deletePlayerByLastName(player.getLastName());
            }
        }
                   
     // Validation birth year field with invalid type data. 
        final List<String> invalidYearList 
        = PlayerTestObjects.getInValidType();
        for (String invalidYear : invalidYearList) {
            playersInDBBeforCreation = PlayerRepository.countAllPlayers();
            if (!PlayerRepository.isPlayerPresent(validPlayer)) {
                PlayerRepository.insertPlayer(validPlayer);
            }
            playersListPage = PlayerListPage.refreshPlayerList();
            playerDetailPage = playersListPage
                    .showPlayerInfo(validPlayer);
            editPlayerPage = playerDetailPage.pressEditButton();
            editPlayerPage.getData().setAllFields(validPlayer);
            editPlayerPage.getData().getBirthYearInput().clear();
            editPlayerPage.getData().getBirthYearInput().type(invalidYear);
            editPlayerPage.unsuccessfulPressSave();
            
            specification
            .For(editPlayerPage.getData().getErrorMessageBirthYear())
            .valueMatch(TestsConstants
                    .VALIDATION_ERROR_TEXT_WRONG_TYPE_NUMBER)
            .next()
            .For(repository)
            .numberOfPlayersHasNotChanged(playersInDBBeforCreation)
            .playerPresent(validPlayer);
        }
  
        // Validation birth year field with valid value.   
        final List<Player> validPlayers 
        = PlayerTestObjects.getValidYearPlayers();
        for (Player player : validPlayers) {
            if (!PlayerRepository.isPlayerPresent(validPlayer)) {
                PlayerRepository.insertPlayer(validPlayer);
            }
            playersListPage = PlayerListPage.refreshPlayerList();
            playerDetailPage = playersListPage
                    .showPlayerInfo(validPlayer);
            editPlayerPage = playerDetailPage.pressEditButton();
            editPlayerPage.getData().setAllFields(player);
            editPlayerPage.pressSave();
            
            specification
            .For(repository)
            .playerPresent(player)
            .playerNotPresent(validPlayer);
            
            if (PlayerRepository.isPlayerPresent(player)) {
                PlayerRepository.deletePlayerByLastName(player.getLastName());
            }
        }
        
        specification.check();
    }
    /**
     * This method performs the operations necessary after the test execution.
     */
    @AfterClass
    public final void tearDown() {
        RollbackPreconditions.forValidationPlayerFildsTest(playersData);
        //WebDriverUtils.stop();
        //System.out.println("Result of test-case execution:");
    }
}
