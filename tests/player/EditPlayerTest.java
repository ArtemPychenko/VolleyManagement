package com.softserveinc.ita.volleymanagementtests.tests.player;

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
import com.softserveinc.ita.volleymanagementtests.pages.HomePage;
import com.softserveinc.ita.volleymanagementtests.pages.PlayerDetailPage;
import com.softserveinc.ita.volleymanagementtests.tests.TestsConstants;
import com.softserveinc.ita.volleymanagementtests.tools.WebDriverUtils;

/**
 * @author Alla Prykhodchenko This test-case for checking edit player
 *         functionality of the VolleyManagement application.
 *
 */
public class EditPlayerTest {
    /**
     * Main page object of testing application.
     */
    private HomePage homePage;
    /**
     * Edit player page.
     */
    private EditPlayerPage editPlayerPage;
    /**
     * Page, which shown player info.
     */
    private PlayerDetailPage playerInfo;
    /**
     * Valid player object.
     */
    private Player player;
    /**
     * Player object for edition.
     */
    private Player playerForEdition;
    /**
     * Number of players with valid player data before test execution.
     */
    private int prevNumberOfPlayer;
    /**
     * Number of players in database before test execution.
     */
    private int prevcountAllPlayers;
    /**
     * Number of players with player for edition data before test execution.
     */
    private int prevNumberOfPlayerForEdition;
    /**
     * Existing player database.
     */
    private PlayerRepository repository;
    /**
     * Specification object for soft-assert report create.
     */
    private Specification specification;

    /**
     * This method performs the operations necessary to run the test.
     */
    @BeforeClass
    public final void setUp() {
        WebDriverUtils.load(TestsConstants.HOME_PAGE);
        homePage = new HomePage();
        specification = Specification.get();
        DALTools.startConnection(TestsConstants.CONNECT_DB_STRING);
        player = PlayerTestObjects.getValidPlayer();
        prevNumberOfPlayer = PlayerRepository.countPlayers(player);
        prevcountAllPlayers = PlayerRepository.countAllPlayers();
        playerInfo = homePage.getNavigationPage().getPlayersPage()
                .showPlayerInfo();
    }
    /**
     * This test for checking edit player functionality for player with
     * all player fields data.
     */
    @Test
    public final void editPlayer() {
        playerForEdition = playerInfo.getPlayer();
        prevNumberOfPlayerForEdition = PlayerRepository
                .countPlayers(playerForEdition);
        editPlayerPage = playerInfo.pressEditButton();
        editPlayerPage.getData().setFirstName(player);
        specification.For(editPlayerPage.getData().getFirstNameInput())
        .isContain(player.getFirstName())
        .next();
        editPlayerPage.getData().setSecondName(player);
        specification.For(editPlayerPage.getData().getSecondNameInput())
        .isContain(player.getLastName())
        .next();
        editPlayerPage.getData().setBirthYear(player);
        specification.For(editPlayerPage.getData().getBirthYearInput())
        .isContain(String.valueOf(player.getBirthYear()))
        .next();
        editPlayerPage.getData().setHeight(player);
        specification.For(editPlayerPage.getData().getHeightInput())
        .isContain(String.valueOf(player.getHeight()))
        .next();
        editPlayerPage.getData().setWeight(player);
        specification.For(editPlayerPage.getData().getWeightInput())
        .isContain(String.valueOf(player.getWeight()))
        .next();
        playerInfo = editPlayerPage.pressSave();
        playerInfo.pressReturnButton();
        specification.For(repository)
        .numberOfPlayersHasNotChanged(prevcountAllPlayers)
        .next()
        .For(repository)
        .playerWasAdded(player, prevNumberOfPlayer)
        .next()
        .For(repository)
        .playerWasDeleted(playerForEdition, prevNumberOfPlayerForEdition);
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
