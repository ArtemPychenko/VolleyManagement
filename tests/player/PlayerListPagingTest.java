package com.softserveinc.ita.volleymanagementtests.tests.player;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.softserveinc.ita.volleymanagementtests.criterias.Specification;
import com.softserveinc.ita.volleymanagementtests.pages.PlayerListPage;
import com.softserveinc.ita.volleymanagementtests.tests.RollbackPreconditions;
import com.softserveinc.ita.volleymanagementtests.tests.SetPreconditions;
import com.softserveinc.ita.volleymanagementtests.tests.TestsConstants;
import com.softserveinc.ita.volleymanagementtests.tools.WebDriverUtils;

/**
 * This is Test-case class for Paging on PlayerList functionality.
 * "http://localhost:23801/WebApi/Home" test.
 *
 * @author Artem Pozdeev
 */
public class PlayerListPagingTest {

    /**
     * Main page object of testing Web site.
     */
    private PlayerListPage playerPage;

    /**
     * This method is setting up test Preconditions.
     */
    @BeforeClass
    public final void setUp() {
        WebDriverUtils.load(TestsConstants.HOME_PAGE);
        playerPage = SetPreconditions.forPlayerPagingTest();
    }

    /**
     * Test for the list of player paging functionality.
     */
    @Test
    public final void playerListPaging() {
        Specification.get().For(playerPage)
                .readPlayersFromDB()
                .isPageQuantityCorrect()
                .isCurrentPageNumCorrect(1)
                .isQuantityPlayersCorrect()
                .isPlayersSortedCorrect()
                .repeatTestForAllPages()
                .repeatTestForPage(1)
                .isUIPlayerListCorrespondsDB()
                .next()
                .check();
    }

    /**
     * This method is roll back Precondition settings.
     */
    @AfterClass
    public final void tearDown() {
        RollbackPreconditions.forPlayersTable();
    }
}
