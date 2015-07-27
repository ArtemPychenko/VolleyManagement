package com.softserveinc.ita.volleymanagementtests.criterias;

import java.util.List;

import com.softserveinc.ita.volleymanagementtests.domain.repositories
            .TournamentRepository;
import com.softserveinc.ita.volleymanagementtests.domain.repositories
                                                        .TournamentTestObjects;
import com.softserveinc.ita.volleymanagementtests.pages.TournamentListPage;
import com.softserveinc.ita.volleymanagementtests.pages.SearchString;

/**
 * This class contain methods for
 * test Search Tournament functionality with different criteria.
 * @author DP-076ATQC
 *
 */
public final class TournamentSearchCriteria implements ISpecification {

    /**
     * This variable for will contain testing SearchString.
     */
    private SearchString searchString;

    /**
     * Logger.
     */
    private Specification specification;

    /**
     * TournamentListPage object to gain testing List.
     */
    private TournamentListPage tournamentListPage;

    /**
     * Current testing page number.
     */
    private short currentPage;

    /**
     * The list of tournament reads from UI.
     */
    private List<String> tournamentListUI;

    /**
     * Tournament from the top(UI) of tournament list.
     */
    private String topTournament;
    /**
     * Tournament lower the top(UI) of tournament list.
     */
    private String lowerTournament;

    /**
     * Expected quantity of double tournament search.
     */
    private int expectedQuantity = 2;

    /**
     * Class constructor.
     * @param inputSearchString - aim SearchString.
     * @param inputSpecification - Logger.
     */
    private TournamentSearchCriteria(final SearchString inputSearchString,
            final Specification inputSpecification) {
        this.searchString = inputSearchString;
        this.specification = inputSpecification;
        tournamentListPage = new TournamentListPage();
        currentPage = tournamentListPage.getCurrentPageNumber();
        tournamentListUI = tournamentListPage.getTournamentNameList();
        topTournament = tournamentListUI.get(0);
        lowerTournament = tournamentListUI.get(tournamentListUI.size() - 1);
    }

    /**
     * Static method for create new TournamentSearchCriteria.
     * @param inputSearchString - aim SearchString.
     * @param inputSpecification - logger.
     * @return new TournamentSearchCriteria
     */
    public static TournamentSearchCriteria get(
            final SearchString inputSearchString,
            final Specification inputSpecification) {
        return new TournamentSearchCriteria(
                inputSearchString, inputSpecification);
    }

    /**
     * This method try to find the top(UI) tournament of tournament list.
     * @return current TournamentSearchCriteria.
     */
    public TournamentSearchCriteria tryFindTopTournament() {
        tournamentListUI = searchString.inputText(topTournament)
                .getTournamentListPage().getTournamentNameList();
        this.specification.add((
                tournamentListUI.get(0).compareTo(topTournament) == 0)
                && (tournamentListUI.size() == 1),
                "\nSearch faild. The top tournament of list wasn't found.\n"
                        .concat(makeBugReport(topTournament, 1)));
        searchString.clickClear();
        return this;
    }

    /**
     * This method deletes the top(UI) tournament of tournament list.
     * @return current TournamentSearchCriteria.
     */
    public TournamentSearchCriteria deleteTopTournament() {
        TournamentRepository.deleteTournamentByName(topTournament);
        tournamentListPage = TournamentListPage.refreshTournamentList();
        searchString = tournamentListPage.getSearchString();
        return this;
    }

    /**
     * This method try to find deleted on previous test-step tournament.
     * @return current TournamentSearchCriteria.
     */
    public TournamentSearchCriteria tryFindDeletedTournament() {
        tournamentListUI = searchString.inputText(topTournament)
                .getTournamentListPage().getTournamentNameList();
        this.specification.add(tournamentListUI.size() == 0,
                "\nSearch faild. Deleted tournament was found.\n"
                        .concat(makeBugReport()));
        searchString.clickClear();
        return this;
    }

    /**
     * This method try to set up incorrect symbols in SearchString.
     * @param symbolString - string with special symbols.
     * @return current TournamentSearchCriteria.
     */
    public TournamentSearchCriteria tryFindIncorrectSymbols(
            final String symbolString) {
        boolean testPassed;
        for (int i = 0; i < symbolString.length(); i++) {
            testPassed = searchString
                    .inputText(symbolString.substring(i, (i + 1)))
                    .getTournamentListPage().getErrorSearchMessage() != null;
            this.specification.add(testPassed, "Error message for \""
                            .concat(symbolString.substring(i, (i + 1)))
                            .concat("\" in search string not appear."));
            searchString.clickClear();
            if (!testPassed) {
                break;
            }
        }
        return this;
    }

    /**
     * This method try to find the lower(UI) tournament of tournament list.
     * @return current TournamentSearchCriteria.
     */
    public TournamentSearchCriteria tryFindLowerTournament() {
        tournamentListUI = searchString.inputText(lowerTournament)
                .getTournamentListPage().getTournamentNameList();
        this.specification.add(
                (tournamentListUI.get(0).compareTo(lowerTournament) == 0)
                        && (tournamentListUI.size() == 1),
                "\nSearch faild. The lower tournament of list wasn't found."
                        .concat(makeBugReport(lowerTournament, 1)));
        searchString.clickClear();
        return this;
    }

     /**
      * This method try to find tournament from previous TournamentList page.
      * @return current TournamentSearchCriteria.
      */
     public TournamentSearchCriteria tryFindPreviousTournament() {
         tournamentListUI = searchString.inputText(lowerTournament)
                 .getTournamentListPage().getTournamentNameList();
         this.specification.add(
                 (tournamentListUI.get(0).compareTo(lowerTournament) == 0)
                 && (tournamentListUI.size() == 1),
                 "\nSearch faild. Tournament from previous page wasn't found. "
                 .concat(makeBugReport(lowerTournament, 1)));
         searchString.clickClear();
         return this;
     }

     /**
      * Method add the duplicate of lower(UI) tournament in to tournament list.
      * @return current TournamentSearchCriteria.
      */
     public TournamentSearchCriteria addDuplicateTournament() {
         TournamentRepository.insertTournament(TournamentTestObjects
                 .getValidTournament(lowerTournament));
         tournamentListPage = TournamentListPage.refreshTournamentList();
         searchString = tournamentListPage.getSearchString();
         return this;
     }

     /**
      * This method try to find double of identical tournaments.
      * @return current TournamentSearchCriteria.
      */
     public TournamentSearchCriteria tryFindBothTournaments() {
         tournamentListUI = searchString.inputText(lowerTournament)
                 .getTournamentListPage().getTournamentNameList();
         this.specification.add((tournamentListUI.get(0)
                 .compareTo(lowerTournament) == 0)
                 && (tournamentListUI.size() == expectedQuantity),
                 "\nSearch faild. Duplicate tournament wasn't found. "
                 .concat(makeBugReport(lowerTournament, expectedQuantity)));
         searchString.clickClear();
         return this;
     }

    /**
     * This method make template string
     *  with the bug report of "success tournament search" test.
     * @param tournament Expected search result tournament.
     * @param quantity Expected search result quantity.
     * @return Bug report.
     */
    private String makeBugReport(final String tournament, final int quantity) {
        StringBuilder bugReport = new StringBuilder();
        bugReport.append("Or search incorrect.\n")
                .append("Expected number of tournaments = ")
                .append(quantity).append(".")
                .append(" Actual number of tournaments = ")
                .append(String.valueOf(tournamentListUI.size()))
                .append("\nExpected Tournament name = ")
                .append(tournament)
                .append(". Actual Tournament name = ")
                .append(tournamentListUI.get(0));
        return bugReport.toString();
    }

    /**
     * This method make template string
     *  with the bug report of "empty tournament list result" search test.
     * @return Bug report.
     */
    private String makeBugReport() {
        StringBuilder bugReport = new StringBuilder();
        bugReport.append("Or search incorrect. Search return ")
                .append(String.valueOf(tournamentListUI.size()))
                .append(" tournaments.\n");
        return bugReport.toString();
    }

    /**
     * This switch on the next page of tournament list page.
     * @return current TournamentSearchCriteria.
     */
    public TournamentSearchCriteria nextPage() {
        tournamentListPage = tournamentListPage
                .switchPage((short) (currentPage + 1));
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
