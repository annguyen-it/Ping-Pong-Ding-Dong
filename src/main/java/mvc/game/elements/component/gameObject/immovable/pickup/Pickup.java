package main.java.mvc.game.elements.component.gameObject.immovable.pickup;

import main.java.mvc.game.elements.component.gameObject.immovable.ImmovableGameObject;
import main.java.mvc.game.elements.function.intangible.bonus.Bonus;
import main.java.mvc.game.elements.function.intangible.bonus.BonusType;

import java.awt.*;
import java.util.Calendar;

/**
 * Class {@code Pickup} represents a game object in Game's playground.
 * If ball collides with it, a {@code Bonus} appears, and it disappears.
 * It's not moving at all, but will disappear after some seconds if no balls collides with it.
 *
 * @see main.java.mvc.game.elements.function.intangible.bonus.Bonus
 */
public class Pickup extends ImmovableGameObject {
    /**
     * {@code Pickup}'s size
     * <p>
     * This is represents both width and height.
     * </p>
     */
    private static final int PICKUP_SIZE = 50;

    /**
     * The time that instance has appeared on playground, measured by timestamp in milliseconds
     */
    private final long appearTime;

    /**
     * The type of bonus that instance has
     *
     * @see main.java.mvc.game.elements.function.intangible.bonus.BonusType
     */
    private final BonusType bonusType;

    /**
     * Constructor of {@code Pickup}
     * <p>
     * When a {@code Pickup}'s instance is appear, it should be located randomly in allowed area, and has a random bonus type.
     * </p>
     */
    public Pickup(int x, int y) {
        super(x, y);

        appearTime = Calendar.getInstance().getTimeInMillis();
        bonusType = Bonus.randomType();
    }

    public BonusType getBonusType() {
        return bonusType;
    }

    public long getAppearTime() {
        return appearTime;
    }

    /**
     * Get image path of bonusType to display to the UI
     * @return A {@code String}
     */
    public String getImagePath() {
        return Bonus.getImagePath(bonusType);
    }

    /**
     * Get bound of instance to calculate collision
     * @return A {@code Rectangle}
     */
    public Rectangle getBound() {
        return new Rectangle(x, y, PICKUP_SIZE, PICKUP_SIZE);
    }
}
