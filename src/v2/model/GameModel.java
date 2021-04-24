package v2.model;

import v2.component.Ball;
import v2.component.paddle.LeftPaddle;
import v2.component.paddle.Paddle;
import v2.component.paddle.RightPaddle;
import v2.sound.GameSoundPlayer;

import java.util.ArrayList;
import java.util.List;

public class GameModel implements Model {

    private Paddle rightPaddle;
    private Paddle leftPaddle;
    private List<Ball> balls;
    private Ball ball;
    private Star star;

    private final GameSoundPlayer soundPlayer = new GameSoundPlayer();

    public GameModel() {
        initBoard();
    }

    public Paddle getLeftPaddle() {
        return leftPaddle;
    }

    public Paddle getRightPaddle() {
        return rightPaddle;
    }

    public List<Ball> getBalls() {
        return balls;
    }

    public void initBoard() {
        leftPaddle = new LeftPaddle();
        rightPaddle = new RightPaddle();

        balls = new ArrayList<>();
        balls.add(new Ball(soundPlayer));
        ball = new Ball(soundPlayer);
        star = new Star();
    }

    public void updatePaddles() {
        leftPaddle.tryMove();
        rightPaddle.tryMove();

        balls.forEach(ball -> {
            if (ball.willCollideLeftPaddle(leftPaddle)) {
                ball.collide(leftPaddle);
            }
            else if (ball.willCollideRightPaddle(rightPaddle)) {
                ball.collide(rightPaddle);
            }
        });
    }

    public void updateBall() {
        balls.forEach(ball -> {
            ball.tryMove();

            Paddle losePaddle = ball.isOutTheBoard(leftPaddle, rightPaddle);

            if (losePaddle == leftPaddle) {
                rightPaddle.increaseScore();
                tryAddNewBall();
            }
            else if (losePaddle == rightPaddle) {
                leftPaddle.increaseScore();
                tryAddNewBall();
            }
        });
    }

    public void tryAddNewBall(){
        if (balls.size() == 1){
            balls.set(1, new Ball(soundPlayer));
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
