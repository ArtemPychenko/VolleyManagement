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
import com.softserveinc.ita.volleymanagementtests.pages.EditTournamentPage;
import com.softserveinc.ita.volleymanagementtests.pages.HomePage;
import com.softserveinc.ita.volleymanagementtests.pages.TournamentDetailsPage;
import com.softserveinc.ita.volleymanagementtests.pages.TournamentListPage;
import com.softserveinc.ita.volleymanagementtests.tests.RollbackPreconditions;
import com.softserveinc.ita.volleymanagementtests.tests.SetPreconditions;
import com.softserveinc.ita.volleymanagementtests.tests.TestsConstants;
import com.softserveinc.ita.volleymanagementtests.tools.WebDriverUtils;

    /**
     * This test-case for validation of the scheme dropdown 
     * on edit tournament page of the VolleyManagement application.
     * @author Andriy Lantukh 
     */
public class ValidateTournamentSchemeFieldUpdate {
    /**
     * Specification object for soft-assert report create.
     */
    private Specification specification;
    /**
     * Main page object of testing application.
     */
    private HomePage homePage;
    /**
     * Edit tournament page.
     */
    private EditTournamentPage editTournamentPage;
    /**
     * Detail tournament page.
     */
    private TournamentDetailsPage tournamentDetailsPage;
    /**
     * Tournament list page.
     */
    private TournamentListPage tournamentListPage;
    /**
     * The tournament instance with valid data entered.
     */
    private Tournament validTournament;
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
        validTournament = TournamentTestObjects.getValidTournament();
        TournamentRepository.insertTournament(validTournament);
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
        
       editTournamentPage = homePage.getNavigationPage().getTournamentListPage()
               .showTournamentInfo(validTournament).pressEditButton();
       // validation available values  
       specification
       .For(editTournamentPage.getData().getSchemeDropdown())
       .valuesMatch(TournamentTestObjects.getSchemeForUI());
       
       // Validation save  with available values scheme dropdown.   
       tournamentListPage = editTournamentPage.getNavigation()
               .getTournamentListPage();
       final List<Tournament> validTournaments = TournamentTestObjects
               .getValidTournamentsScheme();
       for (Tournament tournament : validTournaments) {
           if (!TournamentRepository.isTournamentPresent(validTournament)) {
               TournamentRepository.insertTournament(validTournament);
           }
           tournamentListPage = TournamentListPage.refreshTournamentList();
           tournamentDetailsPage = tournamentListPage
                   .showTournamentInfo(validTournament);
           editTournamentPage = tournamentDetailsPage.pressEditButton();
           editTournamentPage.getData().setAllFields(tournament);
           editTournamentPage.pressSave();
           
           specification
           .For(repository)
           .isTournamentPresent(tournament)
           .isTournamentNotPresent(validTournament);
           
           if (TournamentRepository.isTournamentPresent(tournament)) {
               TournamentRepository.deleteTournament(tournament);
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



