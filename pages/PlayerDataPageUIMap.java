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
public class PlayerDataPageUIMap {

    /**
     * @param pageTitleLabel is the title of the page;
     */
    private ILabel pageTitleLabel;
    /**
     * @param player's first name label.
     */
    private ILabel firstNameLabel;
    /**
     * @param firstNameInput holds location of input for player's
     * first name.
     */
    private ITextInput firstNameInput;
    /**
     * @param player's last name label.
     */
    private ILabel lastNameLabel;
    /**
     * @param lastNameInput holds location of input for player's
     * last name.
     */
    private ITextInput lastNameInput;
    /**
     * @param birthYearLabel.
     */
    private ILabel birthYearLabel;
    /**
     * @param birthYearInput holds location of input for player's
     * birth year.
     */
    private ITextInput birthYearInput;
    /**
     * @param heightLabel.
     */
    private ILabel heightLabel;
    /**
     * @param heightInput holds location of input for player's
     * year of the player's height.
     */
    private ITextInput heightInput;
    /**
     * @param weightLabel.
     */
    private ILabel weightLabel;
    /**
     * @param weightInput holds location of input for player's
     * year of the player's weight.
     */
    private ITextInput weightInput;
    /**
     * validation message for first name.
     */
    private IValidationLabel errorMessageFirstName;
    /**
     * validation message for last name.
     */
    private IValidationLabel errorMessageLastName;
    /**
     * validation message for birth year.
     */
    private IValidationLabel errorMessageBirthYear;
    /**
     * validation message for height.
     */
    private IValidationLabel errorMessageHeight;
    /**
     * validation message for weight.
     */
    private IValidationLabel errorMessageWeight;
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
     * @return sets the locator of message.
     */
    public final IValidationLabel getErrorMessageLabelFirstName() {
        this.errorMessageFirstName = ValidationLabel.getByXpath("//div["
                + "label[contains(.,'Имя')]]/div[@class = \"hint\"]");
        return errorMessageFirstName;
    }
    /**
     * @return sets the locator of message.
     */
    public final IValidationLabel getErrorMessageLabelLastName() {
        if (this.errorMessageLastName == null) {
            this.errorMessageLastName = ValidationLabel.getByXpath("//div["
                    + "label[contains(.,'Фамилия')]]/div[@class = \"hint\"]");
        }
        return errorMessageLastName;
    }
    /**
     * @return sets the locator of message.
     */
    public final IValidationLabel getErrorMessageBirthYear() {
        if (this.errorMessageBirthYear == null) {
            this.errorMessageBirthYear = ValidationLabel.getByXpath("//div["
                    + "label[contains(.,'Год рождения')]]/div[@class = "
                    + "\"hint\"]");
        }
        return errorMessageBirthYear;
    }
    /**
     * @return sets the locator of message.
     */
    public final IValidationLabel getErrorMessageLabelHeight() {
        if (this.errorMessageHeight == null) {
            this.errorMessageHeight = ValidationLabel.getByXpath("//div["
                    + "label[contains(.,'Рост')]]/div[@class = \"hint\"]");
        }
        return errorMessageHeight;
    }
    /**
     * @return sets the locator of message.
     */
    public final IValidationLabel getErrorMessageLabelWeight() {
        if (this.errorMessageWeight == null) {
            this.errorMessageWeight = ValidationLabel.getByXpath("//div["
                    + "label[contains(.,'Вес')]]/div[@class = \"hint\"]");
        }
        return errorMessageWeight;
    }
    /**
     * @return first name player's label.
     */
    public final ILabel getFirstNameLabel() {
        if (this.firstNameLabel == null) {
            this.firstNameLabel = Label.getByCss("#main > div:nth-child(n) "
                    + "> div > div.panel-body > form > div:nth-child(1) "
                    + "> label");
        }
        return firstNameLabel;
    }
    /**
     * @return first name player's input.
     */
    public final ITextInput getFirstNameInput() {
        if (this.firstNameInput == null) {
            this.firstNameInput = TextInput.getByCss("[name=firstName]");
        }
        return firstNameInput;
    }
    /**
     * @return last name player's label.
     */
    public final ILabel getLastNameLabel() {
        if (this.lastNameLabel == null) {
            this.lastNameLabel = Label
            		.getByCss("#main > div:nth-child(n) > "
            		        + "div > div.panel-body "
            		        + "> form > div:nth-child(2) > label");
        }
        return lastNameLabel;
    }
    /**
     * @return last name player's input.
     */
    public final ITextInput getLastNameInput() {
        if (this.lastNameInput == null) {
            this.lastNameInput = TextInput.getByCss("[name=lastName]");
        }
        return lastNameInput;
    }
    /**
     * @return year of player's birth's label.
     */
    public final ILabel getBirthYearLabel() {
        if (this.birthYearLabel == null) {
            this.birthYearLabel = Label.getByCss("#main > div:nth-child(n) "
                    + "> div > div.panel-body > form > div:nth-child(3) "
                    + "> label");
        }
        return birthYearLabel;
    }
    /**
    * @return year of player's birth's input.
     */
    public final ITextInput getBirthYearInput() {
        if (this.birthYearInput == null) {
            this.birthYearInput = TextInput.getByCss("[name=birthYear]");
        }
        return birthYearInput;
    }
    /**
     * @return the player's height label.
     */
    public final ILabel getHeightLabel() {
        if (this.heightLabel == null) {
            this.heightLabel = Label.getByCss("#main > div:nth-child(n) > div "
                    + "> div.panel-body > form > div:nth-child(4) > label");
        }
        return heightLabel;
    }
    /**
     * @return the player's height input.
      */
     public final ITextInput getHeightInput() {
         if (this.heightInput == null) {
             this.heightInput = TextInput.getByCss("[name=height]");
         }
         return heightInput;
     }
     /**
      * @return the player's height label.
      */
     public final ILabel getWeightLabel() {
         if (this.weightLabel == null) {
             this.weightLabel = Label.getByCss("#main > div:nth-child(n) > div "
                     + "> div.panel-body > form > div:nth-child(5) > label");
         }
         return weightLabel;
     }
     /**
      * @return the player's height input.
       */
      public final ITextInput getWeightInput() {
          if (this.weightInput == null) {
              this.weightInput = TextInput.getByCss("[name=weight]");
          }
          return weightInput;
      }
}
