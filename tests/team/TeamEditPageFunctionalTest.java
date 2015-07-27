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
import com.softserveinc.ita.volleymanagementtests.pages.NavigationPage;
import com.softserveinc.ita.volleymanagementtests.pages.TeamDataPage;
import com.softserveinc.ita.volleymanagementtests.pages.TeamDetailPage;
import com.softserveinc.ita.volleymanagementtests.pages.TeamListPage;
import com.softserveinc.ita.volleymanagementtests.tests.RollbackPreconditions;
import com.softserveinc.ita.volleymanagementtests.tests.TestsConstants;
import com.softserveinc.ita.volleymanagementtests.tools.WebDriverUtils;
/**
 * Test compare fields content in database and on edit team page.
 * @author Artem Pychenko
 *
 */
public class TeamEditPageFunctionalTest {
	/**
	 * Specification object for soft-assert report create.
	 */
	private Specification specification;
	/**
	 * Object of NavigationPage class.
	 */
	private NavigationPage homePage;
	/**
	 * Object of TeamListPage class.
	 */
	private TeamListPage teamListPage;
	/**
	 * Object of TeamDetailPage class.
	 */
	private TeamDetailPage detailTeamPage;
	/**
	 * Object of TeamDataPage class.
	 */
	private TeamDataPage teamDataPage;
	/**
	 * Object of Team class.
	 */
	private Team expectedTeam;
	/**
	 * Object of TeanRepository class.
	 */
	private TeamRepository repository;
	/**
    * teamsData will save the team in set up method in his current state
    * to restore them after test will complete in tear down method.
    */
    private List<Team> teamsData;
    /**
	 * This method performs the operations necessary to run the test.
	 */
	 @BeforeClass
	 public final void setUp() {
		 	DALTools.startConnection(TestsConstants.CONNECT_DB_STRING);
            teamsData = TeamRepository.getAllTeams();
	        DALTools.clearTable(TestsConstants.TEAM_TABLE);
			expectedTeam = TeamTestObjects.getValidTeam();
			TeamRepository.insertTeam(expectedTeam);;
	        specification = Specification.get();
	    }
	 /**
	  * Test compare fields content in database and on edit team page.
	  */
	 @Test
	 public final void compareEditTeamPage() {
	     specification.For(repository).teamPresent(expectedTeam).next();
	     WebDriverUtils.load(TestsConstants.HOME_PAGE);
	     homePage = new NavigationPage();
	     teamListPage = homePage.getTeamListPage();
	     detailTeamPage = teamListPage.showTeamInfo();
	     teamDataPage = this.detailTeamPage.pressEditButton().getData();
	     specification.For(teamDataPage.getNameInput())
	     			  .isContain(expectedTeam.getTeamName())
	     			  .next();
	     specification.For(teamDataPage.getCaptainInput())
	     			  .isContain(expectedTeam.getCaptain().toString())
	     			  .next();
	     specification.For(teamDataPage.getCoachInput())
	     			  .isContain(expectedTeam.getCoach())
	     			  .next();
	     specification.For(teamDataPage.getaAhievementsInput())
	     			  .isContain(expectedTeam.getAchievements())
	     			  .next();
	     specification.check();
	    }
	    /**
	     * This method performs the operations necessary to stop the test and close
	     * browser.
	     */
	    @AfterClass
	    public final void tearDown() {
	    	RollbackPreconditions.forValidationTeamFildsTest(teamsData);
            //WebDriverUtils.stop();
	    }
}
