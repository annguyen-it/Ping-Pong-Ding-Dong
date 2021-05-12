package v2.component.other;

import v2.component.intangible.bonus.Bonus;
import v2.component.gameObject.immovable.star.StarType;

import java.awt.*;
import java.util.AbstractMap;
import java.util.Map;

public class BonusProcessBar {

    private final Bonus bonus;

    private static final Map<StarType, Color> colorMap = Map.ofEntries(
            new AbstractMap.SimpleEntry<>(StarType.bigBall, Color.blue),
            new AbstractMap.SimpleEntry<>(StarType.multiBall, Color.green),
            new AbstractMap.SimpleEntry<>(StarType.speedingBall, Color.pink),
            new AbstractMap.SimpleEntry<>(StarType.slowlyPaddle, Color.yellow)
    );

    public BonusProcessBar(Bonus bonus) {
        this.bonus = bonus;
    }

    public Color getColor() {
        return colorMap.get(bonus.getStarType());
    }

    public int getWidth() {
        return bonus.getTimeLeft()/45;
    }
}
