package com.softserveinc.ita.volleymanagementtests.tests.tournament;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.softserveinc.ita.volleymanagementtests.criterias.Specification;
import com.softserveinc.ita.volleymanagementtests.pages.HomePage;
import com.softserveinc.ita.volleymanagementtests.pages.TournamentListPage;
import com.softserveinc.ita.volleymanagementtests.tests.TestsConstants;
import com.softserveinc.ita.volleymanagementtests.tools.WebDriverUtils;

/**
 * @author Alla Prykhodchenko This test-case for checking UI elements of the
 *         tournament list page of the VolleyManagement application.
 */
public class TournamentListPageUITest {
    /**
     * Specification object for soft-assert report create.
     */
    private Specification specification;
    /**
     * Main page object of testing application.
     */
    private HomePage homePage;
    /**
     * Team list page.
     */
    private TournamentListPage tournamentListPage;
    /**
     * This method performs the operations necessary to run the test.
     */
    @BeforeClass
    public final void setUp() {
        WebDriverUtils.load(TestsConstants.HOME_PAGE);
        homePage = new HomePage();
        tournamentListPage = homePage.getNavigationPage()
                .getTournamentListPage();
        specification = Specification.get();
    }
    @Test
    public final void tournamentListPageUI() {
        specification.For(tournamentListPage.getTournamentsListLabel())
        .isVisible()
        .valueMatch(TestsConstants.TOURNAMENT_LIST_PAGE_LABEL)
        .next()
        .For(tournamentListPage.getAddButton())
        .isVisible()
        .textMatch(TestsConstants.ADD_BUTTON_LABEL)
        .next()
        .For(tournamentListPage.getSearchReturnButton())
        .isVisible()
        .textMatch(TestsConstants.RETURN_BUTTON)
        .next()
        .For(tournamentListPage.getSearchTextInput())
        .isVisible()
        .isContainPlaceholder(TestsConstants.SEARCH_TEXT_INPUT)
        .isEmpty();
        specification.check();
    }
    /**
     * This method performs the operations necessary after the test execution.
     */
    @AfterClass
    public final void tearDown() {
        // WebDriverUtils.stop();
    }
}
