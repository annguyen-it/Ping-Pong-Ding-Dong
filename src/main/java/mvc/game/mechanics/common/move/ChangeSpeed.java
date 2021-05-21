package main.java.mvc.game.mechanics.common.move;

import main.java.mvc.game.elements.component.gameObject.GameObject;
import main.java.mvc.game.mechanics.Mechanic;

/**
 * Mechanic to change speed of {@code GameObject}
 *
 * @param <T> {@code GameObject} implementation which caused the changing
 *
 * @see main.java.mvc.game.elements.component.gameObject.GameObject
 */
public interface ChangeSpeed<T extends GameObject> extends Mechanic {

    /**
     * Ball speed is changed by GameObject
     * <p>
     * This function is called when we do not know speed will increase or decrease, we just know it should be changed.
     * </p>
     *
     * @param causeObject implementation of GameObject
     */
    void changeSpeed(T causeObject);

    /**
     * Increase speed
     */
    void speedUp();

    /**
     * Decrease speed
     */
    void speedDown();

    /**
     * Make the speed return initial speed
     */
    void returnInitialSpeed();
}
