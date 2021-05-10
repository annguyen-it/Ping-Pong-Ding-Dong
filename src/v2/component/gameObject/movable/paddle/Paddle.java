package v2.component.gameObject.movable.paddle;

import v2.Game;
import v2.board.Score;
import v2.component.gameObject.GameObject;
import v2.component.gameObject.movable.VerticalOnlyMovableGameObject;
import v2.mechanics.paddle.PaddleMechanics;
import v2.component.helper.model.Vector;

public abstract class Paddle extends VerticalOnlyMovableGameObject implements PaddleMechanics {

    private static final Vector INITIAL_VECTOR = new Vector();
    private static final double INITIAL_SPEED = 10;

    public static final int INITIAL_PADDLE_WIDTH = 16;
    public static final int INITIAL_PADDLE_HEIGHT = 100;
    public static final int DISTANCE_TO_CROSS = 30;
    public static final int INITIAL_PADDLE_Y = Game.HEIGHT/2 - INITIAL_PADDLE_HEIGHT/2;

    protected Score score = new Score();

    private int height = INITIAL_PADDLE_HEIGHT;
    private double speed = INITIAL_SPEED;

    public Paddle(int x, int y) {
        super(x, y, INITIAL_VECTOR, INITIAL_SPEED);
    }

    public int getHeight() {
        return height;
    }

    @Override
    public void tryMove() {
        if (!willWallCollide()) {
            move();
        }
        else if (y + vector.getY() < 0) {
            stopAtTopBorder();
        }
        else {
            stopAtBottomBorder();
        }
    }

    @Override
    public void stop() {    
        vector = new Vector();
    }

    @Override
    public void stopAtTopBorder() {
        y = 0;
    }

    @Override
    public void stopAtBottomBorder() {
        y = Game.HEIGHT - INITIAL_PADDLE_HEIGHT - 40;
    }

    @Override
    public boolean willWallCollide() {
        double nextPos = y + vector.getY();
        return nextPos < 0 || nextPos + INITIAL_PADDLE_HEIGHT + 40 > Game.HEIGHT;
    }

    @Override
    public void wallCollide() { }

    @Override
    public void changeSpeed(GameObject causeObject) { }

    @Override
    public void willMoveUp() {
        vector = new Vector(90);
    }

    @Override
    public void willMoveDown() {
        vector = new Vector(270);
    }

    public void increaseScore() {
        score.increase();
    }

    public int getScore() {
        return score.getScore();
    }

    public Score getScoreObject() {
        return score;
    }
}
