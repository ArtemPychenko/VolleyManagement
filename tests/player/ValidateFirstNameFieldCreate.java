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
import com.softserveinc.ita.volleymanagementtests.pages.HomePage;
import com.softserveinc.ita.volleymanagementtests.pages.NewPlayerPage;
import com.softserveinc.ita.volleymanagementtests.pages.PlayerListPage;
import com.softserveinc.ita.volleymanagementtests.tests.RollbackPreconditions;
import com.softserveinc.ita.volleymanagementtests.tests.SetPreconditions;
import com.softserveinc.ita.volleymanagementtests.tests.TestsConstants;
import com.softserveinc.ita.volleymanagementtests.tools.WebDriverUtils;

/**
 * This class does validation first name field through new functionality.
 * It doesn't do validation location validation error message on the page.
 * @author Andriy Lantukh
 *
 */
public class ValidateFirstNameFieldCreate {
    /**
     * an instance of the main page of the web site.
     */
    private HomePage homePage;
    /**
     * an instance of the page of new player creating.
     */
    private NewPlayerPage newPlayerPage;
    /**
     * an instance of the page with the list of players.
     */
    private PlayerListPage playersListPage;
    /**
     *  database access.
     */
    private PlayerRepository repository;
    /**
     *  an specification for check.
     */
    private Specification specification;
    /**
     * playersData will save the players in set up method in his current state
     * to restore them after test will complete in tear down method.
     */
    private List<Player> playersData;
    /**
     * player - player object.
     */
    private Player player;
    
    /**
     * This method performs the operations necessary to run the test.
     */
    @BeforeClass
    public final void setUp() {
        DALTools.startConnection(TestsConstants.CONNECT_DB_STRING);
        playersData = SetPreconditions.forValidationPlayerFildsTest();
        WebDriverUtils.load(TestsConstants.HOME_PAGE);
        homePage = new HomePage();
        specification = Specification.get();
    }
    /**
     * The player shouldn't be save in the database for invalid player.
     * The validation messages should be shown as it is wrong type entered.
     * 
     * If valid player are entered.
     * The player should be saved in the database.
     */   
    @Test
    public final void validateFirstNameField() {
            
        // Validation field with invalid data type. 
        final List<Player> invalidPlayers 
            = PlayerTestObjects.getInvalidTypeFirstNamePlayers();
        playersListPage = homePage.getNavigationPage().getPlayersPage();
        for (Player invalidPlayer : invalidPlayers) {
            newPlayerPage = playersListPage.openCreatePlayerPage();
            newPlayerPage.getData().setAllFields(invalidPlayer);
            newPlayerPage.unsuccessfulPressSave();
            
            specification
            .For(repository)
            .playerNotPresent(invalidPlayer)
            .next()
            .For(newPlayerPage.getData().getErrorMessageLabelFirstName())
            .valueMatch(TestsConstants
                    .VALIDATION_ERROR_TEXT_WRONG_TYPE_NAME);
            
            if (PlayerRepository.isPlayerPresent(invalidPlayer)) {
                PlayerRepository.deletePlayerByLastName(invalidPlayer
                        .getLastName());
            }
            playersListPage = PlayerListPage.refreshPlayerList();
        }
            
        // Validation field with empty field. 
        player = PlayerTestObjects.getInvalidEmptyFirstNamePlayer();
        newPlayerPage = playersListPage.openCreatePlayerPage();
        newPlayerPage.getData().setAllFields(player);
        newPlayerPage.unsuccessfulPressSave();
        specification
        .For(newPlayerPage.getData().getErrorMessageLabelFirstName())
        .valueMatch(TestsConstants
                .VALIDATION_ERROR_TEXT_EMPTY_NAME_FIELD)
         .next()
         .For(repository)
         .playerNotPresent(player);
          
        if (PlayerRepository.isPlayerPresent(player)) {
            PlayerRepository.deletePlayerByLastName(player.getLastName());
        }
        playersListPage = PlayerListPage.refreshPlayerList();
            
        // Validation field with more than 60 symbols field. 
        player = PlayerTestObjects.getInvalidTooBigFirstNamePlayer();
        newPlayerPage = playersListPage.openCreatePlayerPage();
        newPlayerPage.getData().setAllFields(player);
        newPlayerPage.unsuccessfulPressSave();
        
        specification
        .For(newPlayerPage.getData().getErrorMessageLabelFirstName())
        .valueMatch(TestsConstants.VALIDATION_ERROR_TEXT_TOO_BIG_NAME)
        .next()
        .For(repository)
        .playerNotPresent(player);
 
        if (PlayerRepository.isPlayerPresent(player)) {
            PlayerRepository.deletePlayerByLastName(player.getLastName());
        }
        playersListPage = PlayerListPage.refreshPlayerList();
            
        // Validation field with valid value.   
        final List<Player> validPlayers 
        = PlayerTestObjects.getValidFirstNamePlayers();
        for (Player validPlayer : validPlayers) {
            newPlayerPage = playersListPage.openCreatePlayerPage();
            newPlayerPage.getData().setAllFields(validPlayer);
            newPlayerPage.pressSave();
            
            specification
            .For(repository)
            .playerPresent(validPlayer);
            
            if (PlayerRepository.isPlayerPresent(validPlayer)) {
                PlayerRepository.deletePlayerByLastName(validPlayer
                        .getLastName());
            }
            playersListPage = PlayerListPage.refreshPlayerList();
        }
        specification.check();
    }
    /**
     * This method performs the operations necessary after the test execution.
     */
    @AfterClass
    public final void tearDown() {
        RollbackPreconditions.forValidationPlayerFildsTest(playersData);
       // WebDriverUtils.stop();
        //System.out.println("Result of test-case execution:");
    }
}


