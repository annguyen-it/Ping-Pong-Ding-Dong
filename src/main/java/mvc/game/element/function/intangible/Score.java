package main.java.mvc.game.element.function.intangible;

public class Score {

    private int score = 0;

    public Score() { }

    public int getScore() {
        return score;
    }

    public void increase() {
        score++;
    }
}
