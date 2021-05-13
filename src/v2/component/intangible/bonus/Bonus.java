package v2.component.intangible.bonus;

import v2.board.GameSide.Side;
import v2.component.gameObject.movable.ball.Ball;
import v2.component.other.BonusProcessBar;
import v2.controller.GameController;
import v2.model.GameModel;

import java.util.AbstractMap;
import java.util.Map;
import java.util.Random;

abstract public class Bonus {
    private static final Map<BonusType, String> IMAGE_MAP = Map.ofEntries(
            new AbstractMap.SimpleEntry<>(BonusType.bigBall, "resources/img/big-ball.png"),
            new AbstractMap.SimpleEntry<>(BonusType.multiBall, "resources/img/multi-ball.png"),
            new AbstractMap.SimpleEntry<>(BonusType.speedingBall, "resources/img/speed-up.png"),
            new AbstractMap.SimpleEntry<>(BonusType.slowlyPaddle, "resources/img/speed-down.png")
            // TODO: Implement increaseOwnPaddleSize and decreaseEnemyPaddleSize
    );

    public static final int EXIST_TIME = 8800;

    protected final BonusType bonusType;
    private final BonusProcessBar processBar = this instanceof HasTimeLimit ? new BonusProcessBar(this) : null;

    private int timeLeft = this instanceof HasTimeLimit ? EXIST_TIME : 0;

    protected Side receiveSide;
    protected GameModel gameModel;
    protected Ball ball;

    public Bonus(BonusType bonusType) {
        this.bonusType = bonusType;
    }

    public static Bonus type(BonusType bonusType){
        switch (bonusType){
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

    public static String getImagePath(BonusType bonusType){
        return IMAGE_MAP.get(bonusType);
    }

    public static BonusType randomType(){
        return BonusType.values()[new Random().nextInt(BonusType.values().length)];
    }

    public Bonus with(GameModel gameModel){
        this.gameModel = gameModel;
        return this;
    }

    public Bonus by(Side side, Ball ball){
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

    public boolean timeout(){
        return timeLeft <= 0;
    }

    public BonusType getType() {
        return bonusType;
    }

    public boolean sameTypeWith(Bonus bonus){
        return bonusType == bonus.bonusType;
    }

    public void decreaseTimeLeft() {
        timeLeft -= GameController.GAME_DELAY;
    }

    public boolean hasTimeLimit(){
        return this instanceof HasTimeLimit;
    }

    public boolean canAppearWhenActivated(){
        return this instanceof CanAppearWhenActivated;
    }

    public abstract void activate();

    public void deactivate() { }
}
