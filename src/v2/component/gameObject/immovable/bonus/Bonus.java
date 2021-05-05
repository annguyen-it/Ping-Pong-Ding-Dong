package v2.component.gameObject.immovable.bonus;

import v2.component.gameObject.immovable.star.BonusType;
import v2.controller.GameController;

import java.awt.*;
import java.util.AbstractMap;
import java.util.Map;

public class Bonus {

    public static final int EXIST_TIME = 8800;

    private static final Map<BonusType, Color> colorMap = Map.ofEntries(
            new AbstractMap.SimpleEntry<>(BonusType.bigBall, Color.blue),
            new AbstractMap.SimpleEntry<>(BonusType.multiBall, Color.green),
            new AbstractMap.SimpleEntry<>(BonusType.speedUp, Color.pink),
            new AbstractMap.SimpleEntry<>(BonusType.speedDown, Color.red)
    );

    private final BonusType starType;
    private int timeLeft = EXIST_TIME;

    public Bonus(BonusType starType) {
        this.starType = starType;
    }

    public void setTimeLeft(int timeLeft) {
        this.timeLeft = timeLeft;
    }

    public int getTimeLeft() {
        return timeLeft;
    }

    public Color getColor() {
        return colorMap.get(starType);
    }

    public BonusType getStarType() {
        return starType;
    }

    public void decreaseTimeLeft() {
        timeLeft -= GameController.GAME_DELAY;
    }
}
