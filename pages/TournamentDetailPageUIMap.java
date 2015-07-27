package com.softserveinc.ita.volleymanagementtests.pages;

import com.softserveinc.ita.volleymanagementtests.tools.controls.Button;
import com.softserveinc.ita.volleymanagementtests.tools.controls.Label;
import com.softserveinc.ita.volleymanagementtests.tools.controls.interfaces.IButton;
import com.softserveinc.ita.volleymanagementtests.tools.controls.interfaces.ILabel;


public class TournamentDetailPageUIMap {
    /**
     * @param pageTitleLabel is the title of the page;
     */
    private ILabel pageTitleLabel;
    
    /**
     * @param tournament name label.
     */
    private ILabel tournamentNameLabel;
    
    /**
     * @param tournamentNameInput holds location of input tournament name.
     */
    private ILabel tournamentNameInput;
    
    /**
     * @param description label.
     */
    private ILabel descriptionLabel;
    
    /**
     * @param descriptionInput holds location of input for description.
     */
    private ILabel descriptionInput;
    
    /**
     * @param seasonLabel.
     */
    private ILabel seasonLabel;
    
    /**
     * @param seasonInput holds location of season input.
     */
    private ILabel seasonInput;
    
    /**
     * @param startAndEndOfRegistrationLabel.
     */
    private ILabel startAndEndOfRegistrationLabel;
    
    /**
     * @param startAndEndOfRegistrationInput holds location of input for
     * tornament's registration start and end.
     */
    private ILabel startAndEndOfRegistrationInput;
    
    /**
     * @param startAndEndOfTournamentLabel.
     */
    private ILabel startAndEndOfTournamentLabel;
    
    /**
     * @param startAndEndOfTournamentInput holds location of input for
     * tornament's registration start and end.
     */
    private ILabel startAndEndOfTournamentInput;/**
     * @param startAndEndOfTransferLabel.
     */
    private ILabel startAndEndOfTransferLabel;
    
    /**
     * @param startAndEndOfTransferInput holds location of input for
     * tornament's registration start and end.
     */
    private ILabel startAndEndOfTransferInput;
    /**
     * @param schemeLabel.
     */
    private ILabel schemeLabel;
    /**
     * @param schemeInput holds location of scheme input.
     */
    private ILabel schemeInput;
    /**
     * @param editButton holds location of edit button.
     */
    private IButton editButton;
    /**
     * @param returnButton holds location to return button
     */
    private IButton returnButton;
    /**
     * @param deliteButton holds location to delete button.
     */
    private IButton deleteButton;
    
    public TournamentDetailPageUIMap() {
    }
    
    public ILabel getpageTitleLabel() {
        if (this.pageTitleLabel == null) {
            this.pageTitleLabel = Label.getByCss("#main > div.homepage "
                    + "> div > div.panel-heading > div > div.col-sm-8 > h4");
        }
        return pageTitleLabel;
    }
    public ILabel getTournamentNameLabel() {
        if (this.tournamentNameLabel == null) {
            this.tournamentNameLabel = Label.getByCss("#main > div.homepage "
                    + "> div > div.panel-body > ul > div:nth-child(1) > label");
        }
        return tournamentNameLabel;
    }
    public ILabel getTournamentNameInput() {
        if (this.tournamentNameInput == null) {
            this.tournamentNameInput = Label.getByName("name");
        }
        return tournamentNameInput;
    }
    public ILabel getDescriptionLabel() {
        if (this.descriptionLabel == null) {
            this.descriptionLabel = Label.getByCss("#main > div.homepage > div"
                    + " > div.panel-body > ul > div:nth-child(2) > label");
        }
        return descriptionLabel;
    }
    public ILabel getDescriptionInput() {
        if (this.descriptionInput == null) {
            this.descriptionInput = Label.getByName("description");
        }
        return descriptionInput;
    }
    public ILabel getSeasonLabel() {
        if (this.seasonLabel == null) {
            this.seasonLabel = Label.getByCss("#main > div.homepage > div >"
                    + " div.panel-body > ul > div:nth-child(3) > label");
        }
        return seasonLabel;
    }
    public ILabel getSeasonInput() {
        if (this.seasonInput == null) {
            this.seasonInput = Label.getByName("season");
        }
        return seasonInput;
    }
    public ILabel getStartAndEndOfRegistrationLabel() {
        if (this.startAndEndOfRegistrationLabel == null) {
            this.startAndEndOfRegistrationLabel= Label.getByCss("#main >"
                    + " div.homepage > div > div.panel-body > ul >"
                    + " div:nth-child(4) > label");
        }
        return startAndEndOfRegistrationLabel;
    }
    public ILabel getStartAndEndOfRegistrationInput() {
        if (this.startAndEndOfRegistrationInput == null) {
            this.startAndEndOfRegistrationInput = Label.getByCss("#main > div.homepage > div >"
                    + " div.panel-body > ul > div:nth-child(4) > p");
        }
        return startAndEndOfRegistrationInput;
    }
    public ILabel getStartAndEndOfTournamentLabel() {
        if (this.startAndEndOfTournamentLabel == null) {
            this.startAndEndOfTournamentLabel= Label.getByCss("#main >"
                    + " div.homepage > div > div.panel-body > ul >"
                    + " div:nth-child(5) > label");
        }
        return startAndEndOfTournamentLabel;
    }
    public ILabel getStartAndEndOfTournamentInput() {
        if (this.startAndEndOfTournamentInput == null) {
            this.startAndEndOfTournamentInput = Label.getByCss("#main >"
                    + " div.homepage > div > div.panel-body > ul >"
                    + " div:nth-child(5) > p");
        }
        return startAndEndOfTournamentInput;
    }
    public ILabel getStartAndEndOfTransferLabel() {
        if (this.startAndEndOfTransferLabel == null) {
            this.startAndEndOfTransferLabel = Label.getByCss("#main >"
                    + " div.homepage > div > div.panel-body > ul >"
                    + " div:nth-child(6) > label");
        }
        return startAndEndOfTransferLabel;
    }
    public ILabel getStartAndEndOfTransferInput() {
        if (this.startAndEndOfTransferInput == null) {
            this.startAndEndOfTransferInput = Label.getByCss("#main >"
                    + " div.homepage > div > div.panel-body > ul >"
                    + " div:nth-child(6) > p");
        }
        return startAndEndOfTransferInput;
    }
    public ILabel getSchemeLabel() {
        if (this.schemeLabel == null) {
            this.schemeLabel = Label.getByCss("#main >"
                    + " div.homepage > div > div.panel-body > ul >"
                    + " div:nth-child(7) > label");
        }
        return schemeLabel;
    }
    public ILabel getSchemeInput() {
        if (this.schemeInput == null) {
            this.schemeInput = Label.getByXpath(".//*[@id='main']/div[3]/div/div[2]/ul/div[7]/p");
        }
        return schemeInput;
    }
    public IButton getEditButton() {
            this.editButton =  Button.getByCss(".btn.btn-success.pull-right.edit");
        return editButton;
    }
    public IButton getReturnButton() {
        if (this.returnButton == null) {
            this.returnButton =  Button.getByCss(
                    ".btn.btn-warning.pull-left.cancel");
        }
        return returnButton;
    }
    public IButton getDeleteButton() {
        if (this.deleteButton == null) {
            this.deleteButton =  Button.getByCss(
                   ".btn.btn-danger.pull-right.delete");
        }
        return deleteButton;
    }
    //TODO:
    //linkToReglamentLabel
    //linkToReglamentInput;
}
