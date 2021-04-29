package v2.model;

import v2.board.Side;
import v2.component.gameObject.immovable.bonus.Bonus;
import v2.component.gameObject.immovable.star.Star;
import v2.component.gameObject.movable.ball.Ball;
import v2.component.gameObject.movable.paddle.*;
import v2.component.helper.factory.BonusFactory;
import v2.component.helper.factory.StarFactory;
import v2.utils.sound.GameSoundPlayer;

import java.util.ArrayList;
import java.util.List;

public class GameModel implements Model {

    private RightPaddle rightPaddle;
    private LeftPaddle leftPaddle;
    private List<Ball> balls;
    private StarFactory starFactory;
    private BonusFactory bonusFactory;
    private List<Bonus> listBonus;
//    public static int checkCollideStar=0;
//
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

    public List<Bonus> getListBonus(){return listBonus;}

    public void initBoard() {
        leftPaddle = new LeftPaddle();
        rightPaddle = new RightPaddle();

        balls = new ArrayList<>();
        balls.add(new Ball(soundPlayer));

        starFactory = new StarFactory();
        bonusFactory = new BonusFactory();

        listBonus = bonusFactory.getlistBonus();
    }

    public void updatePaddles() {
        leftPaddle.tryMove();
        rightPaddle.tryMove();

        for (Ball ball : balls) {
            if (ball.willCollide(leftPaddle)) {
                ball.collide(leftPaddle);
            }
            else if (ball.willCollide(rightPaddle)) {
                ball.collide(rightPaddle);
            }
        }
    }

    public void updateBall() {
        for (Ball ball : balls) {
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

    public void updateStar() throws InterruptedException {
        starFactory.update();
        Star star = starFactory.getStar();

        if (star != null) {
            for (Ball ball : balls) {
                if (ball.willCollide(star)) {

                    ball.collide(star);
                    starFactory.createStar();
                    bonusFactory.update();

                }
            }
        }
    }


}
