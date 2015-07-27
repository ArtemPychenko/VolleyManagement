package com.softserveinc.ita.volleymanagementtests.pages;

import java.util.List;

import com.softserveinc.ita.volleymanagementtests.tools.controls.Button;
import com.softserveinc.ita.volleymanagementtests.tools.controls.Grid;
import com.softserveinc.ita.volleymanagementtests.tools.controls.Label;
import com.softserveinc.ita.volleymanagementtests.tools.controls.Link;
import com.softserveinc.ita.volleymanagementtests.tools.controls.TextInput;
import com.softserveinc.ita.volleymanagementtests.tools.controls.ValidationLabel;
import com.softserveinc.ita.volleymanagementtests.tools.controls.interfaces.IButton;
import com.softserveinc.ita.volleymanagementtests.tools.controls.interfaces.ILabel;
import com.softserveinc.ita.volleymanagementtests.tools.controls.interfaces.ILink;
import com.softserveinc.ita.volleymanagementtests.tools.controls.interfaces.ITextInput;
import com.softserveinc.ita.volleymanagementtests.tools.controls.interfaces.IValidationLabel;

/**
 * @author Dp-076 ATQC This class describes UI elements of the team list
 *         page.
 */
public class TeamListPageUIMap {
    /**
     * Team list label of the teams list page.
     */
    private ILabel teamsListLabel;
    /**
     * Add new team button of the teams list page.
     */
    private IButton addButton;
    /**
     * Grid of teams on the teams list page.
     */
    private Grid teamsList;
    /**
     * List of team name on the current team list page.
     */
    private List<String> teamsListName;
    /**
     * Grid of page numbers on the teams list page.
     */
    private Grid pageNumbers;
    /**
     * Get current page number location on the teams list page.
     */
    private ILink activePage;
    /**
     * Search back button of the teams list page.
     */
    private IButton searchReturnButton;
    /**
     * Search text input field on the teams list page.
     */
    private ITextInput searchTextInput;
    /**
     * Search Error message.
     */
    private IValidationLabel errorMessage;

    /**
     * The constructor of the class.
     */
    public TeamListPageUIMap() {
    }
    
    public final ILabel getTeamsListLabel() {
        if (this.teamsListLabel == null) {
            this.teamsListLabel = Label.getByCss(".col-md-9.col-sm-8>h4");
        }
        return teamsListLabel;
    }
    /**
     * @return location of "Add" button.
     */
    public final IButton getAddButton() {
        if (this.addButton == null) {
            this.addButton = Button
                    .getByCss(".btn.btn-success.pull-right.create");
        }
        return addButton;
    }
    /**
     * @return List of teams.
     */
    public final Grid getTeamsList() {
        if (this.teamsList == null) {
            this.teamsList = Grid.getByCss(".team.list-group-item");
        }
        return teamsList;
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
     * @return Button "Back" for search field on current page.
     */
    public final IButton getSearchReturnButton() {
        if (this.searchReturnButton == null) {
            this.searchReturnButton = Button.getByCss(".btn.btn-info");
        }
        return searchReturnButton;
    }
    /**
     * @return Text input field for searching on current page.
     */
    public final ITextInput getSearchTextInput() {
        if (this.searchTextInput == null) {
            this.searchTextInput = TextInput
                    .getByCss(".form-control.searchField");
        }
        return searchTextInput;
    }
    
    /**
     * @return The list of Team name on current page.
     */
    public final List<String> getTeamNameList() {
        if (this.teamsListName == null) {
            this.teamsListName = getTeamsList().getTextList();
        }
        return this.teamsListName;
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
