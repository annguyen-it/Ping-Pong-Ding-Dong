package main.java.mvc.game.element.function.intangible.bonus;

import main.java.mvc.game.element.function.intangible.GameSide;
import main.java.mvc.game.element.component.gameObject.movable.paddle.Paddle;

public class SlowlyPaddle extends Bonus implements HasTimeLimit {

    private Paddle affectedPaddle;

    public SlowlyPaddle() {
        super(BonusType.slowlyPaddle);
    }

    @Override
    public void activate() {
        affectedPaddle = gameModel.getPaddle(GameSide.getOpposite(receiveSide));
        affectedPaddle.speedDown();
    }

    @Override
    public void deactivate() {
        affectedPaddle.returnInitialSpeed();
    }
}
