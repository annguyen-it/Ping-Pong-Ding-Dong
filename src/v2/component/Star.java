package v2.component;

import v2.component.paddle.ImmovableGameObject;

import java.awt.*;
import java.util.Calendar;

public class Star extends ImmovableGameObject {

    private final String imagePath;
    private final long appearTime;

    enum STARIMAGE{
        BLUE("resources/img/starBlue.png") ,
        GREEN("resources/img/starGreen.png"),
        PINK("resources/img/starPink.png") ,
        RED("resources/img/starRed.png") ,
        YELLOW("resources/img/starYellow.png");

        private String  starFile;

        private STARIMAGE(String starFile){
            this.starFile = starFile;
        }

        public String getStarFile() {
            return starFile;
        }
    }

    public Star() {
        super(0, 0);

        x = (int) (Math.random()*800) + 200;
        y = (int) (Math.random()*600) + 100;

        appearTime = Calendar.getInstance().getTimeInMillis();

        STARIMAGE[] starimage = STARIMAGE.values();
        imagePath = starimage[(int) (Math.random()*starimage.length)].getStarFile();
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
