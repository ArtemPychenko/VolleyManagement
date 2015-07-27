package com.softserveinc.ita.volleymanagementtests.pages;

import com.softserveinc.ita.volleymanagementtests.tools.controls.Label;
import com.softserveinc.ita.volleymanagementtests.tools.controls.TextInput;
import com.softserveinc.ita.volleymanagementtests.tools.controls.ValidationLabel;
import com.softserveinc.ita.volleymanagementtests.tools.controls.interfaces.ILabel;
import com.softserveinc.ita.volleymanagementtests.tools.controls.interfaces.ITextInput;
import com.softserveinc.ita.volleymanagementtests.tools.controls.interfaces.IValidationLabel;

/**
 * @author Dp-076 ATQC
 * This class describes UI elements of the main page.
 */
public class TeamDataPageUIMap {

    /**
     * @param pageTitleLabel is the title of the page;
     */
    private ILabel pageTitleLabel;
    /**
     * @param team's name label.
     */
    private ILabel nameLabel;
    /**
     * @param nameInput holds location of input for team's name.
     */
    private ITextInput nameInput;
    /**
     * @param captainLabel holds team's captain name label.
     */
    private ILabel captainLabel;
    /**
     * @param captainInput holds location of input for team's captain
     * name.
     */
    private ITextInput captainInput;
    /**
     * @param team's coach name coachLabel.
     */
    private ILabel coachLabel;
    /**
     * @param coachInput holds location of the team's coach input.
     */
    private ITextInput coachInput;
    /**
     * @param achievementsLabel.
     */
    private ILabel achievementsLabel;
    /**
     * @param achievementsInput holds location of team's achievements input.
     */
    private ITextInput achievementsInput;
    /**
     * validation message for incorrect data inputed to the name field.
     */
    private IValidationLabel errorMessageName;
    /**
     * validation message for incorrect data inputed to the captain field.
     */
    private IValidationLabel errorMessageCaptain;
    /**
     * validation message for incorrect data inputed to the coach field.
     */
    private IValidationLabel errorMessageCoach;
    /**
     * validation message for incorrect data inputed to the achievements field.
     */
    private IValidationLabel errorMessageAchievements;
    /**
     * @return page title label.
     */
    public final ILabel getPageTitleLabel() {
        if (this.pageTitleLabel == null) {
            this.pageTitleLabel = Label.getByCss(".panel-heading>h4");
        }
        return pageTitleLabel;
    }
    /**
     * @return label of validation message for name.
     * sets the location of it.
     */
    public final IValidationLabel getErrorMessageLabelName() {
        if (this.errorMessageName == null) {
            this.errorMessageName = ValidationLabel.getByXpath("//div["
                    + "label[contains(.,'Имя')]]/div[@class = \"hint\"]");
        }
        return errorMessageName;
    }
    /**
     * @return label of validation message for captain.
     * sets the location of it.
     */
    public final IValidationLabel getErrorMessageLabelCaptain() {
        if (this.errorMessageCaptain == null) {
            this.errorMessageCaptain = ValidationLabel.getByXpath("//div["
                    + "label[contains(.,'Капитан')]]/div[@class = \"hint\"]");
        }
        return errorMessageCaptain;
    }
    /**
     * @return label of validation message for coach.
     * sets the location of it.
     */
    public final IValidationLabel getErrorMessageLabelCoach() {
        if (this.errorMessageCoach == null) {
            this.errorMessageCoach = ValidationLabel.getByXpath("//div["
                    + "label[contains(.,'Тренер')]]/div[@class = \"hint\"]");
        }
        return errorMessageCoach;
    }
    /**
     * @return label of validation message for achievements.
     * sets the location of it.
     */
    public final IValidationLabel getErrorMessageLabelAchievements() {
        if (this.errorMessageAchievements == null) {
            this.errorMessageAchievements = ValidationLabel.getByXpath("//div["
                    + "label[contains(.,'Достижения')]]/div[@class = "
                    + "\"hint\"]");
        }
        return errorMessageAchievements;
    }
    /**
     * @return first team's name label.
     */
    public final ILabel getNameLabel() {
        if (this.nameLabel == null) {
            this.nameLabel = Label.getByCss("#main > div:nth-child(n) "
                    + "> div > div.panel-body > form > div:nth-child(1) "
                    + "> label");
        }
        return nameLabel;
    }
    /**
     * @return team's name input.
     */
    public final ITextInput getNameInput() {
        if (this.nameInput == null) {
            this.nameInput = TextInput.getByCss("[name=name]");
        }
        return nameInput;
    }
    /**
     * @return teams's captain label.
     */
    public final ILabel getCaptainLabel() {
        if (this.captainLabel == null) {
            this.captainLabel = Label
                    .getByCss("#main > div:nth-child(n) > div > div.panel-body "
                            + "> form > div:nth-child(2) > label");
        }
        return captainLabel;
    }
    /**
     * @return team's captain input.
     */
    public final ITextInput getCaptainInput() {
        if (this.captainInput == null) {
            this.captainInput = TextInput.getByCss("[name=captain]");
        }
        return captainInput;
    }
    /**
     * @return year of player's birth's label.
     */
    public final ILabel getCoachLabel() {
        if (this.coachLabel == null) {
            this.coachLabel = Label.getByCss("#main > div:nth-child(n) "
                    + "> div > div.panel-body > form > div:nth-child(3) "
                    + "> label");
        }
        return coachLabel;
    }
    /**
    * @return year of player's birth's input.
     */
    public final ITextInput getCoachInput() {
        if (this.coachInput == null) {
            this.coachInput = TextInput.getByCss("[name=coach]");
        }
        return coachInput;
    }
    /**
     * @return the player's height label.
     */
    public final ILabel getAchievementsLabel() {
        if (this.achievementsLabel == null) {
            this.achievementsLabel = Label.getByCss("#main > div:nth-child(n) "
                    + "> div "
                    + "> div.panel-body > form > div:nth-child(4) > label");
        }
        return achievementsLabel;
    }
    /**
     * @return the player's height input.
      */
     public final ITextInput getAhievementsInput() {
         if (this.achievementsInput == null) {
             this.achievementsInput = TextInput.getByCss("[name=achievements]");
         }
         return achievementsInput;
     }
}
