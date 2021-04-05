package v2.model;

public class EnterNameDialogModel {

    private final String playerName1;
    private final String playerName2;

    public EnterNameDialogModel(String playerName1, String playerName2) {
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
