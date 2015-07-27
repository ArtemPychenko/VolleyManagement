package com.softserveinc.ita.volleymanagementtests.pages;

import com.softserveinc.ita.volleymanagementtests.domain.models.Team;
import com.softserveinc.ita.volleymanagementtests.tools.controls.interfaces.ILabel;
import com.softserveinc.ita.volleymanagementtests.tools.controls.interfaces.ITextInput;
import com.softserveinc.ita.volleymanagementtests.tools.controls.interfaces.IValidationLabel;
/**
 * @author Dp-076 ATQC
 * This class is a superclass for new team page and edit team page.
 * So kept the most of logic in it.
 */
public class TeamDataPage {
    /**
     * @param controls encapsulates map of page.
     */
    private TeamDataPageUIMap controls;
    /**
     * Initialization of team data page ui map to get the control of page's
     * element's location.
     */
    public TeamDataPage() {
        controls = new TeamDataPageUIMap();
    }
    /**
     * @return validation error message of name.
     */
    public final IValidationLabel getErrorMessageLabelName() {
        return controls.getErrorMessageLabelName();
    }
    /**
     * @return validation error message of captain.
     */
    public final IValidationLabel getErrorMessageLabelCaptain() {
        return controls.getErrorMessageLabelCaptain();
    }
    /**
     * @return validation error message of coach.
     */
    public final IValidationLabel getErrorMessageLabelCoach() {
        return controls.getErrorMessageLabelCoach();
    }
    /**
     * @return validation error message of achievements.
     */
    public final IValidationLabel getErrorMessageLabelAchievements() {
        return controls.getErrorMessageLabelAchievements();
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
    public final ILabel getNameLabel() {
        return controls.getNameLabel();
    }
    /**
     * @return name input.
     */
    public final ITextInput getNameInput() {
        return controls.getNameInput();
    }
    /**
     * @param team is an instance of team model.
     * @return team data page.
     * typing the team name to the name input.
     */
    public final TeamDataPage setName(final Team team) {
        controls.getNameInput().click();
        controls.getNameInput().clear();
        controls.getNameInput().type(team.getTeamName());
        return this;
    }
    /**
     * @return captain label.
     */
    public final ILabel getCaptainLabel() {
        return controls.getCaptainLabel();
    }
    /**
     * @return captain input.
     */
    public final ITextInput getCaptainInput() {
        return controls.getCaptainInput();
    }
    /**
     * @param team is an instance of team model.
     * @return team data page.
     * typing the captain name to the captain input.
     */
    public final TeamDataPage setCaptain(final Team team) {
        controls.getCaptainInput().click();
        controls.getCaptainInput().clear();
        controls.getCaptainInput().type(team.getCaptain().toString());
        return this;
    }
    /**
     * @return coach label.
     */
    public final ILabel getCoachLabel() {
        return controls.getCoachLabel();
    }
    /**
     * @return coach input.
     */
    public final ITextInput getCoachInput() {
        return controls.getCoachInput();
    }
    /**
     * @param team is an instance of team model.
     * @return team data page.
     * typing the coach name to the coach input.
     */
    public final TeamDataPage setCoach(final Team team) {
        controls.getCoachInput().click();
        controls.getCoachInput().clear();
        if (team.getCoach() == null) {
            controls.getCoachInput().type("");
        } else {
        controls.getCoachInput().type(team.getCoach().toString());
        }
        return this;
    }
    /**
     * @return achievements label.
     */
    public final ILabel getAchievementsLabel() {
        return controls.getAchievementsLabel();
    }
    /**
     * @return achievements input.
     */
    public final ITextInput getaAhievementsInput() {
        return controls.getAhievementsInput();
    }
    /**
     * @param team is an instance of team model.
     * @return team data page.
     * typing the achievements to the achievementsch input.
     */
    public final TeamDataPage setaAhievements(final Team team) {
        controls.getAhievementsInput().click();
        controls.getAhievementsInput().clear();
        if (team.getAchievements() == null) {
            controls.getAhievementsInput().type("");
        } else {
        controls.getAhievementsInput().type(team.getAchievements().toString());
        }
        return this;
    }
    /**
     * @param achievements is string to type to the achievements field.
     * @return team data page.
     * typing the achievements to the achievements input.
     */
    public final TeamDataPage setAhievements(final String achievements) {
        controls.getAhievementsInput().click();
        controls.getAhievementsInput().clear();
        controls.getAhievementsInput().type(achievements);
        return this;
    }
    /**.
     * This method sets all fields from Player object to PlayerDatePage
     * @param team is an instance of team model.
     * @return team data page.
     */
    public final TeamDataPage setAllFields(final Team team) {
        setName(team);
        setCaptain(team);
        setCoach(team);
        setaAhievements(team);
        return this;
    }
}
