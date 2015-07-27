package com.softserveinc.ita.volleymanagementtests.criterias;

import com.softserveinc.ita.volleymanagementtests.domain.models.Player;
import com.softserveinc.ita.volleymanagementtests.domain.repositories
.PlayerRepository;
/**
 * @author Dp-076 ATQC
 * This class contain methods for
 * check PlayerRepository object on different criteria.
 */
public final class PlayerRepositoryCriteria implements ISpecification {
    /**
     * Logger.
     */
    private Specification specification;
    /**
     * Constructor of the class.
     * @param aRepository - aim player repository.
     * @param aSpecification - logger.
     */
    private PlayerRepositoryCriteria(final PlayerRepository aRepository,
            final Specification aSpecification) {
        this.specification = aSpecification;
    }
    /**
     * Static method for create new PlayerRepositoryCriteria.
     * @param repository - player repository object.
     * @param specification - - Specification object.
     * @return new PlayerRepositoryCriteria.
     */
    public static PlayerRepositoryCriteria get(
            final PlayerRepository repository,
            final Specification specification) {
        return new PlayerRepositoryCriteria(repository, specification);
    }
    /**
     * @param prevNumber - previous number of players in database.
     * @return result of check, is number of players in database has increased.
     */
    public PlayerRepositoryCriteria numberOfPlayersHasIncreased(
            final int prevNumber) {
        this.specification.add(PlayerRepository.countAllPlayers() > prevNumber,
                "Number of players in database has not increased");
        return this;
    }
    /**
     * @param prevNumber - previous number of players in database.
     * @return result of check, is number of players in database has decreased.
     */
    public PlayerRepositoryCriteria numberOfPlayersHasDecreased(
            final int prevNumber) {
        this.specification.add(PlayerRepository.countAllPlayers() < prevNumber,
                "Number of players in database has not decreased");
        return this;
    }
    /**
     * @param prevNumber - previous number of players in database.
     * @return result of check, is number of players in database
     * has not changed.
     */
    public PlayerRepositoryCriteria numberOfPlayersHasNotChanged(
            final int prevNumber) {
        this.specification.add(PlayerRepository.countAllPlayers() == prevNumber,
                "Number of players in database was changed");
        return this;
    }
    /**
     * @param player - player for check.
     * @param prevNumber - number of players in database with parameters of
     * given player.
     * @return result of check, is given player was added to database.
     */
    public PlayerRepositoryCriteria playerWasAdded(final Player player,
            final int prevNumber) {
        this.specification.add(
                PlayerRepository.countPlayers(player) > prevNumber,
                "Player " + player.getFirstName() + " " + player.getLastName()
                + " was not added to database");
        return this;
    }
    /**
     * @param player - player for check.
     * @param prevNumber - number of players in database with parameters of
     * given player.
     * @return result of check, is given player was deleted from database.
     */
    public PlayerRepositoryCriteria playerWasDeleted(final Player player,
            final int prevNumber) {
        this.specification.add(
                PlayerRepository.countPlayers(player) < prevNumber,
                "Player " + player.getFirstName() + " " + player.getLastName()
                + " was not deleted from database");
        return this;
    }
    /**
     * @param player - player for check.
     * @return result of check, is given player found in database.
     */
    public PlayerRepositoryCriteria playerPresent(final Player player) {
        this.specification.add(PlayerRepository
                .isPlayerPresent(player),
                "Player not present in database. Expect: First Name: "
                + player.getFirstName()
                + " Last Name:" + player.getLastName()
                + " Birth year:" + player.getBirthYear()
                + " Height:" + player.getHeight()
                + " Weight:" + player.getWeight());
        return this;
    }
    /**
     * @param player - player for check.
     * @return result of check, is given player not found in database.
     */
    public PlayerRepositoryCriteria playerNotPresent(final Player player) {
        this.specification.add(!PlayerRepository
                .isPlayerPresent(player),
                "Player present in database. Actual: First Name: "
                + player.getFirstName()
                + " Last Name:" + player.getLastName()
                + " Birth year:" + player.getBirthYear()
                + " Height:" + player.getHeight()
                + " Weight:" + player.getWeight());
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
