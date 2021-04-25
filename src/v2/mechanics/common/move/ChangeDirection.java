package v2.mechanics.common.move;

import v2.component.gameObject.GameObject;
import v2.mechanics.Mechanic;

/**
 * Mechanic to change direction of GameObject.
 *
 * @param <T> GameObject which caused the changing
 * @see v2.component.gameObject.GameObject
 * @see v2.mechanics.Mechanic
 */
public interface ChangeDirection<T extends GameObject> extends Mechanic {

    /**
     * Ball direction is changed by GameObject
     *
     * @param causeObject implementation of GameObject
     */
    void changeDirection(T causeObject);
}
