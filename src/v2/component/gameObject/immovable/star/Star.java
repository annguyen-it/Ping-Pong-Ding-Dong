package v2.component.gameObject.immovable.star;

import v2.Game;
import v2.component.gameObject.immovable.ImmovableGameObject;

import java.awt.*;
import java.util.Arrays;
import java.util.Calendar;

public class Star extends ImmovableGameObject {

    private final long appearTime;
    private final String imagePath;
    private final StarType type;

    private static final String[] BONUS_IMAGE = new String[]{
            "resources/img/starBlue.png",
            "resources/img/starGreen.png",
            "resources/img/starPink.png",
            "resources/img/starRed.png",
            //            "resources/img/starYellow.png"
    };

    public Star() {
        super(randomX(), randomY());

        appearTime = Calendar.getInstance().getTimeInMillis();

        int randomNumber = (int) (Math.random()*BONUS_IMAGE.length);

        imagePath = BONUS_IMAGE[randomNumber];
        type = Arrays.asList(StarType.values()).get(randomNumber);
    }

    public StarType getType() {
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
