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
import com.softserveinc.ita.volleymanagementtests.pages.PlayerDetailPage;
import com.softserveinc.ita.volleymanagementtests.pages.PlayerListPage;
import com.softserveinc.ita.volleymanagementtests.tests.RollbackPreconditions;
import com.softserveinc.ita.volleymanagementtests.tests.SetPreconditions;
import com.softserveinc.ita.volleymanagementtests.tests.TestsConstants;
import com.softserveinc.ita.volleymanagementtests.tools.WebDriverUtils;
/**
 * This class check UI elements on the player detail page.
 * @author Artem Pychenko
 */
public class PlayerDetailedPageUITest {
	/**
	 * Specification object for soft-assert report create.
	 */
	private Specification specification;
	/**
	 * Object of HomePage class.
	 */
	private HomePage homePage;
	/**
	 * Object of PlayersDetailPage class.
	 */
	private PlayerDetailPage detailPlayerPage;
	/**
	* PlayerData will save the player in set up method in his current state
	* to restore them after test will complete in tear down method.
	*/
	private List<Player> playersData;
	/**
	 * Object of Player class.
	 */
	private Player expectedPlayer;
	/**
     * Object of PlayersListPage class.
     */
    private PlayerListPage playerListPage;

	/**
	 * This method performs the operations necessary to run the test.
	 */
	@BeforeClass
	public final void setUp() {
	 	DALTools.startConnection(TestsConstants.CONNECT_DB_STRING);
	 	playersData = SetPreconditions.forValidationPlayerFildsTest();
        DALTools.clearTable(TestsConstants.PLAYER_TABLE);
		expectedPlayer = PlayerTestObjects.getValidPlayer();
		PlayerRepository.insertPlayer(expectedPlayer);
		WebDriverUtils.load(TestsConstants.HOME_PAGE);
		homePage = new HomePage();
        playerListPage = homePage.getNavigationPage().getPlayersPage();
		detailPlayerPage = playerListPage.showPlayerInfo();
        specification = Specification.get();
  }

	/**
     * Check UI element on the page.
     */
    @Test
    public final void checkUIElements() {
    	specification.For(detailPlayerPage.getTitleLabel())
    				 .valueMatch(TestsConstants.PLAYER_INFORMATION_LABEL)
    				 .isVisible()
    				 .next()
    				 
    				 .For(detailPlayerPage.getEditButton())
    				 .textMatch(TestsConstants.EDIT_BUTTON)
    				 .isVisible()
    				 .next()
    				 
    				 .For(detailPlayerPage.getFirstNameLabel())
		 			 .isVisible()
		 			 .valueMatch(TestsConstants.PLAYER_NAME_LABEL)
		 		     .next()
		 				
		 			 .For(detailPlayerPage.getSecondNameLabel())
		 			 .isVisible()
		 			 .valueMatch(TestsConstants.PLAYER_SECOND_NAME_LABEL)
		 			 .next()
		 				
		 			 .For(detailPlayerPage.getBirthYearLabel())
		 			 .isVisible()
		 			 .valueMatch(TestsConstants.PLAYER_BIRTH_YEAR_LABEL)
		 			 .next()

		 			 .For(detailPlayerPage.getHeightLabel())
		 			 .isVisible()
		 			 .valueMatch(TestsConstants.PLAYER_HEIGHT_LABEL)
		 			 .next()

		 			 .For(detailPlayerPage.getWeightLabel())
		 			 .isVisible()
		 			 .valueMatch(TestsConstants.PLAYER_WEIGHT_LABEL)
		 			 .next()
		 			 
		 			.For(detailPlayerPage.getReturnButton())
   				    .textMatch(TestsConstants.RETURN_BUTTON)
   				    .isVisible()
   				    .next()
   				    
   				    .For(detailPlayerPage.getDeleteButton())
   				    .textMatch(TestsConstants.DELETE_BUTTON)
   				    .isVisible()
   				    .next(); 
    }
    /**
     * This method performs the operations necessary to stop the test and close
     * browser.
     */
    @AfterClass
    public final void tearDown() {
    	RollbackPreconditions.forValidationPlayerFildsTest(playersData);
    }
}
