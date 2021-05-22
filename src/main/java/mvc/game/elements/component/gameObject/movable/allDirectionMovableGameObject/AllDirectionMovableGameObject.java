package main.java.mvc.game.elements.component.gameObject.movable.allDirectionMovableGameObject;

import main.java.mvc.game.elements.component.gameObject.movable.MovableGameObject;
import main.java.mvc.game.elements.function.intangible.Vector;

/**
 * Class {@code AllDirectionMovableGameObject} represents GameObject which can move by all direction <br>
 * (i.e {@link main.java.mvc.game.elements.component.gameObject.movable.allDirectionMovableGameObject.ball.Ball}
 */
public abstract class AllDirectionMovableGameObject extends MovableGameObject {

    public AllDirectionMovableGameObject(int x, int y, Vector vector, double speed) {
        super(x, y, vector, speed);
    }
}
