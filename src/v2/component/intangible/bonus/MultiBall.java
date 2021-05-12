package v2.component.intangible.bonus;

import v2.component.gameObject.movable.ball.Ball;
import v2.component.intangible.Vector;
import v2.utils.sound.GameSoundPlayer;

public class MultiBall extends Bonus {

    public MultiBall() {
        super(BonusType.multiBall);
    }

    @Override
    public void active() {
        GameSoundPlayer soundPlayer = gameModel.getSoundPlayer();
        int x = ball.getX();
        int y = ball.getY();
        Vector vector = ball.getVector();
        double speed = ball.getSpeed();

        gameModel.addBall(new Ball(soundPlayer, x, y, vector.getReflection(), speed));
        gameModel.addBall(new Ball(soundPlayer, x, y, vector.getOpposite(), speed));
    }

    @Override
    public void deactive() { }
}
