package com.softserveinc.ita.volleymanagementtests.pages;

import java.util.List;

import com.softserveinc.ita.volleymanagementtests.domain.models.Team;
import com.softserveinc.ita.volleymanagementtests.tests.TestsConstants;
import com.softserveinc.ita.volleymanagementtests.tools.WebDriverUtils;
import com.softserveinc.ita.volleymanagementtests.tools.controls.Grid;
import com.softserveinc.ita.volleymanagementtests.tools.controls.interfaces.IButton;
import com.softserveinc.ita.volleymanagementtests.tools.controls.interfaces.ILabel;
import com.softserveinc.ita.volleymanagementtests.tools.controls.interfaces.ILink;
import com.softserveinc.ita.volleymanagementtests.tools.controls.interfaces.ITextInput;
import com.softserveinc.ita.volleymanagementtests.tools.controls.interfaces.IValidationLabel;

/**
 * @author Dp-076 ATQC This class describes team list page.
 */
public class TeamListPage extends Page{
    /**
     * @param controls
     *            encapsulates map of web site.
     */
    private TeamListPageUIMap controls;
    private NavigationPage navigationPage;

    /**
     * Constructor of the class.
     */
    public TeamListPage() {
        controls = new TeamListPageUIMap();
        navigationPage = new NavigationPage();
    }

    public NavigationPage getNavigation() {
        return this.navigationPage;
    }

    /**
     * @return new page with team info.
     */
    public final TeamDetailPage showTeamInfo() {
        controls.getTeamsList().get(0).click();
        return new TeamDetailPage();
    }

    /**
     * @return new page where you can add new team.
     */
    public final NewTeamPage openCreateTeamPage() {
        controls.getAddButton().click();
        return new NewTeamPage();
    }

    /**
     * @return the same page with searching results.
     */
    public final TeamListPage searchTeam(String searchRequest) {
        controls.getSearchTextInput().type(searchRequest);
        return this;
    }

    /**
     * @return new page with team list.
     */
    public final TeamListPage checkReturnButtonFunction() {
        controls.getSearchReturnButton().click();
        return new TeamListPage();
    }

    /**
     * @return number of current page.
     */
    public short getCurrentPageNumber() {
        short currentPageNumber = 0;
        ILink activeLink = controls.getActivePageLink();
        if (activeLink == null) {
            return 1;
        }
        currentPageNumber = Short.valueOf(activeLink
                .getText());
        return currentPageNumber;
    }
    
    /**
     * @return go to a specified page.
     */
    public final TeamListPage switchPage(short pageNumber) {
        if(getPageQuantity() == 1) {
            throw new IllegalStateException("You can't switch "
                    + "because only one page is present.");
        }
        if(controls.getPageNumbers().getSize() < pageNumber) {
            throw new IllegalStateException("You can't switch "
                    + "because pageNumber to switch out of range pages.");
        }
        if(getCurrentPageNumber() == pageNumber) {
            throw new IllegalStateException("You can't switch "
                    + "because you already at this page.");
        }
        controls.getPageNumbers().get(pageNumber - 1).click();
        return new TeamListPage();
    }
    
    /**
     * @return page quantity.
     */
    public int getPageQuantity() {
        Grid pagingGrid = controls.getPageNumbers();
        if (pagingGrid == null) {
            return 1;
        }
        return pagingGrid.getSize();
    }

    public final ILabel getTeamsListLabel() {
        return controls.getTeamsListLabel();
    }

    public final IButton getAddButton() {
        return controls.getAddButton();
    }

    public final IButton getSearchReturnButton() {
        return controls.getSearchReturnButton();
    }

    public final ITextInput getSearchTextInput() {
        return controls.getSearchTextInput();
    }

    /**
     * @return new page with team info for current team.
     */
    public final TeamDetailPage showTeamInfo(Team team) {
        int pagesQuantity = getPageQuantity();
        boolean isTeamNotFound = true;
        TeamListPage teamListPage = new TeamListPage();
        if (pagesQuantity == 1) {
            return showTeamInfoOnPage(team);
        }
        if (teamListPage.getCurrentPageNumber() != 1) {
            teamListPage = teamListPage.switchPage((short) 1);
        }
        while (isTeamNotFound) {
            TeamDetailPage teamDetailPage = showTeamInfoOnPage(team);
            if (teamDetailPage != null) {
                isTeamNotFound = false;
                return teamDetailPage;
            }
            if (teamListPage.getCurrentPageNumber() == pagesQuantity) {
                break;
            }
            short nextPageNumber = (short)(teamListPage
                    .getCurrentPageNumber() + 1);
            teamListPage = teamListPage.switchPage(nextPageNumber);
        }
        return null;
    }

    public final TeamDetailPage showTeamInfoOnPage(Team team) {
        String teamsNames = team.getTeamName();
        Grid teamLinks = controls.getTeamsList();
        int rowsQuantity =  teamLinks.getSize();
        for (int i = 0; i < rowsQuantity; i++ ) {
            if (teamLinks.get(i).getText().equals(teamsNames)) {
                teamLinks.get(i).click();
                return new TeamDetailPage();
            }
        }
    return null;
    }

    /**
     * @return New SearchString.
     */
    public final SearchString getSearchString() {
        return new SearchString();
    }

    /**
     * @return The new Error Message of search.
     */
    public final IValidationLabel getErrorSearchMessage() {
        return controls.getErrorMessage();
    }

    /**
     * @return The list of Team name on current page.
     */
    public List<String> getTeamNameList() {
        return controls.getTeamNameList();
    }

    /**
     * This method does update team list.
     * 
     * @return TeamListPage
     */
    public static final TeamListPage refreshTeamList() {
        WebDriverUtils.load("http://localhost:23801/");
        WebDriverUtils.load(TestsConstants.HOME_PAGE);
        NavigationPage navigationPage = new NavigationPage();
        TeamListPage teamListPage = navigationPage.getTeamListPage();
        return teamListPage;
    }
}
