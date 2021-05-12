package v2.component.intangible.bonus;

import v2.board.GameSide.Side;
import v2.component.gameObject.movable.ball.Ball;
import v2.component.intangible.Vector;
import v2.utils.sound.GameSoundPlayer;

public class MultiBall extends Bonus {

    public MultiBall() {
        super(BonusType.multiBall);
    }

    @Override
    public void activate() {
        GameSoundPlayer soundPlayer = gameModel.getSoundPlayer();
        int x = ball.getX();
        int y = ball.getY();
        int size = ball.getSize();
        Side lastTouch = ball.getLastTouch();
        Vector vector = ball.getVector();
        double speed = ball.getSpeed();

        gameModel.addBall(new Ball(soundPlayer, x, y, size, vector.getReflection(), speed, lastTouch));
        gameModel.addBall(new Ball(soundPlayer, x, y, size, vector.getOpposite(), speed, lastTouch));
    }
}
