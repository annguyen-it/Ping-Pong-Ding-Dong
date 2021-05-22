package main.java.mvc.game.elements.component.helper.factory;

import main.java.App;
import main.java.mvc.game.elements.component.gameObject.immovable.pickup.Pickup;
import main.java.mvc.game.elements.component.helper.controller.BonusController;
import main.java.mvc.game.elements.function.intangible.bonus.BonusType;

import java.util.Calendar;

public class PickupFactory {

    /**
     * Width of the appearing area of {@code Pickup}
     * <p>
     * Pickup should only appear in a specific area, not all playground. This will make game to be fair enough.
     * </p>
     */
    private static final int APPEAR_AREA_WIDTH = 650;

    /**
     * Width of the appearing area of {@code Pickup}
     */
    private static final int APPEAR_AREA_HEIGHT = 600;

    private static final int PICKUP_EXISTING_TIME = 10000;

    private final BonusController bonusController;
    private Pickup currentPickup;

    public PickupFactory(BonusController bonusController) {
        this.bonusController = bonusController;
    }

    public Pickup getPickup() {
        return currentPickup;
    }

    public void update() {
        long currentTime = Calendar.getInstance().getTimeInMillis();

        if (currentPickup == null || currentTime - currentPickup.getAppearTime() >= PICKUP_EXISTING_TIME) {
            create();
        }
    }

    public void create() {
        Pickup pickup;
        boolean duplicate;

        do {
            pickup = new Pickup(randomX(), randomY());
            duplicate = false;

            for (BonusType type : bonusController.getExcludeBonusTypeList()) {
                if (pickup.getBonusType() == type) {
                    duplicate = true;
                    break;
                }
            }
        }
        while (duplicate);

        currentPickup = pickup;
    }

    /**
     * Generate a random integer number that on x coordinate in allowed area
     * @return A {@code int}
     */
    private static int randomX() {
        return (int) (Math.random()*APPEAR_AREA_WIDTH) + (App.WIDTH - APPEAR_AREA_WIDTH)/2;
    }

    /**
     * Generate a random integer number that on y coordinate in allowed area
     * @return A int
     */
    private static int randomY() {
        return (int) (Math.random()*APPEAR_AREA_HEIGHT) + (App.HEIGHT - APPEAR_AREA_HEIGHT)/2;
    }
}
