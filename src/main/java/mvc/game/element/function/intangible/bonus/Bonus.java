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

abstract public class Bonus {

    private static final Map<BonusType, String> IMAGE_MAP = Map.ofEntries(
            new AbstractMap.SimpleEntry<>(BonusType.bigBall, ImagePathProvider.Game.bigBall),
            new AbstractMap.SimpleEntry<>(BonusType.decreaseEnemyPaddleSize, ImagePathProvider.Game.decreaseEnemyPaddleSize),
            new AbstractMap.SimpleEntry<>(BonusType.increaseOwnPaddleSize, ImagePathProvider.Game.increaseOwnPaddleSize),
            new AbstractMap.SimpleEntry<>(BonusType.multiBall, ImagePathProvider.Game.multiBall),
            new AbstractMap.SimpleEntry<>(BonusType.slowlyPaddle, ImagePathProvider.Game.slowlyPaddle),
            new AbstractMap.SimpleEntry<>(BonusType.speedingBall, ImagePathProvider.Game.speedingBall)
    );

    public static final int EXIST_TIME = 2200;

    protected final BonusType bonusType;
    private final BonusProcessBar processBar = this instanceof HasTimeLimit ? new BonusProcessBar(this) : null;

    private int timeLeft = this instanceof HasTimeLimit ? EXIST_TIME : 0;

    protected Side receiveSide;
    protected GameModel gameModel;
    protected Ball ball;

    public Bonus(BonusType bonusType) {
        this.bonusType = bonusType;
    }

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
                return new DecreaseEnemyPaddleSize();
        }
    }

    public static String getImagePath(BonusType bonusType) {
        return IMAGE_MAP.get(bonusType);
    }

    public static BonusType randomType() {
        return BonusType.values()[new Random().nextInt(BonusType.values().length)];
    }

    public Bonus with(GameModel gameModel) {
        this.gameModel = gameModel;
        return this;
    }

    public Bonus by(Side side, Ball ball) {
        this.receiveSide = side;
        this.ball = ball;
        return this;
    }

    public BonusProcessBar getProcessBar() {
        return processBar;
    }

    public void reset() {
        this.timeLeft = EXIST_TIME;
    }

    public int getTimeLeft() {
        return timeLeft;
    }

    public boolean timeout() {
        return timeLeft <= 0;
    }

    public BonusType getType() {
        return bonusType;
    }

    public boolean sameTypeWith(Bonus bonus) {
        return bonusType == bonus.bonusType;
    }

    public void decreaseTimeLeft() {
        timeLeft -= GameController.GAME_DELAY;
    }

    public boolean hasTimeLimit() {
        return this instanceof HasTimeLimit;
    }

    public boolean canAppearWhenActivated() {
        return this instanceof CanAppearWhenActivated;
    }

    public abstract void activate();

    public void deactivate() { }
}
