package com.softserveinc.ita.volleymanagementtests.tests.tournament;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.softserveinc.ita.volleymanagementtests.criterias.Specification;
import com.softserveinc.ita.volleymanagementtests.domain.repositories
.TournamentTestObjects;
import com.softserveinc.ita.volleymanagementtests.pages.HomePage;
import com.softserveinc.ita.volleymanagementtests.pages.NewTournamentPage;
import com.softserveinc.ita.volleymanagementtests.tests.TestsConstants;
import com.softserveinc.ita.volleymanagementtests.tools.WebDriverUtils;

/**
 * This test case for checking content dropdown season in new page.
 * @author Dmytro Maslov.
 */
public class SeasonDropdownContentInNewTournamentPageTest {
    /**
     * Main page object of testing application.
     */
    private HomePage homePage;
    /**
     * New tournament page.
     */
    private NewTournamentPage newTournamentPage;
    /**
     * Specification object for soft-assert report create.
     */
    private Specification specification;

    /**
     * This method performs the operations necessary to run the test.
     */
    @BeforeClass
    public final void setUp() {
        WebDriverUtils.load(TestsConstants.HOME_PAGE);
        homePage = new HomePage();
        specification = Specification.get();
    }
    /**Test method.*/
    @Test
    public final void checkDropdownContent() {
       newTournamentPage = homePage.getNavigationPage()
                .getTournamentListPage()
                .openCreateTournamentPage();
       specification.For(newTournamentPage.getData()
               .getSeasonDropdown()).valuesMatch(
                       TournamentTestObjects.getCurrentSeasonListForUI(
                               TournamentTestObjects.currentSeasonYear));
        specification.check();
    }
    /**
     * This method performs the operations necessary after the test execution.
     */
    @AfterClass
    public final void tearDown() {
    }
}
