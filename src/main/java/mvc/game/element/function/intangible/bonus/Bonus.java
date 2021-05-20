package main.java.mvc.game.element.function.intangible.bonus;

import main.java.mvc.game.element.function.intangible.GameSide.Side;
import main.java.mvc.game.element.component.gameObject.movable.ball.Ball;
import main.java.mvc.game.element.function.tangible.BonusProcessBar;
import main.java.mvc.game.GameController;
import main.java.mvc.game.GameModel;
import main.java.utils.image.ImagePathProvider;

import java.util.AbstractMap;
import java.util.Map;
import java.util.Random;

/**
 * Class {@code Bonus} gives to game many special effect.
 * <p>
 * {@code Bonus} is equipped in {@code Pickup}. <br>
 * When a {@code Ball} collides with a {@code Pickup}, {@code Bonus of {@code Pickup} will be add to the game.
 * Some {@code Bonus} have existing time limit, some others do not.
 * </p>
 */
abstract public class Bonus {

    /**
     * Map type of bonus to its image path
     */
    private static final Map<BonusType, String> IMAGE_MAP = Map.ofEntries(
            new AbstractMap.SimpleEntry<>(BonusType.bigBall, ImagePathProvider.Game.bigBall),
            new AbstractMap.SimpleEntry<>(BonusType.decreaseOpponentPaddleSize, ImagePathProvider.Game.decreaseOpponentPaddleSize),
            new AbstractMap.SimpleEntry<>(BonusType.increaseOwnPaddleSize, ImagePathProvider.Game.increaseOwnPaddleSize),
            new AbstractMap.SimpleEntry<>(BonusType.multiBall, ImagePathProvider.Game.multiBall),
            new AbstractMap.SimpleEntry<>(BonusType.slowlyPaddle, ImagePathProvider.Game.slowlyPaddle),
            new AbstractMap.SimpleEntry<>(BonusType.speedingBall, ImagePathProvider.Game.speedingBall)
    );

    /**
     * Existing time of {@code Bonus}
     * <p>
     * This is only necessary of implementation has time limit.
     * </p>
     */
    public static final int EXIST_TIME = 2200;

    /**
     * Type of bonus
     *
     * @see main.java.mvc.game.element.function.intangible.bonus.BonusType
     */
    protected final BonusType bonusType;

    /**
     * Process bar of {@code Bonus}
     * <p>
     * If instance does not have time limit, it does not need this.
     * </p>
     *
     * @see main.java.mvc.game.element.function.tangible.BonusProcessBar
     */
    private final BonusProcessBar processBar = this instanceof HasTimeLimit ? new BonusProcessBar(this) : null;

    /**
     * Time duration left to affect {@code Bonus}
     * <p>
     * If instance does not have time limit, it does not need this.
     * </p>
     */
    private int timeLeft = this instanceof HasTimeLimit ? EXIST_TIME : 0;

    /**
     * The side with {@code Paddle} having the last touch to the {@code ball} and then collides {@code Bonus}
     */
    protected Side receiveSide;

    /**
     * {@code GameModel} of current Game
     */
    protected GameModel gameModel;

    /**
     * The {@code Ball} that collide with {@code Bonus}
     */
    protected Ball ball;

    protected Bonus(BonusType bonusType) {
        this.bonusType = bonusType;
    }

    public BonusProcessBar getProcessBar() {
        return processBar;
    }

    public int getTimeLeft() {
        return timeLeft;
    }

    public BonusType getType() {
        return bonusType;
    }

    /**
     * @param bonusType type of Bonus
     *
     * @return the implementation of {@code Bonus} that matches with param bonusType
     */
    public static Bonus type(BonusType bonusType) {
        switch (bonusType) {
            case bigBall:
                return new BigBall();

            case multiBall:
                return new MultiBall();

            case speedingBall:
                return new SpeedingBall();

            case slowlyPaddle:
                return new SlowlyPaddle();

            case increaseOwnPaddleSize:
                return new IncreaseOwnPaddleSize();

            default:
                return new DecreaseOpponentPaddleSize();
        }
    }

    /**
     * @param bonusType type of {@code Bonus}
     *
     * @return Image path of Bonus type as param
     */
    public static String getImagePath(BonusType bonusType) {
        return IMAGE_MAP.get(bonusType);
    }

    /**
     * Generate random {@code BonusType}
     * <p>
     * This function is called only from {@code Pickup}'s constructor.
     * </p>
     *
     * @return {@code BonusType}
     */
    public static BonusType randomType() {
        return BonusType.values()[new Random().nextInt(BonusType.values().length)];
    }

    /**
     * Assign a {@code GameModel} to {@link #gameModel}
     *
     * @param gameModel {@code GameModel}
     *
     * @return this instance
     */
    public Bonus with(GameModel gameModel) {
        this.gameModel = gameModel;
        return this;
    }

    /**
     * Assign receiveSide and ball
     *
     * @param side {@code Side}
     * @param ball {@code Ball}
     *
     * @return this instance
     */
    public Bonus by(Side side, Ball ball) {
        this.receiveSide = side;
        this.ball = ball;
        return this;
    }

    /**
     * Reset existing time of {@code Bonus}
     * <p>
     * This function is called when a {@code Ball} collides a {@code Pickup} having an activated {@code Bonus}
     * </p>
     */
    public void reset() {
        this.timeLeft = EXIST_TIME;
    }

    /**
     * @return true if {@link #timeLeft} is less than or equal to zero, else false.
     */
    public boolean timeout() {
        return timeLeft <= 0;
    }

    /**
     * Used for compare {@code Bonus}es
     *
     * @param bonus other {@code Bonus} to compare
     *
     * @return true if they are the same type, else false.
     */
    public boolean sameTypeWith(Bonus bonus) {
        return bonusType == bonus.bonusType;
    }

    /**
     * Decrease time left to exist
     */
    public void decreaseTimeLeft() {
        timeLeft -= GameController.GAME_DELAY;
    }

    /**
     * @return true if instance has time limit, else false.
     */
    public boolean hasTimeLimit() {
        return this instanceof HasTimeLimit;
    }

    /**
     * @return true if {@code Pickup} type X can appear when X has been activated, else false
     */
    public boolean canAppearWhenActivated() {
        return this instanceof CanAppearWhenActivated;
    }

    /**
     * Activate the {@code Bonus}
     */
    public abstract void activate();

    /**
     * Deactivate the {@code Bonus}
     */
    public void deactivate() { }
}
