package com.softserveinc.ita.volleymanagementtests.pages;

import java.util.List;

import com.softserveinc.ita.volleymanagementtests.tools.controls.Button;
import com.softserveinc.ita.volleymanagementtests.tools.controls.Label;
import com.softserveinc.ita.volleymanagementtests.tools.controls.Grid;
import com.softserveinc.ita.volleymanagementtests.tools.controls.Link;
import com.softserveinc.ita.volleymanagementtests.tools.controls
.ValidationLabel;
import com.softserveinc.ita.volleymanagementtests.tools.controls.interfaces
.IButton;
import com.softserveinc.ita.volleymanagementtests.tools.controls.interfaces
.ILabel;
import com.softserveinc.ita.volleymanagementtests.tools.controls.interfaces
.ILink;
import com.softserveinc.ita.volleymanagementtests.tools.controls.interfaces
.IValidationLabel;

/**
 * @author Dp-076 ATQC This class describes UI elements of the players list
 *         page.
 */
public class PlayersListPageUIMap {
    /**
     * Players list label of the players list page.
     */
    private ILabel playersListLabel;
    /**
     * Add new player button of the players list page.
     */
    private IButton addButton;
    /**
     * List of player Name on the current players list page.
     */
    private List<String> playersListName;
    /**
     * Grid of players on the players list page.
     */
    private Grid playersList;
    /**
     * Grid of page numbers on the players list page.
     */
    private Grid pageNumbers;
    /**
     * Get current page number location on the players list page.
     */
    private ILink activePage;
    /**
     * Search Error message.
     */
    private IValidationLabel errorMessage;

    /**
     * The constructor of the class.
     */
    public PlayersListPageUIMap() {
    }
    /**
     * @return player list label.
     */
    public final ILabel getPlayersListLabel() {
        if (this.playersListLabel == null) {
            this.playersListLabel = Label.getByCss(".col-md-9.col-sm-8>h4");
        }
        return playersListLabel;
    }

    /**
     * @return location of "Add" button.
     */
    public final IButton getAddButton() {
       // if (this.addButton == null) {
            this.addButton = Button
                    .getByCss(".btn.btn-success.pull-right.create");
     //   }
        return this.addButton;
    }

    /**
     * @return List of players.
     */
    public final Grid getPlayerList() {
        if (this.playersList == null) {
            this.playersList = Grid.getByCss(".player.list-group-item");
        }
        return playersList;
    }
    /**
     * @return The list of Player name on current page.
     */
    public final List<String> getPlayerNameList() {
        if (this.playersListName == null) {
            this.playersListName = getPlayerList().getTextList();
        }
        return this.playersListName;
    }

    /**
     * @return List of page numbers.
     */
    public final Grid getPageNumbers() {
        if (getActivePageLink() != null) {
        this.pageNumbers = Grid.getByCss(".pagination>li>a");
        return pageNumbers;
        }
        return null;
    }

    /**
     * @return current page link in footer.
     */
    public final ILink getActivePageLink() {
        activePage = Link.getByCss(".active>a");
        if (this.activePage.isPresent()) {
            return activePage;
        }
        return null;
    }
    /**
     * Getter for pop-up Error message on the Player page.
     * @return Error message label.
     */
    public final IValidationLabel getErrorMessage() {
        this.errorMessage = ValidationLabel
                .getByXpath("//div[@class = \"hint\"]");
        if (this.errorMessage.isPresent()) {
            return errorMessage;
        }
        return null;
    }

}
