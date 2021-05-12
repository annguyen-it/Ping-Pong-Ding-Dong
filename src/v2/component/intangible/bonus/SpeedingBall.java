package v2.component.intangible.bonus;

import v2.component.gameObject.movable.ball.Ball;

public class SpeedingBall extends Bonus {

    public SpeedingBall() {
        super(BonusType.speedingBall);
    }

    @Override
    public void active() {
        for (Ball ball : gameModel.getBalls()){
            ball.speedUp();
        }
    }

    @Override
    public void deactive() {
        for (Ball ball :  gameModel.getBalls()){
            ball.returnInitialSpeed();
        }
    }
}
