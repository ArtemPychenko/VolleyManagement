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
 * Functionality test for view detail page.
 * @author Dmytro Maslov.
 *
 */
public class ViewPlayerDetailsFunTest {
    /**Variable for specification.*/
    private Specification specification;
    /**Contain home page.*/
    private HomePage homePage;
    /**Contain player list page.*/
    private PlayerListPage playersListPage;
    /**Contain player detail page.*/
    private PlayerDetailPage detailPlayerPage;
    /**Expected player.*/
    private Player expectedPlayer;
    /**Player repository.*/
    private PlayerRepository repository;
    /**Save all player from DB.*/
    private List<Player> playerInBD;
    /**Before test method.*/
    @BeforeClass
    public final void setUp() {
        DALTools.startConnection(TestsConstants.CONNECT_DB_STRING);
        playerInBD = SetPreconditions.forValidationPlayerFildsTest();
        expectedPlayer = PlayerTestObjects.getValidPlayer();
        PlayerRepository.insertPlayer(expectedPlayer);
        specification = Specification.get();
    }
    /**Test method.*/
    @Test
    public final void editPlayerPageUI() {
        specification.For(repository).playerPresent(expectedPlayer).next();
        WebDriverUtils.load(TestsConstants.HOME_PAGE);
        homePage = new HomePage();
        playersListPage = homePage.getNavigationPage().getPlayersPage();
        detailPlayerPage = playersListPage.showPlayerInfo();
        specification.For(detailPlayerPage.getFirstNameInputLabel())
                .valueMatch(expectedPlayer.getFirstName()).next()
                .For(detailPlayerPage.getSecondNameInputLabel())
                .valueMatch(expectedPlayer.getLastName()).next()
                .For(detailPlayerPage.getBirthYearInputLabel())
                .valueMatch(expectedPlayer.getBirthYear().toString()).next()
                .For(detailPlayerPage.getHeightInputLabel())
                .valueMatch(expectedPlayer.getHeight().toString()).next()
                .For(detailPlayerPage.getWeightInputLabel())
                .valueMatch(expectedPlayer.getWeight().toString());
        specification.check();
    }
    /**After test method.*/
    @AfterClass
    public final void tearDown() {
        RollbackPreconditions.forValidationPlayerFildsTest(playerInBD);
    }

}
