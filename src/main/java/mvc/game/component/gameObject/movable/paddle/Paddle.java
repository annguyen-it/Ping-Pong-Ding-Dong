package main.java.mvc.game.component.gameObject.movable.paddle;

import main.java.App;
import main.java.mvc.game.board.GameSide;
import main.java.mvc.game.board.Score;
import main.java.mvc.game.component.gameObject.movable.VerticalOnlyMovableGameObject;
import main.java.mvc.game.mechanics.paddle.PaddleMechanics;
import main.java.mvc.game.component.intangible.Vector;

public abstract class Paddle extends VerticalOnlyMovableGameObject implements PaddleMechanics {

    private static final Vector INITIAL_VECTOR = new Vector();
    private static final double INITIAL_SPEED = 9;

    public static final int INITIAL_WIDTH = 16;
    public static final int INITIAL_HEIGHT = 100;
    public static final int DISTANCE_TO_CROSS = 30;
    public static final int INITIAL_Y = App.HEIGHT/2 - INITIAL_HEIGHT/2;

    private static final int MAX_HEIGHT = 150;
    private static final int MIN_HEIGHT = 50;

    private int height = INITIAL_HEIGHT;

    protected Score score = new Score();
    private final GameSide.Side side;

    public Paddle(GameSide.Side side, int x, int y) {
        super(x, y, INITIAL_VECTOR, INITIAL_SPEED);
        this.side = side;
        speed = INITIAL_SPEED;
    }

    public int getHeight() {
        return height;
    }

    public int getWidth() {
        return INITIAL_WIDTH;
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
    public void sizeUp() {
        height = MAX_HEIGHT;
    }

    @Override
    public void sizeDown() {
        height = MIN_HEIGHT;
    }

    @Override
    public void returnInitialSize() {
        height = INITIAL_HEIGHT;
    }

    @Override
    public void returnInitialSpeed() {
        if (speed < INITIAL_SPEED){
            speed = INITIAL_SPEED;
        }
    }

    @Override
    public void speedDown() {
        if (speed == INITIAL_SPEED){
            speed -= 4;
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
        y = App.HEIGHT - height - 40;
    }

    @Override
    public boolean willWallCollide() {
        double nextPos = y + vector.getY();
        return nextPos < 0 || nextPos + height + 40 > App.HEIGHT;
    }

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

    public GameSide.Side getSide() {
        return side;
    }
}
