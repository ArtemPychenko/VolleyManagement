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
import com.softserveinc.ita.volleymanagementtests.pages.TournamentListPage;
import com.softserveinc.ita.volleymanagementtests.tests.RollbackPreconditions;
import com.softserveinc.ita.volleymanagementtests.tests.SetPreconditions;
import com.softserveinc.ita.volleymanagementtests.tests.TestsConstants;
import com.softserveinc.ita.volleymanagementtests.tools.WebDriverUtils;

/**
 * This test-case for validation end tournament date field  
 * on edit tournament page of the VolleyManagement application.
 * @author Andriy Lantukh 
 * 
 * This method checks only empty field and wrong type input data.
 * It needs to append validation before start and end and other dates.
 * 
 */
public class ValidateTournamentEndDateFieldUpdate {
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
     * The tournament instance with valid data entered.
     */
    private Tournament validTournament;
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
    /**.
     * tournaments quantity before test 
     * for checking the quantity after test operations.
     */
    private int tuornamentsInDBBeforTest;
    
    
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
    public final void validateTournamentStartDateFieldCreate() {
       
        // Validation field with invalid data type. 
       tournamentListPage = homePage.getNavigationPage()
               .getTournamentListPage();
       final List<String> invalidDates = TournamentTestObjects
               .getInvalidTypeForDate();
       for (String invalidDate : invalidDates) {
           tuornamentsInDBBeforTest = TournamentRepository
                   .countAllTournaments();
           if (!TournamentRepository.isTournamentPresent(validTournament)) {
               TournamentRepository.insertTournament(validTournament);
           editTournamentPage = tournamentListPage
                   .showTournamentInfo(validTournament).pressEditButton();
           editTournamentPage.getData().getTournamentEndInput().clear();
           editTournamentPage.getData().getTournamentEndInput()
                   .type(invalidDate);
           editTournamentPage.unsuccessfulPressSave();
                                
           specification
           .For(editTournamentPage.getData()
                   .getErrorMessageLabelTournamentEnd())
           .valueMatch(TestsConstants
                   .VALIDATION_ERROR_TEXT_WRONG_TYPE_DATE)
           .next()
           .For(repository)
           .isTournamentPresent(validTournament)
           .numberOfTournamentsHasNotChanged(tuornamentsInDBBeforTest);
               
           tournamentListPage = TournamentListPage.refreshTournamentList();    
       }
       
       // Validation empty field. 
       invalidDate = TournamentTestObjects.getInvalidEmptyTypeForDate();
       tuornamentsInDBBeforTest = TournamentRepository
               .countAllTournaments();
       editTournamentPage = tournamentListPage
               .showTournamentInfo(validTournament).pressEditButton();
       editTournamentPage.getData().getTournamentEndInput().clear();
       editTournamentPage.getData().getTournamentEndInput()
               .type(invalidDate);
       editTournamentPage.unsuccessfulPressSave();
                            
       specification
       .For(editTournamentPage.getData().getErrorMessageLabelTournamentEnd())
       .valueMatch(TestsConstants
               .VALIDATION_ERROR_TEXT_EMPTY_NAME_FIELD)
       .next()
       .For(repository)
       .isTournamentPresent(validTournament)
       .numberOfTournamentsHasNotChanged(tuornamentsInDBBeforTest);
           
       tournamentListPage = TournamentListPage.refreshTournamentList();   

       // Validation field with out of range data.
       //TODO after receive more information about dates. 
       
       // Validation field with two values on edges  of range.
       //TODO after receive more information about dates. 
       
       specification.check();
       }
      
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





