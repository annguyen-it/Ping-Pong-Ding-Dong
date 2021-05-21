package main.java.mvc.game.elements.function.intangible.bonus;

import main.java.mvc.game.elements.function.intangible.GameSide.Side;
import main.java.mvc.game.elements.component.gameObject.movable.ball.Ball;
import main.java.mvc.game.elements.function.intangible.Vector;
import main.java.mvc.game.sound.GameSoundPlayer;

/**
 * Class {@code MultiBall} is a implementation of Bonus.
 * It add 2 more {@code Ball}s to the playground and they fall to both two sides
 */
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
        Side lastTouch = ball.getLastTouchSide();
        Vector vector = ball.getVector();
        double speed = ball.getSpeed();

        gameModel.addBall(new Ball(x, y, vector.getOpposite(), speed, size, lastTouch, soundPlayer));

        if (vector.isHorizontalVector()) {
            gameModel.addBall(new Ball(x, y, new Vector(vector.getAlpha() + 30), speed, size, lastTouch, soundPlayer));
        }
        else {
            gameModel.addBall(new Ball(x, y, vector.getReflection(), speed, size, lastTouch, soundPlayer));
        }
    }
}
