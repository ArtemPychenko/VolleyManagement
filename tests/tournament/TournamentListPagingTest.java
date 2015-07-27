package com.softserveinc.ita.volleymanagementtests.tests.tournament;


import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.softserveinc.ita.volleymanagementtests.criterias.Specification;
import com.softserveinc.ita.volleymanagementtests.pages.TournamentListPage;
import com.softserveinc.ita.volleymanagementtests.tests.RollbackPreconditions;
import com.softserveinc.ita.volleymanagementtests.tests.SetPreconditions;
import com.softserveinc.ita.volleymanagementtests.tests.TestsConstants;
import com.softserveinc.ita.volleymanagementtests.tools.WebDriverUtils;

/**
 * This is Test-case class for Paging on TeamList functionality.
 * "http://localhost:23801/WebApi/Home" test.
 *
 * @author Artem Pozdeev
 */
public class TournamentListPagingTest {

    /**
     * Main page object of testing Web site.
     */
    private TournamentListPage tournamentListPage;

    /**
     * This method is setting up test Preconditions.
     */
    @BeforeClass
    public final void setUp() {
        WebDriverUtils.load(TestsConstants.HOME_PAGE);
        tournamentListPage = SetPreconditions.forTournamentPagingTest();
    }

    /**
     * Test for the list of team paging functionality.
     */
    @Test
    public final void teamListPaging() {
        Specification.get()
        .For(tournamentListPage)
                .readTournamentsFromDB()
                .isPageQuantityCorrect()
                .isCurrentPageNumCorrect(1)
                .isQuantityTournamentsCorrect()
                .isTournamentsSortedCorrect()
                .repeatTestForAllPages()
                .repeatTestForPage(1)
                .isUITournamentListCorrespondsDB()
                .next()
                .check();
    }

    /**
     * This method is roll back Precondition settings.
     */
    @AfterClass
    public final void tearDown() {
        RollbackPreconditions.forTournamentsTable();
    }
}
