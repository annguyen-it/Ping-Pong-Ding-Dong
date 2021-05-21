package main.java.mvc.game.elements.function.intangible.bonus;

import main.java.mvc.game.elements.component.gameObject.movable.ball.Ball;

/**
 * Class {@code BigBall} is a implementation of Bonus. It makes {@code Ball}(s) in playground become bigger
 * <p>
 * {@code BigBall} has time limit and can appear again when it activates.
 * </p>
 */
public class BigBall extends Bonus implements HasTimeLimit, CanAppearWhenActivated {

    public BigBall() {
        super(BonusType.bigBall);
    }

    @Override
    public void activate() {
        for (Ball ball : gameModel.getBallList()) {
            ball.sizeUp();
        }
    }

    @Override
    public void deactivate() {
        for (Ball ball : gameModel.getBallList()) {
            ball.returnInitialSize();
        }
    }
}
