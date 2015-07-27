package com.softserveinc.ita.volleymanagementtests.criterias;

import java.util.ArrayList;
import java.util.List;

import com.softserveinc.ita.volleymanagementtests.domain.models.Player;
import com.softserveinc.ita.volleymanagementtests.domain.repositories
                                                            .PlayerRepository;
import com.softserveinc.ita.volleymanagementtests.pages.PlayerListPage;
import com.softserveinc.ita.volleymanagementtests.tests.TestsConstants;

/**
 * This class contain methods for
 * test Player list with different criteria.
 *
 * @author DP-076ATQC
 */
public final class PlayerListCriteria implements ISpecification {

    /**
     * The list of player reads from UI.
     */
    private static List<String> playerListUI = new ArrayList<String>(0);

    /**
     * The list of player reads from DB.
     */
    private static List<Player> playerListDB;

    /**
     * If (isSortingTestFaild = true),
     * then isPlayersSortedCorrect() criteria not checks for next page.
     */
    private static boolean isSortingTestPassed = true;

    /**
     * The page quantity on current player list window.
     */
    private static int pageQuantity;

    /**
     * Current player list window.
     */
    private PlayerListPage playersPage;

    /**
     * Logger.
     */
    private Specification specification;

    /**
     * Class constructor.
     *
     * @param inputPage - aim player list page for test.
     * @param inputSpecification - Logger.
     */
    private PlayerListCriteria(final PlayerListPage inputPage,
            final Specification inputSpecification) {
        this.playersPage = inputPage;
        this.specification = inputSpecification;
    }

    /**
     * Static method for create new PlayerListCriteria.
     * @param inputPage - aim player list page for test.
     * @param inputSpecification - logger.
     * @return new PlayerListCriteria
     */
    public static PlayerListCriteria get(final PlayerListPage inputPage,
            final Specification inputSpecification) {
        return new PlayerListCriteria(inputPage, inputSpecification);
    }

    /**
     * This method read and saves all players from DB in playerListDB.
     * @return current PlayerListCriteria.
     */
    public PlayerListCriteria readPlayersFromDB() {
        PlayerListCriteria.playerListDB = PlayerRepository.getAllPlayers();
        PlayerListCriteria.pageQuantity = (playerListDB.size() - 1)
                / TestsConstants.ITEMS_PER_PAGE + 1;
        return this;
    }

    /**
     * This method read all players from current UI page.
     *  And saves them in playerListUI.
     * @return current PlayerListCriteria.
     */
    public PlayerListCriteria readPlayersFromUI() {
        PlayerListCriteria.playerListUI
                .addAll(playersPage.getPlayerNameList());
        return this;
    }

    /**
     * This method checks compliance of pages quantity from UI and from DB.
     * @return current PlayerListCriteria.
     */
    public PlayerListCriteria isPageQuantityCorrect() {
        this.specification.add(
                PlayerListCriteria.pageQuantity
                == playersPage.getPageQuantity(),
                "The quantity of player pages is not correct.\n");
        return this;
    }

    /**
     * This method checks compliance of current page number and input number.
     * @param currentPage - expected current page number.
     * @return current PlayerListCriteria.
     */
    public PlayerListCriteria isCurrentPageNumCorrect(final int currentPage) {
        this.specification.add(
                playersPage.getCurrentPageNumber() == currentPage,
                "The number of current player page is not correct.\n");
        return this;
    }

    /**
     * This method checks compliance of players quantity on UI and SRS.
     * @return current PlayerListCriteria.
     */
    public PlayerListCriteria isQuantityPlayersCorrect() {
        boolean isQuantityCorrect = true;
        if (playersPage.getCurrentPageNumber()
                == PlayerListCriteria.pageQuantity) {
            isQuantityCorrect = (playersPage.getPlayerNameList().size()
                    % TestsConstants.ITEMS_PER_PAGE == playerListDB.size()
                    % TestsConstants.ITEMS_PER_PAGE);
        } else {
            isQuantityCorrect = (playersPage.getPlayerNameList().size()
                    == TestsConstants.ITEMS_PER_PAGE);
        }
        this.specification.add(isQuantityCorrect,
                "The quantity of players on current page is not correct.\n");
        return this;
    }
    /**
     * Method checks compliance of player list sorting from UI and from SRS.
     * @return current PlayerListCriteria.
     */
    public PlayerListCriteria isPlayersSortedCorrect() {
        if (PlayerListCriteria.isSortingTestPassed) {
            List<String> playerList = new ArrayList<String>(0);
            playerList.addAll(playersPage.getPlayerNameList());
            playerList.sort(null);
            boolean isEqual = true;
            StringBuilder compareReport = new StringBuilder();
            for (int i = 0; i < playerList.size(); i++) {
                isEqual &= playersPage.getPlayerNameList().get(i)
                        .equals(playerList.get(i));
                compareReport.append(playersPage.getPlayerNameList().get(i))
                .append(" - ").append(playerList.get(i)).append("\n");
            }
            PlayerListCriteria.isSortingTestPassed = isEqual;
            this.specification.add(isEqual,
                    "Players aren`t sorted alphabetically by Full name.\n"
                    .concat("Actual result - Expected result\n")
                    .concat(compareReport.toString()));
        }
        return this;
    }

    /**
     * Method repeats test-steps from test-case for certain page.
     * isCurrentPageNumCorrect(pageNum) and isQuantityPlayersCorrect() checks.
     * @param pageNum page number for test-steps.
     */
    private void testCaseForPage(final int pageNum) {
        playersPage = playersPage.switchPage((short) pageNum);
        next().For(playersPage)
                .isCurrentPageNumCorrect(pageNum)
                .isQuantityPlayersCorrect()
                .isPlayersSortedCorrect();
    }

    /**
     * Method repeats test-steps from test-case for all pages, except current.
     * @return current PlayerListCriteria.
     */
    public PlayerListCriteria repeatTestForAllPages() {
        readPlayersFromUI();
        for (int i = (playersPage.getCurrentPageNumber() + 1); i <= playersPage
                .getPageQuantity(); i++) {
            testCaseForPage(i);
            readPlayersFromUI();
        }
        return this;
    }

    /**
     * Method repeats test-steps from test-case for certain page.
     * @param pageNum page number for test-steps.
     * @return current PlayerListCriteria.
     */
    public PlayerListCriteria repeatTestForPage(final int pageNum) {
        testCaseForPage(pageNum);
        return this;
    }

    /**
     * Method checks compliance of full player list from UI and from DB.
     * @return current PlayerListCriteria.
     */
    public PlayerListCriteria isUIPlayerListCorrespondsDB() {
        List<String> playerListNameDB = new ArrayList<String>(0);
        for (Player player : PlayerListCriteria.playerListDB) {
            playerListNameDB.add(player.getFullName());
        }
        playerListNameDB.sort(null);
        playerListUI.sort(null);
        this.specification.add(playerListUI.equals(playerListNameDB),
                "Player list readed from UI isn`t corresponds to DB");
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
