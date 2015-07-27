package com.softserveinc.ita.volleymanagementtests.domain.repositories;

import java.util.ArrayList;
import java.util.List;

import com.softserveinc.ita.volleymanagementtests.domain.models.Team;

/**
 * @author DP-076ATQC
 * This class allows to create team objects for tests.
 */
public class TeamTestObjects {
	/**
	 * Constant contain valid Achievements size.
	 */
   private static final int validAchievementsSize = 4000;
    /**
     * String contain valid length.
     */
    private static final String validCount = "abcdefghijklmnopqrstuvwxyz"
            + "abcd";
    /**
     * String contain not valid length.
     */
    private static final String invalidCount = "abcdefghijklmnopqrstuvwxyz"
            + "abcde";

    /**
     * String contain valid length.
     */
    private static final String validCountCoach = "abcdefghijklmnopqrstuvwxyz"
            + "abcdefghijklmnopqrstuvwxyz" + "abcdefgh";
    /**
     * String contain not valid length.
     */
    private static final String invalidCountCoach = "abcdefghijklmnopqrstuvwxyz"
            + "abcdefghijklmnopqrstuvwxyz" + "abcdefghi";

    /**
     * @return one valid team with all fields.
     */
    public static Team getValidTeam() {
        return new Team("Рожденные в 90-е", 1, "Неизвестный тренер",
                "Неизвестные достижения");
    }

    /**
     * @return one valid team with required fields only.
     */
    public static Team getValidTeamWithRequiredFields() {
        return new Team("Факел", 2);
    }

    /**
     * Valid team's name.
     * @return  team's list with valid name.
     */
    public static List<Team> getValidNameTeams() {
        List<Team> validNameTeams = new ArrayList<Team>();
        for (String name: getValidName()) {
            validNameTeams.add(new Team(name, 3,
                    "Неизвестный тренер", "Неизвестные достижения"));
        }
        return validNameTeams;
    }
    /**
     * Invalid team's name.
     * @return team with invalid name.
     * Team's name is invalid because it is empty.
     */
    public static Team getInvalidEmptyTeamName() {
        Team invalidTeamName = new Team("", 3,
                "Неизвестный тренер", "Неизвестные достижения");
        return invalidTeamName;
    }
    /**
     * Invalid team's name.
     * @return team with invalid name.
     * Team's name is invalid because it is empty.
     */
    public static Team getInvalidTooBigTeamName() {
        Team invalidTeamName = new Team(invalidCount,
                3, "Неизвестный тренер", "Неизвестные достижения");
        return invalidTeamName;
    }
    /**
     * Valid team's name.
     * @return  coach's list with valid name.
     */
    public static List<Team> getValidCoachNames() {
        List<Team> validNameTeams = new ArrayList<Team>();
        for (String name: getValidCoachName()) {
            validNameTeams.add(new Team("Рожденные в 90-е", 3,
                    name, "Неизвестные достижения"));
        }
        return validNameTeams;
    }
    /**
     * Valid team's name.
     * @return  coach's list with valid name.
     */
    public static List<Team> getInvalidCoachNames() {
        List<Team> validNameTeams = new ArrayList<Team>();
        for (String name: getInvalidCoachName()) {
            validNameTeams.add(new Team("Рожденные в 90-е", 3,
                    name, "Неизвестные достижения"));
        }
        return validNameTeams;
    }
    /**
     * Invalid coach's name.
     * @return coach with invalid name.
     */
    public static Team getInvalidTooBigCoachName() {
        Team invalidTeamName = new Team("Рожденные в 90-е",
                3, invalidCountCoach, "Неизвестные достижения");
        return invalidTeamName;
    }
    /**
     * Valid size achievements.
     * @return valid size achievements.
     */
    public static String getValidSizeAchievements() {
    	StringBuilder validAchievements = new StringBuilder();
    	while (validAchievements.length() != validAchievementsSize) {
    		validAchievements.append(
    		        "abcdefghijklmnopqrstuvwxyz12345678901234");
    	}
    	return validAchievements.toString();
    }
    /**
     * Invalid size achievements.
     * @return invalid size achievements.
     */
    public static String getInvalidSizeAchievements() {
    	String invalidAchievements = TeamTestObjects
    	        .getValidSizeAchievements();
    	invalidAchievements += "a";
    	return invalidAchievements;
    }
    /**
     * Valid data for team's name.
     * Use for create teams with valid name.
     * @return list with valid name
     */
    public static List<String> getValidName() {
        List<String> validName = new ArrayList<String>();
        validName.add("New");
        validName.add(validCount);
        validName.add("N");
        validName.add("Новый");
        validName.add("ũǘŚşΘỸ");
        validName.add("A-a");
        validName.add("A a");
        validName.add("A’a");
        validName.add("123");
        validName.add("+");
        validName.add("_");
        validName.add("&");
        validName.add("^");
        validName.add("@");
        validName.add("/");
        validName.add("|");
        validName.add("\\");
        validName.add(",");
        validName.add(".");
        validName.add("!");
        return validName;
    }
    /**
     * Valid data for team's name.
     * Use for create teams with valid name.
     * @return list with valid name
     */
    public static List<String> getValidCoachName() {
        List<String> validName = new ArrayList<String>();
        validName.add("New");
        validName.add(validCountCoach);
        validName.add("N");
        validName.add("Новый");
        validName.add("ũǘŚşΘỸ");
        validName.add("A-a");
        validName.add("A a");
        validName.add("A’a");
        return validName;
    }
    /**
     * @return list with invalid coach's names.
     */
    public static List<String> getInvalidCoachName() {
        List<String> validName = new ArrayList<String>();
        validName.add("123");
        validName.add("+");
        validName.add("_");
        validName.add("&");
        validName.add("^");
        validName.add("@");
        validName.add("/");
        validName.add("|");
        validName.add("\\");
        validName.add(",");
        validName.add(".");
        validName.add("!");
        return validName;
    }

    /**
     * Method for get the list of test Teams.
     * Test team model - TestTeam*,
     *  where * - char counter, for example "AAB"
     * @param requiredTestTeams  - required test teams quantity.
     * @return list of test teams.
     */
    public static List<Team> getTestTeamList(final int requiredTestTeams) {
            List<Team> teamList = new ArrayList<Team>();
            int A = 'A';
            int Z = 'Z';
            StringBuilder teamCount = new StringBuilder();
            for (int i = 0; i < requiredTestTeams; i++) {
                if ((i + A) >= Z) {
                    for (int j = 1; j <= ((i + A) / Z); j++) {
                        teamCount = teamCount.append("A");
                    }
                }
                char unicodeChar = (char) (i % (Z - A) + A);
                teamCount = teamCount.append(String.valueOf(unicodeChar));
                teamList.add(new Team("TestTeam".concat(teamCount.toString()),
                        PlayerRepository.getLastId()));
                teamCount.delete(0, teamCount.length());
            }
            return teamList;
    }

}
