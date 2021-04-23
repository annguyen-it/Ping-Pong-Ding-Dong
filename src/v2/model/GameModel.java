package v2.model;

import v2.component.Ball;
import v2.component.Star;
import v2.component.paddle.LeftPaddle;
import v2.component.paddle.Paddle;
import v2.component.paddle.RightPaddle;
import v2.sound.GameSoundPlayer;

public class GameModel implements Model {

    private Paddle rightPaddle;
    private Paddle leftPaddle;
    private Ball ball;
    private Star star;

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
        star = new Star();
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
            ball = new Ball(soundPlayer);
        }
        else if (losePaddle == rightPaddle) {
            leftPaddle.increaseScore();
            ball = new Ball(soundPlayer, Ball.InitialDirection.left);
        }
    }

    public void updateStar(){
        if(star.isCollision(ball)){
            pickupStar();
            star.checkStar = false;
            ball.starCollide();
            star = new Star();
        }
    }

    public void pickupStar(){
        switch (star.getImage()){
            case "resources/img/starBlue.png":
                ball.upsizeBall();
            case "resources/img/starGreen.png":
                ball.upsizeBall();
            case "resources/img/starPink.png":
                ball.upsizeBall();
            case "resources/img/starRed.png":
                ball.upsizeBall();
            case "resources/img/starYellow.png":
                ball.upsizeBall();
        }
    }
}
