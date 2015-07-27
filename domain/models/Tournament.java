package com.softserveinc.ita.volleymanagementtests.domain.models;

import java.sql.Date;

/**
 *  @author Dp-076 ATQC
 *  This class describes Tournament essence.
 */
public class Tournament {
    /**
     * UI splitter.
     */
    private static final String UI_SPLITTER = "\\.";
    /**
     * Tournament's name.
     */
    private String name;
    /**
     * Tournament's description.
     */
    private String description;
    /**
     * Tournament's season.
     */
    private int season;
    /**
     * Tournament's scheme.
     */
    private int scheme;
    /**
     * Tournament's regulations link.
     */
    private String regulationsLink;
    /**
     * Tournament's applying period start.
     */
    private Date applyingPeriodStart;
    /**
     * Tournament's applying period end.
     */
    private Date applyingPeriodEnd;
    /**
     * Tournament's start.
     */
    private Date gamesStart;
    /**
     * Tournament's end.
     */
    private Date gamesEnd;
    /**
     * Tournament's transfer start.
     */
    private Date transferStart;
    /**
     * Tournament's transfer end.
     */
    private Date transferEnd;

    /**
     * Default constructor of the class.
     */
    public Tournament() {

    }
    /**
     * Getter for tournament's name field.
     * @return tournament's name.
     */
    public final String getName() {
        return this.name;
    }
    /**
     * Setter for tournament's name field.
     * @param aName - given tournament's name.
     */
    public final void setName(final String aName) {
        this.name = aName;
    }
    /**
     * Getter for tournament's description field.
     * @return tournament's description.
     */
    public final String getDescription() {
        return this.description;
    }
    /**
     * Setter for tournament's description field.
     * @param aDescription - given tournament's description.
     */
    public final void setDescription(final String aDescription) {
        this.description = aDescription;
    }
    /**
     * Getter for tournament's season field.
     * @return tournament's season.
     */
    public final int getSeason() {
        return this.season;
    }
    /**
     * Getter for tournament's season field.
     * @return tournament's season in UI format.
     */
    public final String getSeasonForUI() {
        return String.valueOf(this.season) + "/"
                + String.valueOf(this.season + 1);
    }
    /**
     * Setter for tournament's season field.
     * @param aSeason - given int tournament's season.
     */
    public final void setSeason(final int aSeason) {
        this.season = aSeason;
    }
    /**
     * Setter for tournament's season field.
     * @param aSeason - given String tournament's season.
     */
    public final void setSeason(final String aSeason) {
        String[] years = aSeason.split("/");
        int startYear = Integer.parseInt(years[0]);
        this.season = startYear;
    }
    /**
     * Getter for tournament's applying period start field(RegistrationStart).
     * @return tournament's applying period start.
     */
    public final Date getApplyingPeriodStart() {
        return this.applyingPeriodStart;
    }
    /**
     * Getter for tournament's applying period start field(RegistrationStart).
     * @return tournament's applying period start in UI format.
     */
    public final String getApplyingPeriodStartForUI() {
        return this.convertInUIStyle(this.applyingPeriodStart.toString());
    }
    /**
     * Setter for tournament's applying period start field.
     * @param registrationStart - given Date tournament's applying period start.
     */
    public final void setApplyingPeriodStart(final Date registrationStart) {
        this.applyingPeriodStart = registrationStart;
    }
    /**
     * Setter for tournament's applying period start field.
     * @param registrationStart - given String tournament's applying period
     * start.
     */
    public final void setApplyingPeriodStart(final String registrationStart) {
        this.applyingPeriodStart = Date
                .valueOf(this.convertInDataStyle(registrationStart));
    }
    /**
     * Getter for tournament's applying period end field(RegistrationEnd).
     * @return tournament's applying period end.
     */
    public final Date getApplyingPeriodEnd() {
        return this.applyingPeriodEnd;
    }
    /**
     * Getter for tournament's applying period end field(RegistrationEnd).
     * @return tournament's applying period end in UI format.
     */
    public final String getApplyingPeriodEndForUI() {
        return this.convertInUIStyle(this.applyingPeriodEnd.toString());
    }
    /**
     * Setter for tournament's applying period end field.
     * @param aRegistrationEnd - given Date tournament's applying period end.
     */
    public final void setApplyingPeriodEnd(final Date aRegistrationEnd) {
        this.applyingPeriodEnd = aRegistrationEnd;
    }
    /**
     * Setter for tournament's applying period end field.
     * @param registrationEnd - given String tournament's applying period end.
     */
    public final void setApplyingPeriodEnd(final String registrationEnd) {
        this.applyingPeriodEnd = Date
                .valueOf(this.convertInDataStyle(registrationEnd));
    }
    /**
     * @return tournament's applying start/end period in UI format.
     */
    public final String getApplyingPeriodStartEndForUI() {
        return getApplyingPeriodStartForUI() + " / "
    + getApplyingPeriodEndForUI();
    }
    /**
     * Getter for tournament's start field.
     * @return tournament's start.
     */
    public final Date getGamesStart() {
        return this.gamesStart;
    }
    /**
     * Getter for tournament's start field.
     * @return tournament's start in UI format.
     */
    public final String getGamesStartForUI() {
        return this.convertInUIStyle(this.gamesStart.toString());
    }
    /**
     * Setter for tournament's start field.
     * @param tournamentStart - given Date tournament's start.
     */
    public final void setGamesStart(final Date tournamentStart) {
        this.gamesStart = tournamentStart;
    }
    /**
     * Setter for tournament's start field.
     * @param tournamentStart - given String tournament's start.
     */
    public final void setGamesStart(final String tournamentStart) {
        this.gamesStart = Date
                .valueOf(this.convertInDataStyle(tournamentStart));
    }
    /**
     * Getter for tournament's end field.
     * @return tournament's end.
     */
    public final Date getGamesEnd() {
        return this.gamesEnd;
    }
    /**
     * Getter for tournament's end field.
     * @return tournament's end in UI format.
     */
    public final String getGamesEndForUI() {
        return this.convertInUIStyle(this.gamesEnd.toString());
    }
    /**
     * Setter for tournament's end field.
     * @param tournamentEnd - given Date tournament's end.
     */
    public final void setGamesEnd(final Date tournamentEnd) {
        this.gamesEnd = tournamentEnd;
    }
    /**
     * Setter for tournament's end field.
     * @param tournamentEnd - given String tournament's end.
     */
    public final void setGamesEnd(final String tournamentEnd) {
        this.gamesEnd = Date.valueOf(this.convertInDataStyle(tournamentEnd));
    }
    /**
     * @return tournament's start/end period in UI format.
     */
    public final String getGamesStartEndForUI() {
        return getGamesStartForUI() + " / " + getGamesEndForUI();
    }
    /**
     * Getter for tournament's transfer start field.
     * @return tournament's transfer start in UI format.
     */
    public final String getTransferStartForUI() {
        return this.convertInUIStyle(this.transferStart.toString());
    }
    /**
     * Setter for tournament's transfer start field.
     * @param aTransferStart - given Date tournament's transfer start.
     */
    public final void setTransferStart(final Date aTransferStart) {
        this.transferStart = aTransferStart;
    }
    /**
     * Setter for tournament's transfer start field.
     * @param aTransferStart - given String tournament's transfer start.
     */
    public final void setTransferStart(final String aTransferStart) {
        this.transferStart = Date
                .valueOf(this.convertInDataStyle(aTransferStart));
    }
    /**
     * Getter for tournament's transfer start field.
     * @return tournament's transfer start.
     */
    public final Date getTransferStart() {
        return this.transferStart;
    }
    /**
     * Getter for tournament's transfer end field.
     * @return tournament's transfer end.
     */
    public final Date getTransferEnd() {
        return this.transferEnd;
    }

    /**
     * Getter for tournament's transfer end field.
     * @return tournament's transfer end in UI format.
     */
    public final String getTransferEndForUI() {
        return this.convertInUIStyle(this.transferEnd.toString());
    }
    /**
     * Setter for tournament's transfer end field.
     * @param aTransferEnd - given Date transfer end.
     */
    public final void setTransferEnd(final Date aTransferEnd) {
        this.transferEnd = aTransferEnd;
    }

    /**
     * Setter for tournament's transfer end field.
     * @param aTransferEnd - given String transfer end.
     */
    public final void setTransferEnd(final String aTransferEnd) {
        this.transferEnd = Date.valueOf(this.convertInDataStyle(aTransferEnd));
    }
    /**
     * @return tournament's transfer start/end period in UI format.
     */
    public final String getTransferStartEndForUI() {
        return getTransferStartForUI() + " / " + getTransferEndForUI();
    }
    /**
     * Getter for tournament's regulations link field.
     * @return tournament's regulations link.
     */
    public final String getRegulationsLink() {
        return this.regulationsLink;
    }
    /**
     * Setter for tournament's regulations link field.
     * @param regulation - given tournament's regulations link.
     */
    public final void setRegulationsLink(final String regulation) {
        this.regulationsLink = regulation;
    }
    /**
     * Getter for tournament's scheme field.
     * @return tournament's scheme.
     */
    public final int getScheme() {
        return this.scheme;
    }
    /**
     * Setter for tournament's scheme field.
     * @param aScheme - given tournament's scheme.
     */
    public final void setScheme(final int aScheme) {
        this.scheme = aScheme;
    }
    /**
     * @param date - given String date.
     * @return String date in UI style format.
     */
    private String convertInUIStyle(final String date) {
        String[] dateArray = date.split("-");
        return dateArray[2] + "." + dateArray[1] + "." + dateArray[0];
    }

    /**
     * @param date - given String date.
     * @return String in Date style format.
     */
    private String convertInDataStyle(final String date) {
        String[] dateArray = date.split(UI_SPLITTER);
        return dateArray[2] + "-" + dateArray[1] + "-" + dateArray[0];
    }
    @Override
    public final int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result
                + ((description == null) ? 0 : description.hashCode());
        result = prime * result + ((name == null) ? 0 : name.hashCode());
        result = prime * result + ((applyingPeriodEnd == null) ? 0
                : applyingPeriodEnd.hashCode());
        result = prime * result + ((applyingPeriodStart == null) ? 0
                : applyingPeriodStart.hashCode());
        result = prime * result
                + ((regulationsLink == null) ? 0 : regulationsLink.hashCode());
        result = prime * result + scheme;
        result = prime * result + season;
        result = prime * result
                + ((gamesEnd == null) ? 0 : gamesEnd.hashCode());
        result = prime * result
                + ((gamesStart == null) ? 0 : gamesStart.hashCode());
        result = prime * result
                + ((transferEnd == null) ? 0 : transferEnd.hashCode());
        result = prime * result
                + ((transferStart == null) ? 0 : transferStart.hashCode());
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
        Tournament other = (Tournament) obj;
        if (description == null) {
            if (other.description != null) {
                return false;
            }
        } else if (!description.equals(other.description)) {
            return false;
        }
        if (name == null) {
            if (other.name != null) {
                return false;
            }
        } else if (!name.equals(other.name)) {
            return false;
        }
        if (applyingPeriodEnd == null) {
            if (other.applyingPeriodEnd != null) {
                return false;
            }
        } else if (!applyingPeriodEnd.equals(other.applyingPeriodEnd)) {
            return false;
        }
        if (applyingPeriodStart == null) {
            if (other.applyingPeriodStart != null) {
                return false;
            }
        } else if (!applyingPeriodStart.equals(other.applyingPeriodStart)) {
            return false;
        }
        if (regulationsLink == null) {
            if (other.regulationsLink != null) {
                return false;
            }
        } else if (!regulationsLink.equals(other.regulationsLink)) {
            return false;
        }
        if (scheme != other.scheme) {
            return false;
        }
        if (season != other.season) {
            return false;
        }
        if (gamesEnd == null) {
            if (other.gamesEnd != null) {
                return false;
            }
        } else if (!gamesEnd.equals(other.gamesEnd)) {
            return false;
        }
        if (gamesStart == null) {
            if (other.gamesStart != null) {
                return false;
            }
        } else if (!gamesStart.equals(other.gamesStart)) {
            return false;
        }
        if (transferEnd == null) {
            if (other.transferEnd != null) {
                return false;
            }
        } else if (!transferEnd.equals(other.transferEnd)) {
            return false;
        }
        if (transferStart == null) {
            if (other.transferStart != null) {
                return false;
            }
        } else if (!transferStart.equals(other.transferStart)) {
            return false;
        }
        return true;
    }

}
