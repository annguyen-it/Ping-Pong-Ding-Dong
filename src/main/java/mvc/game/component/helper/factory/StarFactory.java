package main.java.mvc.game.component.helper.factory;

import main.java.mvc.game.component.gameObject.immovable.star.Pickup;
import main.java.mvc.game.component.helper.controller.BonusController;
import main.java.mvc.game.component.intangible.bonus.BonusType;

import java.util.Calendar;

public class StarFactory {

    private static final int STAR_EXISTING_TIME = 10000;

    private final BonusController bonusController;
    private Pickup currentPickup;

    public StarFactory(BonusController bonusController) {
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
        boolean duplicate;
        Pickup pickup;

        do {
            pickup = new Pickup();
            duplicate = false;

            for (BonusType type : bonusController.getExcludeBonusType()) {
                if (pickup.getType() == type) {
                    duplicate = true;
                    break;
                }
            }
        }
        while (duplicate);

        currentPickup = pickup;
    }
}
