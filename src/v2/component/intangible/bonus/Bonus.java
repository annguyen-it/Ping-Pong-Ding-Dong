package v2.component.intangible.bonus;

import v2.board.GameSide.Side;
import v2.component.gameObject.immovable.star.StarType;
import v2.component.other.BonusProcessBar;
import v2.controller.GameController;
import v2.model.GameModel;

abstract public class Bonus {

    public static final int EXIST_TIME = 8800;

    private final StarType starType;
    private final BonusProcessBar processBar = new BonusProcessBar(this);
    private int timeLeft = EXIST_TIME;

    protected Side receiveSide;
    protected GameModel gameModel;

    public Bonus(StarType starType) {
        this.starType = starType;
    }

    public static Bonus type(StarType starType){
        switch (starType){
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

    public Bonus by(Side side){
        this.receiveSide = side;
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

    public StarType getStarType() {
        return starType;
    }

    public boolean sameTypeWith(Bonus bonus){
        return starType == bonus.starType;
    }

    public void decreaseTimeLeft() {
        timeLeft -= GameController.GAME_DELAY;
    }

    public abstract void active();

    public abstract void deactive();
}
