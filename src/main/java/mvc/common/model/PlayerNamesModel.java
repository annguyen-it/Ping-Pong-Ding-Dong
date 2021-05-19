package main.java.mvc.common.model;

/**
 * Class {@code PlayerNamesModel} is a model, used to pass players' name from Menu to Game
 */
public class PlayerNamesModel {

    private final String playerName1;
    private final String playerName2;

    public PlayerNamesModel(String playerName1, String playerName2) {
        this.playerName1 = playerName1;
        this.playerName2 = playerName2;
    }

    public String getPlayerName1() {
        return playerName1;
    }

    public String getPlayerName2() {
        return playerName2;
    }
}
