package main.java.mvc.game.element.function.intangible.bonus;

import main.java.mvc.game.element.function.intangible.GameSide.Side;
import main.java.mvc.game.element.component.gameObject.movable.ball.Ball;
import main.java.mvc.game.element.function.intangible.Vector;
import main.java.mvc.game.sound.GameSoundPlayer;

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
