package com.softserveinc.ita.volleymanagementtests.criterias;

import com.softserveinc.ita.volleymanagementtests.domain.models.Team;

/**
 * @author Dp-076 ATQC.
 */
public final class TeamCriteria implements ISpecification {
    /**
     * identify of player field as Team model.
     */
    private Team team;
    /**
     * identify of specification field.
     */
    private Specification specification;

    /**
     * Constructor.
     * @param theTeam
     *            - Team object.
     * @param theSpecification
     *            - Specification object.
     */
    private TeamCriteria(final Team theTeam,
            final Specification theSpecification) {
        team = theTeam;
        specification = theSpecification;
    }

    /**
     * @param team
     *            - Team object
     * @param specification
     *            - Specification object.
     * @return new criteria for player.
     */
    public static TeamCriteria get(final Team team,
            final Specification specification) {
        return new TeamCriteria(team, specification);
    }

    /**
     * Check is team matches with another player.
     * @param expectedTeam
     *            - expected team for comparison.
     * @return specification.
     */
    public TeamCriteria teamMatch(final Team expectedTeam) {
        this.specification.add(this.team.equals(expectedTeam),
                "Object Team doesn't match.");
        return this;
    }

    /**
     * Check is team name matches with another team name.
     * @param expectedResult
     *            - expected name for comparison.
     * @return specification.
     */
    public TeamCriteria teamNameMatch(final String expectedResult) {
        this.specification.add(this.team.getTeamName().equals(expectedResult),
                "Team name doesn't match. Expect:" + expectedResult
                        + " Actual:" + this.team.getTeamName());
        return this;
    }

    /**
     * Check is captain matches with another captain.
     * @param expectedResult
     *            - expected captain for comparison.
     * @return specification.
     */
    public TeamCriteria captainMatch(final String expectedResult) {
        this.specification.add(this.team.getCaptain().equals(expectedResult),
                "Captain doesn't match. Expect:" + expectedResult + " Actual:"
                        + this.team.getCaptain());
        return this;
    }

    /**
     * Check is coach matches with another coach.
     * @param expectedResult
     *            - expected coach for comparison.
     * @return specification.
     */
    public TeamCriteria coachMatch(final String expectedResult) {
        this.specification.add(this.team.getCoach().equals(expectedResult),
                "Coach doesn't match. Expect:" + expectedResult + " Actual:"
                        + this.team.getCoach());
        return this;
    }

    /**
     * Check is achievement matches with another achievement.
     * @param expectedResult
     *            - expected achievement for comparison.
     * @return specification.
     */
    public TeamCriteria achievementMatch(final String expectedResult) {
        this.specification.add(
                this.team.getAchievements().equals(expectedResult),
                "Achievement doesn't match. Expect:" + expectedResult
                        + " Actual:" + this.team.getAchievements());
        return this;
    }

    @Override
    public Specification next() {
        return this.specification;
    }
}
