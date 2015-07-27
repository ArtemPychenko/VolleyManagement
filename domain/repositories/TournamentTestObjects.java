package com.softserveinc.ita.volleymanagementtests.domain.repositories;

import java.sql.Date;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Calendar;

import com.softserveinc.ita.volleymanagementtests.domain.models.Tournament;

/**
 * @author DP-076ATQC
 * This class allows to create team objects for tests.
 */
@SuppressWarnings("deprecation")
public class TournamentTestObjects {
    /**.
     * Constant contain valid Description size
     */
   private static final int validDescriptionSize = 300;
   /**.
    * Constant contain valid RegulationsLink size
    */
   private static final int validRegulationsLinkSize = 255;
    /**
     * String contain valid length of the tornament's name for name field.
     */
    private static final String validCountName = "abcdefghijklmnopqrstuvwxyz"
            + "abcdefghijklmnopqrstuvwxyz" + "abcdefgh";
    /**.
     * String contain not valid length
     */
    private static final String invalidCountName = "abcdefghijklmnopqrstuvwxyz"
            + "abcdefghijklmnopqrstuvwxyz" + "abcdefghi";
    /**
     * valid before season value.
     */
    private static final int seasonBefore = 5;
    /**
     * valid after season value.
     */
    private static final int seasonAfter = 10;
    /**
     * holds current year of a season.
     */
    public static final int currentSeasonYear = 2014;
    /**
     * @param date takes a date to parse to string format.
     * @return date in string format.
     */
    public static String dateToString(final Date date) {
        return date.toLocalDate().format(DateTimeFormatter.ofPattern(
                "dd.MM.uuuu"));
    }
    /**
     * takes the current date in long type.
     */
    private static final long currentDate = System.currentTimeMillis();
    /**
     * sets the applying period start with current date.
     */
    private static final Date dateApplyingPeriodStart = new Date(currentDate);
    /**
     * sets the applying period end with current date.
     */
    private static Date dateApplyingPeriodEnd = new Date(currentDate);
    /**
     * sets the game start with current date.
     */
    private static Date dateGamesStart = new Date(currentDate);
    /**
     * sets the game end with current date.
     */
    private static Date dateGamesEnd = new Date(currentDate);
    /**
     * sets the transfer start with current date.
     */
    private static Date dateTransferStart = new Date(currentDate);
    /**
     * sets the transfer end with current date.
     */
    private static Date dateTransferEnd = new Date(currentDate);

    static
    {
        dateApplyingPeriodEnd.setMonth(dateApplyingPeriodStart.getMonth() + 1);
        dateGamesStart.setMonth(dateApplyingPeriodStart.getMonth() + 1);
        dateGamesEnd.setMonth(dateGamesStart.getMonth() + 3);
        dateTransferStart.setMonth(dateGamesStart.getMonth() + 1);
        dateTransferEnd.setMonth(dateTransferStart.getMonth() + 1);
    }

    /**
     * @param dateAsString is a string to check it for data format correctness.
     * @return true if date format is correct.
     */
    public static final boolean isDateformatCorrect(final String dateAsString) {
        boolean isCorrect = true;
        try {
            String[] dateArray = dateAsString.split(".");
            int date = 0;
            int month = 1;
            int year = 2;
            Date dateAsDate = new Date(System.currentTimeMillis());
            dateAsDate.setYear(Integer.valueOf(dateArray[year]));
            dateAsDate.setMonth(Integer.valueOf(dateArray [month]));
            dateAsDate.setDate(Integer.valueOf(dateArray [date]));
        } catch (NumberFormatException e) {
            isCorrect = false;
        } catch (IllegalArgumentException e) {
            isCorrect = false;
        }
        return isCorrect;
    }
    /**
     * @return one valid tournament with all fields.
     */
    public static Tournament getValidTournament() {
        Tournament tournament = new Tournament();
        tournament.setName("TestTournamentName");
        tournament.setDescription("TestDescription");
        tournament.setSeason("2015/2016");
        tournament.setApplyingPeriodStart(dateToString(
                dateApplyingPeriodStart));
        tournament.setApplyingPeriodEnd(dateToString(dateApplyingPeriodEnd));
        tournament.setGamesStart(dateToString(dateGamesStart));
        tournament.setGamesEnd(dateToString(dateGamesEnd));
        tournament.setTransferStart(dateToString(dateTransferStart));
        tournament.setTransferEnd(dateToString(dateTransferEnd));
        tournament.setScheme(1);
        tournament.setRegulationsLink("TestLink");
        return tournament;
    }

    /**.
     * Overload method getValidTournament()
     * @return one valid tournament with all fields.
     * @param tourName is a parameter to set as name.
     */
    public static Tournament getValidTournament(final String tourName) {
        Tournament tournament = getValidTournament();
        tournament.setName(tourName);
        return tournament;
    }

    /**.
     * Overload method getValidTournament()
     * @return one valid tournament with all fields.
     * @param tourSeason is a parameter to set as season.
     * @param tourName is a parameter to set as name.
     */
    public static Tournament getValidTournament(
            final String tourName, short tourSeason) {
        Tournament tournament = getValidTournament();
        tournament.setName(tourName);
        tournament.setSeason(tourSeason);
        return tournament;
    }

    /**
     * @return one valid tournament with required fields only.
     */
    public static Tournament getValidTournamentWithRequiredFields() {
        Tournament tournament = new Tournament();
        tournament.setName("TestTournamentNameReqFields");
        tournament.setSeason("2015/2016");
        tournament.setApplyingPeriodStart(dateToString(
                dateApplyingPeriodStart));
        tournament.setApplyingPeriodEnd(dateToString(dateApplyingPeriodEnd));
        tournament.setGamesStart(dateToString(dateGamesStart));
        tournament.setGamesEnd(dateToString(dateGamesEnd));
        tournament.setTransferStart(dateToString(dateTransferStart));
        tournament.setTransferEnd(dateToString(dateTransferEnd));
        tournament.setScheme(1);
        return tournament;
    }

    /**
     * Valid tournament's name.
     * @return  tournament's list with valid name.
     */
    public static List<Tournament> getValidTournamentsName() {
        List<Tournament> validNameTournaments = new ArrayList<Tournament>();
        for (String name: getValidName()) {
            Tournament tournament = getValidTournament();
            tournament.setName(name);
            validNameTournaments.add(tournament);
        }
        return validNameTournaments;
    }
    /**
     * Valid regulations link name.
     * @return  tournament's list with valid regulations link.
     */
    public static List<Tournament> getValidRegulationsLink() {
        List<Tournament> validNameTournaments = new ArrayList<Tournament>();
        for (String link: getValidName()) {
            Tournament tournament = getValidTournament();
            tournament.setRegulationsLink(link);
            validNameTournaments.add(tournament);
        }
        return validNameTournaments;
    }
    /**
     * Invalid tournament's name.
     * @return tournament with invalid name.
     * Tornament's name is invalid because it is empty.
     */
    public static Tournament getInvalidEmptyTournamentName() {
        Tournament tournamentInvalidName = getValidTournament();
        tournamentInvalidName.setName("");
        return tournamentInvalidName;
    }
    /**
     * Invalid tournament's name.
     * @return tournament's with invalid name.
     * Tornament's name is invalid because it is more than is allowed (>60symb).
     */
    public static Tournament getInvalidTooBigTournamentName() {
        Tournament tournamentInvalidName = getValidTournament();
        tournamentInvalidName.setName(invalidCountName);
        return tournamentInvalidName;
    }
    /**
     * Valid tournament's description.
     * @return  tournament's list with valid name.
     */
    public static Tournament getValidDescriptionSize() {
            Tournament tournament = getValidTournament();
            tournament.setName(getValidSizeDescription());
            return tournament;
    }
    /**
     * Invalid tournament's description.
     * @return  tournament's list with valid name.
     */
    public static Tournament getInvlidDescriptionSize() {
            Tournament tournament = getValidTournament();
            tournament.setName(getInvalidSizeDescription());
            return tournament;
    }
    /**
     * Valid tournament's RegulationsLink.
     * @return  tournament's list with invalid data.
     */
    public static Tournament getValidRegulationsLinkSize() {
            Tournament tournament = getValidTournament();
            tournament.setRegulationsLink(getValidSizeRegulationsLink());
            return tournament;
    }
    /**
     * Invalid tournament's description.
     * @return  tournament's list with invalid data.
     */
    public static Tournament getInvlidRegulationsLinkSize() {
            Tournament tournament = getValidTournament();
            tournament.setRegulationsLink(getInvalidSizeRegulationsLink());
            return tournament;
    }
    /**
     * @return string of valid size for description input.
     */
    public static String getValidSizeDescription() {
        StringBuilder validDescription = new StringBuilder();
        while (validDescription.length() != validDescriptionSize) {
            validDescription.append("abcdepqrstuvwxyz12345678901234");
        }
        return validDescription.toString();
    }
    /**
     * @return string of invalid size for description input.
     */
    public static String getInvalidSizeDescription() {
        String invalidDescription = TournamentTestObjects
                .getValidSizeDescription();
        invalidDescription += "a";
        return invalidDescription;
    }
    /**
     * @return string of valid size for regulations link input.
     */
    public static String getValidSizeRegulationsLink() {
        StringBuilder validRegulationsLink = new StringBuilder();
        while (validRegulationsLink.length() != validRegulationsLinkSize){
            validRegulationsLink.append(
                    "abcdefghijklmnopqrstuvwxyz12345678901234"
                    + "12345678901");
        }
        return validRegulationsLink.toString();
    }
    /**
     * @return string of invalid size for description input.
     */
    public static String getInvalidSizeRegulationsLink() {
        String invalidRegulationsLink = TournamentTestObjects
                .getValidSizeRegulationsLink();
        invalidRegulationsLink += "a";
        return invalidRegulationsLink;
    }
    /**
     * Tournament with available schemes.
     * @return valid  tournament's list.
     */
    public static List<Tournament> getValidTournamentsScheme() {
        List<Tournament> validTournamentsScheme = new ArrayList<Tournament>();
        for (int scheme: getScheme()) {
            Tournament tournament = getValidTournament();
            tournament.setScheme(scheme);
            validTournamentsScheme.add(tournament);
        }
        return validTournamentsScheme;
    }
    /**
     * Valid data for team's name.
     * Use for create teams with valid name.
     * @return list with valid name
     */
    public static List<String> getValidName() {
        List<String> validName = new ArrayList<String>();
        validName.add("New");
        validName.add(validCountName);
        validName.add("N");
        validName.add("Новый");
        validName.add("ũǘŚşΘỸ");
        validName.add("A-a");
        validName.add("A a");
        validName.add("A’a");
        validName.add("123");
        validName.addAll(TournamentTestObjects.getSpecialCharester());
        return validName;
    }
    /**
     * @return string's list of special cherecters for inputs.
     */
    private static List<String> getSpecialCharester() {
        List<String> specialCharester = new ArrayList<String>();
        specialCharester.add("+");
        specialCharester.add("_");
        specialCharester.add("&");
        specialCharester.add("^");
        specialCharester.add("@");
        specialCharester.add("/");
        specialCharester.add("|");
        specialCharester.add("\\");
        specialCharester.add(",");
        specialCharester.add(".");
        specialCharester.add("!");
        return specialCharester;
    }
    /**
     * @return string's list of invalid type for date input.
     */
    public static List<String> getInvalidTypeForDate() {
        List<String> invalidType = new ArrayList<String>();
        invalidType.add("");
        invalidType.addAll(TournamentTestObjects.getSpecialCharester());
        invalidType.add("chars");
        invalidType.add("2015-06-07");
        invalidType.add("14.14.2015");
        invalidType.add("23.06.1992");
        invalidType.add("23.06.2088");
        return invalidType;
    }
    /**
     * @return string's list of invalid type for applying end date input.
     */
    public static List<String> getInvalidDateForApplyingEnd() {
        List<String> invalidDate = new ArrayList<String>();
        invalidDate.add("");
        invalidDate.addAll(TournamentTestObjects.getSpecialCharester());
        invalidDate.add("chars");
        invalidDate.add("2015-06-07");
        invalidDate.add("23.06.1992");
        return invalidDate;
    }
    /**
     * @return empty string for date input.
     */
    public static String getInvalidEmptyTypeForDate() {
        String invalidType = "";
        return invalidType;
    }
    /**
     * Method for get the list of test Tournaments.
     * Test tournament model - TestTournament*,
     *  where * - char counter, for example "AAB"
     * @param requiredTestTournaments  - required test tournaments quantity.
     * @return list of test tournaments.
     */
    public static List<Tournament> getTestTournamentList(
            final int requiredTestTournaments) {
            List<Tournament> tournamentList = new ArrayList<Tournament>();
            int A = 'A';
            int Z = 'Z';
            StringBuilder tournamentCount = new StringBuilder();
            for (int i = 0; i < requiredTestTournaments; i++) {
                if ((i + A) >= Z) {
                    for (int j = 1; j <= ((i + A) / Z); j++) {
                        tournamentCount = tournamentCount.append("A");
                    }
                }
                char unicodeChar = (char) (i % (Z - A) + A);
                tournamentCount = tournamentCount
                        .append(String.valueOf(unicodeChar));
                tournamentList.add(getValidTournament("TestTournament"
                        .concat(tournamentCount.toString())));
                tournamentCount.delete(0, tournamentCount.length());
            }
            return tournamentList;
    }

    /**
     * Data tournament's scheme.
     * Use for create tournaments with available combinations scheme.
     * @return list with available schemes.
     */
    private static int[] getScheme() {
        int[] schemes = {1, 2, 3};
        return schemes;
    }
    /**
     * Data tournament's scheme for UI.
     * @return list with available schemes.
     */
    public static List<String> getSchemeForUI() {
        List<String> schemes = new ArrayList<String>();
        schemes.add("1");
        schemes.add("2");
        schemes.add("2.5");
        return schemes;
    }
    /**
     * Method calculate current year.
     * @return int current year.
     */
    public static int getCurrentYear() {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new java.util.Date());
        return calendar.get(Calendar.YEAR);
    }
    /**
     * Convert to UI style.
     * @param year - any year
     * @return String in format "XXXX/XXXX"
     */
    private static String convertToUIStyle(final int year) {
        return String.valueOf(year) + "/"
                + String.valueOf(year + 1);
    }
    /**
     * Calculate current season list.
     * @param year - any year
     * @return list current year.
     */
    public static List<String> getCurrentSeasonListForUI(final int year) {
       List<String> validSeason = new ArrayList<String>();
       for (int i = seasonBefore + 1; i > 0; i--) {
           validSeason.add(TournamentTestObjects.convertToUIStyle(year - i));
       }
       for (int i = 0; i != seasonAfter + 1; i++) {
           validSeason.add(TournamentTestObjects.convertToUIStyle(year + i));
       }
       return validSeason;
    }
    /**
     * @param year is a string to parse.
     * @return list of strings of seasons for ui.
     */
    public static List<String> getCurrentSeasonListForUI(final String year) {
        return TournamentTestObjects
                .getCurrentSeasonListForUI(Integer.parseInt(year));
    }

    /**
     * @param date is a date to set to increase it as a border value.
     * @return date
     */
    public static final Date getLargeDate(final Date date) {
        Date invalidDate = date;
        invalidDate.setDate(date.getDate()+1);
        return invalidDate;
    }
    /**
     * @param date is a date to set to low it as a border value.
     * @return date
     */
    public static final Date getLowerDate(final Date date) {
        Date invalidDate = date;
        invalidDate.setDate(date.getDate()-1);
        return invalidDate;
    }

}
