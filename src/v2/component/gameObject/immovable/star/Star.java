package v2.component.gameObject.immovable.star;

import v2.component.gameObject.immovable.ImmovableGameObject;

import java.awt.*;
import java.util.Arrays;
import java.util.Calendar;

public class Star extends ImmovableGameObject {

    private final long appearTime;
    private final String imagePath;
    private final StarType type;

    private static final String[] STAR_IMAGE = new String[]{
            "resources/img/starBlue.png",
            "resources/img/starGreen.png",
            "resources/img/starPink.png",
            "resources/img/starRed.png",
//            "resources/img/starYellow.png"
    };

    public enum StarType {
        bigBall,
        multiBall,
        speedUp,
        speedDown,
//        bigBall,
    }

    public Star() {
        super(randomX(), randomY());

        appearTime = Calendar.getInstance().getTimeInMillis();

        int randomNumber = (int) (Math.random()*STAR_IMAGE.length);

        imagePath = STAR_IMAGE[randomNumber];
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

    private static int randomX(){
        return (int) (Math.random()*800) + 200;
    }

    private static int randomY(){
        return (int) (Math.random()*800) + 200;
    }
}
