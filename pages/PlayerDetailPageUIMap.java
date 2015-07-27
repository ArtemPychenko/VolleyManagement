package com.softserveinc.ita.volleymanagementtests.pages;

import com.softserveinc.ita.volleymanagementtests.tools.controls.Button;
import com.softserveinc.ita.volleymanagementtests.tools.controls.Label;
import com.softserveinc.ita.volleymanagementtests.tools.controls.interfaces.IButton;
import com.softserveinc.ita.volleymanagementtests.tools.controls.interfaces.ILabel;

public class PlayerDetailPageUIMap {
	/**
	 * @param editButton holds location of edit button.
	 */
	private IButton editButton;
	/**
	 * @param returnButton holds location of return button.
	 */
	private IButton returnButton;
	/**
	 * @param deleteButton holds location of delete button.
	 */
	private IButton deleteButton;
	/**
	 * Constructor.
	 * Calls superclass constructor.
	 */
	public PlayerDetailPageUIMap(){
	}
	
	/**
     * @param pageTitleLabel is the title of the page;
     */
    private ILabel pageTitleLabel;
    
    /**
     * @param player's first name label.
     */
    private ILabel firstNameLabel;
    private ILabel firstNameInputLabel;
    
    /**
     * @param player's last name label.
     */
    private ILabel lastNameLabel;
    private ILabel lastNameInputLabel;
    
    /**
     * @param birthYearLabel.
     */
    private ILabel birthYearLabel;
    private ILabel birthYearInputLabel;
    
    /**
     * @param heightLabel.
     */
    private ILabel heightLabel;
    private ILabel heightInputLabel;
    
    /**
     * @param weightLabel.
     */
    private ILabel weightLabel;
    private ILabel weightInputLabel;
    
    /**
     * @return page title label.
     */
    public final ILabel getPageTitleLabel() {
        if (this.pageTitleLabel == null) {
            this.pageTitleLabel = Label.getByCss("#main > div.homepage > div "
                    + "> div.panel-heading > div > div.col-sm-8 > h4");
        }
        return pageTitleLabel;
    }

    
    /**
     * @return first name player's label.
     */
    public final ILabel getFirstNameLabel() {
        if (this.firstNameLabel == null) {
            this.firstNameLabel = Label.getByCss("#main > div.homepage > div "
                    + "> div.panel-body.player-panel > div:nth-child(1) > div"
                    + ".col-md-9.player-info-container > div:nth-child(1) > label");
        }
        return firstNameLabel;
    }
    public final ILabel getFirstNameInputLabel() {
    	if (this.firstNameInputLabel == null) {
            this.firstNameInputLabel = Label.getByName("firstName");
        }
        return firstNameInputLabel;
    }
    
    /**
     * @return last name player's label.
     */
    public final ILabel getLastNameLabel() {
        if (this.lastNameLabel == null) {
            this.lastNameLabel = Label
                    .getByCss("#main > div.homepage > div > div.panel-body"
                            + ".player-panel > div:nth-child(1) > div.col-md-9"
                            + ".player-info-container > div:nth-child(2) > label");
        }
        return lastNameLabel;
    }
    
    public final ILabel getLastNameInputLabel() {
    	if (this.lastNameInputLabel == null) {
            this.lastNameInputLabel = Label.getByName("lastName");
        }
        return lastNameInputLabel;
    }
    /**
     * @return year of player's birth's label.
     */
    public final ILabel getBirthYearLabel() {
        if (this.birthYearLabel == null) {
            this.birthYearLabel = Label.getByCss("#main > div.homepage > div "
                    + "> div.panel-body.player-panel > div:nth-child(1) > div"
                    + ".col-md-9.player-info-container > div:nth-child(3) > label");
        }
        return birthYearLabel;
    }
    public final ILabel getBirthYearInputLabel() {
    	if (this.birthYearInputLabel == null) {
            this.birthYearInputLabel = Label.getByName("birthYear");
        }
        return birthYearInputLabel;
    }
    
    /**
     * @return the player's height label.
     */
    public final ILabel getHeightLabel() {
        if (this.heightLabel == null) {
            this.heightLabel = Label.getByCss("#main > div.homepage > div > div"
                    + ".panel-body.player-panel > div:nth-child(1) > div"
                    + ".col-md-9.player-info-container > div:nth-child(4) > label");
        }
        return heightLabel;
    }
    public final ILabel getHeightInputLabel() {
    	if (this.heightInputLabel == null) {
            this.heightInputLabel = Label.getByName("height");
        }
        return heightInputLabel;
    }
    
     /**
      * @return the player's height label.
      */
     public final ILabel getWeightLabel() {
         if (this.weightLabel == null) {
             this.weightLabel = Label.getByCss("#main > div.homepage > div > "
                     + "div.panel-body.player-panel > div:nth-child(1) > div"
                     + ".col-md-9.player-info-container > div:nth-child(5) > label");
         }
         return weightLabel;
     }
     public final ILabel getWeightInputLabel() {
     	if (this.weightInputLabel == null) {
             this.weightInputLabel = Label.getByName("weight");
         }
         return weightInputLabel;
     }
     
     
	/**
	 * This method return edit button.
	 * @return edit button
	 */
	public IButton getEditButton() {
		if (this.editButton == null) {
            this.editButton = Button.getByCss(".btn.btn-success.pull-right.edit");
        }
        return editButton;
	}
	/**
	 * This method return return button.
	 * @return return button
	 */
	public IButton getReturnButton() {
		if (this.returnButton == null) {
			this.returnButton = Button.getByCss(
					".btn.btn-warning.pull-left.cancel");
		}
		return returnButton;
	}
	/**
	 * This method return delete button.
	 * @return delete button
	 */
	public IButton getDeleteButton() {
		if (this.deleteButton == null) {
			this.deleteButton = Button.getByCss(".btn.btn-danger.pull-right.delete");
		}
		return deleteButton;
	}
}
