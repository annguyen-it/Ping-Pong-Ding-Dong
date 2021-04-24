package v2.component.paddle;

import v2.Game;
import v2.board.Score;
import v2.component.GameObject;
import v2.component.MovableGameObject;
import v2.mechanics.PaddleMechanics;

public abstract class Paddle extends MovableGameObject implements PaddleMechanics {

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
    public void tryMove() {
        if (!willWallCollide()) {
            move();
        }
        else if (y + dy < 0) {
            stopAtTopBorder();
        }
        else {
            stopAtBottomBorder();
        }
    }

    @Override
    public void move() {
        y += dy;
    }

    @Override
    public void stop() {
        dy = 0;
    }

    @Override
    public void stopAtTopBorder() {
        y = 0;
    }

    @Override
    public void stopAtBottomBorder() {
        y = Game.HEIGHT - PADDLE_HEIGHT - 40;
    }

    @Override
    public boolean willWallCollide() {
        int nextPos = y + dy;
        return nextPos < 0 || nextPos + PADDLE_HEIGHT + 40 > Game.HEIGHT;
    }

    @Override
    public void wallCollide() { }

    @Override
    public void changeSpeed(GameObject causeObject) { }

    @Override
    public void willMoveUp() {
        dy = -PADDLE_SPEED;
    }

    @Override
    public void willMoveDown() {
        dy = PADDLE_SPEED;
    }

    public void increaseScore() {
        score.increase();
    }

    public Score getScoreObject() {
        return score;
    }
}
