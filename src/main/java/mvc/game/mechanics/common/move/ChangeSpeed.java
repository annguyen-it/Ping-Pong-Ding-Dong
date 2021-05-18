package main.java.mvc.game.mechanics.common.move;

import main.java.mvc.game.element.component.gameObject.GameObject;
import main.java.mvc.game.mechanics.Mechanic;

/**
 * Mechanic to change speed of game object
 *
 * @param <T> GameObject implementation which caused the changing
 * @see main.java.mvc.game.mechanics.Mechanic
 * @see main.java.mvc.game.element.component.gameObject.GameObject
 */
public interface ChangeSpeed<T extends GameObject> extends Mechanic {

    /**
     * Ball speed is changed by GameObject
     *
     * @param causeObject implementation of GameObject
     */
    void changeSpeed(T causeObject);

    void speedUp();

    void speedDown();

    void returnInitialSpeed();
}
