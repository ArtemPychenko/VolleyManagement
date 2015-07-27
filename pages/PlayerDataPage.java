package com.softserveinc.ita.volleymanagementtests.pages;

import com.softserveinc.ita.volleymanagementtests.domain.models.Player;
import com.softserveinc.ita.volleymanagementtests.tools.controls.interfaces.ILabel;
import com.softserveinc.ita.volleymanagementtests.tools.controls.interfaces.ITextInput;
import com.softserveinc.ita.volleymanagementtests.tools.controls.interfaces.IValidationLabel;
/**
 * @author Dp-076 ATQC
 * This class is a superclass for new player page and edit player page.
 * So kept the most of logic in it.
 */
public class PlayerDataPage {
    /**
     * @param controls encapsulates map of page.
     */
    private PlayerDataPageUIMap controls;
    /**
     * Initialization of player data page ui map to get the control of page's
     * element's location.
     */
    public PlayerDataPage(){
        controls = new PlayerDataPageUIMap();
    }
    /**
     * @return validation error message of first name.
     */
    public final IValidationLabel getErrorMessageLabelFirstName() {
        return controls.getErrorMessageLabelFirstName();
    }
    /**
     * @return validation error message of last name.
     */
    public final IValidationLabel getErrorMessageLabelLastName() {
        return controls.getErrorMessageLabelLastName();
    }
    /**
     * @return validation error message of birth.
     */
    public final IValidationLabel getErrorMessageBirthYear() {
        return controls.getErrorMessageBirthYear();
    }
    /**
     * @return validation error message of height.
     */
    public final IValidationLabel getErrorMessageLabelHeight() {
        return controls.getErrorMessageLabelHeight();
    }
    /**
     * @return validation error message of weight.
     */
    public final IValidationLabel getErrorMessageLabelWeight() {
        return controls.getErrorMessageLabelWeight();
    }
    /**
     * @return page title.
     */
    public final ILabel getTitleLabel() {
        return controls.getPageTitleLabel();
    }
    /**
     * @return name label.
     */
    public final ILabel getFirstNameLabel() {
        return controls.getFirstNameLabel();
    }
    /**
     * @return first name input.
     */
    public final ITextInput getFirstNameInput() {
        return controls.getFirstNameInput();
    }
    /**
     * @param player is an instance of player model.
     * @return player data page.
     * typing the player first name to the first name input.
     */
    public final PlayerDataPage setFirstName(final Player player) {
        controls.getFirstNameInput().click();
        controls.getFirstNameInput().clear();
        controls.getFirstNameInput().type(player.getFirstName());
        return this;
    }
    /**
     * @return last name label.
     */
    public final ILabel getSecondNameLabel() {
        return controls.getLastNameLabel();
    }
    /**
     * @return second name input.
     */
    public final ITextInput getSecondNameInput() {
        return controls.getLastNameInput();
    }
    /**
     * @param player is an instance of player model.
     * @return player data page.
     * typing the player last name to the last name input.
     */
    public final PlayerDataPage setSecondName(final Player player) {
        controls.getLastNameInput().click();
        controls.getLastNameInput().clear();
        controls.getLastNameInput().type(player.getLastName());
        return this;
    }
    /**
     * @return birth year label.
     */
    public final ILabel getBirthYearLabel() {
        return controls.getBirthYearLabel();
    }
    /**
     * @return birth year input.
     */
    public final ITextInput getBirthYearInput() {
        return controls.getBirthYearInput();
    }
    /**
     * @param player is an instance of player model.
     * @return player data page.
     * typing the player birth to the birth input.
     */
    public final PlayerDataPage setBirthYear(final Player player) {
        controls.getBirthYearInput().click();
        controls.getBirthYearInput().clear();
        if (player.getBirthYear() == null) {
            controls.getBirthYearInput().type("");
        } else {
        controls.getBirthYearInput().type(player.getBirthYear().toString());
        }
        return this;
    }
    /**
     * @return height label.
     */
    public final ILabel getHeightLabel() {
        return controls.getHeightLabel();
    }
    /**
     * @return height input.
     */
    public final ITextInput getHeightInput() {
        return controls.getHeightInput();
    }
    /**
     * @param player is an instance of player model.
     * @return player data page.
     * typing the player's height to the height input.
     */
    public final PlayerDataPage setHeight(final Player player) {
        controls.getHeightInput().click();
        controls.getHeightInput().clear();
        if (player.getHeight() == null) {
            controls.getHeightInput().type("");
        } else {
        controls.getHeightInput().type(player.getHeight().toString());
        }
        return this;
    }
    /**
     * @return weight label.
     */
    public final ILabel getWeightLabel() {
        return controls.getWeightLabel();
    }
    /**
     * @return weight input.
     */
    public final ITextInput getWeightInput() {
        return controls.getWeightInput();
    }
    /**
     * @param player is an instance of player model.
     * @return player data page.
     * typing the player's weight to the weight input.
     */
    public final PlayerDataPage setWeight(final Player player) {
        controls.getWeightInput().click();
        controls.getWeightInput().clear();
        if (player.getWeight() == null) {
            controls.getWeightInput().type("");
        } else {
        controls.getWeightInput().type(player.getWeight().toString());
        }
        return this;
    }
    /**.
     * This method sets all fields from Player object to PlayerDatePage
     * @param player is an instance of player model.
     * @return player data page.
     */
    public final PlayerDataPage setAllFields(final Player player) {
        setFirstName(player);
        setSecondName(player);
        setBirthYear(player);
        setHeight(player);
        setWeight(player);
        return this;
    }
}
