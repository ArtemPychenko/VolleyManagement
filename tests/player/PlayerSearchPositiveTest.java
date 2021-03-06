package com.softserveinc.ita.volleymanagementtests.tests.player;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.softserveinc.ita.volleymanagementtests.criterias.Specification;
import com.softserveinc.ita.volleymanagementtests.pages.SearchString;
import com.softserveinc.ita.volleymanagementtests.tests.RollbackPreconditions;
import com.softserveinc.ita.volleymanagementtests.tests.SetPreconditions;
import com.softserveinc.ita.volleymanagementtests.tests.TestsConstants;
import com.softserveinc.ita.volleymanagementtests.tools.WebDriverUtils;

/**
 * This is Test-case class for search player functionality.
 * "http://localhost:23801/WebApi/Home" test.
 *
 * @author Artem Pozdeev
 */
public class PlayerSearchPositiveTest {

    /**
     * Main page object of testing Web site.
     */
    private SearchString searchString;

    /**
     * This method is setting up test Preconditions.
     */
    @BeforeClass
    public final void setUp() {
        WebDriverUtils.load(TestsConstants.HOME_PAGE);
        searchString = SetPreconditions.forSearchPlayerPositive();
    }

    /**
     * Test for the list of player paging functionality.
     */
    @Test
    public final void playerSearchTest() {
        Specification.get().ForPlayers(searchString)
                .tryFindTopPlayer()
                .tryFindLowerPlayer()
                .nextPage()
                .tryFindPreviousPlayer()
                .addDuplicatePlayer()
                .tryFindBothPlayers()
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
