package com.softserveinc.ita.volleymanagementtests.pages;

import java.util.List;

import com.softserveinc.ita.volleymanagementtests.domain.models.Player;
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
.IValidationLabel;

/**
 * @author DP076 ATQC.
 *
 */
public class PlayerListPage extends Page {
    /**
     * @param controls encapsulates map of web site.
     */
    private PlayersListPageUIMap controls;
    /**
     * Constructor.
     */
    private NavigationPage navigationPage;
    /**
     * return player list page.
     */
    public PlayerListPage() {
        controls = new PlayersListPageUIMap();
        navigationPage = new NavigationPage();
    }
    /**
     * @return SHTA?!.
     */
    public final NavigationPage getNavigation() {
        return this.navigationPage;
    }
    /**
     * @return new page with player info.
     */
    public final PlayerDetailPage showPlayerInfo() {
        controls.getPlayerList().get(0).click();
        return new PlayerDetailPage();
    }
    /**
     * @param player - object of Player class.
     * @return new page with player info for current player.
     */
    public final PlayerDetailPage showPlayerInfo(final Player player) {
        int pagesQuantity = getPageQuantity();
        boolean isPlayerNotFound = true;
        PlayerListPage playersListPage = new PlayerListPage();
        if (pagesQuantity == 1) {
            return showPlayerInfoOnPage(player);
        }
        if (playersListPage.getCurrentPageNumber() != 1) {
            playersListPage = playersListPage.switchPage((short) 1);
        }
        while (isPlayerNotFound) {
            PlayerDetailPage playerDetailPage = showPlayerInfoOnPage(player);
            if (playerDetailPage != null) {
                isPlayerNotFound = false;
                return playerDetailPage;
            }
            if (playersListPage.getCurrentPageNumber() == pagesQuantity) {
                break;
            }
            short nextPageNumber =
                    (short) (playersListPage.getCurrentPageNumber() + 1);
            playersListPage = playersListPage.switchPage(nextPageNumber);
        }
        return null;
    }
    /**
     * @param player - object of Player class.
     * @return new Player detailed page or null
     */
    public final PlayerDetailPage showPlayerInfoOnPage(final Player player) {
        String playerNames = player.getLastName() + " " + player.getFirstName();
        Grid playerLinks = controls.getPlayerList();
        int rowsQuantity =  playerLinks.getSize();
        for (int i = 0; i < rowsQuantity; i++) {
            if (playerLinks.get(i).getText().equals(playerNames)) {
                playerLinks.get(i).click();
                return new PlayerDetailPage();
            }
        }
    return null;
    }
    /**
     * @return new page where you can add new player.
     */
    public final NewPlayerPage openCreatePlayerPage() {
        controls.getAddButton().click();
        return new NewPlayerPage();
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
     * @return The quantity of players on current page.
     */
    public final int playersQuantity() {
        return controls.getPlayerList().getSize();
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
     * @return Player list on current page.
     */
    public final Grid getPageList() {
        return controls.getPlayerList();
    }
    /**
     * @return The list of Player name on current page.
     */
    public final List<String> getPlayerNameList() {
        return controls.getPlayerNameList();
    }
    /**
     * @param pageNumber - page number.
     * @return go to a specified page.
     */
    public final PlayerListPage switchPage(final short pageNumber) {
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
        return new PlayerListPage();
    }
    /**
     * @return player list label.
     */
    public final ILabel getPlayersListLabel() {
        return controls.getPlayersListLabel();
    }
    /**
     * @return add button.
     */
    public final IButton getAddButton() {
        return controls.getAddButton();
    }
    /**
     * This method does update players list.
     * @return PlayersListPage
     */
    public static final PlayerListPage refreshPlayerList() {
        WebDriverUtils.load("http://localhost:23801/");
        WebDriverUtils.load(TestsConstants.HOME_PAGE);
        NavigationPage navigationPage = new NavigationPage();
       PlayerListPage playersListPage = navigationPage.getPlayersPage();
       return playersListPage;
    }

}
