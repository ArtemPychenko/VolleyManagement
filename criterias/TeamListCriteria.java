package com.softserveinc.ita.volleymanagementtests.criterias;

import java.util.ArrayList;
import java.util.List;

import com.softserveinc.ita.volleymanagementtests.domain.models.Team;
import com.softserveinc.ita.volleymanagementtests.domain.repositories
                                                            .TeamRepository;
import com.softserveinc.ita.volleymanagementtests.pages.TeamListPage;
import com.softserveinc.ita.volleymanagementtests.tests.TestsConstants;

/**
 * This class contain methods for
 * test Team list with different criteria.
 *
 * @author DP-076ATQC
 */
public final class TeamListCriteria implements ISpecification {

    /**
     * The list of team reads from UI.
     */
    private static List<String> teamListUI = new ArrayList<String>(0);

    /**
     * The list of team reads from DB.
     */
    private static List<Team> teamListDB;

    /**
     * If (isSortingTestFaild = true),
     * then isTeamsSortedCorrect() criteria not checks for next page.
     */
    private static boolean isSortingTestPassed = true;

    /**
     * The page quantity on current team list window.
     */
    private static int pageQuantity;

    /**
     * Current team list window.
     */
    private TeamListPage teamsPage;

    /**
     * Logger.
     */
    private Specification specification;

    /**
     * Class constructor.
     *
     * @param inputPage - aim team list page for test.
     * @param inputSpecification - Logger.
     */
    private TeamListCriteria(final TeamListPage inputPage,
            final Specification inputSpecification) {
        this.teamsPage = inputPage;
        this.specification = inputSpecification;
    }

    /**
     * Static method for create new TeamListCriteria.
     * @param inputPage - aim team list page for test..
     * @param inputSpecification - logger.
     * @return new TeamListCriteria
     */
    public static TeamListCriteria get(final TeamListPage inputPage,
            final Specification inputSpecification) {
        return new TeamListCriteria(inputPage, inputSpecification);
    }

    /**
     * This method read and saves all teams from DB in teamListDB.
     * @return current TeamListCriteria.
     */
    public TeamListCriteria readTeamsFromDB() {
        TeamListCriteria.teamListDB = TeamRepository.getAllTeams();
        TeamListCriteria.pageQuantity = (teamListDB.size() - 1)
                / TestsConstants.ITEMS_PER_PAGE + 1;
        return this;
    }

    /**
     * This method read all teams from current UI page.
     *  And saves them in teamListUI.
     * @return current TeamListCriteria.
     */
    public TeamListCriteria readTeamsFromUI() {
        TeamListCriteria.teamListUI
                .addAll(teamsPage.getTeamNameList());
        return this;
    }

    /**
     * This method checks compliance of pages quantity from UI and from DB.
     * @return current TeamListCriteria.
     */
    public TeamListCriteria isPageQuantityCorrect() {
        this.specification.add(
                TeamListCriteria.pageQuantity
                == teamsPage.getPageQuantity(),
                "The quantity of team pages is not correct.\n");
        return this;
    }

    /**
     * This method checks compliance of current page number and input number.
     * @param currentPage - expected current page number.
     * @return current TeamListCriteria.
     */
    public TeamListCriteria isCurrentPageNumCorrect(final int currentPage) {
        this.specification.add(
                teamsPage.getCurrentPageNumber() == currentPage,
                "The number of current team page is not correct.\n");
        return this;
    }

    /**
     * This method checks compliance of teams quantity on UI and SRS.
     * @return current TeamListCriteria.
     */
    public TeamListCriteria isQuantityTeamsCorrect() {
        boolean isQuantityCorrect = true;
        if (teamsPage.getCurrentPageNumber()
                == TeamListCriteria.pageQuantity) {
            isQuantityCorrect = (teamsPage.getTeamNameList().size()
                    % TestsConstants.ITEMS_PER_PAGE == teamListDB.size()
                    % TestsConstants.ITEMS_PER_PAGE);
        } else {
            isQuantityCorrect = (teamsPage.getTeamNameList().size()
                    == TestsConstants.ITEMS_PER_PAGE);
        }
        this.specification.add(isQuantityCorrect,
                "The quantity of teams on current page is not correct.\n");
        return this;
    }

    /**
     * Method checks compliance of team list sorting from UI and from SRS.
     * @return current TeamListCriteria.
     */
    public TeamListCriteria isTeamsSortedCorrect() {
        if (TeamListCriteria.isSortingTestPassed) {
            List<String> teamList = new ArrayList<String>(0);
            teamList.addAll(teamsPage.getTeamNameList());
            teamList.sort(null);
            boolean isEqual = true;
            StringBuilder compareReport = new StringBuilder();
            for (int i = 0; i < teamList.size(); i++) {
                isEqual &= teamsPage.getTeamNameList().get(i)
                        .equals(teamList.get(i));
                compareReport.append(teamsPage.getTeamNameList().get(i))
                .append(" - ").append(teamList.get(i)).append("\n");
            }
            TeamListCriteria.isSortingTestPassed = isEqual;
            this.specification.add(isEqual,
                    "Teams aren`t sorted alphabetically by Team Name.\n"
                    .concat("Actual result - Expected result\n")
                    .concat(compareReport.toString()));
        }
        return this;
    }

    /**
     * Method repeats test-steps from test-case for certain page.
     * isCurrentPageNumCorrect(pageNum) and isQuantityTeamsCorrect() checks.
     * @param pageNum page number for test-steps.
     */
    private void testCaseForPage(final int pageNum) {
        teamsPage = teamsPage.switchPage((short) pageNum);
        next().For(teamsPage)
                .isCurrentPageNumCorrect(pageNum)
                .isQuantityTeamsCorrect()
                .isTeamsSortedCorrect();
    }

    /**
     * Method repeats test-steps from test-case for all pages, except current.
     * @return current TeamListCriteria.
     */
    public TeamListCriteria repeatTestForAllPages() {
        readTeamsFromUI();
        for (int i = (teamsPage.getCurrentPageNumber() + 1); i <= teamsPage
                .getPageQuantity(); i++) {
            testCaseForPage(i);
            readTeamsFromUI();
        }
        return this;
    }

    /**
     * Method repeats test-steps from test-case for certain page.
     * @param pageNum page number for test-steps.
     * @return current TeamListCriteria.
     */
    public TeamListCriteria repeatTestForPage(final int pageNum) {
        testCaseForPage(pageNum);
        return this;
    }

    /**
     * Method checks compliance of full team list from UI and from DB.
     * @return current TeamListCriteria.
     */
    public TeamListCriteria isUITeamListCorrespondsDB() {
        List<String> teamListNameDB = new ArrayList<String>(0);
        for (Team team : TeamListCriteria.teamListDB) {
            teamListNameDB.add(team.getTeamName());
        }
        teamListNameDB.sort(null);
        teamListUI.sort(null);
        this.specification.add(teamListUI.equals(teamListNameDB),
                "Team list readed from UI isn`t corresponds to DB");
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
