package com.softserveinc.ita.volleymanagementtests.criterias;

import com.softserveinc.ita.volleymanagementtests.domain.models.Tournament;
import com.softserveinc.ita.volleymanagementtests.domain.repositories
                                                        .TournamentRepository;
/**
 * @author Dp-076 ATQC
 * This class contain methods for
 * check TournamentRepository object on different criteria.
 */
public final class TournamentRepositoryCriteria implements ISpecification {
        /**
         * Logger.
         */
        private Specification specification;
        /**
         * Constructor of the class.
         * @param aRepository - aim tournament repository.
         * @param aSpecification - logger.
         */
        private TournamentRepositoryCriteria(final TournamentRepository
                aRepository, final Specification aSpecification) {
            this.specification = aSpecification;
        }
        /**
         * Static method for create new TournamentRepositoryCriteria.
         * @param repository - tournament repository object.
         * @param specification - - Specification object.
         * @return new TournamentRepositoryCriteria.
         */
        public static TournamentRepositoryCriteria get(
                final TournamentRepository repository,
                final Specification specification) {
            return new TournamentRepositoryCriteria(repository, specification);
        }

        /**
         * @param tournament - tournament for check.
         * @return result of check, is given tournament found in database.
         */
        public TournamentRepositoryCriteria isTournamentPresent(
                final Tournament tournament) {
            this.specification.add(TournamentRepository
                    .isTournamentPresent(tournament),
                    "Tournament not present in database. Expect: Name: "
                    + tournament.getName()
                    + " Season:" + tournament.getSeason()
                    + " Scheme:" + tournament.getScheme()
                    + " TournamentStart:" + tournament.getGamesStartForUI()
                    + " TournamentEnd:" + tournament.getGamesEndForUI());
            return this;
        }
        /**
         * @param tournament - tournament for check.
         * @return result of check, is given tournament found in database.
         */
        public TournamentRepositoryCriteria isTournamentNotPresent(
                final Tournament tournament) {
            this.specification.add(!TournamentRepository.isTournamentPresent(
                    tournament),
                    "Tournament is present in database. Expect: Name: "
                    + tournament.getName()
                    + " Season:" + tournament.getSeason()
                    + " TournamentStart:" + tournament.getGamesStartForUI()
                    + " TournamentEnd:" + tournament.getGamesEndForUI());
            return this;
        }
        /**
         * @param description - description
         * @return result of check, is tournament with given description
         * found in database.
         */
        public TournamentRepositoryCriteria tournamentPresentByDescription(
                final String description) {
            this.specification.add(TournamentRepository
                    .isTournamentPresentByDescription(description),
                    "Team not present in database. Expect: "
                    + " Description:" + description);
            return this;
        }
        /**
         * @param prevNumber - previous number of tournaments in database.
         * @return result of check, is number of tournaments in database
         * has not changed.
         */
        public TournamentRepositoryCriteria numberOfTournamentsHasNotChanged(
                final int prevNumber) {
            this.specification.add(
                    TournamentRepository.countAllTournaments() == prevNumber,
                    "Number of tournament in database was changed");
            return this;
        }
        /**
         * @param prevNumber - previous number of tournaments in database.
         * @return result of check, is number of tournaments in database
         * has decreased.
         */
        public TournamentRepositoryCriteria numberOfTournamentsHasDecreased(
                final int prevNumber) {
            this.specification.add(
                    TournamentRepository.countAllTournaments() < prevNumber,
                    "Number of tournaments in database has not decreased");
            return this;
        }
        /**
         * @param prevNumber - previous number of tournaments in database.
         * @return result of check, is number of tournaments in database has
         * increased.
         */
        public TournamentRepositoryCriteria numberOfTournamentsHasIncreased(
                final int prevNumber) {
            this.specification.add(TournamentRepository
                    .countAllTournaments() > prevNumber,
                    "Number of tournaments in database has not increased");
            return this;
        }
        /**
         * @param tournament - tournament for check.
         * @param prevNumber - number of tournaments in database with parameters
         * of given tournament.
         * @return result of check, is given tournament was added to database.
         */
        public TournamentRepositoryCriteria tournamentWasAdded(
                final Tournament tournament, final int prevNumber) {
            this.specification.add(
                    TournamentRepository
                    .countTournaments(tournament) > prevNumber,
                    "Tournament " + tournament.getName() + " "
                    + tournament.getDescription()
                    + " was not added to database");
            return this;
        }
        /**
         * @param tournament - tournament for check.
         * @param prevNumber - number of tournaments in database with parameters
         * of given tournament.
         * @return result of check, is given tournament was deleted
         * from database.
         */
        public TournamentRepositoryCriteria tournamentWasDeleted(
                final Tournament tournament, final int prevNumber) {
            this.specification.add(
                    TournamentRepository
                    .countTournaments(tournament) < prevNumber,
                    "Tournament " + tournament.getName() + " "
                    + tournament.getDescription()
                    + " was not deleted from database");
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
