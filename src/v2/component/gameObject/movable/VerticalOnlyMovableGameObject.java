package v2.component.gameObject.movable;

import v2.mechanics.common.move.VerticalMoveOnly;
import v2.component.intangible.Vector;

public abstract class VerticalOnlyMovableGameObject extends MovableGameObject implements VerticalMoveOnly {

    public VerticalOnlyMovableGameObject(int x, int y, Vector vector, double speed) {
        super(x, y, vector, speed);
    }

    @Override
    public void tryMove() {
        if (vector.getX() != 0){
            vector = new Vector();
        }
    }
}
