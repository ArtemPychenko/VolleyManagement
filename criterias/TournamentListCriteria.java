package com.softserveinc.ita.volleymanagementtests.criterias;

import java.util.ArrayList;
import java.util.List;

import com.softserveinc.ita.volleymanagementtests.domain.models.Tournament;
import com.softserveinc.ita.volleymanagementtests.domain.repositories
                                                         .TournamentRepository;
import com.softserveinc.ita.volleymanagementtests.pages.TournamentListPage;
import com.softserveinc.ita.volleymanagementtests.tests.TestsConstants;

/**
 * This class contain methods for
 * test Tournament list with different criteria.
 *
 * @author DP-076ATQC
 */
public final class TournamentListCriteria implements ISpecification {

    /**
     * The list of tournament reads from UI.
     */
    private static List<String> tournamentListUI = new ArrayList<String>(0);

    /**
     * The list of tournament reads from DB.
     */
    private static List<Tournament> tournamentListDB;

    /**
     * If (isSortingTestFaild = true),
     * then isTournamentsSortedCorrect() criteria not checks for next page.
     */
    private static boolean isSortingTestPassed = true;

    /**
     * The page quantity on current tournament list window.
     */
    private static int pageQuantity;

    /**
     * Current tournament list window.
     */
    private TournamentListPage tournamentsPage;

    /**
     * Logger.
     */
    private Specification specification;

    /**
     * Class constructor.
     *
     * @param inputPage - aim tournament list page for test.
     * @param inputSpecification - Logger.
     */
    private TournamentListCriteria(final TournamentListPage inputPage,
            final Specification inputSpecification) {
        this.tournamentsPage = inputPage;
        this.specification = inputSpecification;
    }

    /**
     * Static method for create new TournamentListCriteria.
     * @param inputPage - aim tournament list page for test..
     * @param inputSpecification - logger.
     * @return new TournamentListCriteria
     */
    public static TournamentListCriteria get(final TournamentListPage inputPage,
            final Specification inputSpecification) {
        return new TournamentListCriteria(inputPage, inputSpecification);
    }

    /**
     * This method read and saves all tournaments from DB in tournamentListDB.
     * @return current TournamentListCriteria.
     */
    public TournamentListCriteria readTournamentsFromDB() {
        TournamentListCriteria.tournamentListDB
        = TournamentRepository.getAllTournaments();
        TournamentListCriteria.pageQuantity = (tournamentListDB.size() - 1)
                / TestsConstants.ITEMS_PER_PAGE + 1;
        return this;
    }

    /**
     * This method read all tournaments from current UI page.
     *  And saves them in tournamentListUI.
     * @return current TournamentListCriteria.
     */
    public TournamentListCriteria readTournamentsFromUI() {
        TournamentListCriteria.tournamentListUI
                .addAll(tournamentsPage.getTournamentNameList());
        return this;
    }

    /**
     * This method checks compliance of pages quantity from UI and from DB.
     * @return current TournamentListCriteria.
     */
    public TournamentListCriteria isPageQuantityCorrect() {
        this.specification.add(
                TournamentListCriteria.pageQuantity
                == tournamentsPage.getPageQuantity(),
                "The quantity of tournament pages is not correct.\n");
        return this;
    }

    /**
     * This method checks compliance of current page number and input number.
     * @param currentPage - expected current page number.
     * @return current TournamentListCriteria.
     */
    public TournamentListCriteria isCurrentPageNumCorrect(
            final int currentPage) {
        this.specification.add(
                tournamentsPage.getCurrentPageNumber() == currentPage,
                "The number of current tournament page is not correct.\n");
        return this;
    }

    /**
     * This method checks compliance of tournaments quantity on UI and SRS.
     * @return current TournamentListCriteria.
     */
    public TournamentListCriteria isQuantityTournamentsCorrect() {
        boolean isQuantityCorrect = true;
        if (tournamentsPage.getCurrentPageNumber()
                == TournamentListCriteria.pageQuantity) {
            isQuantityCorrect = (tournamentsPage.getTournamentNameList().size()
                    % TestsConstants.ITEMS_PER_PAGE == tournamentListDB.size()
                    % TestsConstants.ITEMS_PER_PAGE);
        } else {
            isQuantityCorrect = (tournamentsPage.getTournamentNameList().size()
                    == TestsConstants.ITEMS_PER_PAGE);
        }
        this.specification.add(isQuantityCorrect,
               "The quantity of tournaments on current page is not correct.\n");
        return this;
    }

    /**
     * Method checks compliance of tournament list sorting from UI and from SRS.
     * @return current TournamentListCriteria.
     */
    public TournamentListCriteria isTournamentsSortedCorrect() {
        if (TournamentListCriteria.isSortingTestPassed) {
            List<String> tournamentSeasonList = new ArrayList<String>(0);
            tournamentSeasonList.addAll(tournamentsPage
                    .getTournamentSeasonList());
            tournamentSeasonList.sort(null);
            boolean isEqual = true;
            StringBuilder compareReport = new StringBuilder();
            for (int i = 0; i < tournamentSeasonList.size(); i++) {
                isEqual &= tournamentsPage.getTournamentSeasonList().get(i)
                        .equals(tournamentSeasonList.get(i));
                compareReport.append(tournamentsPage
                        .getTournamentSeasonList().get(i))
                .append(" - ").append(tournamentSeasonList.get(i)).append("\n");
            }
            TournamentListCriteria.isSortingTestPassed = isEqual;
            this.specification.add(isEqual,
                    "Tournaments aren`t sorted by Season.\n"
                    .concat("Actual result - Expected result\n")
                    .concat(compareReport.toString()));
        }
        return this;
    }

    /**
     * Method repeats test-steps from test-case for certain page.
     * @param pageNum page number for test-steps.
     */
    private void testCaseForPage(final int pageNum) {
        tournamentsPage = tournamentsPage.switchPage((short) pageNum);
        next().For(tournamentsPage)
                .isCurrentPageNumCorrect(pageNum)
                .isQuantityTournamentsCorrect()
                .isTournamentsSortedCorrect();
    }

    /**
     * Method repeats test-steps from test-case for all pages, except current.
     * @return current TournamentListCriteria.
     */
    public TournamentListCriteria repeatTestForAllPages() {
        readTournamentsFromUI();
        for (int i = (tournamentsPage.getCurrentPageNumber() + 1);
                i <= tournamentsPage.getPageQuantity(); i++) {
            testCaseForPage(i);
            readTournamentsFromUI();
        }
        return this;
    }

    /**
     * Method repeats test-steps from test-case for certain page.
     * @param pageNum page number for test-steps.
     * @return current TournamentListCriteria.
     */
    public TournamentListCriteria repeatTestForPage(final int pageNum) {
        testCaseForPage(pageNum);
        return this;
    }

    /**
     * Method checks compliance of full tournament list from UI and from DB.
     * @return current TournamentListCriteria.
     */
    public TournamentListCriteria isUITournamentListCorrespondsDB() {
        List<String> tournamentListNameDB = new ArrayList<String>(0);
        for (Tournament tournament : TournamentListCriteria.tournamentListDB) {
            tournamentListNameDB.add(tournament.getName());
        }
        tournamentListNameDB.sort(null);
        tournamentListUI.sort(null);
        this.specification.add(tournamentListUI.equals(tournamentListNameDB),
                "Tournament list readed from UI isn`t corresponds to DB");
        return this;
    }

    /**
     * @see com.softserveinc.ita
     *       .volleymanagementtests.criterias.ISpecification#next().
     * @return specification - Logger.
     */
    public Specification next() {
        return this.specification;
    }

}
