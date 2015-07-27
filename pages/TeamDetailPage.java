package com.softserveinc.ita.volleymanagementtests.pages;

import com.softserveinc.ita.volleymanagementtests.domain.models.Team;
import com.softserveinc.ita.volleymanagementtests.tools.controls.interfaces.IButton;
import com.softserveinc.ita.volleymanagementtests.tools.controls.interfaces.ILabel;

/**
 * @author Андрей
 *
 */
/**
 * @author Андрей
 *
 */
/**
 * @author Андрей
 *
 */
/**
 * @author Андрей
 *
 */
/**
 * @author Андрей
 *
 */
public class TeamDetailPage  extends Page {

    private TeamDetailPageUIMap controls;
    private NavigationPage navigationPage;

    public TeamDetailPage() {
            verifyUrlStartWith("http://localhost:23801/Teams/",
                    "It's not detail team page");
            controls = new TeamDetailPageUIMap();
            navigationPage = new NavigationPage();
    }
    /**
     * @return navigation panel.
     */
    public NavigationPage getNavigation() {
        return this.navigationPage;
    }
    /**
     * @return title label.
     */
    public final ILabel getTitleLabel() {
        return controls.getPageTitleLabel();
    }
    /**
     * @return team's name label.
     */
    public final ILabel getTeamNameLabel() {
        return controls.getTeamNameLabel();
    }
    /**
     * @return  team's name data label.
     */
    public final ILabel getTeamNameDataLabel() {
        return controls.getTeamNameDataLabel();
    }
    /**
     * @param team's captain label.
     */
    public final ILabel getCaptainLabel() {
        return controls.getCaptainLabel();
    }
    /**
     * @param team's captain data label.
     */
    public final ILabel getCaptainDataLabel() {
        return controls.getCaptainDataLabel();
    }
    /**
     * @param team's coach label.
     */
    public final ILabel getCoachLabel() {
        return controls.getCoachLabel();
    }
    /**
     * @param team's coach data label.
     */
    public final ILabel getCoachDataLabel() {
        return controls.getCoachDataLabel();
    }
    /**
     * @param team's achievements label.
     */
    public final ILabel getAchievementsLabel() {
        return controls.getAchievementsLabel();
    }
    /**
     * @param team's achievements data label.
     */
    public final ILabel getAchievementsDataLabel() {
        return controls.getAchievementsDataLabel();
    }  
    
    /**
     * This method clicks on edit button.
     * @return new EditTeamPage.
     */
    public EditTeamPage pressEditButton() {
        controls.getEditButton().click();
        return new EditTeamPage();
    }
    /**
     * This method clicks on Return button.
     * @return new TeamListPage.
     */
    public TeamListPage pressReturnButton() {
        controls.getReturnButton().click();
        return new TeamListPage();
    }
    /**
     * This method clicks on Delete button.
     * @return new ConfirmDialogPage.
     */
    public ConfirmDialogPage pressDeleteButton() {
        controls.getDeleteButton().click();
        return new ConfirmDialogPage();
    }
    /**
     * This method returns edit button.
     * @return edit button
     */
    public IButton getEditButton() {
        return controls.getEditButton();
    }
    /**
     * This method returns Return button.
     * @return return button
     */
    public IButton getReturnButton() {
        return controls.getReturnButton();
    }
    /**
     * This method returns delete button.
     * @return delete button
     */
    public IButton getDeleteButton() {
        return controls.getDeleteButton();
    }

    /**
     * This method gets data from fields and makes Team object.
     * @return team.
     */
    public Team getTeam() {
        Team team = new Team();
        team.setTeamName(this.getTeamNameDataLabel().getText());
        team.setCaptain(this.getCaptainDataLabel().getText());
        if (this.getCoachDataLabel().equals("")) {
            team.setCoach(null);
        } else {
            team.setCoach(this.getCoachDataLabel().getText());
        }
        if (this.getAchievementsDataLabel().equals("")) {
            team.setAchievements(null);
        } else {
            team.setAchievements(this.getAchievementsDataLabel().getText());
        }
        return team;
    }
}
