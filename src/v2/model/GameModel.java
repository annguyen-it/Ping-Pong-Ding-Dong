package v2.model;

import v2.board.GameSide;
import v2.board.GameSide.Side;
import v2.component.intangible.bonus.Bonus;
import v2.component.gameObject.immovable.star.Star;
import v2.component.gameObject.movable.ball.Ball;
import v2.component.gameObject.movable.paddle.*;
import v2.component.helper.factory.BallFactory;
import v2.component.helper.controller.BonusController;
import v2.component.helper.factory.StarFactory;
import v2.controller.GameController;
import v2.utils.sound.GameSoundPlayer;

import java.util.List;

public class GameModel implements Model {

    //#region Properties

    GameController controller;

    private final GameSoundPlayer soundPlayer = new GameSoundPlayer();

    private final LeftPaddle leftPaddle = new LeftPaddle();
    private final RightPaddle rightPaddle = new RightPaddle();

    private final BonusController bonusController = new BonusController(this);
    private final BallFactory ballFactory = new BallFactory(soundPlayer);
    private final StarFactory starFactory = new StarFactory(bonusController);

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

    public Star getStar() {
        return starFactory.getStar();
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
        updateStar();
        updateBonus();
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

                if (!ballFactory.hasOnlyOne()){
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
            else {
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

    //#region Star

    private void updateStar() {
        starFactory.update();
        Star star = starFactory.getStar();

        if (star != null) {
            for (Ball ball : ballFactory.getBalls()) {
                if (ball.willCollide(star)) {
                    ball.collide(star);
                    starFactory.createStar();
                    bonusController.receive(star.getType(), ball.getLastTouch(), ball);
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
