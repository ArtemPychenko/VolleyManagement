package com.softserveinc.ita.volleymanagementtests.criterias;

import com.softserveinc.ita.volleymanagementtests.domain.models.Team;
import com.softserveinc.ita.volleymanagementtests.domain.repositories
.TeamRepository;
/**
 * @author Dp-076 ATQC
 * This class contain methods for
 * check TeamRepository object on different criteria.
 */
public final class TeamRepositoryCriteria implements ISpecification {
    /**
     * Logger.
     */
    private Specification specification;
    /**
     * Constructor of the class.
     * @param aRepository - aim team repository.
     * @param aSpecification - logger.
     */
    private TeamRepositoryCriteria(final TeamRepository aRepository,
            final Specification aSpecification) {
        this.specification = aSpecification;
    }
    /**
     * Static method for create new TeamRepositoryCriteria.
     * @param repository - team repository object.
     * @param specification - - Specification object.
     * @return new TeamRepositoryCriteria.
     */
    public static TeamRepositoryCriteria get(final TeamRepository repository,
            final Specification specification) {
        return new TeamRepositoryCriteria(repository, specification);
    }

    /**
     * @param prevNumber - previous number of teams in database.
     * @return result of check, is number of teams in database has decreased.
     */
    public TeamRepositoryCriteria numberOfTeamsHasDecreased(
            final int prevNumber) {
        this.specification.add(TeamRepository.countAllTeams() < prevNumber,
                "Number of teams in database has not decreased");
        return this;
    }
    /**
     * @param prevNumber - previous number of teams in database.
     * @return result of check, is number of teams in database has increased.
     */
    public TeamRepositoryCriteria numberOfTeamsHasIncreased(
            final int prevNumber) {
        this.specification.add(TeamRepository.countAllTeams() > prevNumber,
                "Number of teams in database has not increased");
        return this;
    }
    /**
     * @param prevNumber - previous number of teams in database.
     * @return result of check, is number of teams in database has not changed.
     */
    public TeamRepositoryCriteria numberOfTeamsHasNotChanged(
            final int prevNumber) {
        this.specification.add(TeamRepository.countAllTeams() == prevNumber,
                "Number of teams in database was changed");
        return this;
    }
    /**
     * @param team - team for check.
     * @return result of check, is given team found in database.
     */
    public TeamRepositoryCriteria teamPresent(final Team team) {
        this.specification.add(TeamRepository
                .isTeamPresent(team),
                "Team not present in database. Expect: Name: "
                + team.getTeamName()
                + " CaptainId:" + team.getCaptain()
                + " Coach:" + team.getCoach()
                + " Achievements:" + team.getAchievements());
        return this;
    }
    /**
     * @param achievement - achievement
     * @return result of check, is team with given achievement
     * found in database.
     */
    public TeamRepositoryCriteria teamPresentByAchievement(
            final String achievement) {
        this.specification.add(TeamRepository
                .isTeamPresentByAchievements(achievement),
                "Team not present in database. Expect: "
                + " Achievements:" + achievement);
        return this;
    }
    /**
     * @param team - team for check.
     * @return result of check, is given team not found in database.
     */
    public TeamRepositoryCriteria teamNotPresent(final Team team) {
        this.specification.add(!TeamRepository
                .isTeamPresent(team),
                "Team present in database. Actual: Name: "
                + team.getTeamName()
                + " CaptainId:" + team.getCaptain()
                + " Coach:" + team.getCoach()
                + " Achievements:" + team.getAchievements());
        return this;
    }
    /**
     * @see com.softserveinc.ita.volleymanagementtests.criterias
     * .ISpecification#next().
     * @return specification
     */
    public Specification next() {
        return this.specification;
    }
}
