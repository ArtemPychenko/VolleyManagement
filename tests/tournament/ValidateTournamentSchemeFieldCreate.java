package com.softserveinc.ita.volleymanagementtests.tests.tournament;

import java.util.List;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.softserveinc.ita.volleymanagementtests.criterias.Specification;
import com.softserveinc.ita.volleymanagementtests.domain.models.Tournament;
import com.softserveinc.ita.volleymanagementtests.domain.repositories.DALTools;
import com.softserveinc.ita.volleymanagementtests.domain.repositories
        .TournamentRepository;
import com.softserveinc.ita.volleymanagementtests.domain.repositories
        .TournamentTestObjects;
import com.softserveinc.ita.volleymanagementtests.pages.HomePage;
import com.softserveinc.ita.volleymanagementtests.pages.NewTournamentPage;
import com.softserveinc.ita.volleymanagementtests.pages.TournamentListPage;
import com.softserveinc.ita.volleymanagementtests.tests.RollbackPreconditions;
import com.softserveinc.ita.volleymanagementtests.tests.SetPreconditions;
import com.softserveinc.ita.volleymanagementtests.tests.TestsConstants;
import com.softserveinc.ita.volleymanagementtests.tools.WebDriverUtils;

/**
 * This test-case for validation of the scheme dropdown 
 * on new tournament page of the VolleyManagement application.
 * @author Andriy Lantukh 
 */
public class ValidateTournamentSchemeFieldCreate {
    /**
     * Specification object for soft-assert report create.
     */
    private Specification specification;
    /**
     * Main page object of testing application.
     */
    private HomePage homePage;
    /**
     * New tournament page.
     */
    private NewTournamentPage newTournamentPage;
    /**
     * Tournament list page.
     */
    private TournamentListPage tournamentListPage;
    /**
     * tournamentsData will save the tournaments in set up method 
     * in their current state to restore them after test 
     * will complete in tear down method.
     */
    private List<Tournament> tournamentsData;
    /**
     *  database access.
     */
    private TournamentRepository repository;
    
    
    /**
     * This method performs the operations necessary to run the test.
     */
    @BeforeClass
    public final void setUp() {
        DALTools.startConnection(TestsConstants.CONNECT_DB_STRING);
        tournamentsData = SetPreconditions.forValidationTournamentFieldsTest();
        WebDriverUtils.load(TestsConstants.HOME_PAGE);
        homePage = new HomePage();
        specification = Specification.get();
    }
    /**
     * If valid tournament entered.
     * The tournament should be saved in the database.
     */        
    @Test
    public final void validateTournamentSchemeDropdown() {
        
       newTournamentPage = homePage.getNavigationPage().getTournamentListPage()
               .openCreateTournamentPage();
       // validation available values  
       specification
       .For(newTournamentPage.getData().getSchemeDropdown())
       .valuesMatch(TournamentTestObjects.getSchemeForUI());
       
       // Validation save  with available values scheme dropdown.   
       tournamentListPage = newTournamentPage.pressCancel();
       final List<Tournament> validTournaments = TournamentTestObjects
               .getValidTournamentsScheme();
       for (Tournament validTournament : validTournaments) {
           newTournamentPage = tournamentListPage.openCreateTournamentPage();
           newTournamentPage.getData().setAllFields(validTournament);
           newTournamentPage.pressSave();
               
           specification
           .For(repository)
           .isTournamentPresent(validTournament);
               
           if (TournamentRepository.isTournamentPresent(validTournament)) {
               TournamentRepository.deleteTournamentByName(validTournament
                       .getName());
           }
           tournamentListPage = TournamentListPage.refreshTournamentList();    
       }
       specification.check();
    }
    
    /**
     * This method performs the operations necessary after the test execution.
     */
    @AfterClass
    public final void tearDown() {
        RollbackPreconditions
            .forValidationTournamentFieldsTest(tournamentsData);
        //DALTools.closeConnection();
        //WebDriverUtils.stop();
    }
}


