package v2.board;

import java.awt.*;

public class Score {

    private int score = 0;
    private final int x;
    private final int y;

    public Score(int x, int y) throws HeadlessException {
        this.x = x;
        this.y = y;
    }

    public int getScore(){
        return score;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void increase(){
        score++;
    }
}
