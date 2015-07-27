package com.softserveinc.ita.volleymanagementtests.tests.tournament;

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
 * This is Test-case class for search tournament functionality.
 * "http://localhost:23801/WebApi/Home" test.
 *
 * @author Artem Pozdeev
 */
public class TournamentSearchPositiveTest {

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
        searchString = SetPreconditions.forSearchTournamentPositive();
    }

    /**
     * Test for the list of tournament paging functionality.
     */
    @Test
    public final void tournamentSearchTest() {
        Specification.get().ForTournaments(searchString)
                .tryFindTopTournament()
                .tryFindLowerTournament()
                .nextPage()
                .tryFindPreviousTournament()
                .addDuplicateTournament()
                .tryFindBothTournaments()
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
