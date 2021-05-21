package main.java.mvc.game.elements.function.tangible;

import main.java.mvc.game.elements.function.intangible.bonus.Bonus;
import main.java.mvc.game.elements.function.intangible.bonus.BonusType;

import java.awt.*;
import java.util.AbstractMap;
import java.util.Map;

/**
 * Class {@code BonusProcessBar} is used for display existing time left of {@code Bonus}
 *
 * @see main.java.mvc.game.elements.function.intangible.bonus.Bonus
 */
public class BonusProcessBar {

    /**
     * Map bonus type to display color
     */
    private static final Map<BonusType, Color> COLOR_MAP = Map.ofEntries(
            new AbstractMap.SimpleEntry<>(BonusType.bigBall, Color.blue),
            new AbstractMap.SimpleEntry<>(BonusType.speedingBall, Color.pink),
            new AbstractMap.SimpleEntry<>(BonusType.slowlyPaddle, Color.yellow),
            new AbstractMap.SimpleEntry<>(BonusType.increaseOwnPaddleSize, Color.red),
            new AbstractMap.SimpleEntry<>(BonusType.decreaseOpponentPaddleSize, Color.magenta)
    );

    private final Bonus bonus;

    public BonusProcessBar(Bonus bonus) {
        this.bonus = bonus;
    }

    /**
     * @return {@code Color}, depends on type of {@link #bonus}.
     */
    public Color getColor() {
        return COLOR_MAP.get(bonus.getType());
    }

    /**
     * @return width of {@code BonusProcessBar}, depends on existing time left of {@link #bonus}.
     */
    public int getWidth() {
        return bonus.getTimeLeft()/11;
    }
}
