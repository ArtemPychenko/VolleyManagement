package com.softserveinc.ita.volleymanagementtests.tests.team;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.softserveinc.ita.volleymanagementtests.criterias.Specification;
import com.softserveinc.ita.volleymanagementtests.pages.HomePage;
import com.softserveinc.ita.volleymanagementtests.pages.NewTeamPage;
import com.softserveinc.ita.volleymanagementtests.pages.TeamListPage;
import com.softserveinc.ita.volleymanagementtests.tools.WebDriverUtils;
import com.softserveinc.ita.volleymanagementtests.tests.TestsConstants;
/**
 * @author Aleksandr Zaitsev
 * Class verifies the UI of the page of new team creation.
 */
public class NewTeamPageUITest {
    /**
     * Specification object for soft-assert report create.
     */
    private Specification specification;
    /**
     * an instance of the main page of the web site.
     */
    private HomePage homePage;
    /**
     * an instance of the page with the team's details.
     */
    private TeamListPage teamListPage;
    /**
     * an instance of the new team creation page.
     */
    private NewTeamPage newTeamPage;
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
      * This method performs the operations necessary after the test execution.
      */
     @AfterClass
     public final void tearDown() {
         //WebDriverUtils.stop();
     }
     /**
      * The methods is verifying the UI of the page of new team creation.
      */
     @Test
     public final void newPlayerPageUI() {
         newTeamPage = teamListPage.openCreateTeamPage();

         specification.For(newTeamPage.getData().getTitleLabel())
                        .valueMatch(TestsConstants.CREATE_NEW_TEAM_TITLE_LABEL)
                        .isVisible()
                        .next()

                        .For(newTeamPage.getData().getNameLabel())
                        .isVisible()
                        .valueMatch(TestsConstants.TEAM_NAME_LABEL)
                        .next()

                        .For(newTeamPage.getData().getNameInput())
                        .isVisible()
                        .isEmpty()
                        .next()

                        .For(newTeamPage.getData().getCaptainLabel())
                        .isVisible()
                        .valueMatch(TestsConstants.TEAM_CAPTAIN_LABEL)
                        .next()

                        .For(newTeamPage.getData().getCaptainInput())
                        .isVisible()
                        .isEmpty()
                        .next()

                        .For(newTeamPage.getData().getCoachLabel())
                        .isVisible()
                        .valueMatch(TestsConstants.TEAM_COACH_LABEL)
                        .next()

                        .For(newTeamPage.getData().getCoachInput())
                        .isVisible()
                        .isEmpty()
                        .next()

                        .For(newTeamPage.getData().getAchievementsLabel())
                        .isVisible()
                        .valueMatch(TestsConstants.TEAM_ACHIEVEMENTS_LABEL)
                        .next()

                        .For(newTeamPage.getData().getaAhievementsInput())
                        .isVisible()
                        .isEmpty()
                        .next()

                        .For(newTeamPage.getCancelButton())
                        .isVisible()
                        .textMatch(TestsConstants.CANCEL_BUTTON_LABEL)
                        .next()

                        .For(newTeamPage.getSaveButton())
                        .isVisible()
                        .textMatch(TestsConstants.SAVE_BUTTON_LABEL)
                        .next()
                        .check();

        }

}
