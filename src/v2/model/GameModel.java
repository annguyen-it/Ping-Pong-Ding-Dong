package v2.model;

import v2.board.GameSide;
import v2.board.GameSide.Side;
import v2.component.gameObject.immovable.star.Star;
import v2.component.gameObject.movable.ball.Ball;
import v2.component.gameObject.movable.paddle.*;
import v2.component.helper.factory.StarFactory;
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
            lostBall(loseSide);
        }
    }

    public void lostBall(Side loseSide){
        if (loseSide != Side.unknown){
            if (loseSide == Side.left){
                rightPaddle.increaseScore();
            }
            else {
                leftPaddle.increaseScore();
            }

            tryAddNewBall(GameSide.opposite(loseSide));
        }
    }

    public void tryAddNewBall(Side ballDirection) {
        if (balls.size() == 1) {
            if (willContinueGame()){
                balls.set(0, new Ball(soundPlayer, ballDirection));
            }
            else {
                System.out.println("Stop now");
            }
        }
    }

    public boolean willContinueGame(){
        int leftScore = leftPaddle.getScore();
        int rightScore = rightPaddle.getScore();

        return leftScore == rightScore || Math.max(leftScore, rightScore) < 5;
    }

    public void updateStar() {
        starFactory.update();
        Star star = starFactory.getStar();

        if (star != null) {
            for (Ball ball : balls) {
                if (ball.willCollide(star)) {
                    ball.collide(star);
                    starFactory.createStar();
                }
            }
        }
    }
}
