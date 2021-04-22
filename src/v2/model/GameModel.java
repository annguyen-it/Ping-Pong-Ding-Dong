package v2.model;

import v2.component.Ball;
import v2.component.paddle.LeftPaddle;
import v2.component.paddle.Paddle;
import v2.component.paddle.RightPaddle;
import v2.sound.GameSoundPlayer;

public class GameModel implements Model {

    private Paddle rightPaddle;
    private Paddle leftPaddle;
    private Ball ball;

    private final GameSoundPlayer soundPlayer = new GameSoundPlayer();

    public static boolean pause = false;

    public GameModel() {
        initBoard();
    }

    public Paddle getLeftPaddle() {
        return leftPaddle;
    }

    public Paddle getRightPaddle() {
        return rightPaddle;
    }

    public Ball getBall() {
        return ball;
    }

    public void initBoard() {
        leftPaddle = new LeftPaddle();
        rightPaddle = new RightPaddle();

        ball = new Ball(soundPlayer);
    }

    public void updatePaddles() {
        leftPaddle.move();
        rightPaddle.move();

        if (leftPaddle.isTouched(ball)){
            ball.paddleCollide(leftPaddle);
        }
        else if (rightPaddle.isTouched(ball)) {
            ball.paddleCollide(rightPaddle);
        }
    }

    public void updateBall() {
        ball.move();
        Paddle losePaddle = ball.isOutTheBoard(leftPaddle, rightPaddle);

        if (losePaddle == leftPaddle) {
            rightPaddle.increaseScore();
            ball = new Ball(soundPlayer);
        }
        else if (losePaddle == rightPaddle) {
            leftPaddle.increaseScore();
            ball = new Ball(soundPlayer, Ball.InitialDirection.left);
        }
    }
}
