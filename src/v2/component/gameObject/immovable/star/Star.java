package v2.component.gameObject.immovable.star;

import v2.Game;
import v2.component.gameObject.immovable.ImmovableGameObject;
import v2.component.intangible.bonus.Bonus;
import v2.component.intangible.bonus.BonusType;

import java.awt.*;
import java.util.Calendar;

public class Star extends ImmovableGameObject {

    private final long appearTime;
    private final String imagePath;
    private final BonusType type;

    public Star() {
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
        return (int) (Math.random()*width) + (Game.WIDTH - width)/2;
    }

    private static int randomY() {
        int height = 600;
        return (int) (Math.random()*height) + (Game.HEIGHT - height)/2;
    }
}
