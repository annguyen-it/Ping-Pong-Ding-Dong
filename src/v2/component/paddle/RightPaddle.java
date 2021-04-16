package v2.component.paddle;

import v2.Game;
import v2.board.Score;
import v2.component.Ball;

public class RightPaddle extends Paddle {

    private static final int INITIAL_RIGHT_PADDLE_X = Game.WIDTH - PADDLE_WIDTH - DISTANCE_TO_CROSS - 15;

    public RightPaddle() {
        super(INITIAL_RIGHT_PADDLE_X, INITIAL_PADDLE_Y);
    }

    @Override
    public boolean isTouched(Ball ball) {
        int ballX = ball.getX();
        int ballY = ball.getY();
        int ballDx = ball.getDx();

        return x <= ballX + Ball.BALL_SIZE && ballX + Ball.BALL_SIZE < x + PADDLE_WIDTH &&
                y <= ballY + Ball.BALL_SIZE && ballY <= y + PADDLE_HEIGHT &&
                ballDx > 0;

    }
}