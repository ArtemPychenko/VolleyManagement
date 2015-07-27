package com.softserveinc.ita.volleymanagementtests.tests.team;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.softserveinc.ita.volleymanagementtests.criterias.Specification;
import com.softserveinc.ita.volleymanagementtests.pages.HomePage;
import com.softserveinc.ita.volleymanagementtests.pages.TeamListPage;
import com.softserveinc.ita.volleymanagementtests.tests.TestsConstants;
import com.softserveinc.ita.volleymanagementtests.tools.WebDriverUtils;

/**
 * @author Alla Prykhodchenko This test-case for checking UI elements of the
 *         team list page of the VolleyManagement application.
 */
public class TeamListPageUITest {
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
    private TeamListPage teamListPage;

    /**
     * This method performs the operations necessary to run the test.
     */
    @BeforeClass
    public final void setUp() {
        WebDriverUtils.load(TestsConstants.HOME_PAGE);
        homePage = new HomePage();
        teamListPage = homePage.getNavigationPage().getTeamListPage();
        specification = Specification.get();
    }

    /**
     * Test for check UI elements of the team list page.
     */
    @Test
    public final void teamListPageUI() {
        specification.For(teamListPage.getTeamsListLabel())
                .isVisible()
                .valueMatch(TestsConstants.TEAM_LIST_PAGE_LABEL)
                .next()
                .For(teamListPage.getAddButton())
                .isVisible()
                .textMatch(TestsConstants.ADD_BUTTON_LABEL)
                .next()
                .For(teamListPage.getSearchReturnButton())
                .isVisible()
                .textMatch(TestsConstants.RETURN_BUTTON)
                .next()
                .For(teamListPage.getSearchTextInput())
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
        //WebDriverUtils.stop();
    }
}
