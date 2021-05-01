package v2.component.gameObject.movable.ball;

import v2.Game;
import v2.board.GameSide.Side;
import v2.component.gameObject.immovable.star.Star;
import v2.component.gameObject.movable.AllDirectionMovableGameObject;
import v2.component.gameObject.movable.paddle.LeftPaddle;
import v2.component.gameObject.movable.paddle.Paddle;
import v2.component.gameObject.movable.paddle.RightPaddle;
import v2.mechanics.ball.BallMechanics;
import v2.utils.sound.GameSoundPlayer;
import v2.utils.sound.HasSound;
import v2.component.helper.model.Vector;
import v2.view.GameView;

import java.awt.*;

/**
 * Class Ball
 *
 * @see v2.component.gameObject.movable.AllDirectionMovableGameObject
 * @see v2.mechanics.ball.BallMechanics
 * @see v2.utils.sound.HasSound
 */
public class Ball extends AllDirectionMovableGameObject implements BallMechanics, HasSound {

    //#region Properties
    private static final int INITIAL_BALL_X = 588;
    private static final int INITIAL_BALL_Y = 388;
    private static final int MAX_SPEED = 15;
    private static final int MIN_SPEED = 6;
    private static final double INITIAL_SPEED = 8;


    private static final Vector INITIAL_TO_LEFT_VECTOR = new Vector(180);
    private static final Vector INITIAL_TO_RIGHT_VECTOR = new Vector(0);

    public static final int SIZE = 24;
    public static int checkStarType = 0;

    private int size = SIZE;
    private GameSoundPlayer soundPlayer;

    //#endregion

    //#region Constructors
    /**
     * Constructor with random initial direction of ball, call only one time when game start.
     *
     * @param soundPlayer Sound player of ball
     */
    public Ball(GameSoundPlayer soundPlayer) {
        this(soundPlayer, randomInitialSide());
    }

    /**
     * Constructor with specify direction (left or right).
     * Also set sound player.
     *
     * @param soundPlayer      Sound player of ball
     * @param initialDirection Initial direction of ball
     * @see #setSoundPlayer(v2.utils.sound.GameSoundPlayer)
     */
    public Ball(GameSoundPlayer soundPlayer, Side initialDirection) {
        super(INITIAL_BALL_X, INITIAL_BALL_Y, getInitialVector(initialDirection), INITIAL_SPEED);
        setSoundPlayer(soundPlayer);
    }
    //#endregion

    //#region Own Method
    /**
     * Generate a random initial direction of ball. This method is called by only
     * 1-argument-constructor
     *
     * @return random direction
     * @see v2.board.GameSide.Side
     */
    private static Side randomInitialSide() {
        return (int) (Math.random()*2) == 0 ? Side.left : Side.right;
    }

    /**
     * Convert initial HorizontalDirection type to Vector
     *
     * @param initialDirection HorizontalDirection needs to convert
     * @return converted vector
     * @see Side
     * @see #INITIAL_TO_LEFT_VECTOR
     * @see #INITIAL_TO_RIGHT_VECTOR
     */
    private static Vector getInitialVector(Side initialDirection) {
        return initialDirection == Side.left ? INITIAL_TO_LEFT_VECTOR : INITIAL_TO_RIGHT_VECTOR;
    }

    /**
     * Get current size of ball
     *
     * @return size of ball
     */
    public int getSize() {
        return size;
    }

    /**
     * Get ratio of collision of ball to paddle (0% at top of paddle to 100% at bottom).
     *
     * @param paddle Collided paddle
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
        int paddleDiv = Paddle.INITIAL_PADDLE_HEIGHT/11;

        // position:      0   1   2   3   4   5   6   7   8   9   10
        //              |   |   |   |   |   |   |   |   |   |   |   |
        //                3   2   1   0  -1  -2  -1   0   1   2   3
        //

        switch ((y - topPaddleY)/paddleDiv) {
            case 1:
            case 9:
                speed += 2;
                break;

            case 2:
            case 8:
                speed += 1;
                break;

            case 3:
            case 7:
                break;

            case 4:
            case 6:
                speed -= 1;
                break;

            case 5:
                speed -= 2;
                break;

            default:
                speed += 3;
                break;
        }

        speed = Math.max(speed, MIN_SPEED);
        speed = Math.min(speed, MAX_SPEED);
    }

    @Override
    public void changeSpeed(Star star) {

    }

    @Override
    public void changeDirection(LeftPaddle paddle) {
        double ratio = getRatioCollision(paddle);
        double gamma = 45 - ratio*90;

        if (gamma >= 0) {
            vector = new Vector(gamma);
        }
        else {
            vector = new Vector(360 + gamma);
        }
    }

    @Override
    public void changeDirection(RightPaddle paddle) {
        double ratio = getRatioCollision(paddle);
        double gamma = ratio*90 - 45;

        vector = new Vector(180 + gamma);
    }

    @Override
    public Side isOutTheBoard() {
        if (x < -SIZE) {
            soundPlayer.miss();
            return Side.left;
        }
        else if (x > Game.WIDTH) {
            soundPlayer.miss();
            return Side.right;
        }

        return Side.unknown;
    }
    //#endregion

    //#region Transforms
    @Override
    public void sizeUp() {
        size += 10;
    }

    @Override
    public void sizeDown() {
        // Todo
    }
    //#endregion

    //#region Collide
    @Override
    public boolean willWallCollide() {
        double nextPosY = y + vector.getY();
        return (nextPosY < 0 && vector.getY() < 0) ||
                (nextPosY + size + 40 > Game.HEIGHT && vector.getY() > 0);
    }

    @Override
    public void wallCollide() {
        soundPlayer.wallCollide();
        vector = vector.getReflection();
    }

    @Override
    public boolean willCollide(LeftPaddle paddle) {
        int paddleX = paddle.getX();
        int paddleY = paddle.getY();

        return vector.getX() < 0 &&
                paddleX <= x && x <= paddleX + Paddle.INITIAL_PADDLE_WIDTH &&
                paddleY <= y + size && y <= paddleY + Paddle.INITIAL_PADDLE_HEIGHT;
    }

    @Override
    public boolean willCollide(RightPaddle paddle) {
        int paddleX = paddle.getX();
        int paddleY = paddle.getY();

        return vector.getX() > 0 &&
                paddleX <= x + size && x + size < paddleX + Paddle.INITIAL_PADDLE_WIDTH &&
                paddleY <= y + size && y <= paddleY + Paddle.INITIAL_PADDLE_HEIGHT;
    }

    private Rectangle getBallBound() {
        return new Rectangle(x, y, size, size);
    }

    @Override
    public boolean willCollide(Star star) {
        return getBallBound().intersects(star.getBound());
    }

    @Override
    public void collide(Paddle paddle) {
        changeSpeed(paddle);
        changeDirection(paddle);
        soundPlayer.ballCollide();
    }

    @Override
    public void collide(Star star) {
        GameView.timeLong = 4400;
        soundPlayer.starCollide();

        switch (star.getType()) {
            case bigBall:
                checkStarType =1;
                sizeUp();
                break;

            case multiBall:

                checkStarType =2;
                break;

            case speedUp:
                checkStarType =3;
                break;

            case speedDown:
                checkStarType = 4;
                break;

            default:
        }
    }
    //#endregion

    //#region Others
    @Override
    public void setSoundPlayer(GameSoundPlayer soundPlayer) {
        this.soundPlayer = soundPlayer;
    }
    //#endregion
}