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
import com.softserveinc.ita.volleymanagementtests.pages.EditTeamPage;
import com.softserveinc.ita.volleymanagementtests.pages.TeamDetailPage;
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
public class ValidateTeamNameFieldUpdate {
    /**
     * an instance of the page with the team's details.
     */
    private TeamDetailPage teamDetailPage;
    /**
     * an instance of the team's edit page.
     */
    private EditTeamPage editTeamPage;
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
     * The team instance with valid data entered.
     */
    private Team validTeam;
    /**
     * This method performs the operations necessary to run the test.
     */
    @BeforeClass
    public final void setUp() {
        DALTools.startConnection(TestsConstants.CONNECT_DB_STRING);
        teamsData = SetPreconditions.forValidationTeamFildsTest();
        WebDriverUtils.load(TestsConstants.HOME_PAGE);
        specification = Specification.get();
        validTeam = TeamTestObjects.getValidTeam();
    }
    /**
     * If valid team entered.
     * The team should be saved in the database.
     */              
    @Test
    public final void validateTeamNameValidField() {
        
        // Validation field with valid value.   
        final List<Team> validTeams = TeamTestObjects.getValidNameTeams();
        for (Team theTeam : validTeams) {
            if (!TeamRepository.isTeamPresent(validTeam)) {
                TeamRepository.insertTeam(validTeam);
            }
            teamListPage = TeamListPage.refreshTeamList();
            teamDetailPage = teamListPage.showTeamInfo(validTeam);
            editTeamPage = teamDetailPage.pressEditButton();
            editTeamPage.getData().setAllFields(theTeam);
            editTeamPage.pressSave();
            
            specification
            .For(repository)
            .teamPresent(theTeam)
            .teamNotPresent(validTeam);
            
            if (TeamRepository.isTeamPresent(theTeam)) {
                TeamRepository.deleteTeamByName(theTeam.getTeamName());
            }
            teamListPage = TeamListPage.refreshTeamList();    
        }
        specification.check();
    }
    /**
     * The team shouldn't be save in the database.
     * The validation messages should be shown. 
     */   
    @Test(dependsOnMethods = {"validateTeamNameValidField"}, alwaysRun = true)
    public final void validateTeamNameEmptyField() { 
        
        // Validation with empty field.
        team = TeamTestObjects.getInvalidEmptyTeamName();
        if (!TeamRepository.isTeamPresent(validTeam)) {
                TeamRepository.insertTeam(validTeam);
        }
        teamListPage = TeamListPage.refreshTeamList();
        teamDetailPage = teamListPage.showTeamInfo(validTeam);
        editTeamPage = teamDetailPage.pressEditButton();
        editTeamPage.getData().setAllFields(team);
        editTeamPage.unsuccessfulPressSave();
            
        specification
        .For(editTeamPage.getData().getErrorMessageLabelName())
        .valueMatch(TestsConstants
                .VALIDATION_ERROR_TEXT_EMPTY_NAME_FIELD)
        .next()
        .For(repository)
        .teamNotPresent(team)
        .teamPresent(validTeam);
            
        if (TeamRepository.isTeamPresent(team)) {
            TeamRepository.deleteTeamByName(team.getTeamName());
        }
        teamListPage = TeamListPage.refreshTeamList();    
        
        specification.check();
    }
    /**
     * The team shouldn't be save in the database.
     * The validation messages should be shown. 
     */ 
    @Test(dependsOnMethods = {"validateTeamNameEmptyField"}, alwaysRun = true)
    public final void validateTeamNameBigField() { 
        
        // Validation field with more than 30 symbols field. 
        team = TeamTestObjects.getInvalidTooBigTeamName();
        if (!TeamRepository.isTeamPresent(validTeam)) {
            TeamRepository.insertTeam(validTeam);
        }
        teamListPage = TeamListPage.refreshTeamList();
        teamDetailPage = teamListPage.showTeamInfo(validTeam);
        editTeamPage = teamDetailPage.pressEditButton();
        editTeamPage.getData().setAllFields(team);
        editTeamPage.unsuccessfulPressSave();
        
        specification
        .For(editTeamPage.getData().getErrorMessageLabelName())
        .valueMatch(TestsConstants
                .VALIDATION_ERROR_TEXT_TOO_BIG_TEAM_NAME)
        .next()
        .For(repository)
        .teamNotPresent(team)
        .teamPresent(validTeam);
        
        if (TeamRepository.isTeamPresent(team)) {
            TeamRepository.deleteTeamByName(team.getTeamName());
        }
       
        specification.check();
    }
    /**
     * This method performs the operations necessary after the test
     * execution.
     */   
    @AfterClass
    public final void tearDown() {
        RollbackPreconditions.forValidationTeamFildsTest(teamsData);
        //DALTools.closeConnection();
        //     WebDriverUtils.stop();
        //System.out.println("Result of test-case execution:");
    }
}


