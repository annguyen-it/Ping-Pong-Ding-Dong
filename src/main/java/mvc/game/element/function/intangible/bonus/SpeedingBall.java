package main.java.mvc.game.element.function.intangible.bonus;

import main.java.mvc.game.element.component.gameObject.movable.ball.Ball;

/**
 * Class {@code SpeedingBall} is a implementation of Bonus. <br>
 * It makes {@code Ball}(s) move faster and temporarily increase their speed limit
 * <p>
 * {@code SpeedingBall} has time limit and its {@code Pickup} can appears when it had activated.
 * </p>
 */
public class SpeedingBall extends Bonus implements HasTimeLimit, CanAppearWhenActivated {

    public SpeedingBall() {
        super(BonusType.speedingBall);
    }

    @Override
    public void activate() {
        for (Ball ball : gameModel.getBallList()) {
            ball.speedUp();
        }
    }

    @Override
    public void deactivate() {
        for (Ball ball : gameModel.getBallList()) {
            ball.returnInitialSpeed();
        }
    }
}
