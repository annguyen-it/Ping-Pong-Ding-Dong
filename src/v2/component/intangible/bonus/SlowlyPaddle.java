package v2.component.intangible.bonus;

import v2.board.GameSide;
import v2.component.gameObject.movable.paddle.Paddle;

public class SlowlyPaddle extends Bonus implements HasTimeLimit {

    private Paddle affectedPaddle;

    public SlowlyPaddle() {
        super(BonusType.slowlyPaddle);
    }

    @Override
    public void activate() {
        affectedPaddle = gameModel.getPaddle(GameSide.opposite(receiveSide));
        affectedPaddle.speedDown();
    }

    @Override
    public void deactivate() {
        affectedPaddle.returnInitialSpeed();
    }
}
