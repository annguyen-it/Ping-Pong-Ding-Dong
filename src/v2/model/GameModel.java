package v2.model;

import v2.board.Side;
import v2.component.gameObject.movable.ball.Ball;
import v2.component.gameObject.immovable.Star;
import v2.component.helper.factory.StarFactory;
import v2.component.gameObject.movable.paddle.LeftPaddle;
import v2.component.gameObject.movable.paddle.Paddle;
import v2.component.gameObject.movable.paddle.RightPaddle;
import v2.utils.sound.GameSoundPlayer;

import java.util.ArrayList;
import java.util.List;

public class GameModel implements Model {

    private RightPaddle rightPaddle;
    private LeftPaddle leftPaddle;
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

        for (Ball ball : balls) {
            if (ball.willCollideLeftPaddle(leftPaddle)) {
                ball.collide(leftPaddle);
            }
            else if (ball.willCollideRightPaddle(rightPaddle)) {
                ball.collide(rightPaddle);
            }
        }
    }

    public void updateBall() {
        for (Ball ball : balls){
            ball.tryMove();

            Side loseSide = ball.isOutTheBoard();

            if (loseSide == Side.left) {
                rightPaddle.increaseScore();
                tryAddNewBall(Ball.HorizontalDirection.right);
            }
            else if (loseSide == Side.right) {
                leftPaddle.increaseScore();
                tryAddNewBall(Ball.HorizontalDirection.left);
            }
        }
    }

    public void tryAddNewBall(Ball.HorizontalDirection ballDirection) {
        if (balls.size() == 1) {
            balls.set(0, new Ball(soundPlayer, ballDirection));
        }
    }

    public void updateStar() {
        starFactory.update();
        Star star = starFactory.getStar();

        if (star != null) {
            for (Ball ball : balls) {
                if (star.isCollision(ball)) {
                    pickupStar(star);
                    starFactory.deleteStar();
                }
            }
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
                    ball.sizeUp();
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
