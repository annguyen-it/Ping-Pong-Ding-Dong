package main.java.mvc.game.element.component.gameObject.immovable.pickup;

import main.java.App;
import main.java.mvc.game.element.component.gameObject.immovable.ImmovableGameObject;
import main.java.mvc.game.element.function.intangible.bonus.Bonus;
import main.java.mvc.game.element.function.intangible.bonus.BonusType;

import java.awt.*;
import java.util.Calendar;

public class Pickup extends ImmovableGameObject {

    private static final int APPEAR_AREA_WIDTH = 650;
    private static final int APPEAR_AREA_HEIGHT = 600;
    private static final int PICKUP_SIZE = 50;

    private final long appearTime;
    private final BonusType bonusType;

    public Pickup() {
        super(randomX(), randomY());
        appearTime = Calendar.getInstance().getTimeInMillis();

        bonusType = Bonus.randomType();
    }

    public BonusType getBonusType() {
        return bonusType;
    }

    public long getAppearTime() {
        return appearTime;
    }

    public String getImagePath() {
        return Bonus.getImagePath(bonusType);
    }

    public Rectangle getBound() {
        return new Rectangle(x, y, PICKUP_SIZE, PICKUP_SIZE);
    }

    private static int randomX() {
        return (int) (Math.random()*APPEAR_AREA_WIDTH) + (App.WIDTH - APPEAR_AREA_WIDTH)/2;
    }

    private static int randomY() {
        return (int) (Math.random()*APPEAR_AREA_HEIGHT) + (App.HEIGHT - APPEAR_AREA_HEIGHT)/2;
    }
}
