package v2.component.other;

import v2.component.intangible.Bonus;
import v2.component.gameObject.immovable.star.BonusType;

import java.awt.*;
import java.util.AbstractMap;
import java.util.Map;

public class BonusProcessBar {

    private final Bonus bonus;

    private static final Map<BonusType, Color> colorMap = Map.ofEntries(
            new AbstractMap.SimpleEntry<>(BonusType.bigBall, Color.blue),
            new AbstractMap.SimpleEntry<>(BonusType.multiBall, Color.green),
            new AbstractMap.SimpleEntry<>(BonusType.speedUp, Color.pink),
            new AbstractMap.SimpleEntry<>(BonusType.speedDown, Color.red)
    );

    public BonusProcessBar(Bonus bonus) {
        this.bonus = bonus;
    }

    public Color getColor() {
        return colorMap.get(bonus.getStarType());
    }

    public int getWidth() {
        return bonus.getTimeLeft()/40;
    }
}
