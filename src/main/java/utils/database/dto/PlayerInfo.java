package main.java.utils.database.dto;

public class PlayerInfo {

    private final String name;
    private final int Score;
    private final boolean isWinner;

    public PlayerInfo(String name, int score, boolean isWinner) {
        this.name = name;
        Score = score;
        this.isWinner = isWinner;
    }

    public String getName() {
        return name;
    }

    public int getScore() {
        return Score;
    }

    public boolean isWinner() {
        return isWinner;
    }
}
