package com.softserveinc.ita.volleymanagementtests.pages;

import com.softserveinc.ita.volleymanagementtests.tools.controls.Button;
import com.softserveinc.ita.volleymanagementtests.tools.controls.Calendar;
import com.softserveinc.ita.volleymanagementtests.tools.controls.Dropdown;
import com.softserveinc.ita.volleymanagementtests.tools.controls.Label;
import com.softserveinc.ita.volleymanagementtests.tools.controls.TextInput;
import com.softserveinc.ita.volleymanagementtests.tools.controls.ValidationLabel;
import com.softserveinc.ita.volleymanagementtests.tools.controls.interfaces.IButton;
import com.softserveinc.ita.volleymanagementtests.tools.controls.interfaces.ICalendar;
import com.softserveinc.ita.volleymanagementtests.tools.controls.interfaces.IDropdown;
import com.softserveinc.ita.volleymanagementtests.tools.controls.interfaces.ILabel;
import com.softserveinc.ita.volleymanagementtests.tools.controls.interfaces.ITextInput;
import com.softserveinc.ita.volleymanagementtests.tools.controls.interfaces.IValidationLabel;

/**
 * @author Dp-076 ATQC
 * This class describes UI elements of the main page.
 */
public class TournamentDataPageUIMap {

    /**
     * @param pageTitleLabel is the title of the page;
     */
    private ILabel pageTitleLabel;
    /**
     * @param tournament name label.
     */
    private ILabel tournamentNameLabel;
    /**
     * @param firstNameInput holds location of input tournament name.
     */
    private ITextInput tournamentNameInput;
    /**
     * @param description label.
     */
    private ILabel descriptionLabel;
    /**
     * @param descriptionInput holds location of input for description.
     */
    private ITextInput descriptionInput;
    /**
     * @param seasonLabel.
     */
    private ILabel seasonLabel;
    /**
     * @param seasonInput holds location of seazon input.
     */
    private IDropdown seasonDropdown;
    //Applying
    /**
     * @param termsApplyingLabel.
     */
    private ILabel termsApplyingLabel;
    /**
     * @param startApplyingInput holds location of input for
     * tornament's registration start.
     */
    private ITextInput startApplyingInput;
    /**
     * @param endApplyingInput holds location of input for
     * tornament's registration end.
     */
    private ITextInput endApplyingInput;
    /**
     * @param startApplyingButton.
     */
    private IButton startApplyingButton;
    /**
     * @param endApplyingButton.
     */
    private IButton endApplyingButton;
    //Tournament
    /**
     * @param termsTournamentLabel.
     */
    private ILabel termsTournamentLabel;
    /**
     * @param startTournamentInput holds location of input for
     * tornament's start.
     */
    private ITextInput startTournamentInput;
    /**
     * @param endTournamentInput holds location of input for
     * tornament's end.
     */
    private ITextInput endTournamentInput;
    /**
     * @param startTournamentButton.
     */
    private IButton startTournamentButton;
    /**
     * @param endTournamentButton.
     */
    private IButton endTournamentButton;
    //Transfer
    /**
     * @param termsTransferLabel.
     */
    private ILabel termsTransferLabel;
    /**
     * @param startTransferInput holds location of input for
     * tornament's transer period start.
     */
    private ITextInput startTransferInput;
    /**
     * @param endTransferInput holds location of input for
     * tornament's tranfer period end.
     */
    private ITextInput endTransferInput;
    /**
     * @param startTransferButton.
     */
    private IButton startTransferButton;
    /**
     * @param endTransferButton.
     */
    private IButton endTransferButton;
    /**
     * @param calendar holds the calendar's location.
     */
    private ICalendar calendar;
    /**
     * @param schemeLabel.
     */
    private ILabel schemeLabel;
    /**
     * @param schemeInput holds location of scheme input.
     */
    private IDropdown schemeDropdown;
    /**
     * @param schemeLabel.
     */
    private ILabel linkToReglamentLabel;
    /**
     * @param linkToReglamentInput holds location of linkToReglament input.
     */
    private ITextInput linkToReglamentInput;
    /**
     * holder for error massage of tournament name field.
     */
    private IValidationLabel errorMessageTornamentName;
    /**
     * holder for error massage of tournament description field.
     */
    private IValidationLabel errorMessageDescription;
    /**
     * holder for error massage of tournament regulations link field.
     */
    private IValidationLabel errorMessageRegulationsLink;
    /**
     * holder for error massage of tournament registration start field.
     */
    private IValidationLabel errorMessageApplyingStart;
    /**
     * holder for error massage of tournament registration end field.
     */
    private IValidationLabel errorMessageApplyingEnd;
    /**
     * holder for error massage of tournament game start field.
     */
    private IValidationLabel errorMessageGameStart;
    /**
     * holder for error massage of tournament game end field.
     */
    private IValidationLabel errorMessageGameEnd;
    /**
     * holder for error massage of tournament game start field.
     */
    private IValidationLabel errorMessageTransferStart;
    /**
     * holder for error massage of tournament game end field.
     */
    private IValidationLabel errorMessageTransferEnd;
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
    public final IValidationLabel getErrorMessageLabelTournamentName() {
            this.errorMessageTornamentName = ValidationLabel.getByXpath("//div["
                    + "label[contains(.,'Название')]]/div[@class = \"hint\"]");
        return errorMessageTornamentName;
    }
    /**
     * @return label of validation message for description.
     * sets the location of it.
     */
    public final IValidationLabel getErrorMessageLabelDescription() {
                this.errorMessageDescription = ValidationLabel.getByXpath(
                        "//div["
                        + "label[contains(.,'Описание')]]/div[@class = "
                        + "\"hint\"]");
            return errorMessageDescription;
        }
    /**
     * @return label of validation message for regulations link.
     * sets the location of it.
     */
    public final IValidationLabel getErrorMessageLabelRegulationsLink() {
        this.errorMessageRegulationsLink = ValidationLabel.getByXpath("//div["
                + "label[contains(.,'Ссылка')]]/div[@class = \"hint\"]");
        return errorMessageRegulationsLink;
    }
    /**
     * @return label of validation message for applying start.
     * sets the location of it.
     */
    public final IValidationLabel getErrorMessageLabelApplyingStart() {
        this.errorMessageApplyingStart = ValidationLabel.getByXpath(
                ".//*[@id='datetimepicker1']/div");
        return errorMessageApplyingStart;
    }
    /**
     * @return label of validation message for applying and.
     * sets the location of it.
     */
    public final IValidationLabel getErrorMessageLabelApplyingEnd() {
        this.errorMessageApplyingEnd = ValidationLabel.getByXpath(
                ".//*[@id='datetimepicker2']/div");
        return errorMessageApplyingEnd;
    }
    /**
     * @return label of validation message for tournament start.
     * sets the location of it.
     */
    public final IValidationLabel getErrorMessageLabelTournamentStart() {
        this.errorMessageGameStart = ValidationLabel.getByXpath(
                ".//*[@id='datetimepicker3']/div");
        return errorMessageGameStart;
    }/**
     * @return label of validation message for tournament end.
     * sets the location of it.
     */
    public final IValidationLabel getErrorMessageLabelTournamentEnd() {
        this.errorMessageGameEnd = ValidationLabel.getByXpath(
                ".//*[@id='datetimepicker4']/div");
        return errorMessageGameEnd;
    }
    /**
     * @return label of validation message for tranfer start.
     * sets the location of it.
     */
    public final IValidationLabel getErrorMessageLabelTransferStart() {
        this.errorMessageTransferStart = ValidationLabel.getByXpath(
                ".//*[@id='datetimepicker5']/div");
        return errorMessageTransferStart;
    }
    /**
     * @return label of validation message for transfer end.
     * sets the location of it.
     */
    public final IValidationLabel getErrorMessageLabelTransferEnd() {
        this.errorMessageTransferEnd = ValidationLabel.getByXpath(
                ".//*[@id='datetimepicker6']/div");
        return errorMessageTransferEnd;
    }
    /**
     * @return tournament's name label.
     */
    public final ILabel getTornamentNameLabel() {
        if (this.tournamentNameLabel == null) {
            this.tournamentNameLabel = Label.getByCss("#main > div:nth-child(n)"
                    + " > div > div.panel-body > form > div:nth-child(1) "
                    + "> label");
        }
        return tournamentNameLabel;
    }
    /**
     * @return first name player's input.
     */
    public final ITextInput getTournamentNameInput() {
        if (this.tournamentNameInput == null) {
            this.tournamentNameInput = TextInput.getByCss("[name=name]");
        }
        return tournamentNameInput;
    }
    /**
     * @return last name player's label.
     */
    public final ILabel getDescriptionLabel() {
        if (this.descriptionLabel == null) {
            this.descriptionLabel = Label
                    .getByCss("#main > div:nth-child(n) > div > div.panel-body "
                            + "> form > div:nth-child(2) > label");
        }
        return descriptionLabel;
    }
    /**
     * @return last name player's input.
     */
    public final ITextInput getDescriptionInput() {
        if (this.descriptionInput == null) {
            this.descriptionInput = TextInput.getByCss("[name=description]");
        }
        return descriptionInput;
    }
    /**
     * @return year of player's birth's label.
     */
    public final ILabel getSeasonLabel() {
        if (this.seasonLabel == null) {
            this.seasonLabel = Label.getByCss("#main > div:nth-child(n) "
                    + "> div > div.panel-body > form > div:nth-child(3) "
                    + "> label");
        }
        return seasonLabel;
    }
    /**
    * @return year of player's birth's input.
     */
    public final IDropdown getSeasonDropdown() {
        if (this.seasonDropdown == null) {
            this.seasonDropdown = Dropdown.getByCss("[name=season]");
        }
        return seasonDropdown;
    }
    //Applying
    /**
     * @return the player's height label.
     */
    public final ILabel getApplyingTermsLabel() {
        if (this.termsApplyingLabel == null) {
            this.termsApplyingLabel = Label.getByCss("#main > "
                    + "div:nth-child(n) > div > div.panel-body > form > "
                    + "label:nth-child(4)");
        }
        return termsApplyingLabel;
    }
    /**
     * @return the player's height input.
      */
     public final ITextInput getApplyingStartInput() {
         if (this.startApplyingInput == null) {
             this.startApplyingInput = TextInput.getByCss(
                     "[name=registrationStart]");
         }
         return startApplyingInput;
     }
     /**
      * @return the player's height input.
       */
      public final ITextInput getApplyingEndInput() {
          if (this.endApplyingInput == null) {
              this.endApplyingInput = TextInput.getByCss(
                      "[name=registrationFinish]");
          }
          return endApplyingInput;
      }
      /**
       * @return the player's height label.
       */
      public final IButton getApplyingStartButton() {
        if (this.startApplyingButton == null) {
            this.startApplyingButton = Button.getByXpath(".//*["
                    + "@id='datetimepicker1']/span/span");
        }
        return startApplyingButton;
      }
      /**
       * @return the player's height label.
       */
      public final IButton getApplyingEndButton() {
        if (this.endApplyingButton == null) {
            this.endApplyingButton = Button.getByXpath(".//*["
                    + "@id='datetimepicker2']/span/span");
        }
        return endApplyingButton;
      }
    //Tournament
      /**
       * @return the player's height label.
       */
      public final ILabel getTournamentTermsLabel() {
          if (this.termsTournamentLabel == null) {
              this.termsTournamentLabel = Label.getByCss("#main > "
                      + "div:nth-child(n) > div > div.panel-body > form > "
                      + "label:nth-child(6)");
          }
          return termsTournamentLabel;
      }
      /**
       * @return the player's height input.
        */
       public final ITextInput getTournamentStartInput() {
           if (this.startTournamentInput == null) {
               this.startTournamentInput = TextInput.getByCss(
                       "[name=tournamentStart]");
           }
           return startTournamentInput;
       }
       /**
        * @return the player's height input.
         */
        public final ITextInput getTournamentEndInput() {
            if (this.endTournamentInput == null) {
                this.endTournamentInput = TextInput.getByCss(
                        "[name=tournamentFinish]");
            }
            return endTournamentInput;
        }
      /**
     * @return the player's height label.
     */
    public final IButton getTournamentStartButton() {
        if (this.startTournamentButton == null) {
            this.startTournamentButton = Button.getByXpath(".//*["
                    + "@id='datetimepicker3']/span/span");
        }
        return startTournamentButton;
    }
    /**
     * @return the player's height label.
     */
    public final IButton getTournamentEndButton() {
        if (this.endTournamentButton == null) {
            this.endTournamentButton = Button.getByXpath(".//*["
                    + "@id='datetimepicker4']/span/span");
        }
        return endTournamentButton;
    }
    //Transfer
    /**
     * @return the player's height label.
     */
    public final ILabel getTransferPeriodTermsLabel() {
        if (this.termsTransferLabel == null) {
            this.termsTransferLabel = Label.getByCss("#main > "
                    + "div:nth-child(n) > div > div.panel-body > form > "
                    + "label:nth-child(8)");
        }
        return termsTransferLabel;
    }
    /**
     * @return the player's height input.
      */
     public final ITextInput getTransferStartInput() {
         if (this.startTransferInput == null) {
             this.startTransferInput = TextInput.getByCss(
                     "[name=transferStart]");
         }
         return startTransferInput;
     }
     /**
      * @return the player's height input.
       */
      public final ITextInput getTransferEndInput() {
          if (this.endTransferInput == null) {
              this.endTransferInput = TextInput.getByCss(
                      "[name=transferFinish]");
          }
          return endTransferInput;
      }
    /**
     * @return the player's height label.
     */
    public final IButton getTransferStartButton() {
        if (this.startTransferButton == null) {
            this.startTransferButton = Button.getByXpath(".//*["
                    + "@id='datetimepicker5']/span/span");
        }
        return startTransferButton;
    }
    /**
     * @return the player's height label.
     */
    public final IButton getTransferEndButton() {
        if (this.endTransferButton == null) {
            this.endTransferButton = Button.getByXpath(".//*["
                    + "@id='datetimepicker6']/span/span");
        }
        return endTransferButton;
    }
    /**
     * @return the player's height label.
     */
    public final ILabel getSchemeLabel() {
        if (this.schemeLabel == null) {
            this.schemeLabel = Label.getByXpath(
                    ".//*[@id='main']/div[3]/div/div[2]/form/div[7]/label");
        }
        return schemeLabel;
    }
    /**
     * @return the player's height label.
     */
    public final IDropdown getSchemeDropdown() {
        if (this.schemeDropdown == null) {
            this.schemeDropdown = Dropdown.getByCss("[name=scheme]");
        }
        return schemeDropdown;
    }
    /**
     * @return the player's height label.
     */
    public final ILabel getLinkToReglamentLabel() {
        if (this.linkToReglamentLabel == null) {
                this.linkToReglamentLabel = Label.getByCss("#main > "
                        + "div:nth-child(n) > div > div.panel-body > form > "
                        + "div:nth-child(11) > label");
        }
        return linkToReglamentLabel;
    }
          /**
           * @return the player's height input.
            */
           public final ITextInput getLinkToReglamentInput() {
               if (this.linkToReglamentInput == null) {
                   this.linkToReglamentInput = TextInput.getByCss(
                           "[name=link]");
               }
               return linkToReglamentInput;
           }
           /**
            * @return the player's height label.
            */
           public final ICalendar getCalendar() {
               if (this.calendar == null) {
                   this.calendar = Calendar.getByCss(
                           ".bootstrap-datetimepicker-widget"
                           + ".dropdown-menu");
               }
               return calendar;
           }

}
