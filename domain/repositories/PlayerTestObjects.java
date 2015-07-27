package com.softserveinc.ita.volleymanagementtests.domain.repositories;

import java.util.ArrayList;
import java.util.List;

import com.softserveinc.ita.volleymanagementtests.domain.models.Player;

/**
 * @author DP-076ATQC 
 * This class allows to create player objects for tests.
 */
public class PlayerTestObjects {
    /**
     * String contain valid length
     */
    private static final String validCount = "abcdefghijklmnopqrstuvwxyz"
            + "abcdefghijklmnopqrstuvwxyz" + "abcdefgh";
    /**
     * String contain not valid length.
     */
    private static final String invalidCount = "abcdefghijklmnopqrstuvwxyz"
            + "abcdefghijklmnopqrstuvwxyz" + "abcdefghi";
    /**
     * Min. valid year
     */
    private static final int minValidYear = 1900;
    /**
     * Max. valid year
     */
    private static final int maxValidYear = 2100;
    /**
     * Min. valid height
     */
    private static final int minValidHeight = 10;
    /**
     * Max. valid height
     */
    private static final int maxValidHeight = 250;
    /**
     * Min. valid weight
     */
    private static final int minValidWeight = 10;
    /**
     * Max. valid weight
     */
    private static final int maxValidWeight = 500;

    /**
     * @return one valid player with all fields.
     */
    public static Player getValidPlayer() {
        return new Player("Станислав", "Клочков", 1985, 185, 85);
    }

    /**
     * @return one valid player with required fields only.
     */
    public static Player getValidPlayerWithRequiredFields() {
        return new Player("Персенко", "Антон");
    }

    /**
     * Valid first name.
     * @return players list with valid first name
     */
    public static List<Player> getValidFirstNamePlayers() {
        List<Player> validName = new ArrayList<Player>();
        for (String firstName : PlayerTestObjects.getValidName()) {
            validName.add(new Player(firstName, "Player", 1992, 186, 65));
        }
        return validName;
    }

    /**
     * Invalid first name.
     * @return players list with invalid first name. First name is invalid
     *         because type of symbols is invalid.
     */
    public static List<Player> getInvalidTypeFirstNamePlayers() {
        List<Player> invalidNamePlayers = new ArrayList<Player>();
        for (String firstName : getInvalidTypeNames()) {
            invalidNamePlayers.add(
                    new Player(firstName, "Player", 1992, 186, 65));
        }
        return invalidNamePlayers;
    }

    /**
     * Invalid first name.
     * @return player with invalid first name. First name is invalid because
     *         first name is empty.
     */
    public static Player getInvalidEmptyFirstNamePlayer() {
        Player invalidFirstNamePlayer = new Player("", "Player", 1992, 186, 65);
        return invalidFirstNamePlayer;
    }

    /**
     * Invalid first name.
     * @return player with invalid first name. First name is invalid because
     *         first name has more than 60 symbols.
     */
    public static Player getInvalidTooBigFirstNamePlayer() {
        Player invalidFirstNamePlayer = new Player(
                invalidCount, "Player", 1992, 186, 65);
        return invalidFirstNamePlayer;
    }

    /**
     * Valid second name.
     * @return players list with valid second name
     */
    public static List<Player> getValidSecondNamePlayers() {
        List<Player> validName = new ArrayList<Player>();
        for (String secondName : PlayerTestObjects.getValidName()) {
            validName.add(new Player("New", secondName, 1992, 186, 65));
        }
        return validName;
    }

    /**
     * Invalid second name.
     * @return players list with invalid second name Second name is invalid
     *         because type of symbols is invalid.
     */

    public static List<Player> getInvalidTypeSecondNamePlayers() {
        List<Player> invalidNamePlayer = new ArrayList<Player>();
        for (String secondName : PlayerTestObjects.getInvalidTypeNames()) {
            invalidNamePlayer.add(new Player("New", secondName, 1992, 186, 65));
        }
        return invalidNamePlayer;
    }

    /**
     * Invalid second name.
     * @return player with invalid second name. Second name is invalid because
     *         second name is empty.
     */
    public static Player getInvalidEmptySecondNamePlayer() {
        Player invalidSecondNamePlayer = new Player(
                "Player", "", 1992, 186, 65);
        return invalidSecondNamePlayer;
    }

    /**
     * Invalid second name.
     * @return player with invalid second name. Second name is invalid because
     *         first name has more than 60 symbols.
     */
    public static Player getInvalidTooBigSecondNamePlayer() {
        Player invalidSecondNamePlayer = new Player(
                "Player", invalidCount, 1992, 186, 65);
        return invalidSecondNamePlayer;
    }
    /**
     * Valid year of birth.
     * @return players list with valid year of birth
     */
    public static List<Player> getValidYearPlayers() {
        List<Player> validYearPlayers = new ArrayList<Player>();
        for (Integer year : getValidYear()) {
            validYearPlayers.add(new Player("New", "Player", year, 186, 65));
        }
        return validYearPlayers;
    }
    /**
     * Invalid year of birth.
     * @return players list with invalid year of birth
     */
    public static List<Player> getInvalidYearPlayers() {
        List<Player> invalidYearPlayers = new ArrayList<Player>();
        for (int year : getInvalidYear()) {
            invalidYearPlayers.add(new Player("New", "Player", year, 186, 65));
        }
        return invalidYearPlayers;
    }

    /**
     * Valid player height.
     * @return players list with valid player height
     */
    public static List<Player> getValidHeightPlayers() {
        List<Player> validHeightPlayers = new ArrayList<Player>();
        for (Integer height : getValidHeight()) {
            validHeightPlayers
                    .add(new Player("New", "Player", 1992, height, 65));
        }
        return validHeightPlayers;
    }

    /**
     * Invalid player height.
     * @return players list with invalid player height
     */

    public static List<Player> getInvalidHeightPlayers() {
        List<Player> invalidHeightPlayers = new ArrayList<Player>();
        for (int height : getInvalidHeight()) {
            invalidHeightPlayers.add(
                    new Player("New", "Player", 1992, height, 65));
        }
        return invalidHeightPlayers;
    }

    /**
     * Valid player weight.
     * @return players list with valid player weight
     */
    public static List<Player> getValidWeightPlayers() {
        List<Player> validWeightPlayers = new ArrayList<Player>();
        for (Integer weight : getValidWeight()) {
            validWeightPlayers.add(
                    new Player("New", "Player", 1992, 185, weight));
        }
        return validWeightPlayers;
    }

    /**
     * Invalid player weight.
     * @return players list with invalid player weight
     */
    public static List<Player> getInvalidWeightPlayers() {
        List<Player> invalidWeightPlayers = new ArrayList<Player>();
        for (int weight : PlayerTestObjects.getInvalidWeight()) {
            invalidWeightPlayers.add(
                    new Player("New", "Player", 1992, 185, weight));
        }
        return invalidWeightPlayers;
    }

    /**
     * Invalid data of first name and second name. Use for create players with
     * invalid first name or second name.
     * @return list with invalid name
     */
    private static List<String> getInvalidTypeNames() {
        List<String> invalidNames = new ArrayList<String>();
        invalidNames.add("123");
        invalidNames.add("+");
        invalidNames.add("_");
        invalidNames.add("&");
        invalidNames.add("^");
        invalidNames.add("@");
        invalidNames.add("/");
        invalidNames.add("|");
        invalidNames.add("\\");
        invalidNames.add(",");
        invalidNames.add(".");
        invalidNames.add("!");
        return invalidNames;
    }

    /**
     * Valid data of first name and second name. Use for create players with
     * valid first name or second name.
     * @return list with valid name
     */
    private static List<String> getValidName() {
        List<String> validName = new ArrayList<String>();
        validName.add("New");
        validName.add(validCount);
        validName.add("N");
        validName.add("Новый");
        validName.add("ũǘŚşΘỸ");
        validName.add("A-a");
        validName.add("A a");
        validName.add("A’a");
        return validName;
    }

    /**
     * Valid data year of birth. Use for create players with valid year of
     * birth.
     * @return list with valid year of birth
     */
    private static Integer[] getValidYear() {
        Integer[] validYear = { minValidYear, maxValidYear, null };
        return validYear;
    }

    /**
     * Invalid data year of birth. Use for create players with invalid year of
     * birth.
     * @return list with invalid year of birth
     */
    private static Integer[] getInvalidYear() {
        Integer[] inValidYear = { 0, minValidYear - 1, maxValidYear + 1 };
        return inValidYear;
    }

    /**
     * Valid Height. Use for create players with valid Height.
     * @return list with valid Height
     */
    private static Integer[] getValidHeight() {
        Integer[] validHeight = { minValidHeight, maxValidHeight, null };
        return validHeight;
    }

    /**
     * Invalid Height. Use for create players with invalid Height.
     * @return list with invalid Height
     */
    private static Integer[] getInvalidHeight() {
        Integer[] inValidHeight = { 0, minValidHeight - 1, maxValidHeight + 1 };
        return inValidHeight;
    }

    /**
     * Valid Weight. Use for create players with valid Weight.
     * @return list with valid Weight
     */
    private static Integer[] getValidWeight() {
        Integer[] validWeight = { minValidWeight, maxValidWeight, null };
        return validWeight;
    }

    /**
     * Invalid Weight. Use for create players with invalid Weight.
     * @return list with invalid Weight
     */
    private static Integer[] getInvalidWeight() {
        Integer[] inValidWeight = { 0, minValidWeight - 1, maxValidWeight + 1 };
        return inValidWeight;
    }

    /**
     * Invalid type - string for Birth year, height, weight.
     * @return list with invalid type (string)
     */
    public static List<String> getInValidType() {
        List<String> inValidType = new ArrayList<String>();
        inValidType.add("abc");
        inValidType.add("#!&");
        inValidType.add("1 85");
        inValidType.add("20,00");
        inValidType.add("20.00");
        inValidType.add("20,0");
        inValidType.add("20.0");
        return inValidType;
    }

    /**
     * Method for get the list of test Players. Test player model - FirstName*
     * LastName*, where * - char counter, for example "AAB"
     * @param requiredTestPlayers
     *            - required test players quantity.
     * @return list of test players.
     */
    public static List<Player> getTestPlayerList(
            final int requiredTestPlayers) {
        List<Player> playerList = new ArrayList<Player>();
        int A = 'A';
        int Z = 'Z';
        StringBuilder playerCount = new StringBuilder();
        for (int i = 0; i < requiredTestPlayers; i++) {
            if ((i + A) >= Z) {
                for (int j = 1; j <= ((i + A) / Z); j++) {
                    playerCount = playerCount.append("A");
                }
            }
            char unicodeChar = (char) (i % (Z - A) + A);
            playerCount = playerCount.append(String.valueOf(unicodeChar));
            playerList.add(new Player(
                    "FirstName".concat(playerCount.toString()), "LastName"
                            .concat(playerCount.toString())));
            playerCount.delete(0, playerCount.length());
        }
        return playerList;
    }
}
