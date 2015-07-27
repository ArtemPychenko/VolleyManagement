package com.softserveinc.ita.volleymanagementtests.domain.models;

/**
 * @author Dp-076 ATQC 
 * This class describes Player essence.
 */
public class Player {
    /**
     * Player's Id.
     */
    private int id;
    /**
     * Player's Team Id.
     */
    private Integer teamId = null;
    /**
     * Player's first name.
     */
    private String firstName;
    /**
     * Player's last name.
     */
    private String lastName;
    /**
     * Player's birth year.
     */
    private Integer birthYear = null;
    /**
     * Player's height.
     */
    private Integer height = null;
    /**
     * Player's weight.
     */
    private Integer weight = null;

    /**
     * Default constructor of the class.
     */
    public Player() {
    }

    /**
     * Constructor of the player object.
     * @param aFirstName
     *            - input player first name.
     * @param aLastName
     *            - input player last name.
     * @param aBirthYear
     *            - input player birth year.
     * @param aHeight
     *            - input player height.
     * @param aWeight
     *            - input player weight.
     */
    public Player(final String aFirstName, final String aLastName,
            final Integer aBirthYear, final Integer aHeight,
            final Integer aWeight) {
        this.firstName = aFirstName;
        this.lastName = aLastName;
        this.birthYear = aBirthYear;
        this.height = aHeight;
        this.weight = aWeight;
    }

    /**
     * Constructor of the player object.
     * @param aFirstName
     *            - input player first name.
     * @param aLastName
     *            - input player last name.
     */
    public Player(final String aFirstName, final String aLastName) {
        this.firstName = aFirstName;
        this.lastName = aLastName;
    }

    /**
     * Setter for player's Id field.
     * @param theId
     *            - given id.
     */
    public final void setId(final int theId) {
        this.id = theId;
    }

    /**
     * Getter for player's Id field.
     * @return player's Id.
     */
    public final int getId() {
        return this.id;
    }

    /**
     * Getter for player's team Id field.
     * @return player's team Id.
     */
    public final Integer getTeamId() {
        return teamId;
    }

    /**
     * Setter for player's team Id field.
     * @param aTeamId
     *            - given Integer player's team id.
     */
    public final void setTeamId(final Integer aTeamId) {
        this.teamId = aTeamId;
    }

    /**
     * Setter for player's team Id field.
     * @param aTeamId
     *            - given String player's team id.
     */
    public final void setTeamId(final String aTeamId) {
        if (aTeamId != null) {
            this.teamId = Integer.parseInt(aTeamId);
        }
    }

    /**
     * Getter for player's first name field.
     * @return player's first name.
     */
    public final String getFirstName() {
        return firstName;
    }

    /**
     * Setter for player's first name field.
     * @param aFirstName
     *            - given player's first name.
     */
    public final void setFirstName(final String aFirstName) {
        this.firstName = aFirstName;
    }

    /**
     * Getter for player's last name field.
     * @return player's last name.
     */
    public final String getLastName() {
        return lastName;
    }

    /**
     * Setter for player's last name field.
     * @param aLastName
     *            - given player's last name.
     */
    public final void setLastName(final String aLastName) {
        this.lastName = aLastName;
    }

    /**
     * Getter for player's birth year field.
     * @return player's birth year.
     */
    public final Integer getBirthYear() {
        return birthYear;
    }

    /**
     * Setter for player's birth year field.
     * @param aBirthYear
     *            - given Integer player's birth year.
     */
    public final void setBirthYear(final Integer aBirthYear) {
        this.birthYear = aBirthYear;
    }

    /**
     * Setter for player's birth year field.
     * @param aBirthYear
     *            - given String player's birth year.
     */
    public final void setBirthYear(final String aBirthYear) {
        if (aBirthYear != null) {
            this.birthYear = Integer.parseInt(aBirthYear);
        }
    }

    /**
     * Getter for player's height field.
     * @return player's height.
     */
    public final Integer getHeight() {
        return height;
    }

    /**
     * Setter for player's height field.
     * @param aHeight
     *            - given Integer player's height.
     */
    public final void setHeight(final Integer aHeight) {
        this.height = aHeight;
    }

    /**
     * Setter for player's height field.
     * @param aHeight
     *            - given String player's height.
     */
    public final void setHeight(final String aHeight) {
        if (aHeight != null) {
            this.height = Integer.parseInt(aHeight);
        }
    }

    /**
     * Getter for player's weight field.
     * @return player's weight.
     */
    public final Integer getWeight() {
        return weight;
    }

    /**
     * Setter for player's weight field.
     * @param aWeight
     *            - given Integer player's weight.
     */
    public final void setWeight(final Integer aWeight) {
        this.weight = aWeight;
    }

    /**
     * Setter for player's weight field.
     * @param aWeight
     *            - given String player's weight.
     */
    public final void setWeight(final String aWeight) {
        if (aWeight != null) {
            this.weight = Integer.parseInt(aWeight);
        }
    }

    /**
     * @return player's full name.
     */
    public final String getFullName() {
        return this.lastName.concat(" ").concat(this.firstName);
    }

    @Override
    public final int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result
                + ((birthYear == null) ? 0 : birthYear.hashCode());
        result = prime * result
                + ((firstName == null) ? 0 : firstName.hashCode());
        result = prime * result + ((height == null) ? 0 : height.hashCode());
        result = prime * result
                + ((lastName == null) ? 0 : lastName.hashCode());
        result = prime * result + ((teamId == null) ? 0 : teamId.hashCode());
        result = prime * result + ((weight == null) ? 0 : weight.hashCode());
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
        Player other = (Player) obj;
        if (birthYear == null) {
            if (other.birthYear != null) {
                return false;
            }
        } else if (!birthYear.equals(other.birthYear)) {
            return false;
        }
        if (firstName == null) {
            if (other.firstName != null) {
                return false;
            }
        } else if (!firstName.equals(other.firstName)) {
            return false;
        }
        if (height == null) {
            if (other.height != null) {
                return false;
            }
        } else if (!height.equals(other.height)) {
            return false;
        }
        if (lastName == null) {
            if (other.lastName != null) {
                return false;
            }
        } else if (!lastName.equals(other.lastName)) {
            return false;
        }
        if (teamId == null) {
            if (other.teamId != null) {
                return false;
            }
        } else if (!teamId.equals(other.teamId)) {
            return false;
        }
        if (weight == null) {
            if (other.weight != null) {
                return false;
            }
        } else if (!weight.equals(other.weight)) {
            return false;
        }
        return true;
    }

}
