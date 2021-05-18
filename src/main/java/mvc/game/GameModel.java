package main.java.mvc.game;

import main.java.mvc.game.board.GameSide;
import main.java.mvc.game.board.GameSide.Side;
import main.java.mvc.game.component.intangible.bonus.Bonus;
import main.java.mvc.game.component.gameObject.immovable.star.Pickup;
import main.java.mvc.game.component.gameObject.movable.ball.Ball;
import main.java.mvc.game.component.gameObject.movable.paddle.*;
import main.java.mvc.game.component.helper.factory.BallFactory;
import main.java.mvc.game.component.helper.controller.BonusController;
import main.java.mvc.game.component.helper.factory.StarFactory;
import main.java.mvc.common.Model;
import main.java.utils.sound.GameSoundPlayer;

import java.util.List;

public class GameModel implements Model {

    //#region Properties

    private GameController controller;

    private GameSoundPlayer soundPlayer = new GameSoundPlayer();

    private LeftPaddle leftPaddle = new LeftPaddle();
    private RightPaddle rightPaddle = new RightPaddle();

    private BonusController bonusController = new BonusController(this);
    private BallFactory ballFactory = new BallFactory(soundPlayer);
    private StarFactory starFactory = new StarFactory(bonusController);

    //#endregion

    //#regionConstructor

    public GameModel() {
        soundPlayer.joinGame();
    }

    //#endregion

    //#region Getter

    public GameSoundPlayer getSoundPlayer() {
        return soundPlayer;
    }

    public LeftPaddle getLeftPaddle() {
        return leftPaddle;
    }

    public RightPaddle getRightPaddle() {
        return rightPaddle;
    }

    public Paddle getPaddle(Side side) {
        if (side == Side.left) {
            return leftPaddle;
        }

        if (side == Side.right) {
            return rightPaddle;
        }

        return null;
    }

    public List<Ball> getBalls() {
        return ballFactory.getBalls();
    }

    public Pickup getStar() {
        return starFactory.getPickup();
    }

    public List<Bonus> getBonus() {
        return bonusController.getBonusList();
    }

    //#endregion

    //#region Setter

    public void setController(GameController controller) {
        this.controller = controller;
    }

    //#endregion

    public void update() {
        updatePaddles();
        updateBall();
        updatePickup();
        updateBonus();
    }

    public void reset() {
        leftPaddle = new LeftPaddle();
        rightPaddle = new RightPaddle();

        bonusController.clear();
        ballFactory.clear();

        bonusController = new BonusController(this);
        ballFactory = new BallFactory(soundPlayer);
        starFactory = new StarFactory(bonusController);

        soundPlayer = new GameSoundPlayer();
    }

    //#region Paddle

    private void updatePaddles() {
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

    //#endregion

    //#region Ball

    private void updateBall() {
        List<Ball> balls = ballFactory.getBalls();

        for (int i = 0; i < balls.size(); ) {
            balls.get(i).tryMove();

            Side loseSide = balls.get(i).isOutTheBoard();

            if (loseSide != Side.unknown) {
                lostBall(loseSide);

                if (!ballFactory.hasOnlyOne()) {
                    balls.remove(i);
                }
            }
            else {
                i++;
            }
        }
    }

    private void lostBall(Side loseSide) {
        if (loseSide == Side.left) {
            rightPaddle.increaseScore();
        }
        else {
            leftPaddle.increaseScore();
        }

        if (ballFactory.hasOnlyOne()) {
            removeActivatedBonus();

            if (shouldContinue()) {
                createNewBall(GameSide.opposite(loseSide));
            }
            else if (controller.isStarted()){
                controller.over();
            }
        }
    }

    private boolean shouldContinue() {
        int leftScore = leftPaddle.getScore();
        int rightScore = rightPaddle.getScore();

        return leftScore == rightScore || Math.max(leftScore, rightScore) < 5;
    }

    private void removeActivatedBonus() {
        bonusController.clear();
    }

    private void createNewBall(Side ballDirection) {
        ballFactory.create(ballDirection);
    }

    public void addBall(Ball ball) {
        ballFactory.add(ball);
    }

    //#endregion

    //#region Pickup

    private void updatePickup() {
        starFactory.update();
        Pickup pickup = starFactory.getPickup();

        if (pickup != null) {
            for (Ball ball : ballFactory.getBalls()) {
                if (ball.willCollide(pickup)) {
                    ball.collide(pickup);
                    starFactory.createStar();
                    bonusController.receive(pickup.getType(), ball.getLastTouch(), ball);
                    break;
                }
            }
        }
    }

    //#endregion

    //#region Bonus

    private void updateBonus() {
        bonusController.update();
    }

    //#endregion
}
