package com.softserveinc.ita.volleymanagementtests.tests.tournament;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.softserveinc.ita.volleymanagementtests.criterias.Specification;
import com.softserveinc.ita.volleymanagementtests.domain.repositories.TournamentTestObjects;
import com.softserveinc.ita.volleymanagementtests.pages.EditTournamentPage;
import com.softserveinc.ita.volleymanagementtests.pages.HomePage;
import com.softserveinc.ita.volleymanagementtests.pages.TournamentDetailsPage;
import com.softserveinc.ita.volleymanagementtests.tests.TestsConstants;
import com.softserveinc.ita.volleymanagementtests.tools.WebDriverUtils;

/**
 * This test-case for checking content dropdown season in edit page.
 * @author Dmytro Maslov.
 */
public class SeasonDropdownContentInEditTournamentPageTest {
    /** Main page object of testing application. */
    private HomePage homePage;
    /** New tournament page. */
    private TournamentDetailsPage detailTournamentPage;
    /** Specification object for soft-assert report create. */
    private Specification specification;
    /**Contain season.*/
    private String season;
    /**Contain edit tournament page.*/
    private EditTournamentPage editPage;

    /** This method performs the operations necessary to run the
     * test. */
    @BeforeClass
    public final void setUp() {
        WebDriverUtils.load(TestsConstants.HOME_PAGE);
        homePage = new HomePage();
        specification = Specification.get();
    }
    /**Test method.*/
    @Test
    public final void checkDropdownContent() {
        detailTournamentPage = homePage.getNavigationPage()
                .getTournamentListPage().showFirstTournament();
        season = detailTournamentPage.getSeasonYear();
        editPage = detailTournamentPage.pressEditButton();
        specification.For(editPage.getData().getSeasonDropdown())
            .valuesMatch(
                    TournamentTestObjects.getCurrentSeasonListForUI(season));
        specification.check();
    }
    /** This method performs the operations necessary after the test
     * execution. */
    @AfterClass
    public final void tearDown() {
    }
}
