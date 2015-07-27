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
import com.softserveinc.ita.volleymanagementtests.pages.NavigationPage;
import com.softserveinc.ita.volleymanagementtests.pages.PlayerDetailPage;
import com.softserveinc.ita.volleymanagementtests.tests.RollbackPreconditions;
import com.softserveinc.ita.volleymanagementtests.tests.SetPreconditions;
import com.softserveinc.ita.volleymanagementtests.pages.PlayerListPage;
import com.softserveinc.ita.volleymanagementtests.tests.TestsConstants;
import com.softserveinc.ita.volleymanagementtests.tools.WebDriverUtils;
/**
 * @author Aleksandr Zaitsev
 * Test verifies that after creating player in database by query, the player
 * is present in edit player page (in UI).
 */
public class ViewPlayerEditTestFunctional {
    /**
     * Specification object for soft-assert report create.
     */
    private Specification specification;
    /**
     * an instance of the navigation page of the web site(the page is an
     * right-side panel to get to the proper page.
     */
    private NavigationPage navigationPage;
    /**
     * an instance of the page with the list of players.
     */
    private PlayerListPage playersListPage;
    /**
     * an instance of the page with the player's details.
     */
    private PlayerDetailPage detailPlayerPage;
    /**
     * an instance of the player's edit page.
     */
    private EditPlayerPage editPlayerPage;
    /**
     * the player is which will be get as an actual.
     */
    private Player actualPlayer;
    /**
     * the player is which will be saved as an expected.
     */
    private Player expectedPlayer;
    /**
     * Existing player database.
     */
    private PlayerRepository repository;
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
            DALTools.startConnection(TestsConstants.CONNECT_DB_STRING);
            playersData = SetPreconditions.forValidationPlayerFildsTest();
            expectedPlayer = PlayerTestObjects.getValidPlayer();
            PlayerRepository.insertPlayer(expectedPlayer);
            specification = Specification.get();
        }
     /**
      * This method performs the operations necessary after the test execution.
      */
     @AfterClass
     public final void tearDown() {
         RollbackPreconditions.forValidationPlayerFildsTest(playersData);
         //WebDriverUtils.stop();
     }
     /**
      * This method verifies that the player is saved to the database as equals
      * to the UI player (in the edit player page).
      */
     @Test
     public final void editPlayerPageUI() {
         specification.For(repository).playerPresent(expectedPlayer);
         WebDriverUtils.load(TestsConstants.HOME_PAGE);
         navigationPage = new NavigationPage();
         playersListPage = navigationPage.getPlayersPage();
         detailPlayerPage = playersListPage.showPlayerInfo(expectedPlayer);
         editPlayerPage = detailPlayerPage.pressEditButton();
         actualPlayer = editPlayerPage.getPlayer();
         specification.For(actualPlayer).playerMatch(expectedPlayer);
        }

}
