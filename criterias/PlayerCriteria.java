package com.softserveinc.ita.volleymanagementtests.criterias;

import com.softserveinc.ita.volleymanagementtests.domain.models.Player;
    /**
     * @author Dp-076 ATQC
     * This class is criteria for all players..
     */
public final class PlayerCriteria  implements ISpecification {
    /**
     * identify of player field as Player model.
     */
    private Player player;
    /**
     * identify of specification field.
     */
    private Specification specification;

    /**
     * Constructor.
     * @param thePlayer - Player object
     * @param theSpecification - Specification object
     */
    private PlayerCriteria(final Player thePlayer,
            final Specification theSpecification) {
        player = thePlayer;
        specification = theSpecification;
    }
    /**
     * @param player - Player object
     * @param specification - Specification object
     * @return new criteria for player.
     */
    public static PlayerCriteria get(final Player player,
            final Specification specification) {
        return new PlayerCriteria(player, specification);
    }
    /**
     * Check is player matches with another player.
     * @param expectedPlayer - expected player for comparison
     * @return specification.
     */
    public PlayerCriteria playerMatch(final Player expectedPlayer) {
        this.specification.add(this.player.equals(expectedPlayer),
                "Object Player doesn't match.");
        return this;
    }
    /**
     * Check is player first name matches with another player first name.
     * @param expectedResult - expected first name for comparison
     * @return specification.
     */
    public PlayerCriteria playerFirstNameMatch(final String expectedResult) {
            this.specification.add(
                    this.player.getFirstName().equals(expectedResult),
                    "Player first name doesn't match. Expect:" + expectedResult
                    + " Actual:" +  this.player.getFirstName());
            return this;
    }
    /**
     * Check is player last name matches with another player last name.
     * @param expectedResult - expected last name for comparison
     * @return specification.
     */
    public PlayerCriteria playerLastNameMatch(final String expectedResult) {
            this.specification.add(this.player.getLastName()
                    .equals(expectedResult),
                    "Player last name doesn't match. Expect:" + expectedResult
                    + " Actual:" +  this.player.getLastName());
            return this;
    }
    /**
     * Check is player birth year  matches with another player birth year.
     * @param expectedBirthYear - expected birth year for comparison
     * @return specification.
     */
    public PlayerCriteria birthYearMatch(final Integer expectedBirthYear) {
        this.specification.add(
                this.player.getBirthYear() == expectedBirthYear,
                "Player birth year doesn't match. Expect:" + expectedBirthYear
                + " Actual:" + this.player.getBirthYear());
        return this;
    }
    /**
     * Check is player height  matches with another player height.
     * @param expectedHeight - expected height for comparison
     * @return specification.
     */
    public PlayerCriteria heightMatch(final Integer expectedHeight) {
        if (this.player.getHeight() != null) {
        this.specification.add(
                this.player.getHeight().equals(expectedHeight),
                "Player height doesn't match. Expect:" + expectedHeight
                + " Actual:" + this.player.getHeight());
        }
        return this;
    }
    /**
     * Check is player height not matches with another player height.
     * @param expectedHeight - unexpected height for comparison
     * @return specification.
     */
    public PlayerCriteria heightNotMatch(final String expectedHeight) {
        this.specification.add(
                this.player.getHeight().toString().equals(expectedHeight),
                "Player height shuldn't match. Expect:");
        return this;
    }
    /**
     * Check is player height  matches with another player height.
     * @param expectedWeight - expected height for comparison.
     * @return specification.
     */
    public PlayerCriteria weightMatch(final Integer expectedWeight) {
        this.specification.add(
                this.player.getWeight() == expectedWeight,
                "Player weight doesn't match. Expect:" + expectedWeight
                + " Actual:" + this.player.getWeight());
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
