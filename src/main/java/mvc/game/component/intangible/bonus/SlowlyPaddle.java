package main.java.mvc.game.component.intangible.bonus;

import main.java.mvc.game.board.GameSide;
import main.java.mvc.game.component.gameObject.movable.paddle.Paddle;

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
