package com.softserveinc.ita.volleymanagementtests.domain.models;

/**
 * @author Dp-076 ATQC This class describes Team essence.
 */
public class Team {
    /**
     * Team's name.
     */
    private String teamName;
    /**
     * Team's captain.
     */
    private Integer captain = null;
    /**
     * Team's coach.
     */
    private String coach;
    /**
     * Team's achievements.
     */
    private String achievements;

    /**
     * Default constructor of the class.
     */
    public Team() {
    }

    /**
     * Constructor of the team object.
     * @param ateamName
     *            - input team name.
     * @param acaptain
     *            - input team's captain.
     * @param acoach
     *            - input team's coach.
     * @param aachievements
     *            - input team's achievements.
     */
    public Team(final String ateamName, final Integer acaptain,
            final String acoach, final String aachievements) {
        this.teamName = ateamName;
        this.captain = acaptain;
        this.coach = acoach;
        this.achievements = aachievements;
    }

    /**
     * Constructor of the team object with two required fields.
     * @param aTeamName
     *            - input team name.
     * @param aCaptain
     *            - input team's captain.
     */
    public Team(final String aTeamName, final Integer aCaptain) {
        this.teamName = aTeamName;
        this.captain = aCaptain;
    }
    /**
     * Setter for team name field.
     * @param teamNeme - given team name.
     */
    public final void setTeamName(final String teamNeme) {
        this.teamName = teamNeme;
    }
    /**
     * Getter for team name field.
     * @return team name.
     */
    public final String getTeamName() {
        return this.teamName;
    }
    /**
     * Getter for team's captain field.
     * @return team's captain.
     */
    public final Integer getCaptain() {
        return this.captain;
    }
    /**
     * Setter for team's captain field.
     * @param inputCaptain - given Integer team's captain.
     */
    public final void setCaptain(final Integer inputCaptain) {
        this.captain = inputCaptain;
    }
    /**
     * Setter for team's captain field.
     * @param inputCaptain - given String team's captain.
     */
    public final void setCaptain(final String inputCaptain) {
        if (inputCaptain != null) {
            this.captain = Integer.parseInt(inputCaptain);
        }
    }
    /**
     * Setter for team's coach field.
     * @param aCoach - given team's coach.
     */
    public final void setCoach(final String aCoach) {
        this.coach = aCoach;
    }
    /**
     * Getter for team's coach field.
     * @return team's coach.
     */
    public final String getCoach() {
        return this.coach;
    }
    /**Setter for team's achievements field.
     * @param theAchievements - given team's achievements.
     */
    public final void setAchievements(final String theAchievements) {
        this.achievements = theAchievements;
    }
    /**
     * Getter for team's achievements field.
     * @return team's achievements.
     */
    public final String getAchievements() {
        return this.achievements;
    }

    @Override
    public final int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result
                + ((achievements == null) ? 0 : achievements.hashCode());
        result = prime * result + ((captain == null) ? 0 : captain.hashCode());
        result = prime * result + ((coach == null) ? 0 : coach.hashCode());
        result = prime * result
                + ((teamName == null) ? 0 : teamName.hashCode());
        return result;
    }

    @Override
    public final boolean equals(final Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        Team other = (Team) obj;
        if (achievements == null) {
            if (other.achievements != null) {
                return false;
            }
        } else if (!achievements.equals(other.achievements)) {
            return false;
        }
        if (captain == null) {
            if (other.captain != null) {
                return false;
            }
        } else if (!captain.equals(other.captain)) {
            return false;
        }
        if (coach == null) {
            if (other.coach != null) {
                return false;
            }
        } else if (!coach.equals(other.coach)) {
            return false;
        }
        if (teamName == null) {
            if (other.teamName != null) {
                return false;
            }
        } else if (!teamName.equals(other.teamName)) {
            return false;
        }
        return true;
    }

}
