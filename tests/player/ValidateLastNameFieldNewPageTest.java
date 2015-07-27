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
import com.softserveinc.ita.volleymanagementtests.tests.TestsConstants;
import com.softserveinc.ita.volleymanagementtests.tools.WebDriverUtils;
/**
 * Functionality test for validate last name in new player page.
 * @author Dmytro Maslov
 *
 */
public class ValidateLastNameFieldNewPageTest {
    /**Variable for specification.*/
    private Specification specification;
    /**Contain home page.*/
    private HomePage homePage;
    /**Contain new player page.*/
    private NewPlayerPage newPlayerPage;
    /**Contain invalid player.*/
    private Player newPlayer;
    /**Player repository.*/
    private PlayerRepository repository;
    /**Count player in DB.*/
    private int playerInDB;
    /**Players collection.*/
    private List<Player> newPlayerList;
    /**Save all player from DB.*/
    private List<Player> playerInBD;
    /**Before method.*/
    @BeforeClass
    public final void setUp() {
        DALTools.startConnection(TestsConstants.CONNECT_DB_STRING);
        playerInBD = SetPreconditions.forValidationPlayerFildsTest();
        playerInDB = PlayerRepository.countAllPlayers();
        specification = Specification.get();
        WebDriverUtils.load(TestsConstants.HOME_PAGE);
        homePage = new HomePage();

    }
    /**Last name empty.*/
    @Test
    public final void emptyLastName() {
        newPlayerPage = homePage.getNavigationPage().getPlayersPage()
                .openCreatePlayerPage();
        newPlayer = PlayerTestObjects.getInvalidEmptySecondNamePlayer();
        newPlayerPage.getData().setAllFields(newPlayer);
        newPlayerPage = newPlayerPage.unsuccessfulPressSave();
        specification
                .For(newPlayerPage.getData().getErrorMessageLabelLastName())
                .isPresentFor(newPlayer.getLastName())
                .valueMatch(
                        TestsConstants.VALIDATION_ERROR_TEXT_EMPTY_NAME_FIELD)
                .next();
        specification.For(repository).numberOfPlayersHasNotChanged(playerInDB)
                .next();
        specification.check();
    }
    /**Don't correct type of last name.*/
    @Test
    public final void dontCorrectType() {
        newPlayerPage = homePage.getNavigationPage().getPlayersPage()
                .openCreatePlayerPage();
        newPlayerList = PlayerTestObjects.getInvalidTypeSecondNamePlayers();
        for (Player player : newPlayerList) {
            newPlayerPage.getData().setAllFields(player);
            newPlayerPage = newPlayerPage.unsuccessfulPressSave();
            specification
                    .For(newPlayerPage.getData().getErrorMessageLabelLastName())
                    .isPresentFor(player.getLastName()).isVisible()
                    .valueMatch(
                           TestsConstants.VALIDATION_ERROR_TEXT_WRONG_TYPE_NAME)
                    .next();
            specification.For(repository)
                    .numberOfPlayersHasNotChanged(playerInDB).next();

        }
        specification.check();
    }
    /**Don't correct size.*/
    @Test
    public final void dontCorrectSize() {
        newPlayerPage = homePage.getNavigationPage().getPlayersPage()
                .openCreatePlayerPage();
        newPlayer = PlayerTestObjects.getInvalidTooBigSecondNamePlayer();
        newPlayerPage.getData().setAllFields(newPlayer);
        specification
                .For(newPlayerPage.getData().getErrorMessageLabelLastName())
                .isPresentFor(newPlayer.getLastName())
                .valueMatch(TestsConstants.VALIDATION_ERROR_TEXT_TOO_BIG_NAME)
                .next();
        newPlayerPage = newPlayerPage.unsuccessfulPressSave();
        specification.For(repository).numberOfPlayersHasNotChanged(playerInDB)
                .next();
        specification.check();
    }
    /**Valid last name.*/
    @Test
    public final void validLastName() {
        newPlayerList = PlayerTestObjects.getValidSecondNamePlayers();
        for (Player player : newPlayerList) {
            newPlayerPage = homePage.getNavigationPage().getPlayersPage()
                    .openCreatePlayerPage();
            newPlayerPage.getData().setAllFields(player);
            newPlayerPage.pressSave();
            specification.For(repository)
                    .numberOfPlayersHasIncreased(playerInDB).next()
                    .For(repository).playerPresent(player).next();
            playerInDB = PlayerRepository.countAllPlayers();
        }
        specification.check();
    }
    /**After method.*/
    @AfterClass
    public final void tearDown() {
        // WebDriverUtils.stop();
        RollbackPreconditions.forValidationPlayerFildsTest(playerInBD);
        // DALTools.closeConnection();
        // System.out.println("Result of test-case execution:");
        // System.out.println(specification.getDescription());
    }

}
