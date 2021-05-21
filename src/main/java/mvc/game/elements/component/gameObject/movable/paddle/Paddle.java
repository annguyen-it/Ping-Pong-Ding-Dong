package main.java.mvc.game.elements.component.gameObject.movable.paddle;

import main.java.App;
import main.java.mvc.game.elements.function.intangible.GameSide.Side;
import main.java.mvc.game.elements.component.gameObject.movable.VerticalOnlyMovableGameObject;
import main.java.mvc.game.mechanics.paddle.PaddleMechanics;
import main.java.mvc.game.elements.function.intangible.Vector;

/**
 * Class {@code Paddle} represents GameObject that player control.
 * <p{@code Paddle} has rectangle shape</p>
 */
public abstract class Paddle extends VerticalOnlyMovableGameObject implements PaddleMechanics {

    /**
     * Initial height of {@code Paddle}
     */
    private static final int INITIAL_HEIGHT = 100;

    /**
     * Initial width of {@code Paddle}
     */
    protected static final int INITIAL_WIDTH = 16;

    /**
     * Distance from {@code Paddle} to cross
     */
    protected static final int DISTANCE_TO_CROSS = 30;

    /**
     * Initial position on x coordinate of {@code Paddle}
     */
    public static final int INITIAL_Y = App.HEIGHT/2 - INITIAL_HEIGHT/2;

    /**
     * Initial {@link Vector} of {@code Paddle}
     */
    private static final Vector INITIAL_VECTOR = new Vector();

    /**
     * Initial speed of {@code Paddle}
     */
    private static final double INITIAL_SPEED = 9;

    /**
     * Maximum height of {@code Paddle}
     * <p>
     * Height of instance equals to MAX_HEIGHT when function {@link Paddle#sizeUp()} is called.
     * </p>
     */
    private static final int MAX_HEIGHT = 150;

    /**
     * Minimum height of {@code Paddle}
     * <p>
     * Height of instance equals to MAX_HEIGHT when function {@link Paddle#sizeDown()} is called.
     * </p>
     */
    private static final int MIN_HEIGHT = 50;

    /**
     * Height of instance
     */
    private int height = INITIAL_HEIGHT;

    /**
     * Score of instance
     * <p>Base on {@code score} to determine winner</p>
     */
    protected int score = 0;

    /**
     * Side of instance
     *
     * @see main.java.mvc.game.elements.function.intangible.GameSide.Side
     */
    private final Side side;

    /**
     * Constructor
     *
     * @param side Side
     * @param x    initial position of paddle on x coordinate
     *
     * @see main.java.mvc.game.elements.component.gameObject.movable.paddle.LeftPaddle
     * @see main.java.mvc.game.elements.component.gameObject.movable.paddle.RightPaddle
     */
    public Paddle(Side side, int x) {
        super(x, INITIAL_Y, INITIAL_VECTOR, INITIAL_SPEED);
        this.side = side;
    }

    public int getHeight() {
        return height;
    }

    /**
     * Because width of {@code Paddle} do not change, so return {@link #INITIAL_WIDTH}
     *
     * @return int
     */
    public int getWidth() {
        return INITIAL_WIDTH;
    }

    /**
     * @return Current score
     */
    public int getScore() {
        return score;
    }

    /**
     * @return {@code Side} of instance
     */
    public Side getSide() {
        return side;
    }

    /**
     * Increase score by 1
     * <p>
     * This function is called when the ball reach the opponent's cross
     * </p>
     */
    public void increaseScore() {
        score++;
    }

    /**
     * Try to move the instance
     * <ul>
     *      <li>If does not collide with walls, then move</li>
     *      <li>If overlaps top border, then it should stop at top border</li>
     *      <li>Else, it should stop at bottom border</li>
     * </ul>
     */
    @Override
    public void tryMove() {
        super.tryMove();
        switch (willWallCollide()) {
            case top:
                stopAtTopBorder();
                break;

            case bottom:
                stopAtBottomBorder();
                break;

            default:
                move();
        }
    }

    /**
     * Check if instance will collide with wall or not
     *
     * @return boolean
     */
    @Override
    public Side willWallCollide() {
        double nextPositionY = y + vector.getY();

        if (nextPositionY < 0) {
            return Side.top;
        }

        if (nextPositionY + height + 40 > App.HEIGHT) {
            return Side.bottom;
        }

        return Side.unknown;
    }

    @Override
    public void stopAtTopBorder() {
        y = 0;
    }

    @Override
    public void stopAtBottomBorder() {
        y = App.HEIGHT - height - 40;
    }

    /**
     * Increase the instance's size
     * <p>
     * This function is called when picked bonus {@code IncreaseOwnPaddleSize}.
     * </p>
     *
     * @see main.java.mvc.game.elements.function.intangible.bonus.IncreaseOwnPaddleSize#activate()
     */
    @Override
    public void sizeUp() {
        height = MAX_HEIGHT;
    }

    /**
     * Decrease the size of opponent
     * <p>
     * This function is called when picked bonus {@code DecreaseOpponentPaddleSize}.
     * </p>
     *
     * @see main.java.mvc.game.elements.function.intangible.bonus.DecreaseOpponentPaddleSize#activate()
     */
    @Override
    public void sizeDown() {
        height = MIN_HEIGHT;
    }

    /**
     * Return initial size
     * <p>
     * This function is called when bonus {@code IncreaseOwnPaddleSize} or {@code DecreaseOpponentPaddleSize} is deactivated.
     * </p>
     *
     * @see main.java.mvc.game.elements.function.intangible.bonus.IncreaseOwnPaddleSize#deactivate()
     * @see main.java.mvc.game.elements.function.intangible.bonus.DecreaseOpponentPaddleSize#deactivate()
     */
    @Override
    public void returnInitialSize() {
        height = INITIAL_HEIGHT;
    }

    /**
     * Decrease speed of opponent
     * <p>
     * This function is called when picked bonus {@code SlowlyPaddle}.
     * </p>
     *
     * @see main.java.mvc.game.elements.function.intangible.bonus.SlowlyPaddle#activate()
     */
    @Override
    public void speedDown() {
        if (speed == INITIAL_SPEED) {
            speed -= 4;
        }
    }

    /**
     * Return initial speed
     * This function is called when bonus {@code SlowlyPaddle} is deactivated.
     */
    @Override
    public void returnInitialSpeed() {
        if (speed < INITIAL_SPEED) {
            speed = INITIAL_SPEED;
        }
    }

    /**
     * Stop the paddle
     * <p>
     * This function is called when player releases a moving key
     * </p>
     * <p>
     * Assign {@code new Vector} to vector, so that paddle does not continue to move
     * </p>
     */
    @Override
    public void stop() {
        vector = new Vector();
    }

    @Override
    public void willMoveUp() {
        vector = new Vector(90);
    }

    @Override
    public void willMoveDown() {
        vector = new Vector(270);
    }
}
