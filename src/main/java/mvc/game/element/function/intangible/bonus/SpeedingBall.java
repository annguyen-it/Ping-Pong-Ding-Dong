package main.java.mvc.game.element.function.intangible.bonus;

import main.java.mvc.game.element.component.gameObject.movable.ball.Ball;

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
