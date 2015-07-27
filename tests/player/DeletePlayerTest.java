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
import com.softserveinc.ita.volleymanagementtests.pages.ConfirmDialogPage;
import com.softserveinc.ita.volleymanagementtests.pages.HomePage;
import com.softserveinc.ita.volleymanagementtests.tests.RollbackPreconditions;
import com.softserveinc.ita.volleymanagementtests.tests.SetPreconditions;
import com.softserveinc.ita.volleymanagementtests.tests.TestsConstants;
import com.softserveinc.ita.volleymanagementtests.tools.WebDriverUtils;

/**
 * @author Aleksandr Zaitsev
 * test verifies the delete player functionality.
 */
public class DeletePlayerTest {
    /**
     * Specification object for soft-assert report create.
     */
    private Specification specification;
    /**
     * Existing player database.
     */
    private PlayerRepository repository;
    /**.
     * playersQuantityBeforeDelete holds quantity of players before delete.
     */
    private int playersQuantityBeforeDelete;
    /**
     * an instance of the main page of the web site.
     */
    private HomePage homePage;
    /**
     * valid player - player with valid data.
     */
    private Player validPlayer;
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
        specification = Specification.get();
        homePage = new HomePage();
        validPlayer = PlayerTestObjects.getValidPlayer();
        PlayerRepository.insertPlayer(validPlayer);
        playersQuantityBeforeDelete = PlayerRepository.countAllPlayers();

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
    * This test verifies the text of confirmation dialog page (delete player).
    */
   @Test
    public final void deletePlayerTextMessageTest() {
       specification.For(
       homePage.getNavigationPage().getPlayersPage().showPlayerInfo(
               PlayerTestObjects.getValidPlayer())
       .pressDeleteButton())
       .textDeletePlayerMatch(TestsConstants.DELETE_PLAYER_LABEL)
       .buttonYesMatch(TestsConstants.DELETE_BUTTON_YES_LABEL)
       .buttonNoMatch(TestsConstants.DELETE_BUTTON_NO_LABEL)
       .next().check();
       new ConfirmDialogPage().clickNo();
    }
   /**
    * This test verifies the delete player functionality. If cancel the
    * deleting - player will stay in database.
    */
   @Test
   public final void deletePlayerTestNotConfirm() {
       homePage.getNavigationPage().getPlayersPage().showPlayerInfo(
               PlayerTestObjects.getValidPlayer())
      .pressDeleteButton().clickNo();

      specification.For(repository)
              .numberOfPlayersHasNotChanged(playersQuantityBeforeDelete)
              .playerPresent(validPlayer)
              .next()
              .check();
   }
   /**
    * This test verifies the delete player functionality. If confirm the
    * deleting - player will be deleted from database.
    */
   @Test(dependsOnMethods = {"deletePlayerTestNotConfirm"}, alwaysRun = true)
   public final void deletePlayerTestConfirm() {
       homePage.getNavigationPage().getPlayersPage().showPlayerInfo(
               PlayerTestObjects.getValidPlayer())
      .pressDeleteButton().clickYes();

      specification.For(repository)
              .numberOfPlayersHasDecreased(playersQuantityBeforeDelete)
              .playerNotPresent(validPlayer)
              .next()
              .check();

   }
}
