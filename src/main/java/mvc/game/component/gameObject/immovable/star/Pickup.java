package main.java.mvc.game.component.gameObject.immovable.star;

import main.java.App;
import main.java.mvc.game.component.gameObject.immovable.ImmovableGameObject;
import main.java.mvc.game.component.intangible.bonus.Bonus;
import main.java.mvc.game.component.intangible.bonus.BonusType;

import java.awt.*;
import java.util.Calendar;

public class Pickup extends ImmovableGameObject {

    private final long appearTime;
    private final String imagePath;
    private final BonusType type;

    public Pickup() {
        super(randomX(), randomY());
        appearTime = Calendar.getInstance().getTimeInMillis();

        type = Bonus.randomType();
        imagePath = Bonus.getImagePath(type);
    }

    public BonusType getType() {
        return type;
    }

    public long getAppearTime() {
        return appearTime;
    }

    public String getImagePath() {
        return imagePath;
    }

    public Rectangle getBound() {
        return new Rectangle(x, y, 50, 50);
    }

    private static int randomX() {
        int width = 600;
        return (int) (Math.random()*width) + (App.WIDTH - width)/2;
    }

    private static int randomY() {
        int height = 600;
        return (int) (Math.random()*height) + (App.HEIGHT - height)/2;
    }
}
