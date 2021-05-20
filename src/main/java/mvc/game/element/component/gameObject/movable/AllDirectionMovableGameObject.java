package main.java.mvc.game.element.component.gameObject.movable;

import main.java.mvc.game.element.function.intangible.Vector;

/**
 * Class {@code AllDirectionMovableGameObject} represents GameObject which can move by all direction <br>
 * (i.e {@link main.java.mvc.game.element.component.gameObject.movable.ball.Ball}
 */
public abstract class AllDirectionMovableGameObject extends MovableGameObject {

    public AllDirectionMovableGameObject(int x, int y, Vector vector, double speed) {
        super(x, y, vector, speed);
    }
}
