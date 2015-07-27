package com.softserveinc.ita.volleymanagementtests.tests.team;

import java.util.List;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.softserveinc.ita.volleymanagementtests.criterias.Specification;
import com.softserveinc.ita.volleymanagementtests.domain.models.Team;
import com.softserveinc.ita.volleymanagementtests.domain.repositories.DALTools;
import com.softserveinc.ita.volleymanagementtests.domain.repositories.TeamRepository;
import com.softserveinc.ita.volleymanagementtests.domain.repositories.TeamTestObjects;
import com.softserveinc.ita.volleymanagementtests.pages.HomePage;
import com.softserveinc.ita.volleymanagementtests.pages.TeamDetailPage;
import com.softserveinc.ita.volleymanagementtests.pages.TeamListPage;
import com.softserveinc.ita.volleymanagementtests.tests.RollbackPreconditions;
import com.softserveinc.ita.volleymanagementtests.tests.SetPreconditions;
import com.softserveinc.ita.volleymanagementtests.tests.TestsConstants;
import com.softserveinc.ita.volleymanagementtests.tools.WebDriverUtils;
/**
 * Test check UI elements on detail tournament page.
 * @author Artem Pychenko
 *
 */
public class TeamDetailedPageUITest {
	/**
	 * Specification object for soft-assert report create.
	 */
	private Specification specification;
	/**
	 * Object of HomePage class.
	 */
	private HomePage homePage;
	/**
	 * Object of TeamListPage class.
	 */
	private TeamListPage teamListPage;
	/**
	 * Object of TeamDetailPage class.
	 */
	private TeamDetailPage detailTeamPage;
	/**
	 * TeamData will save the team in set up method in his current state
	 * to restore them after test will complete in tear down method.
	 */
	private List<Team> teamsData;
	/**
	 * Object of Team class.
	 */
	private Team expectedTeam;

	/**
	 * This method performs the operations necessary to run the test.
	 */
	@BeforeClass
	public final void setUp() {
		DALTools.startConnection(TestsConstants.CONNECT_DB_STRING);
	 	teamsData = SetPreconditions.forValidationTeamFildsTest();
        DALTools.clearTable(TestsConstants.TEAM_TABLE);
		expectedTeam = TeamTestObjects.getValidTeam();
		TeamRepository.insertTeam(expectedTeam);
		WebDriverUtils.load(TestsConstants.HOME_PAGE);
		homePage = new HomePage();
		teamListPage = homePage.getNavigationPage().getTeamListPage();
		detailTeamPage = teamListPage.showTeamInfo();
		specification = Specification.get();
	}

	/**
     * Check UI element on the page.
     */
    @Test
    public final void checkUIElements() {
    	specification.For(detailTeamPage.getTitleLabel())
    				 .valueMatch(TestsConstants.TEAM_INFORMATION_LABEL)
    				 .isVisible()
    				 .next()
    				 
    				 .For(detailTeamPage.getEditButton())
    				 .textMatch(TestsConstants.EDIT_BUTTON)
    				 .isVisible()
    				 .next()
    				 
    				 .For(detailTeamPage.getTeamNameLabel())
		 			 .isVisible()
		 			 .valueMatch(TestsConstants.TEAM_NAME_LABEL)
		 		     .next()
		 				
		 			 .For(detailTeamPage.getCaptainLabel())
		 			 .isVisible()
		 			 .valueMatch(TestsConstants.TEAM_CAPTAIN_LABEL)
		 			 .next()
		 				
		 			 .For(detailTeamPage.getCoachLabel())
		 			 .isVisible()
		 			 .valueMatch(TestsConstants.TEAM_COACH_LABEL)
		 			 .next()

		 			 .For(detailTeamPage.getAchievementsLabel())
		 			 .isVisible()
		 			 .valueMatch(TestsConstants.TEAM_ACHIEVEMENTS_LABEL)
		 			 .next()

		 			.For(detailTeamPage.getReturnButton())
   				    .textMatch(TestsConstants.RETURN_BUTTON)
   				    .isVisible()
   				    .next()
   				    
   				    .For(detailTeamPage.getDeleteButton())
   				    .textMatch(TestsConstants.DELETE_BUTTON)
   				    .isVisible()
   				    .next(); 
    }
    /**
     * This method performs the operations necessary to stop the test and close
     * browser.
     */
    @AfterClass
    public final void tearDown() {
    	RollbackPreconditions.forValidationTeamFildsTest(teamsData);
    }
}
