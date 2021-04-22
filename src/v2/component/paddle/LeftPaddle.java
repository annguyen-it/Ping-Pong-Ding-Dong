package v2.component.paddle;

import v2.component.Ball;

public class LeftPaddle extends Paddle {

    public static final int INITIAL_LEFT_PADDLE_X = DISTANCE_TO_CROSS;

    public LeftPaddle() {
        super(INITIAL_LEFT_PADDLE_X, INITIAL_PADDLE_Y);
    }

    @Override
    public boolean isTouched(Ball ball) {
        int ballX = ball.getX();
        int ballY = ball.getY();
        int ballDx = ball.getDx();

        return ballDx < 0 &&
                x <= ballX && ballX <= x + PADDLE_WIDTH &&
                y <= ballY + Ball.BALL_SIZE && ballY <= y + PADDLE_HEIGHT;
    }
}
