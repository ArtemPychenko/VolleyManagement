package com.softserveinc.ita.volleymanagementtests.pages;

import com.softserveinc.ita.volleymanagementtests.tools.controls.Button;
import com.softserveinc.ita.volleymanagementtests.tools.controls.Label;
import com.softserveinc.ita.volleymanagementtests.tools.controls.interfaces.IButton;
import com.softserveinc.ita.volleymanagementtests.tools.controls.interfaces.ILabel;

public class TeamDetailPageUIMap {
   
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
    public TeamDetailPageUIMap(){
            }
            
    /**
     * @param pageTitleLabel is the title of the page;
     */
    private ILabel pageTitleLabel;
        
    /**
     * @param team's name label.
     */
    private ILabel teamNameLabel;
    private ILabel teamNameDataLabel;
        
    /**
     * @param team's captain label.
     */
    private ILabel captainLabel;
    private ILabel captainDataLabel;
        
    /**
     * @param team's coach label.
     */
    private ILabel coachLabel;
    private ILabel coachDataLabel;
        
    /**
     * @param team's achievements label.
     */
    private ILabel achievementsLabel;
    private ILabel achievementsDataLabel;
        
    /**
     * @return page title label.
     */
    public final ILabel getPageTitleLabel() {
        if (this.pageTitleLabel == null) {
            this.pageTitleLabel = Label.getByXpath
                    (".//*[@id='main']/div[3]/div/div[1]/div/div[1]/h4");
        }
        return pageTitleLabel;
    }

    /**
     * @return first team's name label.
     */
    public final ILabel getTeamNameLabel() {
        if (this.teamNameLabel == null) {
            this.teamNameLabel = Label.getByXpath(".//*[@id"
                    + "='main']/div[3]/div/div[2]/div[1]/div[1]/div[1]/label");
        }
        return teamNameLabel;
    }
        
    /**
     * @return first team's name data label.
     */
    public final ILabel getTeamNameDataLabel() {
        if (this.teamNameDataLabel == null) {
            this.teamNameDataLabel = Label.getByName("name");
        }
        return teamNameDataLabel;
    }
        
    /**
     * @return team's captain label.
     */
    public final ILabel getCaptainLabel() {
        if (this.captainLabel == null) {
            this.captainLabel = Label.getByXpath(".//*[@id"
                    + "='main']/div[3]/div/div[2]/div[1]/div[1]/div[2]/label");
        }
        return captainLabel;
    }
    /**
     * @return team's captain data label.
     */
    public final ILabel getCaptainDataLabel() {
            if (this.captainDataLabel == null) {
                this.captainDataLabel = Label.getByName("captain");
            }
            return captainDataLabel;
    }
    /**
     * @return team's coach label.
     */
    public final ILabel getCoachLabel() {
        if (this.coachLabel == null) {
            this.coachLabel = Label.getByXpath(".//*[@id"
                    + "='main']/div[3]/div/div[2]/div[1]/div[1]/div[3]/label");
        }
        return coachLabel;
    }
    /**
     * @return team's coach data label.
     */
    public final ILabel getCoachDataLabel() {
        if (this.coachDataLabel == null) {
            this.coachDataLabel = Label.getByName("coach");
        }
        return coachDataLabel;
    }
    /**
     * @return team's achievements  label.
     */
    public final ILabel getAchievementsLabel() {
        if (this.achievementsLabel == null) {
            this.achievementsLabel = Label.getByXpath(".//*[@id"
                    + "='main']/div[3]/div/div[2]/div[1]/div[1]/div[4]/label");
        }
        return achievementsLabel;
    }
    /**
     * @return team's achievements data label.
     */
    public final ILabel getAchievementsDataLabel() {
        if (this.achievementsDataLabel == null) {
            this.achievementsDataLabel = Label.getByName("achievements");
        }
        return achievementsDataLabel;
    }
    /**
     * This method returns edit button.
     * @return edit button
     */
    public IButton getEditButton() {
        if (this.editButton == null) {
            this.editButton = Button.getByCss(
                    ".btn.btn-success.pull-right.edit");
        }
        return editButton;
    }
    /**
     * This method returns return button.
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
     * This method returns delete button.
     * @return delete button
     */
    public IButton getDeleteButton() {
        if (this.deleteButton == null) {
            this.deleteButton = Button.getByCss(
                    ".btn.btn-danger.pull-right.delete");
        }
        return deleteButton;
    }
}



