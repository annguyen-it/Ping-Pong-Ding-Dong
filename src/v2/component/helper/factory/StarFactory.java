package v2.component.helper.factory;

import v2.component.gameObject.immovable.star.Star;
import v2.component.helper.controller.BonusController;
import v2.component.intangible.bonus.BonusType;

import java.util.Calendar;
import java.util.List;

public class StarFactory {

    private static final int STAR_EXISTING_TIME = 10000;

    private final BonusController bonusController;
    private Star currentStar;

    public StarFactory(BonusController bonusController) {
        this.bonusController = bonusController;
    }

    public Star getStar() {
        return currentStar;
    }

    public void update() {
        long currentTime = Calendar.getInstance().getTimeInMillis();

        if (currentStar == null || currentTime - currentStar.getAppearTime() >= STAR_EXISTING_TIME) {
            createStar();
        }
    }

    public void createStar() {
        boolean duplicate;

        do {
            currentStar = new Star();
            duplicate = false;

            for (BonusType type : bonusController.getExcludeBonusType()) {
                if (currentStar.getType() == type) {
                    duplicate = true;
                    break;
                }
            }
        }
        while (duplicate);
    }
}
