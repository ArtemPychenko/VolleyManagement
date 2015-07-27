package com.softserveinc.ita.volleymanagementtests.pages;

import java.util.List;

import com.softserveinc.ita.volleymanagementtests.tools.controls.Button;
import com.softserveinc.ita.volleymanagementtests.tools.controls.Grid;
import com.softserveinc.ita.volleymanagementtests.tools.controls.Label;
import com.softserveinc.ita.volleymanagementtests.tools.controls.Link;
import com.softserveinc.ita.volleymanagementtests.tools.controls.TextInput;
import com.softserveinc.ita.volleymanagementtests.tools.controls
.ValidationLabel;
import com.softserveinc.ita.volleymanagementtests.tools.controls
.interfaces.IButton;
import com.softserveinc.ita.volleymanagementtests.tools.controls
.interfaces.ILabel;
import com.softserveinc.ita.volleymanagementtests.tools.controls
.interfaces.ILink;
import com.softserveinc.ita.volleymanagementtests.tools.controls
.interfaces.ITextInput;
import com.softserveinc.ita.volleymanagementtests.tools.controls
.interfaces.IValidationLabel;

/**
 * @author Dp-076 ATQC This class describes UI elements of the team list
 *         page.
 */
public class TournamentListPageUIMap {
    /**
     * Team list label of the teams list page.
     */
    private ILabel tournamentsListLabel;
    /**
     * Add new team button of the tournaments list page.
     */
    private IButton addButton;
    /**
     * Grid of tournaments on the tournaments list page.
     */
    private Grid tournamentList;
    /**
     * SingleTon list of tournaments Name on the tournaments page.
     */
    private List<String> tournamentNameList;
    /**
     * SingleTon list of tournaments Season on the tournaments page.
     */
    private List<String> tournamentSeasonList;
    /**
     * Grid of page numbers on the tournaments list page.
     */
    private Grid pageNumbers;
    /**
     * Get current page number location on the tournaments list page.
     */
    private ILink activePage;
    /**
     * Search back button of the tournaments list page.
     */
    private IButton searchReturnButton;
    /**
     * Search text input field on the tournaments list page.
     */
    private ITextInput searchTextInput;
    /**
     * Search Error message.
     */
    private IValidationLabel errorMessage;

    /**
     * The constructor of the class.
     */
    public TournamentListPageUIMap() {
    }
    /**
     * @return main label of tournament list.
     */
    public final ILabel getTournamentsListLabel() {
        if (this.tournamentsListLabel == null) {
            this.tournamentsListLabel = Label.getByCss(".col-md-9.col-sm-8>h4");
        }
        return tournamentsListLabel;
    }
    /**
     * @return location of "Add" button.
     */
    public final IButton getAddButton() {
            this.addButton = Button
                    .getByCss(".btn.btn-success.pull-right.create");
        return addButton;
    }
    /**
     * @return List of tournaments.
     */
    public final Grid getTournamentList() {
        if (this.tournamentList == null) {
            this.tournamentList = Grid
                    .getByCss(".col-sm-9");
        }
        return tournamentList;
    }
    /**
     * @return The grid of Tournament names on current page.
     */
    public final List<String> getTournamentNameList() {
        if (this.tournamentNameList == null) {
            this.tournamentNameList = Grid.getByCss(".col-sm-9").getTextList();
        }
        return tournamentNameList;
    }
    /**
     * @return The grid of Tournament seasons on current page.
     */
    public final List<String> getTournamentSeasonList() {
        if (this.tournamentSeasonList == null) {
            this.tournamentSeasonList = Grid.getByCss(".label.label-default")
                    .getTextList();
        }
        return tournamentSeasonList;
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
     * @return current page link in footer.
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
