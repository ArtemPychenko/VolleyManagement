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
import com.softserveinc.ita.volleymanagementtests.pages.NewTeamPage;
import com.softserveinc.ita.volleymanagementtests.tests.RollbackPreconditions;
import com.softserveinc.ita.volleymanagementtests.tests.SetPreconditions;
import com.softserveinc.ita.volleymanagementtests.tests.TestsConstants;
import com.softserveinc.ita.volleymanagementtests.tools.WebDriverUtils;

/** Functionality test for validate achievements field in edit page.
 * @author Dmytro Maslov */
public class ValidateAchievementsFieldInNewTeamPageTest {
    /**Variable for specification.*/
    private Specification specification;
    /**Contain home page.*/
    private HomePage homePage;
    /**Contain new team page.*/
    private NewTeamPage newTeamPage;
    /**Contain team.*/
    private Team newTeam;
    /**Team repository.*/
    private TeamRepository repository;
    /**Count team in DB.*/
    private int teamInDB;
    /**Collection of achievements.*/
    private List<String> newAchievementsList;
    /**Save all team from DB. */
    private List<Team> teamInBD;
    /**Before method.*/
    @BeforeClass
    public final void setUp() {
        DALTools.startConnection(TestsConstants.CONNECT_DB_STRING);
        teamInBD = SetPreconditions.forValidationTeamFildsTest();
        teamInDB = TeamRepository.countAllTeams();
        specification = Specification.get();
        WebDriverUtils.load(TestsConstants.HOME_PAGE);
        homePage = new HomePage();
    }
    /**Don't correct size.*/
    @Test
    public final void dontCorrectSize() {
        newTeamPage = homePage.getNavigationPage().getTeamListPage()
                .openCreateTeamPage();
        newTeam = TeamTestObjects.getValidTeam();
        newTeam.setAchievements(TeamTestObjects.getInvalidSizeAchievements());
        newTeamPage.getData().setAllFields(newTeam);
        specification
                .For(newTeamPage.getData().getErrorMessageLabelAchievements())
                .valueMatch(
                        TestsConstants
                        .VALIDATION_ERROR_TEXT_WRONG__ACHIEVEMENTS_SIZE)
                .next();
        newTeamPage.unsuccessfulPressSave();
        specification.For(repository).numberOfTeamsHasNotChanged(teamInDB)
                .next();
        specification.check();
    }
    /**Valid achievements.*/
    @Test
    public final void validAchievements() {
        newTeam = TeamTestObjects.getValidTeam();
        newAchievementsList = TeamTestObjects.getValidName();
        newAchievementsList.add(TeamTestObjects.getValidSizeAchievements());
        for (String validAchievements : newAchievementsList) {
            newTeamPage = homePage.getNavigationPage().getTeamListPage()
                    .openCreateTeamPage();
            newTeam.setAchievements(validAchievements);
            newTeamPage.getData().setAllFields(newTeam);
            newTeamPage.pressSave();
            specification.For(repository).numberOfTeamsHasIncreased(teamInDB)
                    .next().For(repository).teamPresent(newTeam).next();
            teamInDB = TeamRepository.countAllTeams();
        }
        specification.check();
    }
    /**After method.*/
    @AfterClass
    public final void tearDown() {
        RollbackPreconditions.forValidationTeamFildsTest(teamInBD);
    }

}
