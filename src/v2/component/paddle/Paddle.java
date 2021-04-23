package v2.component.paddle;

import v2.Game;
import v2.board.Score;
import v2.component.Ball;
import v2.component.GameObject;

public abstract class Paddle extends GameObject {

    public static final int PADDLE_WIDTH = 16;
    public static final int PADDLE_HEIGHT = 100;
    public static final int DISTANCE_TO_CROSS = 30;
    public static final int INITIAL_PADDLE_Y = Game.HEIGHT/2 - PADDLE_HEIGHT/2;

    protected static final int PADDLE_SPEED = 10;
    protected Score score = new Score();

    public Paddle(int x, int y) {
        super(x, y);
    }

    @Override
    public void move() {
        int nextPos = y + dy;
        if (0 <= nextPos && nextPos + PADDLE_HEIGHT + 40 <= Game.HEIGHT) {
            y += dy;
        }
        else if (nextPos < 0) {
            y = 0;
        }
        else {
            y = Game.HEIGHT - PADDLE_HEIGHT - 40;
        }
    }

    public void willUp() {
        dy = -PADDLE_SPEED;
    }

    public void willDown() {
        dy = PADDLE_SPEED;
    }

    public void willStop() {
        dy = 0;
    }

    public abstract boolean isTouched(Ball ball);

    public void increaseScore() {
        score.increase();
    }

    public Score getScoreObject() {
        return score;
    }



}
