package v2.model;

import v2.component.Ball;
import v2.component.Star;
import v2.component.StarFactory;
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
    private StarFactory starFactory;

    private final GameSoundPlayer soundPlayer = new GameSoundPlayer();

    public GameModel() {
        soundPlayer.joinGame();
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

    public Star getStar() {
        return starFactory.getStar();
    }

    public void initBoard() {
        leftPaddle = new LeftPaddle();
        rightPaddle = new RightPaddle();

        balls = new ArrayList<>();
        balls.add(new Ball(soundPlayer));

        starFactory = new StarFactory();
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

    public void tryAddNewBall() {
        if (balls.size() == 1) {
            balls.set(0, new Ball(soundPlayer));
        }
    }

    public void updateStar() {
        starFactory.update();
        Star star = starFactory.getStar();

        if (star != null) {
            balls.forEach(ball -> {
                if (star.isCollision(ball)) {
                    pickupStar(star);
                    starFactory.deleteStar();
                }
            });
        }

        //        if (star.isCollision(ball)) {
        //            pickupStar();
        //            Star.checkStar = false;
        //            ball.starCollide();
        //            star = new Star();
        //        }
    }

    public void pickupStar(Star star) {
        for (Ball ball : balls) {
            switch (star.getType()) {
                case bigBall:
                    ball.upsizeBall();
                    break;

                case multiBall:
                    break;

                case speedUp:
                    break;

                case speedDown:
                    break;

                default:
            }
        }
    }
}
