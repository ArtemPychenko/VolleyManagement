package com.softserveinc.ita.volleymanagementtests.criterias;

import java.util.List;

import com.softserveinc.ita.volleymanagementtests.domain.models.Player;
import com.softserveinc.ita.volleymanagementtests.domain.repositories
            .PlayerRepository;
import com.softserveinc.ita.volleymanagementtests.pages.PlayerListPage;
import com.softserveinc.ita.volleymanagementtests.pages.SearchString;

/**
 * This class contain methods for
 * test Search Player functionality with different criteria.
 * @author DP-076ATQC
 *
 */
public final class PlayerSearchCriteria implements ISpecification {

    /**
     * This variable for will contain testing SearchString.
     */
    private SearchString searchString;

    /**
     * Logger.
     */
    private Specification specification;

    /**
     * PlayerListPage object to gain testing List.
     */
    private PlayerListPage playerListPage;

    /**
     * Current testing page number.
     */
    private short currentPage;

    /**
     * The list of player reads from UI.
     */
    private List<String> playerListUI;

    /**
     * Player from the top(UI) of player list.
     */
    private String topPlayer;
    /**
     * Player lower the top(UI) of player list.
     */
    private String lowerPlayer;

    /**
     * Expected quantity of double player search.
     */
    private int expectedQuantity = 2;

    /**
     * Class constructor.
     * @param inputSearchString - aim SearchString.
     * @param inputSpecification - Logger.
     */
    private PlayerSearchCriteria(final SearchString inputSearchString,
            final Specification inputSpecification) {
        this.searchString = inputSearchString;
        this.specification = inputSpecification;
        playerListPage = new PlayerListPage();
        currentPage = playerListPage.getCurrentPageNumber();
        playerListUI = playerListPage.getPlayerNameList();
        topPlayer = playerListUI.get(0);
        lowerPlayer = playerListUI.get(playerListUI.size() - 1);
    }

    /**
     * Static method for create new PlayerSearchCriteria.
     * @param inputSearchString - aim SearchString.
     * @param inputSpecification - logger.
     * @return new PlayerSearchCriteria
     */
    public static PlayerSearchCriteria get(
            final SearchString inputSearchString,
            final Specification inputSpecification) {
        return new PlayerSearchCriteria(inputSearchString, inputSpecification);
    }

    /**
     * This method try to find the top(UI) player of player list.
     * @return current PlayerSearchCriteria.
     */
    public PlayerSearchCriteria tryFindTopPlayer() {
        playerListUI = searchString.inputText(topPlayer)
                .getPlayersPage().getPlayerNameList();
        this.specification.add((playerListUI.get(0).compareTo(topPlayer) == 0)
                && (playerListUI.size() == 1),
                "\nSearch faild.\nThe top player of list wasn't found. "
                        .concat(makeBugReport(topPlayer, 1)));
        searchString.clickClear();
        return this;
    }

    /**
     * This method deletes the top(UI) player of player list.
     * @return current PlayerSearchCriteria.
     */
    public PlayerSearchCriteria deleteTopPlayer() {
        String[] fullName = topPlayer.split("\\s");
        PlayerRepository.deletePlayerByFullName(fullName[1], fullName[0]);
        playerListPage = PlayerListPage.refreshPlayerList();
        searchString = playerListPage.getSearchString();
        return this;
    }

    /**
     * This method try to find deleted on previous test-step player.
     * @return current PlayerSearchCriteria.
     */
    public PlayerSearchCriteria tryFindDeletedPlayer() {
        playerListUI = searchString.inputText(topPlayer)
                .getPlayersPage().getPlayerNameList();
        this.specification.add(playerListUI.size() == 0,
                "\nSearch faild. Deleted player was found.\n"
                        .concat(makeBugReport()));
        searchString.clickClear();
        return this;
    }

    /**
     * This method try to set up incorrect symbols in SearchString.
     * @param symbolString - string with special symbols.
     * @return current PlayerSearchCriteria.
     */
    public PlayerSearchCriteria tryFindIncorrectSymbols(
            final String symbolString) {
        boolean testPassed;
        for (int i = 0; i < symbolString.length(); i++) {
            testPassed = searchString
                    .inputText(symbolString.substring(i, (i + 1)))
                    .getPlayersPage().getErrorSearchMessage() != null;
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
     * This method try to find the lower(UI) player of player list.
     * @return current PlayerSearchCriteria.
     */
    public PlayerSearchCriteria tryFindLowerPlayer() {
        playerListUI = searchString.inputText(lowerPlayer)
                .getPlayersPage().getPlayerNameList();
        this.specification.add(
                (playerListUI.get(0).compareTo(lowerPlayer) == 0)
                        && (playerListUI.size() == 1),
                "\nSearch faild. The lower player of list wasn't found."
                        .concat(makeBugReport(lowerPlayer, 1)));
        searchString.clickClear();
        return this;
    }

     /**
      * This method try to find player from previous PlayerList page.
      * @return current PlayerSearchCriteria.
      */
     public PlayerSearchCriteria tryFindPreviousPlayer() {
         playerListUI = searchString.inputText(lowerPlayer)
                 .getPlayersPage().getPlayerNameList();
         this.specification.add(
                 (playerListUI.get(0).compareTo(lowerPlayer) == 0)
                 && (playerListUI.size() == 1),
                 "\nSearch faild. The player of previous page wasn't found. "
                 .concat(makeBugReport(lowerPlayer, 1)));
         searchString.clickClear();
         return this;
     }

     /**
      * This method add the duplicate of lower(UI) player on player list.
      * @return current PlayerSearchCriteria.
      */
     public PlayerSearchCriteria addDuplicatePlayer() {
         String[] fullName = lowerPlayer.split("\\s");
         PlayerRepository.insertPlayer(new Player(fullName[1], fullName[0]));
         playerListPage = PlayerListPage.refreshPlayerList();
         searchString = playerListPage.getSearchString();
         return this;
     }

     /**
      * This method try to find double of identical players.
      * @return current PlayerSearchCriteria.
      */
     public PlayerSearchCriteria tryFindBothPlayers() {
         playerListUI = searchString.inputText(lowerPlayer)
                 .getPlayersPage().getPlayerNameList();
         this.specification.add((playerListUI.get(0)
                 .compareTo(lowerPlayer) == 0)
                 && (playerListUI.size() == expectedQuantity),
                 "\nSearch faild. Duplicate player wasn't found. "
                 .concat(makeBugReport(lowerPlayer, expectedQuantity)));
         searchString.clickClear();
         return this;
     }

    /**
     * This method make template string
     *  with the bug report of "success player search" test.
     * @param player Expected search result player.
     * @param quantity Expected search result quantity.
     * @return Bug report.
     */
    private String makeBugReport(final String player, final int quantity) {
        StringBuilder bugReport = new StringBuilder();
        bugReport.append("Or search incorrect.\n")
                .append("Expected number of players = ")
                .append(quantity).append(".")
                .append(" Actual number of players = ")
                .append(String.valueOf(playerListUI.size())).append(".\n")
                .append("Expected Player name: ")
                .append(player).append(".\n")
                .append("Actual Player name:\n");
        for (int i = 0; i < playerListUI.size(); i++) {
            bugReport.append(i + 1).append(") ")
            .append(playerListUI.get(i)).append(".\n");
        }
        return bugReport.toString();
    }

    /**
     * This method make template string
     *  with the bug report of "empty player list result" search test.
     * @return Bug report.
     */
    private String makeBugReport() {
        StringBuilder bugReport = new StringBuilder();
        bugReport.append("Or search incorrect. Search return ")
                .append(String.valueOf(playerListUI.size()))
                .append(" players.\n");
        return bugReport.toString();
    }

    /**
     * This switch on the next page of player list page.
     * @return current PlayerSearchCriteria.
     */
    public PlayerSearchCriteria nextPage() {
        playerListPage = playerListPage.switchPage((short) (currentPage + 1));
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
