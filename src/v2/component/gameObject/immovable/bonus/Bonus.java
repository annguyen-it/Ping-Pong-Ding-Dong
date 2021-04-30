package v2.component.gameObject.immovable.bonus;

import v2.component.gameObject.immovable.star.StarType;
import v2.controller.GameController;

import java.awt.*;
import java.util.AbstractMap;
import java.util.Map;

public class Bonus {

    private static final Map<StarType, Color> colorMap = Map.ofEntries(
            new AbstractMap.SimpleEntry<>(StarType.bigBall, Color.blue),
            new AbstractMap.SimpleEntry<>(StarType.multiBall, Color.green),
            new AbstractMap.SimpleEntry<>(StarType.speedUp, Color.pink),
            new AbstractMap.SimpleEntry<>(StarType.speedDown, Color.red)
    );



    private final StarType starType;
    private int timeLeft = 8800;

    public Bonus(StarType starType) {
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

    public StarType getStarType() {
        return starType;
    }

    public void decreaseTimeLeft() {
        timeLeft -= GameController.GAME_DELAY;
    }
}
