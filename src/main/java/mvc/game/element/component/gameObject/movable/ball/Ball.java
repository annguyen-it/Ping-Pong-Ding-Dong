package main.java.mvc.game.element.component.gameObject.movable.ball;

import main.java.App;
import main.java.mvc.game.element.function.intangible.GameSide.Side;
import main.java.mvc.game.element.component.gameObject.immovable.pickup.Pickup;
import main.java.mvc.game.element.component.gameObject.movable.AllDirectionMovableGameObject;
import main.java.mvc.game.element.component.gameObject.movable.paddle.LeftPaddle;
import main.java.mvc.game.element.component.gameObject.movable.paddle.Paddle;
import main.java.mvc.game.element.component.gameObject.movable.paddle.RightPaddle;
import main.java.mvc.game.mechanics.ball.BallMechanics;
import main.java.mvc.game.sound.GameSoundPlayer;
import main.java.mvc.game.element.function.intangible.Vector;

import java.awt.*;

/**
 * Class Ball
 *
 * @see main.java.mvc.game.element.component.gameObject.movable.AllDirectionMovableGameObject
 * @see main.java.mvc.game.mechanics.ball.BallMechanics
 */
public class Ball extends AllDirectionMovableGameObject implements BallMechanics {

    //#region Properties
    private static final int INITIAL_X = 588;
    private static final int INITIAL_Y = 388;
    private static final int INITIAL_SIZE = 24;
    private static final double INITIAL_SPEED = 9;

    private static final double MAX_SPEED = 15;
    private static final double MIN_SPEED = 8;
    private static final double BONUS_MAX_SPEED = 20;
    private static final double BONUS_MIN_SPEED = 13;

    private static final Vector INITIAL_TO_LEFT_VECTOR = new Vector(180);
    private static final Vector INITIAL_TO_RIGHT_VECTOR = new Vector(0);

    private final GameSoundPlayer soundPlayer;

    private Side lastTouchSide;
    private int size;

    //#endregion

    //#region Constructors

    /**
     * Constructor with random initial direction of ball, call only one time when game start.
     *
     * @param soundPlayer Sound player of ball
     */
    public Ball(GameSoundPlayer soundPlayer) {
        this(randomInitialSide(), soundPlayer);
    }

    /**
     * Constructor with specify direction (left or right).
     * Also set sound player.
     *
     * @param soundPlayer      Sound player of ball
     * @param initialDirection Initial direction of ball
     */
    public Ball(Side initialDirection, GameSoundPlayer soundPlayer) {
        this(INITIAL_X, INITIAL_Y, getInitialVector(initialDirection), INITIAL_SPEED, INITIAL_SIZE,
                Side.unknown, soundPlayer);
    }

    public Ball(int x, int y, Vector vector, double speed, int size, Side lastTouchSide, GameSoundPlayer soundPlayer) {
        super(x, y, vector, speed);

        this.size = size;
        this.lastTouchSide = lastTouchSide;
        this.soundPlayer = soundPlayer;
    }

    //#endregion

    //#region Own Method

    /**
     * Generate a random initial direction of ball. This method is called by only
     * 1-argument-constructor
     *
     * @return random direction
     *
     * @see main.java.mvc.game.element.function.intangible.GameSide.Side
     */
    private static Side randomInitialSide() {
        return (int) (Math.random()*2) == 0 ? Side.left : Side.right;
    }

    /**
     * Convert initial HorizontalDirection type to Vector
     *
     * @param initialDirection HorizontalDirection needs to convert
     *
     * @return converted vector
     *
     * @see main.java.mvc.game.element.function.intangible.GameSide.Side
     * @see #INITIAL_TO_LEFT_VECTOR
     * @see #INITIAL_TO_RIGHT_VECTOR
     */
    private static Vector getInitialVector(Side initialDirection) {
        return initialDirection == Side.left
                ? INITIAL_TO_LEFT_VECTOR
                : INITIAL_TO_RIGHT_VECTOR;
    }

    /**
     * Get current size of ball
     *
     * @return size of ball
     */
    public int getSize() {
        return size;
    }

    public Side getLastTouchSide() {
        return lastTouchSide;
    }

    /**
     * Get ratio of collision of ball to paddle (0.0 at top of paddle to 1.0 at bottom).
     *
     * @param paddle Collided paddle
     *
     * @return ratio of collision
     */
    private double getRatioCollision(Paddle paddle) {
        return (y - paddle.getY() + 1.0*size)/(paddle.getHeight() + size);
    }
    //#endregion

    //#region Moving
    @Override
    public void tryMove() {
        x += vector.getX();

        if (willWallCollide()) {
            wallCollide();
        }
        else {
            move();
        }
    }

    @Override
    public void changeSpeed(Paddle paddle) {
        int topPaddleY = paddle.getY();
        int paddleDiv = paddle.getHeight()/11;

        // position:      0   1   2   3   4   5   6   7   8   9   10
        //              |   |   |   |   |   |   |   |   |   |   |   |
        //                3   2   1   0  -1  -2  -1   0   1   2   3
        //

        switch ((y - topPaddleY)/paddleDiv) {
            case 1:
            case 9:
                speed += 1.5;
                break;

            case 2:
            case 8:
                speed += 0.5;
                break;

            case 3:
            case 7:
                break;

            case 4:
            case 6:
                speed -= 0.5;
                break;

            case 5:
                speed -= 1.5;
                break;

            default:
                speed += 2.5;
                break;
        }

        speed = Math.max(speed, MIN_SPEED);
        speed = Math.min(speed, MAX_SPEED);
    }

    @Override
    public void speedUp() {
        speed = Math.min(speed + 5, BONUS_MAX_SPEED);
    }

    @Override
    public void returnInitialSpeed() {
        if (speed >= BONUS_MIN_SPEED) {
            speed -= 5;
        }
    }

    @Override
    public void changeDirection(LeftPaddle paddle) {
        double ratio = getRatioCollision(paddle);
        double alpha = 45 - ratio*90;

        if (alpha >= 0) {
            vector = new Vector(alpha);
        }
        else {
            vector = new Vector(360 + alpha);
        }
    }

    @Override
    public void changeDirection(RightPaddle paddle) {
        double ratio = getRatioCollision(paddle);
        double alpha = ratio*90 - 45;

        vector = new Vector(180 + alpha);
    }

    @Override
    public Side isOutTheBoard() {
        if (x < -size) {
            soundPlayer.lostBall();
            return Side.left;
        }
        else if (x > App.WIDTH) {
            soundPlayer.lostBall();
            return Side.right;
        }

        return Side.unknown;
    }
    //#endregion

    //#region Transforms
    @Override
    public void sizeUp() {
        if (size == INITIAL_SIZE) {
            size += 10;
        }
    }

    @Override
    public void returnInitialSize() {
        if (size > INITIAL_SIZE) {
            size -= 10;
        }
    }

    //#endregion

    //#region Collide
    @Override
    public boolean willWallCollide() {
        double nextPositionY = y + vector.getY();
        return (nextPositionY < 0 && vector.getY() < 0) ||
               (nextPositionY + size + 40 > App.HEIGHT && vector.getY() > 0);
    }

    @Override
    public void wallCollide() {
        soundPlayer.ballCollideWall();
        vector = vector.getReflection();
    }

    /**
     * @param paddle LeftPaddle
     *
     * @return <ul>
     * <li>true if reach all below condition:
     *      <ol>
     *          <li>Ball is moving to the left.</li>
     *          <li>Ball must be between paddle's width.</li>
     *          <li>Ball must be between paddle's height.</li>
     *          <li>If ball speed is larger than paddle's width, a serious bug will be caused: Ball goes through the paddle.
     *              Because in that case, ball's X coordinate will be increased immediately, large enough to reach out paddle's bound
     *              So, we must add this condition.</li>
     *      </ol>
     *  </li>
     *  <li>else, return false</li>
     *  </ul>
     */
    @Override
    public boolean willCollide(LeftPaddle paddle) {
        int paddleX = paddle.getX();
        int paddleY = paddle.getY();

        return vector.getX() < 0 &&                                                 //  (1)
               paddleX <= x && x <= paddleX + paddle.getWidth() &&                  //  (2)
               paddleY <= y + size && y <= paddleY + paddle.getHeight() &&          //  (3)
               (speed < paddle.getWidth() || paddleX <= x && x <= paddleX + speed); //  (4)
    }

    /**
     * @param paddle RightPaddle
     *
     * @return <ul>
     * <li>true if reach all below condition:
     *      <ol>
     *          <li>Ball is moving to the right.</li>
     *          <li>Ball must be between paddle's width.</li>
     *          <li>Ball must be between paddle's height.</li>
     *          <li>If ball speed is larger than paddle's width, a serious bug will be caused: Ball goes through the paddle.
     *              Because in that case, ball's X coordinate will be increased immediately, large enough to reach out paddle's bound
     *              So, we must add this condition.</li>
     *      </ol>
     *  </li>
     *  <li>else, return false</li>
     *  </ul>
     */
    @Override
    public boolean willCollide(RightPaddle paddle) {
        int paddleX = paddle.getX();
        int paddleY = paddle.getY();

        return vector.getX() > 0 &&                                                                 //  (1)
               paddleX <= x + size && x + size <= paddleX + paddle.getWidth() &&                    //  (2)
               paddleY <= y + size && y <= paddleY + paddle.getHeight() &&                          //  (3)
               (speed < paddle.getWidth() || paddleX <= x + size && x + size <= paddleX + speed);   //  (4)
    }

    @Override
    public boolean willCollide(Pickup pickup) {
        return getBallBound().intersects(pickup.getBound()) && lastTouchSide != Side.unknown;
    }

    private Rectangle getBallBound() {
        return new Rectangle(x, y, size, size);
    }

    @Override
    public void collide(Paddle paddle) {
        lastTouchSide = paddle.getSide();

        changeSpeed(paddle);
        changeDirection(paddle);
        soundPlayer.ballCollidePaddle();
    }

    @Override
    public void collide(Pickup __) {
        soundPlayer.ballCollidePickup();
    }
    //#endregion
}