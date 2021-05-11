package v2.component.intangible.bonus;

import v2.board.GameSide;
import v2.component.gameObject.immovable.star.StarType;
import v2.component.gameObject.movable.paddle.Paddle;

public class SlowlyPaddle extends Bonus {

    private Paddle affectedPaddle;

    public SlowlyPaddle() {
        super(StarType.slowlyPaddle);
    }

    @Override
    public void active() {
        affectedPaddle = gameModel.getPaddle(GameSide.opposite(receiveSide));

        System.out.println(affectedPaddle.getScore());

        affectedPaddle.speedDown();
    }

    @Override
    public void deactive() {
        affectedPaddle.returnInitialSpeed();
    }
}
