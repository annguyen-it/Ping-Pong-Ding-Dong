package main.java.mvc.game.mechanics.common.move;

import main.java.mvc.game.element.component.gameObject.GameObject;
import main.java.mvc.game.mechanics.Mechanic;

/**
 * Mechanic to change direction of {@code GameObject}.
 *
 * @param <T> {@code GameObject} which caused the changing
 *
 * @see main.java.mvc.game.element.component.gameObject.GameObject
 */
public interface ChangeDirection<T extends GameObject> extends Mechanic {

    /**
     * Perform change of {@code Ball} direction by {@code GameObject}
     *
     * @param causeObject implementation of {@code GameObject}
     */
    void changeDirection(T causeObject);
}
