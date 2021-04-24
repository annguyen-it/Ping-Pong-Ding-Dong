package v2.component;

import java.awt.*;

public class Star {

    public static boolean checkStar = false;
    private static int starX;
    private static int starY;
    private static String image;


    private static final String[] STAR_IMAGE = new String[]{"resources/img/starBlue.png","resources/img/starGreen.png","resources/img/starPink.png","resources/img/starRed.png","resources/img/starYellow.png"};

    public Star(){
        generateStar();
    }

    public static int getstarX(){
        return starX;
    }

    public static int getStarY(){
        return starY;
    }
    public static String getImage(){
        return image;
    }

    private void generateStar(){
        if(checkStar == false){
            String imageStar = STAR_IMAGE[(int) (Math.random() * STAR_IMAGE.length)];
            int stX = (int) (Math.random() * 800) + 200;
            int stY = (int) (Math.random() * 600) + 100;
            checkStar = true;
            image = imageStar;
            starX = stX;
            starY = stY;
        }

    }

    private Rectangle getBallBound(Ball ball){
        return new Rectangle(ball.getX(),ball.getY(),Ball.getBallSize(),Ball.getBallSize());
    }

    private Rectangle getStarBound(){
        return new Rectangle(starX,starY,50,50);
    }

    public boolean isCollision(Ball ball) {
        return getBallBound(ball).intersects(getStarBound());
    }


}
