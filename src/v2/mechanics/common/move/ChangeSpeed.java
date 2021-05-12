package v2.mechanics.common.move;

import v2.component.gameObject.GameObject;
import v2.mechanics.Mechanic;

/**
 * Mechanic to change speed of game object
 *
 * @param <T> GameObject implementation which caused the changing
 * @see v2.mechanics.Mechanic
 * @see v2.component.gameObject.GameObject
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
