package main.java.mvc.game.elements.component.gameObject.movable.verticalOnlyMovableGameObject;

import main.java.mvc.game.elements.component.gameObject.movable.MovableGameObject;
import main.java.mvc.game.mechanics.common.move.VerticalMoveOnly;
import main.java.mvc.game.elements.function.intangible.Vector;

/**
 * Class {@code VerticalOnlyMovableGameObject} represents GameObject which can only move vertically <br>
 * (i.e {@link main.java.mvc.game.elements.component.gameObject.movable.allDirectionMovableGameObject.ball.Ball}
 */
public abstract class VerticalOnlyMovableGameObject extends MovableGameObject implements VerticalMoveOnly {

    public VerticalOnlyMovableGameObject(int x, int y, Vector vector, double speed) {
        super(x, y, vector, speed);
    }

    /**
     * Normalize {@code Vector} if tries to move horizontally
     */
    @Override
    public void tryMove() {
        if (vector.getX() != 0) {
            vector = new Vector();
        }
    }
}
