package v2.model;

import v2.board.GameSide;
import v2.board.GameSide.Side;
import v2.board.Side;
import v2.component.gameObject.immovable.bonus.Bonus;
import v2.component.gameObject.immovable.star.Star;
import v2.component.gameObject.movable.ball.Ball;
import v2.component.gameObject.movable.paddle.*;
import v2.component.helper.factory.BonusFactory;
import v2.component.helper.factory.StarFactory;
import v2.controller.GameController;
import v2.utils.sound.GameSoundPlayer;

import java.util.ArrayList;
import java.util.List;

public class GameModel implements Model {
    GameController controller;

    private RightPaddle rightPaddle;
    private LeftPaddle leftPaddle;
    private List<Ball> balls;
    private StarFactory starFactory;
    private BonusFactory bonusFactory;

    private final GameSoundPlayer soundPlayer = new GameSoundPlayer();

    public GameModel() {
        soundPlayer.joinGame();
        initBoard();
    }

    public void setController(GameController controller) {
        this.controller = controller;
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

    public List<Bonus> getBonus() {
        return bonusFactory.getBonusList();
    }

    public void initBoard() {
        leftPaddle = new LeftPaddle();
        rightPaddle = new RightPaddle();

        balls = new ArrayList<>();
        balls.add(new Ball(soundPlayer));

        starFactory = new StarFactory();
        bonusFactory = new BonusFactory();
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
                controller.over();
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
                    bonusFactory.createBonus(star.getType());
                }
            }
        }
    }

    public void updateBonus(){
        bonusFactory.update();
    }
}
