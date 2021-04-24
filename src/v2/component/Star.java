package v2.component;

import v2.component.paddle.ImmovableGameObject;

import java.awt.*;
import java.util.Calendar;

public class Star extends ImmovableGameObject {

    private final String imagePath;
    private final long appearTime;

    private static final String[] STAR_IMAGE = new String[]{
            "resources/img/starBlue.png",
            "resources/img/starGreen.png",
            "resources/img/starPink.png",
            "resources/img/starRed.png",
            "resources/img/starYellow.png"
    };

    public Star() {
        super(0, 0);

        x = (int) (Math.random()*800) + 200;
        y = (int) (Math.random()*600) + 100;

        appearTime = Calendar.getInstance().getTimeInMillis();

        imagePath = STAR_IMAGE[(int) (Math.random()*STAR_IMAGE.length)];
    }

    public long getAppearTime() {
        return appearTime;
    }

    public String getImagePath() {
        return imagePath;
    }

    private Rectangle getBallBound(Ball ball) {
        return new Rectangle(ball.getX(), ball.getY(), ball.getBallSize(), ball.getBallSize());
    }

    private Rectangle getStarBound() {
        return new Rectangle(x, y, 50, 50);
    }

    public boolean isCollision(Ball ball) {
        return getBallBound(ball).intersects(getStarBound());
    }
}
