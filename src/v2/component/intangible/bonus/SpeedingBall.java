package v2.component.intangible.bonus;

import v2.component.gameObject.movable.ball.Ball;

public class SpeedingBall extends Bonus implements HasTimeLimit {

    public SpeedingBall() {
        super(BonusType.speedingBall);
    }

    @Override
    public void activate() {
        for (Ball ball : gameModel.getBalls()) {
            ball.speedUp();
        }
    }

    @Override
    public void deactivate() {
        for (Ball ball : gameModel.getBalls()) {
            ball.returnInitialSpeed();
        }
    }
}
