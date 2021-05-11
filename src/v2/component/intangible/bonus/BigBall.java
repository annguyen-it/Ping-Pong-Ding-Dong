package v2.component.intangible.bonus;

import v2.component.gameObject.immovable.star.StarType;
import v2.component.gameObject.movable.ball.Ball;

public class BigBall extends Bonus {

    public BigBall() {
        super(StarType.bigBall);
    }

    @Override
    public void active() {
        for (Ball ball : gameModel.getBalls()){
            ball.sizeUp();
        }
    }

    @Override
    public void deactive() {
        for (Ball ball : gameModel.getBalls()){
            ball.returnInitialSize();
        }
    }
}
