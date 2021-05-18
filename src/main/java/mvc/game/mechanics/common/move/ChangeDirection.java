package main.java.mvc.game.mechanics.common.move;

import main.java.mvc.game.component.gameObject.GameObject;
import main.java.mvc.game.mechanics.Mechanic;

/**
 * Mechanic to change direction of GameObject.
 *
 * @param <T> GameObject which caused the changing
 * @see main.java.mvc.game.component.gameObject.GameObject
 * @see main.java.mvc.game.mechanics.Mechanic
 */
public interface ChangeDirection<T extends GameObject> extends Mechanic {

    /**
     * Ball direction is changed by GameObject
     *
     * @param causeObject implementation of GameObject
     */
    void changeDirection(T causeObject);
}
