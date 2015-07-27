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
import com.softserveinc.ita.volleymanagementtests.pages.HomePage;
import com.softserveinc.ita.volleymanagementtests.pages.NewPlayerPage;
import com.softserveinc.ita.volleymanagementtests.tests.TestsConstants;
import com.softserveinc.ita.volleymanagementtests.tools.WebDriverUtils;

/**
 * @author Alla Prykhodchenko This test-case for checking create new player
 *         functionality of the VolleyManagement application.
 */
public class CreateNewPlayerTest {
    /**
     * Main page object of testing application.
     */
    private HomePage homePage;
    /**
     * Create new player page.
     */
    private NewPlayerPage newPlayerPage;
    /**
     * Valid player object with all fields.
     */
    private Player player;
    /**
     * Valid player object with required fields.
     */
    private Player reqFieldPlayer;
    /**
     * Specification object for soft-assert report create.
     */
    private Specification specification;
    /**
     * Existing player database.
     */
    private PlayerRepository repository;
    /**
     * Number of players with valid player data before test execution.
     */
    private int prevNumberOfPlayer;
    /**
     * Number of players with valid player with required fields data before test
     * execution.
     */
    private int prevNumberOfReqFieldPlayer;
    /**
     * Number of players in database before test execution.
     */
    private int prevcountAllPlayers;

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
        reqFieldPlayer = PlayerTestObjects.getValidPlayerWithRequiredFields();
        prevNumberOfPlayer = PlayerRepository.countPlayers(player);
        prevNumberOfReqFieldPlayer = PlayerRepository.countPlayers(
                reqFieldPlayer);
        prevcountAllPlayers = PlayerRepository.countAllPlayers();
    }

    /**
     * This test for checking create new player functionality for player with
     * all player fields data.
     */
    @Test
    public final void createNewPlayer() {
       newPlayerPage = homePage.getNavigationPage()
                .getPlayersPage()
                .openCreatePlayerPage();
        newPlayerPage.getData().setFirstName(player);
        specification.For(newPlayerPage.getData()
                .getFirstNameInput())
                .isContain(player.getFirstName())
                .next();
        newPlayerPage.getData().setSecondName(player);
        specification.For(newPlayerPage.getData()
                .getSecondNameInput())
                .isContain(player.getLastName())
                .next();
        newPlayerPage.getData().setBirthYear(player);
        specification.For(newPlayerPage.getData()
                .getBirthYearInput())
                .isContain(String.valueOf(player.getBirthYear()))
                .next();
        newPlayerPage.getData().setHeight(player);
        specification.For(newPlayerPage.getData()
                .getHeightInput())
                .isContain(String.valueOf(player.getHeight()))
                .next();
        newPlayerPage.getData().setWeight(player);
        specification.For(newPlayerPage.getData()
                .getWeightInput())
                .isContain(String.valueOf(player.getWeight()))
                .next();
        newPlayerPage.getSaveButton().click();
        specification.For(repository)
                .numberOfPlayersHasIncreased(prevcountAllPlayers)
                .next()
                .For(repository)
                .playerWasAdded(player, prevNumberOfPlayer);
        specification.check();
    }
    /**
     * This test for checking create new player functionality for player with
     * required fields data.
     */
    @Test
    public final void createNewPlayerWithRequiredFields() {
        newPlayerPage = homePage.getNavigationPage()
                .getPlayersPage()
                .openCreatePlayerPage();
        newPlayerPage.getData().setFirstName(reqFieldPlayer);
        specification.For(newPlayerPage.getData()
                .getFirstNameInput())
                .isContain(reqFieldPlayer.getFirstName())
                .next();
        newPlayerPage.getData().setSecondName(reqFieldPlayer);
        specification.For(newPlayerPage.getData()
                .getSecondNameInput())
                .isContain(reqFieldPlayer.getLastName())
                .next();
        newPlayerPage.getSaveButton().click();
        specification.For(repository)
                .numberOfPlayersHasIncreased(prevcountAllPlayers)
                .next()
                .For(repository)
                .playerWasAdded(reqFieldPlayer, prevNumberOfReqFieldPlayer);
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
