package v2.component.intangible;

import v2.component.gameObject.immovable.star.BonusType;
import v2.component.other.BonusProcessBar;
import v2.controller.GameController;

public class Bonus {

    public static final int EXIST_TIME = 8800;

    private final BonusType starType;
    private final BonusProcessBar processBar = new BonusProcessBar(this);
    private int timeLeft = EXIST_TIME;

    public Bonus(BonusType starType) {
        this.starType = starType;
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

    public BonusType getStarType() {
        return starType;
    }

    public void decreaseTimeLeft() {
        timeLeft -= GameController.GAME_DELAY;
    }
}
