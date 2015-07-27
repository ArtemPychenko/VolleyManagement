package com.softserveinc.ita.volleymanagementtests.tests.team;

import java.util.List;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.softserveinc.ita.volleymanagementtests.criterias.Specification;
import com.softserveinc.ita.volleymanagementtests.domain.models.Team;
import com.softserveinc.ita.volleymanagementtests.domain.repositories.DALTools;
import com.softserveinc.ita.volleymanagementtests.domain.repositories
        .TeamRepository;
import com.softserveinc.ita.volleymanagementtests.domain.repositories
        .TeamTestObjects;
import com.softserveinc.ita.volleymanagementtests.pages.HomePage;
import com.softserveinc.ita.volleymanagementtests.pages.NewTeamPage;
import com.softserveinc.ita.volleymanagementtests.pages.TeamListPage;
import com.softserveinc.ita.volleymanagementtests.tests.RollbackPreconditions;
import com.softserveinc.ita.volleymanagementtests.tests.SetPreconditions;
import com.softserveinc.ita.volleymanagementtests.tests.TestsConstants;
import com.softserveinc.ita.volleymanagementtests.tools.WebDriverUtils;

    /**
     * This class does validation team's name field through new functionality.
     * It doesn't do validation location validation error message on the page.
     * @author Andriy Lantukh
     *
     */
public class ValidateTeamNameFieldCreate {
    /**
     * an instance of the main page of the web site.
     */
    private HomePage homePage;
    /**
     * an instance of the page of new team creating.
     */
    private NewTeamPage newTeamPage;
    /**
     * an instance of the page with the list of teams.
     */
    private TeamListPage teamListPage;
    /**
     *  database access.
     */
    private TeamRepository repository;
    /**
     *  an specification for check.
     */
    private Specification specification;
    /**
     * teamsData will save the teams in set up method in their current state
     * to restore them after test will complete in tear down method.
     */
    private List<Team> teamsData;
    /**
     * team - team object.
     */
    private Team team;
    /**
     * This method performs the operations necessary to run the test.
     */
    @BeforeClass
    public final void setUp() {
        DALTools.startConnection(TestsConstants.CONNECT_DB_STRING);
        teamsData = SetPreconditions.forValidationTeamFildsTest();
        WebDriverUtils.load(TestsConstants.HOME_PAGE);
        homePage = new HomePage();
        specification = Specification.get();
    }
    /**
     * The team shouldn't be save in the database for invalid team.
     * The validation messages should be shown as it is wrong type entered.
     * 
     * If valid team entered.
     * The team should be saved in the database.
     */          
    @Test
    public final void validateTeamsNameField() {
         
        // Validation field with empty field. 
        team = TeamTestObjects.getInvalidEmptyTeamName();
        teamListPage = homePage.getNavigationPage().getTeamListPage();
        newTeamPage = teamListPage.openCreateTeamPage();
        newTeamPage.getData().setAllFields(team);
        newTeamPage.unsuccessfulPressSave();
           
        specification
        .For(newTeamPage.getData().getErrorMessageLabelName())
        .valueMatch(TestsConstants
                .VALIDATION_ERROR_TEXT_EMPTY_NAME_FIELD)
        .next()
        .For(repository)
        .teamNotPresent(team);
                       
        if (TeamRepository.isTeamPresent(team)) {
            TeamRepository.deleteTeamByName(team.getTeamName());
        }
        teamListPage = TeamListPage.refreshTeamList();    
           
        // Validation field with more than 30 symbols field. 
        team = TeamTestObjects.getInvalidTooBigTeamName();
        newTeamPage = teamListPage.openCreateTeamPage();
        newTeamPage.getData().setAllFields(team);
        newTeamPage.unsuccessfulPressSave();
           
        specification
        .For(newTeamPage.getData().getErrorMessageLabelName())
        .valueMatch(TestsConstants.VALIDATION_ERROR_TEXT_TOO_BIG_TEAM_NAME)
        .next()
        .For(repository)
        .teamNotPresent(team);
                  
        if (TeamRepository.isTeamPresent(team)) {
            TeamRepository.deleteTeamByName(team.getTeamName());
        }
        teamListPage = TeamListPage.refreshTeamList();    
                
        // Validation field with valid value.   
        final List<Team> validTeams = TeamTestObjects.getValidNameTeams();
        for (Team validTeam : validTeams) {
            newTeamPage = teamListPage.openCreateTeamPage();
            newTeamPage.getData().setAllFields(validTeam);
            newTeamPage.pressSave();
                
            specification
            .For(repository)
            .teamPresent(validTeam);
                
            if (TeamRepository.isTeamPresent(validTeam)) {
                TeamRepository.deleteTeamByName(validTeam.getTeamName());
            }
            teamListPage = TeamListPage.refreshTeamList();    
        }
        specification.check();
    }
    /**
     * This method performs the operations necessary after the test execution.
     */  
    @AfterClass
    public final void tearDown() {
        RollbackPreconditions.forValidationTeamFildsTest(teamsData);
        //DALTools.closeConnection();
        //     WebDriverUtils.stop();
        //System.out.println("Result of test-case execution:");
    }
}


