package main.java.mvc.game;

import main.java.mvc.game.element.function.intangible.GameSide;
import main.java.mvc.game.element.function.intangible.GameSide.Side;
import main.java.mvc.game.element.function.intangible.bonus.Bonus;
import main.java.mvc.game.element.component.gameObject.immovable.pickup.Pickup;
import main.java.mvc.game.element.component.gameObject.movable.ball.Ball;
import main.java.mvc.game.element.component.gameObject.movable.paddle.*;
import main.java.mvc.game.element.component.helper.factory.BallFactory;
import main.java.mvc.game.element.component.helper.controller.BonusController;
import main.java.mvc.game.element.component.helper.factory.PickupFactory;
import main.java.mvc.common.Model;
import main.java.mvc.game.sound.GameSoundPlayer;

import java.util.List;

public class GameModel extends Model {

    //#region Properties

    private GameController controller;
    private final GameSoundPlayer soundPlayer = new GameSoundPlayer();

    private LeftPaddle leftPaddle = new LeftPaddle();
    private RightPaddle rightPaddle = new RightPaddle();

    private BonusController bonusController = new BonusController(this);
    private PickupFactory pickupFactory = new PickupFactory(bonusController);
    private BallFactory ballFactory = new BallFactory(soundPlayer);

    //#endregion

    //#regionConstructor

    public GameModel() {
        soundPlayer.joinGame();
        soundPlayer.playBackgroundAudio();
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

    public List<Ball> getBallList() {
        return ballFactory.getBallList();
    }

    public Pickup getPickup() {
        return pickupFactory.getPickup();
    }

    public List<Bonus> getBonusList() {
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

        ballFactory.clear();

        bonusController = new BonusController(this);
        ballFactory = new BallFactory(soundPlayer);
        pickupFactory = new PickupFactory(bonusController);
    }

    //#region Paddle

    private void updatePaddles() {
        leftPaddle.tryMove();
        rightPaddle.tryMove();

        for (Ball ball : ballFactory.getBallList()) {
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
        List<Ball> balls = ballFactory.getBallList();

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
                createNewBall(GameSide.getOpposite(loseSide));
            }
            else if (controller.isStarted()){
                controller.getView().repaint();
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
        pickupFactory.update();
        Pickup pickup = pickupFactory.getPickup();

        if (pickup != null) {
            for (Ball ball : ballFactory.getBallList()) {
                if (ball.willCollide(pickup)) {
                    ball.collide(pickup);
                    bonusController.receive(pickup.getBonusType(), ball.getLastTouchSide(), ball);
                    pickupFactory.create();
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
