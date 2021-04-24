package v2.component;

import java.awt.*;

public class Star {

    public static boolean checkStar = false;
    private int starX;
    private int starY;
    private String imagePath;

    private static final String[] STAR_IMAGE = new String[]{
            "resources/img/starBlue.png",
            "resources/img/starGreen.png",
            "resources/img/starPink.png",
            "resources/img/starRed.png",
            "resources/img/starYellow.png"
    };

    public Star() {
        generateStar();
    }

    public int getStarX() {
        return starX;
    }

    public int getStarY() {
        return starY;
    }

    public String getImagePath() {
        return imagePath;
    }

    private void generateStar() {
        if (!checkStar) {
            String imageStar = STAR_IMAGE[(int) (Math.random()*STAR_IMAGE.length)];
            int stX = (int) (Math.random()*800) + 200;
            int stY = (int) (Math.random()*600) + 100;
            checkStar = true;
            imagePath = imageStar;
            starX = stX;
            starY = stY;
        }
    }

    private Rectangle getBallBound(Ball ball) {
        return new Rectangle(ball.getX(), ball.getY(), Ball.getBallSize(), Ball.getBallSize());
    }

    private Rectangle getStarBound() {
        return new Rectangle(starX, starY, 50, 50);
    }

    public boolean isCollision(Ball ball) {
        return getBallBound(ball).intersects(getStarBound());
    }
}
