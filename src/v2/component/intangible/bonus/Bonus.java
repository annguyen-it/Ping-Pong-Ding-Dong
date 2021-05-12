package v2.component.intangible.bonus;

import v2.board.GameSide.Side;
import v2.component.gameObject.movable.ball.Ball;
import v2.component.other.BonusProcessBar;
import v2.controller.GameController;
import v2.model.GameModel;

abstract public class Bonus {

    public static final int EXIST_TIME = 8800;

    protected final BonusType bonusType;
    private final BonusProcessBar processBar = new BonusProcessBar(this);
    private int timeLeft = EXIST_TIME;

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

            case slowlyPaddle:
                return new SlowlyPaddle();

            default:
                return new SpeedingBall();
        }
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

    public abstract void active();

    public abstract void deactive();
}
