package main.java.mvc.game.element.component.gameObject.movable;

import main.java.mvc.game.mechanics.common.move.VerticalMoveOnly;
import main.java.mvc.game.element.function.intangible.Vector;

public abstract class VerticalOnlyMovableGameObject extends MovableGameObject implements VerticalMoveOnly {

    public VerticalOnlyMovableGameObject(int x, int y, Vector vector, double speed) {
        super(x, y, vector, speed);
    }

    /// TODO: Will we need this?
    @Override
    public void tryMove() {
        if (vector.getX() != 0){
            vector = new Vector();
        }
    }
}
