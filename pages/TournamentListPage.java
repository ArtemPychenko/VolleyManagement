package com.softserveinc.ita.volleymanagementtests.pages;

import java.util.List;

import com.softserveinc.ita.volleymanagementtests.domain.models.Tournament;
import com.softserveinc.ita.volleymanagementtests.tests.TestsConstants;
import com.softserveinc.ita.volleymanagementtests.tools.WebDriverUtils;
import com.softserveinc.ita.volleymanagementtests.tools.controls.Grid;
import com.softserveinc.ita.volleymanagementtests.tools.controls.interfaces
.IButton;
import com.softserveinc.ita.volleymanagementtests.tools.controls.interfaces
.ILabel;
import com.softserveinc.ita.volleymanagementtests.tools.controls.interfaces
.ILink;
import com.softserveinc.ita.volleymanagementtests.tools.controls.interfaces
.ITextInput;
import com.softserveinc.ita.volleymanagementtests.tools.controls.interfaces
.IValidationLabel;

/**
 * @author Dp-076 ATQC.
 * This class describes tournament list page.
 */
public class TournamentListPage extends Page {
    /**
     * @param controls
     *            encapsulates map of web site.
     */
    private TournamentListPageUIMap controls;
    /**
     *Object of the NavigationHage class.
     */
    private NavigationPage navigationPage;

    /**
     * Constructor of the class.
     */
    public TournamentListPage() {
        controls = new TournamentListPageUIMap();
        navigationPage = new NavigationPage();
    }
    /**
     * @return current Navigation page.
     */
    public final NavigationPage getNavigation() {
        return this.navigationPage;
    }

    /**
     * @return new page with tournament info.
     */
    public final TournamentDetailsPage showTournamentInfo() {
        controls.getTournamentList().get(0).click();
        return new TournamentDetailsPage();
    }

    /**
     * @return new page where you can add new tournament.
     */
    public final NewTournamentPage openCreateTournamentPage() {
        controls.getAddButton().click();
        return new NewTournamentPage();
    }

    /**
     * @param searchRequest - text for searching tournament.
     * @return the same page with searching results.
     */
    public final TournamentListPage searchTournament(
            final String searchRequest) {
        controls.getSearchTextInput().type(searchRequest);
        return this;
    }

    /**
     * @return New SearchString.
     */
    public final SearchString getSearchString() {
        return new SearchString();
    }

    /**
     * @return new page with tournament list.
     */
    public final TournamentListPage checkReturnButtonFunction() {
        controls.getSearchReturnButton().click();
        return new TournamentListPage();
    }

    /**
     * @return number of current page.
     */
    public final short getCurrentPageNumber() {
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
     * @return The new Error Message of search.
     */
    public final IValidationLabel getErrorSearchMessage() {
        return controls.getErrorMessage();
    }
    /**
     * @param pageNumber - page number.
     * @return go to a specified page.
     */
    public final TournamentListPage switchPage(final short pageNumber) {
        if (getPageQuantity() == 1) {
            throw new IllegalStateException("You can't switch "
                    + "because only one page is present.");
        }
        if (controls.getPageNumbers().getSize() < pageNumber) {
            throw new IllegalStateException("You can't switch "
                    + "because pageNumber to switch out of range pages.");
        }
        if (getCurrentPageNumber() == pageNumber) {
            throw new IllegalStateException("You can't switch "
                    + "because you already at this page.");
        }
        controls.getPageNumbers().get(pageNumber - 1).click();
        return new TournamentListPage();
    }
    /**
     * @return page quantity.
     */
    public final int getPageQuantity() {
        Grid pagingGrid = controls.getPageNumbers();
        if (pagingGrid == null) {
            return 1;
        }
        return pagingGrid.getSize();
    }
    /**
     * @return main label of tournament list.
     */
    public final ILabel getTournamentsListLabel() {
        return controls.getTournamentsListLabel();
    }
    /**
     * @return add button.
     */
    public final IButton getAddButton() {
        return controls.getAddButton();
    }
    /**
     * @return save return button.
     */
    public final IButton getSearchReturnButton() {
        return controls.getSearchReturnButton();
    }
    /**
     * @return search text input.
     */
    public final ITextInput getSearchTextInput() {
        return controls.getSearchTextInput();
    }

    /**
     * @param tournament - object of Tournament class.
     * @return new page with tournament info for current tournament.
     */
    public final TournamentDetailsPage showTournamentInfo(
            final Tournament tournament) {
        int pagesQuantity = getPageQuantity();
        boolean isTournamentNotFound = true;
        TournamentListPage tournamentListPage = new TournamentListPage();
        if (pagesQuantity == 1) {
            return showTournamentInfoOnPage(tournament);
        }
        if (tournamentListPage.getCurrentPageNumber() != 1) {
            tournamentListPage = tournamentListPage.switchPage((short) (1));
        }
        TournamentDetailsPage tournamentDetailPage;
        while (isTournamentNotFound) {
            tournamentDetailPage = showTournamentInfoOnPage(tournament);
            if (tournamentDetailPage != null) {
                isTournamentNotFound = false;
                return tournamentDetailPage;
            }
            if (tournamentListPage.getCurrentPageNumber() == pagesQuantity) {
                break;
            }
            short nextPageNumber = (short) (tournamentListPage
                    .getCurrentPageNumber() + 1);
            tournamentListPage = tournamentListPage.switchPage(nextPageNumber);
        }
        return null;
    }
    /**
     * @return details of the tournament.
     */
    public final TournamentDetailsPage showFirstTournament() {
        Grid tournamentLinks = controls.getTournamentList();
        tournamentLinks.get(0).click();
        return new TournamentDetailsPage();
    }
    /**
     * @param tournament - object of Tournament class.
     * @return new page with tournament info for current tournament.
     */
    public final TournamentDetailsPage showTournamentInfoOnPage(
            final Tournament tournament) {
        String tournamentsName = tournament.getName();
        Grid tournamentLinks = controls.getTournamentList();
        int rowsQuantity =  tournamentLinks.getSize();
        for (int i = 0; i < rowsQuantity; i++) {
            if (tournamentLinks.get(i).getText().equals(tournamentsName)) {
                tournamentLinks.get(i).click();
                return new TournamentDetailsPage();
            }
        }
    return null;
    }
    /**
     * @return The list of tournament name on current page.
     */
    public final List<String> getTournamentNameList() {
        return controls.getTournamentNameList();
    }
    /**
     * @return The list of tournament season on current page.
     */
    public final List<String> getTournamentSeasonList() {
        return controls.getTournamentSeasonList();
    }

    /**
     * This method does update tournament list.
     * @return TournamentListPage
     */
    public static final TournamentListPage refreshTournamentList() {
        WebDriverUtils.load("http://localhost:23801/");
        WebDriverUtils.load(TestsConstants.HOME_PAGE);
        NavigationPage navigationPage = new NavigationPage();
        TournamentListPage tournamentListPage = navigationPage
                .getTournamentListPage();
        return tournamentListPage;
    }
}

