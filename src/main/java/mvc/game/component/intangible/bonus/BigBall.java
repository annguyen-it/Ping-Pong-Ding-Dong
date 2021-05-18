package main.java.mvc.game.component.intangible.bonus;

import main.java.mvc.game.component.gameObject.movable.ball.Ball;

public class BigBall extends Bonus implements HasTimeLimit, CanAppearWhenActivated {

    public BigBall() {
        super(BonusType.bigBall);
    }

    @Override
    public void activate() {
        for (Ball ball : gameModel.getBalls()){
            ball.sizeUp();
        }
    }

    @Override
    public void deactivate() {
        for (Ball ball : gameModel.getBalls()){
            ball.returnInitialSize();
        }
    }
}
