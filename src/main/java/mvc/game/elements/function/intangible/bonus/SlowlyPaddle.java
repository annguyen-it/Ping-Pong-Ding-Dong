package main.java.mvc.game.elements.function.intangible.bonus;

import main.java.mvc.game.elements.function.intangible.GameSide;
import main.java.mvc.game.elements.component.gameObject.movable.paddle.Paddle;

/**
 * Class {@code SlowlyPaddle} is a implementation of Bonus. It makes opponent's paddle moves slower
 * <p>
 * {@code SlowlyPaddle} has time limit.
 * </p>
 */
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
