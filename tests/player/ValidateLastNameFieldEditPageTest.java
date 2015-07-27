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
import com.softserveinc.ita.volleymanagementtests.tests.RollbackPreconditions;
import com.softserveinc.ita.volleymanagementtests.tests.SetPreconditions;
import com.softserveinc.ita.volleymanagementtests.tests.TestsConstants;
import com.softserveinc.ita.volleymanagementtests.tools.WebDriverUtils;
/**
 * Functionality test for validate last name in edit player page.
 * Check error message and saving in DB
 * @author Dmytro Maslov
 *
 */
public class ValidateLastNameFieldEditPageTest {
    /**Variable for specification.*/
    private Specification specification;
    /**Contain home page.*/
    private HomePage homePage;
    /**Contain edit player page.*/
    private EditPlayerPage editPlayerPage;
    /**Contain invalid player.*/
    private Player newPlayer;
    /**Player repository.*/
    private PlayerRepository repository;
    /**Count player in DB.*/
    private int playerInDB;
    /**Players collection.*/
    private List<Player> newPlayerList;
    /**Expect player.*/
    private Player expectedPlayer;
    /**Actual player.*/
    private Player actualPlayer;
    /**Save all player from DB.*/
    private List<Player> playerInBD;
    /**Constant number for first player.*/
    private static final int FIRST_PLAYER = 0;
    /**Before method.*/
    @BeforeClass
    public final void setUp() {
        DALTools.startConnection(TestsConstants.CONNECT_DB_STRING);
        playerInBD = SetPreconditions.forValidationPlayerFildsTest();
        expectedPlayer = PlayerTestObjects.getValidPlayer();
        PlayerRepository.insertPlayer(expectedPlayer);
        playerInDB = PlayerRepository.countAllPlayers();
        specification = Specification.get();
        WebDriverUtils.load(TestsConstants.HOME_PAGE);
        homePage = new HomePage();

    }
    /**Last name empty.*/
    @Test
    public final void emptyLastName() {
        editPlayerPage = homePage.getNavigationPage().getPlayersPage()
                .showPlayerInfo().pressEditButton();
        newPlayer = PlayerTestObjects.getInvalidEmptySecondNamePlayer();
        editPlayerPage.getData().setSecondName(newPlayer);
        editPlayerPage = editPlayerPage.unsuccessfulPressSave();
        specification
                .For(editPlayerPage.getData().getErrorMessageLabelLastName())
                .isPresentFor("empty")
                .valueMatch(
                        TestsConstants.VALIDATION_ERROR_TEXT_EMPTY_NAME_FIELD)
                .next();
        actualPlayer = PlayerRepository
                .getPlayersByFirstName(expectedPlayer.getFirstName())
                .get(FIRST_PLAYER);
        specification.For(repository).numberOfPlayersHasNotChanged(playerInDB)
                .next();
        specification.For(actualPlayer).playerMatch(expectedPlayer);
        specification.check();
    }
    /**Don't correct type of last name.*/
    @Test
    public final void dontCorrectType() {
        editPlayerPage = homePage.getNavigationPage().getPlayersPage()
                .showPlayerInfo().pressEditButton();
        newPlayerList = PlayerTestObjects.getInvalidTypeSecondNamePlayers();
        for (Player player : newPlayerList) {
            editPlayerPage.getData().setSecondName(player);
            editPlayerPage = editPlayerPage.unsuccessfulPressSave();
            specification
                    .For(editPlayerPage.getData()
                            .getErrorMessageLabelLastName())
                    .isPresentFor(player.getLastName()).isVisible()
                    .valueMatch(
                           TestsConstants.VALIDATION_ERROR_TEXT_WRONG_TYPE_NAME)
                    .next();
            actualPlayer = PlayerRepository
                    .getPlayersByFirstName(expectedPlayer.getFirstName())
                    .get(FIRST_PLAYER);
            specification.For(repository)
                    .numberOfPlayersHasNotChanged(playerInDB).next();
            specification.For(actualPlayer).playerMatch(expectedPlayer);
        }
        specification.check();
    }
    /**Don't correct size.*/
    @Test
    public final void dontCorrectSize() {
        editPlayerPage = homePage.getNavigationPage().getPlayersPage()
                .showPlayerInfo().pressEditButton();
        newPlayer = PlayerTestObjects.getInvalidTooBigSecondNamePlayer();
        editPlayerPage.getData().setSecondName(newPlayer);
        editPlayerPage = editPlayerPage.unsuccessfulPressSave();
        specification
                .For(editPlayerPage.getData().getErrorMessageLabelLastName())
                .isPresentFor(newPlayer.getLastName())
                .valueMatch(TestsConstants.VALIDATION_ERROR_TEXT_TOO_BIG_NAME)
                .next();
        specification.For(repository).numberOfPlayersHasNotChanged(playerInDB)
                .next();
        actualPlayer = PlayerRepository
                .getPlayersByFirstName(expectedPlayer.getFirstName())
                .get(FIRST_PLAYER);
        specification.For(actualPlayer).playerMatch(expectedPlayer);
        specification.check();
    }
    /**Valid last name.*/
    @Test
    public final void validLastName() {
        newPlayerList = PlayerTestObjects.getValidSecondNamePlayers();
        for (Player player : newPlayerList) {
            editPlayerPage = homePage.getNavigationPage().getPlayersPage()
                    .showPlayerInfo().pressEditButton();
            editPlayerPage.getData().setSecondName(player);
            editPlayerPage.pressSave();
            actualPlayer = PlayerRepository
                    .getPlayersByFirstName(expectedPlayer.getFirstName())
                    .get(FIRST_PLAYER);
            expectedPlayer.setLastName(player.getLastName());
            specification.For(actualPlayer).playerMatch(expectedPlayer);
            specification.For(repository)
                    .numberOfPlayersHasNotChanged(playerInDB).next();
        }
        specification.check();
    }
    /**After method.*/
    @AfterClass
    public final void tearDown() {
        RollbackPreconditions.forValidationPlayerFildsTest(playerInBD);
    }

}
