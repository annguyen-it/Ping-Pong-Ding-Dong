package v2.component;

import v2.Game;
import v2.component.paddle.Paddle;

public class Ball extends GameObject {
    private static final int INITIAL_BALL_X = 588;
    private static final int INITIAL_BALL_Y = 388;
    public static final int BALL_SIZE = 24;

    public Ball() {
        super(INITIAL_BALL_X, INITIAL_BALL_Y);
        dx = 3;
        dy = 8;
    }

    @Override
    public void move() {
        int nextPosX = x + dx;
        int nextPosY = y + dy;

        x = nextPosX;
        if (0 <= nextPosY && nextPosY + BALL_SIZE + 40 <= Game.HEIGHT) {
            y += dy;
        }
        else {
            wallCollide();
        }
    }

    public void wallCollide(){
        dy *= -1;
    }

    public void paddleCollide(){
        dx *= -1;
    }

    public Paddle isOutTheBoard(Paddle paddleLeft, Paddle paddleRight){
        if (x < 0) return paddleLeft;
        if (x + BALL_SIZE > Game.WIDTH) return paddleRight;
        return null;
    }
}