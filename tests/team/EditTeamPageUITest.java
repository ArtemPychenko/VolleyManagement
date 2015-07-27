package com.softserveinc.ita.volleymanagementtests.tests.team;

import java.util.List;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.softserveinc.ita.volleymanagementtests.criterias.Specification;
import com.softserveinc.ita.volleymanagementtests.domain.models.Team;
import com.softserveinc.ita.volleymanagementtests.domain.repositories.TeamRepository;
import com.softserveinc.ita.volleymanagementtests.domain.repositories.TeamTestObjects;
import com.softserveinc.ita.volleymanagementtests.pages.EditTeamPage;
import com.softserveinc.ita.volleymanagementtests.pages.HomePage;
import com.softserveinc.ita.volleymanagementtests.pages.TeamDetailPage;
import com.softserveinc.ita.volleymanagementtests.pages.TeamListPage;
import com.softserveinc.ita.volleymanagementtests.tests.RollbackPreconditions;
import com.softserveinc.ita.volleymanagementtests.tests.SetPreconditions;
import com.softserveinc.ita.volleymanagementtests.tests.TestsConstants;
import com.softserveinc.ita.volleymanagementtests.tools.WebDriverUtils;
/**
 * @author Aleksandr Zaitsev
 * Class verifies the UI of the edit team page.
 */
public class EditTeamPageUITest {
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
     * an instance of the edit team page.
     */
    private EditTeamPage editTeamrPage;
    /**
     * an instance of the team's details page.
     */
    private TeamDetailPage teamDetailsPage;
    /**
     * teamData will save the team in set up method in his current state
     * to restore them after test will complete in tear down method.
     */
    private List<Team> teamData;
    /**
     * This method performs the operations necessary to run the test.
     */
     @BeforeClass
     public final void setUp() {
         WebDriverUtils.load(TestsConstants.HOME_PAGE);
         homePage = new HomePage();
         teamData = SetPreconditions.forValidationTeamFildsTest();
         TeamRepository.insertTeam(TeamTestObjects.getValidTeam());
         teamListPage = homePage.getNavigationPage().getTeamListPage();
         specification = Specification.get();
         teamDetailsPage = teamListPage.showTeamInfo();
         editTeamrPage = teamDetailsPage.pressEditButton();
        }
     /**
      * This method performs the operations necessary after the test execution.
      */
     @AfterClass
     public final void tearDown() {
         RollbackPreconditions.forValidationTeamFildsTest(teamData);
     }
     /**
      * The methods is verifying the UI of the edit team page.
      */
     @Test
     public final void editPlayerPageUI() {
         specification.For(editTeamrPage.getData().getTitleLabel())
                        .valueMatch(TestsConstants.EDIT_TEAM_TITLE_LABEL)
                        .isVisible()
                        .next()

                        .For(editTeamrPage.getData().getNameLabel())
                        .isVisible()
                        .valueMatch(TestsConstants.TEAM_NAME_LABEL)
                        .next()

                        .For(editTeamrPage.getData().getCaptainLabel())
                        .isVisible()
                        .valueMatch(TestsConstants.TEAM_CAPTAIN_LABEL)
                        .next()

                        .For(editTeamrPage.getData().getCoachLabel())
                        .isVisible()
                        .valueMatch(TestsConstants.TEAM_COACH_LABEL)
                        .next()

                        .For(editTeamrPage.getData().getAchievementsLabel())
                        .isVisible()
                        .valueMatch(TestsConstants.TEAM_ACHIEVEMENTS_LABEL)
                        .next()

                        .For(editTeamrPage.getCancelButton())
                        .isVisible()
                        .textMatch(TestsConstants.CANCEL_BUTTON_LABEL)
                        .next()

                        .For(editTeamrPage.getSaveButton())
                        .isVisible()
                        .textMatch(TestsConstants.SAVE_BUTTON_LABEL)
                        .next()
                        .check();

        }
}
