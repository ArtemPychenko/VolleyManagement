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
import com.softserveinc.ita.volleymanagementtests.pages.PlayerListPage;
import com.softserveinc.ita.volleymanagementtests.tests.RollbackPreconditions;
import com.softserveinc.ita.volleymanagementtests.tests.SetPreconditions;
import com.softserveinc.ita.volleymanagementtests.tests.TestsConstants;
import com.softserveinc.ita.volleymanagementtests.tools.WebDriverUtils;
/**
 * This class check UI elements on the player detail page.
 * @author Artem Pychenko
 */
public class PlayerListPageUITest {
    /**
     * Specification object for soft-assert report create.
     */
    private Specification specification;
    /**
     * Object of HomePage class.
     */
    private HomePage homePage;
    /**
     * Object of PlayersListPage class.
     */
    private PlayerListPage playersListPage;
    /**
	* PlayerData will save the player in set up method in his current state
	* to restore them after test will complete in tear down method.
	*/
	private List<Player> playerData;
	/**
	 * Object of Player class.
	 */
	private Player expectedPlayer;
    

    /**
     * This method performs the operations necessary to run the test.
     */
    @BeforeClass
    public final void setUp() {
    	DALTools.startConnection(TestsConstants.CONNECT_DB_STRING);
	 	playerData = SetPreconditions.forValidationPlayerFildsTest();
        DALTools.clearTable(TestsConstants.PLAYER_TABLE);
		expectedPlayer = PlayerTestObjects.getValidPlayer();
		PlayerRepository.insertPlayer(expectedPlayer);
		WebDriverUtils.load(TestsConstants.HOME_PAGE);
		homePage = new HomePage();
        playersListPage = homePage.getNavigationPage().getPlayersPage();
        specification = Specification.get();
    }

    /**
     * Check UI element on the page.
     */
    @Test
    public final void checkUIElements() {
        specification.For(playersListPage.getPlayersListLabel())
                     .valueMatch("������ �������")
                     .next()
                     
                     .For(playersListPage.getAddButton())
                     .isVisible()
                     .next()
                     
                     .For(playersListPage.getAddButton())
                     .textMatch("��������")
                     .next()
                     
                     .For(playersListPage.getSearchString().getClearButton())
                     .isVisible()
                     .next()
                     
                     .For(playersListPage.getSearchString().getClearButton())
                     .textMatch("�����")
                     .next()
                     
                     .For(playersListPage.getSearchString().getTextInput())
                     .isVisible()
                     .next()
                     
                     .For(playersListPage.getSearchString().getTextInput())
                     .isEmpty()
                     .next();        
    }

    /**
     * This method performs the operations necessary to stop the test and close
     * browser.
     */
    @AfterClass
    public final void tearDown() {
    	RollbackPreconditions.forValidationPlayerFildsTest(playerData);
    }
}
