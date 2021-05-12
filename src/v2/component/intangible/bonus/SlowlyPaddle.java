package v2.component.intangible.bonus;

import v2.board.GameSide;
import v2.component.gameObject.movable.paddle.Paddle;

public class SlowlyPaddle extends Bonus {

    private Paddle affectedPaddle;

    public SlowlyPaddle() {
        super(BonusType.slowlyPaddle);
    }

    @Override
    public void active() {
        affectedPaddle = gameModel.getPaddle(GameSide.opposite(receiveSide));
        affectedPaddle.speedDown();
    }

    @Override
    public void deactive() {
        affectedPaddle.returnInitialSpeed();
    }

    @Override
    public boolean sameTypeWith(Bonus bonus) {
        return super.sameTypeWith(bonus) && affectedPaddle == ((SlowlyPaddle) bonus).affectedPaddle;
    }
}
