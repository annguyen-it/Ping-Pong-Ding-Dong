package main.java.mvc.game.elements.component.gameObject.movable.ball;

import main.java.App;
import main.java.mvc.game.elements.function.intangible.GameSide.Side;
import main.java.mvc.game.elements.component.gameObject.immovable.pickup.Pickup;
import main.java.mvc.game.elements.component.gameObject.movable.AllDirectionMovableGameObject;
import main.java.mvc.game.elements.component.gameObject.movable.paddle.LeftPaddle;
import main.java.mvc.game.elements.component.gameObject.movable.paddle.Paddle;
import main.java.mvc.game.elements.component.gameObject.movable.paddle.RightPaddle;
import main.java.mvc.game.mechanics.ball.BallMechanics;
import main.java.mvc.game.sound.GameSoundPlayer;
import main.java.mvc.game.elements.function.intangible.Vector;

import java.awt.*;

/**
 * Class {@code Ball} represents a game object in Game's playground.
 * It can move by all directions, collide with walls, {@code Paddle}s and {@code Pickup}s.
 * <p>
 * {@code Ball} has circle shape
 * </p>
 * <p>
 * If {@code Ball} reach cross of a side, the opposite side Paddle would be received one points.
 * </p>
 *
 * @see main.java.mvc.game.elements.component.gameObject.movable.paddle.Paddle
 * @see main.java.mvc.game.elements.component.gameObject.immovable.pickup.Pickup
 */
public class Ball extends AllDirectionMovableGameObject implements BallMechanics {

    //#region Properties

    /**
     * The initial position on x coordinate of {@code Ball}
     */
    private static final int INITIAL_X = 588;

    /**
     * The initial position on y coordinate of {@code Ball}
     */
    private static final int INITIAL_Y = 388;


    /**
     * The initial size of {@code Ball}
     * <p>
     * This is represents both width and height.
     * </p>
     */
    private static final int INITIAL_SIZE = 24;

    /**
     * The initial speed of {@code Ball}
     */
    private static final double INITIAL_SPEED = 9;

    /**
     * The maximum speed of {@code Ball} (in normal condition)
     */
    private static final double MAX_SPEED = 15;

    /**
     * The minimum speed of {@code Ball} (in normal condition)
     */
    private static final double MIN_SPEED = 8;

    /**
     * The maximum speed of {@code Ball} (when bonus {@code SpeedingBall} is activated)
     *
     * @see main.java.mvc.game.elements.function.intangible.bonus.SpeedingBall
     */
    private static final double BONUS_MAX_SPEED = 20;

    /**
     * The minimum speed of {@code Ball} (when bonus {@code SpeedingBall} is activated)
     *
     * @see main.java.mvc.game.elements.function.intangible.bonus.SpeedingBall
     */
    private static final double BONUS_MIN_SPEED = 13;

    /**
     * Default vector of {@code Ball} when it was created and has initial falling side is left
     */
    private static final Vector INITIAL_TO_LEFT_VECTOR = new Vector(180);

    /**
     * Default vector of {@code Ball} when it was created and has initial falling side is right
     */
    private static final Vector INITIAL_TO_RIGHT_VECTOR = new Vector(0);

    /**
     * Use this to play sound
     *
     * <p>All balls on the playground are use the same sound player, which was initialized in {@code GameModel}</p>
     *
     * @see main.java.mvc.game.sound.GameSoundPlayer
     * @see main.java.mvc.game.GameModel
     */
    private final GameSoundPlayer soundPlayer;

    /**
     * The side which has the last touch. When instance was created, this is assign to Side.unknown
     *
     * @see main.java.mvc.game.elements.function.intangible.GameSide.Side
     */
    private Side lastTouchSide;

    /**
     * The side of instance
     */
    private int size;

    /**
     * Maximum speed of instance can move
     *
     * <p>
     * This can be {@code MAX_SPEED} or {@code BONUS_MAX_SPEED}
     * </p>
     *
     * @see Ball#MAX_SPEED
     * @see Ball#BONUS_MAX_SPEED
     */
    private double maxSpeed = MAX_SPEED;

    /**
     * Minimum speed of instance can move
     * <p>
     * This can be {@code MIN_SPEED} or {@code BONUS_MIN_SPEED}
     * </p>
     *
     * @see Ball#MIN_SPEED
     * @see Ball#BONUS_MIN_SPEED
     */
    private double minSpeed = MIN_SPEED;

    //#endregion

    //#region Constructors

    /**
     * Constructor with random initial direction of ball
     * <p>
     * This constructor is called only one time when Game start (in GameController).
     * </p>
     *
     * @param soundPlayer Sound player of ball
     *
     * @see main.java.mvc.game.GameController
     */
    public Ball(GameSoundPlayer soundPlayer) {
        this(randomInitialSide(), soundPlayer);
    }

    /**
     * Constructor with specify direction (Side.left or Side.right) and a {@code SoundPlayer}'s instance.
     *
     * <p>
     * This constructor is called every time the last ball in playground is lost (in GameModel)
     * </p>
     *
     * @param soundPlayer      Sound player of ball
     * @param initialDirection Initial direction of ball
     *
     * @see main.java.mvc.game.GameModel
     */
    public Ball(Side initialDirection, GameSoundPlayer soundPlayer) {
        this(INITIAL_X, INITIAL_Y, getInitialVector(initialDirection), INITIAL_SPEED, INITIAL_SIZE,
                Side.unknown, soundPlayer);
    }

    /**
     * Constructor with fully necessary properties of {@code Ball}
     * <p>
     * This constructor is called every time new ball is created by bonus {@code MultiBall}
     * </p>
     *
     * @param x             initial x coordinate
     * @param y             initial y coordinate
     * @param vector        initial vector
     * @param speed         initial speed
     * @param size          initial size
     * @param lastTouchSide initial last touch side
     * @param soundPlayer   initial sound player
     */
    public Ball(int x, int y, Vector vector, double speed, int size, Side lastTouchSide, GameSoundPlayer soundPlayer) {
        super(x, y, vector, speed);

        this.size = size;
        this.lastTouchSide = lastTouchSide;
        this.soundPlayer = soundPlayer;
    }

    //#endregion

    //#region Own Method

    /**
     * Generate a random initial moving direction of ball.
     * <p>
     * This method is only one time in a match, called by {@link #Ball(main.java.mvc.game.sound.GameSoundPlayer)}
     * </p>
     *
     * @return random direction
     */
    private static Side randomInitialSide() {
        return (int) (Math.random()*2) == 0 ? Side.left : Side.right;
    }

    /**
     * Receives an initial direction and return a {@code Vector} match with that direction
     *
     * @param initialDirection HorizontalDirection needs to convert
     *
     * @return A {@code Vector}
     *
     * @see main.java.mvc.game.elements.function.intangible.Vector
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

    /**
     * Get the side of paddle that have the last touch instance
     * <p>
     * If there are no paddle touches ball, the side would be {@code Side.unknown}
     * </p>
     *
     * @return a {@code Side}
     */
    public Side getLastTouchSide() {
        return lastTouchSide;
    }

    /**
     * Get ratio of collision of ball to paddle (0.0 at top of paddle to 1.0 at bottom).
     *
     * @param paddle Collided paddle
     *
     * @return a {@code double} in range [0.0, 1.0]
     */
    private double getRatioCollision(Paddle paddle) {
        return (y - paddle.getY() + 1.0*size)/(paddle.getHeight() + size);
    }

    //#endregion

    //#region Moving

    /**
     * Try to moves instance.
     * <p>
     * If ball will collide, do a collision, else keep moving instance.
     * </p>
     */
    @Override
    public void tryMove() {
        x += vector.getX();

        if (willWallCollide() != Side.unknown) {
            wallCollide();
        }
        else {
            move();
        }
    }

    /**
     * Change speed of instance
     * <p>
     * Divide paddle to 11 regions, marked from 0 to 10. The instance speed should be changed, depend on which region it collides.
     * </p>
     * <p>
     * position:
     * <blockquote><pre>
     *                0   1   2   3   4   5   6   7   8   9   10
     *              |   |   |   |   |   |   |   |   |   |   |   |
     * </pre></blockquote>
     * </p>
     *
     * @param paddle The {@code Paddle} which collides with instance
     */
    @Override
    public void changeSpeed(Paddle paddle) {
        //  y coordinate of the top edge of the paddle
        int topPaddleY = paddle.getY();

        //  Need to add 'size', for case ball collides with the edge of paddle, 'size'/2 for each edge
        //  Because of dividing paddle into 11 regions, total height should be divided by 12.0, see more in below comment
        double paddleDiv = (paddle.getHeight() + size)/12.0;

        //  Need to add 'size'/2.0, because the middle part of the ball will collide with the paddle, not the top
        //  (y + size/2.0 - topPaddleY) always less than 14.0, and collideRegion never larger than 11.
        //  That's why we need dividing 12.0 in previous operator.
        int collideRegion = (int) ((y + size/2.0 - topPaddleY)/(paddleDiv));

        switch (collideRegion) {
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
                //  Do not change speed
                break;

            case 4:
            case 6:
                speed -= 0.5;
                break;

            case 5:
                speed -= 1.5;
                break;

            // 0 or 10, or any exceptions
            default:
                speed += 2;
                break;
        }

        //  normalize speed
        speed = Math.max(speed, minSpeed);
        speed = Math.min(speed, maxSpeed);
    }

    /**
     * Speed up and temporarily increase speed limit
     * <p>
     * This function is called when picked bonus SpeedingBall
     * </p>
     *
     * @see main.java.mvc.game.elements.function.intangible.bonus.SpeedingBall#activate()
     */
    @Override
    public void speedUp() {
        speed = Math.min(speed + 5, BONUS_MAX_SPEED);
        minSpeed = BONUS_MIN_SPEED;
        maxSpeed = BONUS_MAX_SPEED;
    }

    /**
     * Return initial speed limit
     * <p>
     * This function is called when bonus SpeedingBall is deactivated
     * </p>
     *
     * @see main.java.mvc.game.elements.function.intangible.bonus.SpeedingBall#deactivate()
     */
    @Override
    public void returnInitialSpeed() {
        if (speed >= BONUS_MIN_SPEED) {
            speed -= 5;
        }

        minSpeed = MIN_SPEED;
        maxSpeed = MAX_SPEED;
    }

    /**
     * Change moving direction, depends on "collision ratio".
     * <p>
     * New vector is in range [0, 45deg] or [315deg, 359deg]
     * </p>
     *
     * @param paddle LeftPaddle
     *
     * @see Ball#getRatioCollision(main.java.mvc.game.elements.component.gameObject.movable.paddle.Paddle)
     */
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

    /**
     * Change moving direction, depends on "collision ratio".
     * <p>
     * New vector is in range [135deg, 225deg]
     * </p>
     *
     * @param paddle RightPaddle
     *
     * @see Ball#getRatioCollision(main.java.mvc.game.elements.component.gameObject.movable.paddle.Paddle)
     */
    @Override
    public void changeDirection(RightPaddle paddle) {
        double ratio = getRatioCollision(paddle);
        double alpha = ratio*90 - 45;

        vector = new Vector(180 + alpha);
    }

    /**
     * Determine which side lost ball
     * <p>
     * If ball is not lost, return Side unknown
     * </p>
     *
     * @return {@code Side}
     */
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

    /**
     * Increase size
     * <p>
     * This function is called when picked bonus {@code BigBall}.
     *
     * @see main.java.mvc.game.elements.function.intangible.bonus.BigBall#activate()
     * </p>
     */
    @Override
    public void sizeUp() {
        if (size == INITIAL_SIZE) {
            size += 10;
        }
    }

    /**
     * Return initial size
     * <p>
     * This function is called when bonus BigBall is deactivated
     * </p>
     *
     * @see main.java.mvc.game.elements.function.intangible.bonus.BigBall#deactivate()
     */
    @Override
    public void returnInitialSize() {
        if (size > INITIAL_SIZE) {
            size -= 10;
        }
    }

    //#endregion

    //#region Collide

    /**
     * Check if collides with the wall or not
     *
     * @return <ul>
     * <li>Side.top if next position is out of top bound of playground and ball is moving up.</li>
     * <li>Side.bottom if next position is out of bottom bound of playground and ball is moving down.</li>
     * <li>Else return Side.unknown.</li>
     * </ul>
     */
    @Override
    public Side willWallCollide() {
        double nextPositionY = y + vector.getY();

        if (nextPositionY < 0 && vector.getY() < 0) {
            return Side.top;
        }

        if (nextPositionY + size + 40 > App.HEIGHT && vector.getY() > 0) {
            return Side.bottom;
        }

        return Side.unknown;
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
     *              Because in that case, ball's X coordinate will be increased immediately, large enough to reach out paddle's bound.
     *              So, we must have this condition.</li>
     *      </ol>
     *  </li>
     *  <li>Else, return false.</li>
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
     *              So, we must have this condition.</li>
     *      </ol>
     *  </li>
     *  <li>Else, return false</li>
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

    /**
     * Check if collides {@code Pickup} or not
     * <p>
     * We just want to know ball collides with {@code Pickup} or not, but not complex behavior like ball with wall or with paddle.
     * So, just keep it simple.
     * </p>
     *
     * @param pickup Pickup
     *
     * @return boolean
     *
     * @implNote If the ball has not ever collide any {@code Paddle}, it would not collide {@code Pickup}
     */
    @Override
    public boolean willCollide(Pickup pickup) {
        return getbound().intersects(pickup.getBound()) && lastTouchSide != Side.unknown;
    }

    /**
     * Get bound
     * <p>
     * This function is called from only want to check if instance collides {@code Pickup} or not.
     * </p>
     *
     * @return rectangle
     *
     * @see Ball#willCollide(main.java.mvc.game.elements.component.gameObject.immovable.pickup.Pickup)
     */
    private Rectangle getbound() {
        return new Rectangle(x, y, size, size);
    }

    /**
     * Make a collision with paddle
     * <p>
     * This function is execute only if willCollide() return true in GameModel.
     * </p>
     *
     * @param paddle Paddle
     *
     * @see main.java.mvc.game.GameModel
     */
    @Override
    public void collide(Paddle paddle) {
        lastTouchSide = paddle.getSide();

        changeSpeed(paddle);
        changeDirection(paddle);
        soundPlayer.ballCollidePaddle();
    }

    /**
     * Make a collision with {@code Pickup}
     *
     * @param pickup {@code Pickup} (Just to make sure the GameObject that instance collides with is {@code Pickup}.<br>
     *               We actually do not use this)
     */
    @Override
    public void collide(Pickup pickup) {
        soundPlayer.ballCollidePickup();
    }

    //#endregion
}