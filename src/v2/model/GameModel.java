package v2.model;

import v2.board.GameSide;
import v2.board.GameSide.Side;
import v2.component.gameObject.immovable.bonus.Bonus;
import v2.component.gameObject.immovable.star.Star;
import v2.component.gameObject.movable.ball.Ball;
import v2.component.gameObject.movable.paddle.*;
import v2.component.helper.factory.BallFactory;
import v2.component.helper.factory.BonusFactory;
import v2.component.helper.factory.StarFactory;
import v2.controller.GameController;
import v2.utils.sound.GameSoundPlayer;

import java.util.List;

public class GameModel implements Model {

    GameController controller;

    private final GameSoundPlayer soundPlayer = new GameSoundPlayer();

    private final LeftPaddle leftPaddle = new LeftPaddle();
    private final RightPaddle rightPaddle = new RightPaddle();

    private final BallFactory ballFactory = new BallFactory(soundPlayer);
    private final StarFactory starFactory = new StarFactory();
    private final BonusFactory bonusFactory = new BonusFactory();

    public GameModel() {
        soundPlayer.joinGame();
    }

    public GameSoundPlayer getSoundPlayer() {
        return soundPlayer;
    }

    public void setController(GameController controller) {
        this.controller = controller;
    }

    public LeftPaddle getLeftPaddle() {
        return leftPaddle;
    }

    public RightPaddle getRightPaddle() {
        return rightPaddle;
    }

    public List<Ball> getBalls() {
        return ballFactory.getBalls();
    }

    public Star getStar() {
        return starFactory.getStar();
    }

    public List<Bonus> getBonus() {
        return bonusFactory.getBonusList();
    }

    public void updatePaddles() {
        leftPaddle.tryMove();
        rightPaddle.tryMove();

        for (Ball ball : ballFactory.getBalls()) {
            if (ball.willCollide(leftPaddle)) {
                ball.collide(leftPaddle);
            }
            else if (ball.willCollide(rightPaddle)) {
                ball.collide(rightPaddle);
            }
        }
    }

    public void updateBall() {
        for (Ball ball : ballFactory.getBalls()) {
            ball.tryMove();

            Side loseSide = ball.isOutTheBoard();

            if (loseSide != Side.unknown) {
                lostBall(loseSide);

            }
        }
    }

    public void lostBall(Side loseSide) {
        if (loseSide == Side.left) {
            rightPaddle.increaseScore();
        }
        else {
            leftPaddle.increaseScore();
        }

        if (ballFactory.hasOnlyOne()) {
            removeAllBonus();
            addNewBall(GameSide.opposite(loseSide));
        }
    }

    public void removeAllBonus(){
        bonusFactory.clear();
    }

    public void addNewBall(Side ballDirection) {
        if (willContinueGame()) {
            ballFactory.createBall(ballDirection);
        }
        else {
            controller.over();
        }
    }

    public boolean willContinueGame() {
        int leftScore = leftPaddle.getScore();
        int rightScore = rightPaddle.getScore();

        return leftScore == rightScore || Math.max(leftScore, rightScore) < 5;
    }

    public void updateStar() {
        starFactory.update();
        Star star = starFactory.getStar();

        if (star != null) {
            for (Ball ball : ballFactory.getBalls()) {
                if (ball.willCollide(star)) {
                    ball.collide(star);
                    starFactory.createStar();
                    bonusFactory.createBonus(star.getType());
                }
            }
        }
    }

    public void updateBonus() {
        bonusFactory.update();
    }
}
