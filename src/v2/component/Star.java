package v2.component;

import v2.component.paddle.ImmovableGameObject;

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
        super(0, 0);

        x = (int) (Math.random()*800) + 200;
        y = (int) (Math.random()*600) + 100;

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

    private Rectangle getBallBound(Ball ball) {
        return new Rectangle(ball.getX(), ball.getY(), ball.getSize(), ball.getSize());
    }

    private Rectangle getStarBound() {
        return new Rectangle(x, y, 50, 50);
    }

    public boolean isCollision(Ball ball) {
        return getBallBound(ball).intersects(getStarBound());
    }
}
