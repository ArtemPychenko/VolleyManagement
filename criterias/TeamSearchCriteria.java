package com.softserveinc.ita.volleymanagementtests.criterias;

import java.util.List;

import com.softserveinc.ita.volleymanagementtests.domain.models.Team;
import com.softserveinc.ita.volleymanagementtests.domain.repositories
            .TeamRepository;
import com.softserveinc.ita.volleymanagementtests.pages.TeamListPage;
import com.softserveinc.ita.volleymanagementtests.pages.SearchString;

/**
 * This class contain methods for
 * test Search Team functionality with different criteria.
 * @author DP-076ATQC
 *
 */
public final class TeamSearchCriteria implements ISpecification {

    /**
     * This variable for will contain testing SearchString.
     */
    private SearchString searchString;

    /**
     * Logger.
     */
    private Specification specification;

    /**
     * TeamListPage object to gain testing List.
     */
    private TeamListPage teamListPage;

    /**
     * Current testing page number.
     */
    private short currentPage;

    /**
     * The list of team reads from UI.
     */
    private List<String> teamListUI;

    /**
     * Team from the top(UI) of team list.
     */
    private String topTeam;
    /**
     * Team lower the top(UI) of team list.
     */
    private String lowerTeam;

    /**
     * Expected quantity of double team search.
     */
    private int expectedQuantity = 2;

    /**
     * Class constructor.
     * @param inputSearchString - aim SearchString.
     * @param inputSpecification - Logger.
     */
    private TeamSearchCriteria(final SearchString inputSearchString,
            final Specification inputSpecification) {
        this.searchString = inputSearchString;
        this.specification = inputSpecification;
        teamListPage = new TeamListPage();
        currentPage = teamListPage.getCurrentPageNumber();
        teamListUI = teamListPage.getTeamNameList();
        topTeam = teamListUI.get(0);
        lowerTeam = teamListUI.get(teamListUI.size() - 1);
    }

    /**
     * Static method for create new TeamSearchCriteria.
     * @param inputSearchString - aim SearchString.
     * @param inputSpecification - logger.
     * @return new TeamSearchCriteria
     */
    public static TeamSearchCriteria get(
            final SearchString inputSearchString,
            final Specification inputSpecification) {
        return new TeamSearchCriteria(inputSearchString, inputSpecification);
    }

    /**
     * This method try to find the top(UI) team of team list.
     * @return current TeamSearchCriteria.
     */
    public TeamSearchCriteria tryFindTopTeam() {
        teamListUI = searchString.inputText(topTeam)
                .getTeamListPage().getTeamNameList();
        this.specification.add((teamListUI.get(0).compareTo(topTeam) == 0)
                && (teamListUI.size() == 1),
                "\nSearch faild. The top team of list wasn't found.\n"
                        .concat(makeBugReport(topTeam, 1)));
        searchString.clickClear();
        return this;
    }

    /**
     * This method deletes the top(UI) team of team list.
     * @return current TeamSearchCriteria.
     */
    public TeamSearchCriteria deleteTopTeam() {
        TeamRepository.deleteTeamByName(topTeam);
        teamListPage = TeamListPage.refreshTeamList();
        searchString = teamListPage.getSearchString();
        return this;
    }

    /**
     * This method try to find deleted on previous test-step team.
     * @return current TeamSearchCriteria.
     */
    public TeamSearchCriteria tryFindDeletedTeam() {
        teamListUI = searchString.inputText(topTeam)
                .getTeamListPage().getTeamNameList();
        this.specification.add(teamListUI.size() == 0,
                "\nSearch faild. Deleted team was found.\n"
                        .concat(makeBugReport()));
        searchString.clickClear();
        return this;
    }

    /**
     * This method try to set up incorrect symbols in SearchString.
     * @param symbolString - string with special symbols.
     * @return current TeamSearchCriteria.
     */
    public TeamSearchCriteria tryFindIncorrectSymbols(
            final String symbolString) {
        boolean testPassed;
        for (int i = 0; i < symbolString.length(); i++) {
            testPassed = searchString
                    .inputText(symbolString.substring(i, (i + 1)))
                    .getTeamListPage().getErrorSearchMessage() != null;
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
     * This method try to find the lower(UI) team of team list.
     * @return current TeamSearchCriteria.
     */
    public TeamSearchCriteria tryFindLowerTeam() {
        teamListUI = searchString.inputText(lowerTeam)
                .getTeamListPage().getTeamNameList();
        this.specification.add(
                (teamListUI.get(0).compareTo(lowerTeam) == 0)
                        && (teamListUI.size() == 1),
                "\nSearch faild. The lower team of list wasn't found."
                        .concat(makeBugReport(lowerTeam, 1)));
        searchString.clickClear();
        return this;
    }

     /**
      * This method try to find team from previous TeamList page.
      * @return current TeamSearchCriteria.
      */
     public TeamSearchCriteria tryFindPreviousTeam() {
         teamListUI = searchString.inputText(lowerTeam)
                 .getTeamListPage().getTeamNameList();
         this.specification.add(
                 (teamListUI.get(0).compareTo(lowerTeam) == 0)
                 && (teamListUI.size() == 1),
                 "\nSearch faild. The team of previous page wasn't found. "
                 .concat(makeBugReport(lowerTeam, 1)));
         searchString.clickClear();
         return this;
     }

     /**
      * This method add the duplicate of lower(UI) team on team list.
      * @return current TeamSearchCriteria.
      */
     public TeamSearchCriteria addDuplicateTeam() {
         TeamRepository.insertTeam(new Team(lowerTeam, 0));
         teamListPage = TeamListPage.refreshTeamList();
         searchString = teamListPage.getSearchString();
         return this;
     }

     /**
      * This method try to find double of identical teams.
      * @return current TeamSearchCriteria.
      */
     public TeamSearchCriteria tryFindBothTeams() {
         teamListUI = searchString.inputText(lowerTeam)
                 .getTeamListPage().getTeamNameList();
         this.specification.add((teamListUI.get(0)
                 .compareTo(lowerTeam) == 0)
                 && (teamListUI.size() == expectedQuantity),
                 "\nSearch faild. Duplicate team wasn't found. "
                 .concat(makeBugReport(lowerTeam, expectedQuantity)));
         searchString.clickClear();
         return this;
     }

    /**
     * This method make template string
     *  with the bug report of "success team search" test.
     * @param team Expected search result team.
     * @param quantity Expected search result quantity.
     * @return Bug report.
     */
    private String makeBugReport(final String team, final int quantity) {
        StringBuilder bugReport = new StringBuilder();
        bugReport.append("Or search incorrect.\n")
                .append("Expected number of teams = ")
                .append(quantity).append(".")
                .append(" Actual number of teams = ")
                .append(String.valueOf(teamListUI.size()))
                .append("\nExpected Team name = ")
                .append(team)
                .append(". Actual Team name = ")
                .append(teamListUI.get(0));
        return bugReport.toString();
    }

    /**
     * This method make template string
     *  with the bug report of "empty team list result" search test.
     * @return Bug report.
     */
    private String makeBugReport() {
        StringBuilder bugReport = new StringBuilder();
        bugReport.append("Or search incorrect. Search return ")
                .append(String.valueOf(teamListUI.size()))
                .append(" teams.\n");
        return bugReport.toString();
    }

    /**
     * This switch on the next page of team list page.
     * @return current TeamSearchCriteria.
     */
    public TeamSearchCriteria nextPage() {
        teamListPage = teamListPage.switchPage((short) (currentPage + 1));
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
