package main.java.mvc.game.element.component.helper.factory;

import main.java.mvc.game.element.component.gameObject.immovable.pickup.Pickup;
import main.java.mvc.game.element.component.helper.controller.BonusController;
import main.java.mvc.game.element.function.intangible.bonus.BonusType;

import java.util.Calendar;

public class PickupFactory {

    private static final int STAR_EXISTING_TIME = 10000;

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

        if (currentPickup == null || currentTime - currentPickup.getAppearTime() >= STAR_EXISTING_TIME) {
            createStar();
        }
    }

    public void createStar() {
        Pickup pickup;
        boolean duplicate;

        do {
            pickup = new Pickup();
            duplicate = false;

            for (BonusType type : bonusController.getExcludeBonusType()) {
                if (pickup.getBonusType() == type) {
                    duplicate = true;
                    break;
                }
            }
        }
        while (duplicate);

        currentPickup = pickup;
    }
}
