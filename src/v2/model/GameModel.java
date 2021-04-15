package v2.model;

import v2.component.Ball;
import v2.controller.Controller;
import v2.component.paddle.LeftPaddle;
import v2.component.paddle.Paddle;
import v2.component.paddle.RightPaddle;


public class GameModel extends Model {

    private Paddle rightPaddle;
    private Paddle leftPaddle;
    private Ball ball;
    public static boolean pause = false;

    public GameModel(Controller controller) {
        super(controller);
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

        ball = new Ball();
    }

    public void updatePaddles() {
        leftPaddle.move();
        rightPaddle.move();

        if (leftPaddle.isTouched(ball) || rightPaddle.isTouched(ball)) {
            ball.paddleCollide();
        }
    }


    public void updateBall() {
        ball.move();
        Paddle losePaddle = ball.isOutTheBoard(leftPaddle, rightPaddle);

        if (losePaddle == leftPaddle) {
            rightPaddle.increaseScore();
            ball = new Ball();
        }
        else if (losePaddle == rightPaddle) {
            leftPaddle.increaseScore();
            ball = new Ball();
            ball.paddleCollide();
        }
    }


}
