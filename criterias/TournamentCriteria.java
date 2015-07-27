package com.softserveinc.ita.volleymanagementtests.criterias;


import com.softserveinc.ita.volleymanagementtests.domain.models.Tournament;

/**
 * @author Dp-076 ATQC.
 */
public final class TournamentCriteria implements ISpecification {
    /**
     * identify of tournament field as Tournament model.
     */
    private Tournament tournament;
    /**
     * identify of specification field.
     */
    private Specification specification;

    /**
     * Constructor.
     * @param theTournament - Tournament object
     * @param theSpecification - Specification object
     */
    private TournamentCriteria(final Tournament theTournament
            , final Specification theSpecification) {
        tournament = theTournament;
        specification = theSpecification;
    }

    /**
     * @param tournament - Tournament object
     * @param specification - Specification object
     * @return new criteria for tournament.
     */
    public static TournamentCriteria get(final Tournament tournament
            , final Specification specification) {
        return new TournamentCriteria(tournament, specification);
    }

    /**
     * Check is tournament matches with another tournament.
     * @param expectedTournament - expected tournament for comparison
     * @return specification.
     */
    public TournamentCriteria tournamentMatch(
            final Tournament expectedTournament) {
            this.specification.add(this.tournament.equals(expectedTournament)
                    , "Objects Tournament doesn't match.");
         return this;
    }

    /**
     * Check is tournament name matches with another team name.
     * @param expectedResult
     *            - expected name for comparison
     * @return specification.
     */
    public TournamentCriteria tournamentNameMatch(
            final String expectedResult) {
        this.specification.add(this.tournament.getName()
                .equals(expectedResult),
                "Tournament name doesn't match. Expect:"
                        + expectedResult + " Actual:" + this.tournament
                        .getName());
        return this;
    }

    /**
     * Check is description matches with another description.
     * @param expectedResult
     *            - expected description for comparison
     * @return specification.
     */
    public TournamentCriteria descriptionMatch(
            final String expectedResult) {
        this.specification.add(this.tournament.getDescription()
                .equals(expectedResult),
                "Description doesn't match. Expect:"
                        + expectedResult + " Actual:" + this.tournament
                        .getDescription());
        return this;
    }

    /**
     * Check is season matches with another season.
     * @param expectedResult
     *            - expected season for comparison
     * @return specification.
     */
    public TournamentCriteria seasonMatch(
            final int expectedResult) {
        this.specification.add(this.tournament.getSeason()
                == expectedResult,
                "Season doesn't match. Expect:" + expectedResult
                + " Actual:" + this.tournament.getSeason());
        return this;
    }
    /**
     * Check is scheme matches with another scheme.
     * @param expectedResult
     *            - expected scheme for comparison
     * @return specification.
     */
    public TournamentCriteria schemeMatch(
            final int expectedResult) {
        this.specification.add(this.tournament.getScheme()
                == expectedResult,
                "Scheme doesn't match. Expect:" + expectedResult
                + " Actual:" + this.tournament.getScheme());
        return this;
    }
    /**
     * Check is regulations link matches with another regulations link.
     * @param expectedResult
     *            - expected regulations link for comparison
     * @return specification.
     */
    public TournamentCriteria regulationsLinkMatch(
            final String expectedResult) {
        this.specification.add(this.tournament.getRegulationsLink()
                .equals(expectedResult),
                "Regulations link doesn't match. Expect:"
                        + expectedResult + " Actual:" + this.tournament
                        .getRegulationsLink());
        return this;
    }
    /**
     * Check is applying period start date matches with another
     * applying period start date.
     * @param expectedResult
     *            - expected applying period start for comparison
     * @return specification.
     */
    public TournamentCriteria applyingPeriodStartMatch(
            final String expectedResult) {
        this.specification.add(this.tournament.getApplyingPeriodStartForUI()
                .equals(expectedResult),
                "Applying period start doesn't match. Expect:"
                        + expectedResult + " Actual:" + this.tournament
                        .getApplyingPeriodStartForUI());
        return this;
    }
    /**
     * Check is applying period end  date  matches with another
     * applying period end date.
     * @param expectedResult
     *            - expected applying period end date for comparison
     * @return specification.
     */
    public TournamentCriteria applyingPeriodEndMatch(
            final String expectedResult) {
        this.specification.add(this.tournament.getApplyingPeriodEndForUI()
                .equals(expectedResult),
                "Applying period end doesn't match. Expect:"
                        + expectedResult + " Actual:" + this.tournament
                        .getApplyingPeriodEndForUI());
        return this;
    }
    /**
     * Check is games start date matches with another games start date.
     * @param expectedResult
     *            - expected  games start date for comparison
     * @return specification.
     */
    public TournamentCriteria  gamesStartMatch(
            final String expectedResult) {
        this.specification.add(this.tournament.getGamesStartForUI()
                .equals(expectedResult),
                "Games start date doesn't match. Expect:"
                        + expectedResult + " Actual:" + this.tournament
                        .getGamesStartForUI());
        return this;
    }
    /**
     * Check is games end date matches with another games end date.
     * @param expectedResult
     *            - expected  games end date for comparison
     * @return specification.
     */
    public TournamentCriteria  gamesEndMatch(
            final String expectedResult) {
        this.specification.add(this.tournament.getGamesEndForUI()
                .equals(expectedResult),
                "Games end date doesn't match. Expect:"
                        + expectedResult + " Actual:" + this.tournament
                        .getGamesEndForUI());
        return this;
    }
    /**
     * Check is transfer start date matches with another transfer start date.
     * @param expectedResult
     *            - expected  transfer start date for comparison
     * @return specification.
     */
    public TournamentCriteria  transferStartMatch(
            final String expectedResult) {
        this.specification.add(this.tournament.getTransferStartForUI()
                .equals(expectedResult),
                "Transfer start date doesn't match. Expect:"
                        + expectedResult + " Actual:" + this.tournament
                        .getTransferStartForUI());
        return this;
    }
    /**
     * Check is transfer end date matches with another transfer end date.
     * @param expectedResult
     *            - expected  transfer end date for comparison
     * @return specification.
     */
    public TournamentCriteria  transferEndMatch(
            final String expectedResult) {
        this.specification.add(this.tournament.getTransferEndForUI()
                .equals(expectedResult),
                "Transfer end date doesn't match. Expect:"
                        + expectedResult + " Actual:" + this.tournament
                        .getTransferEndForUI());
        return this;
    }
    /**
     * Next specification.
     * @return specification.
     */
    public Specification next() {
        return this.specification;
    }
}
