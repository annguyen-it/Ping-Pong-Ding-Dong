package main.java.mvc.game.mechanics.common.move;

import main.java.mvc.game.mechanics.Mechanic;

/**
 * Moving mechanic for {@code GameObject}
 *
 * @see main.java.mvc.game.element.component.gameObject.GameObject
 */
public interface Movable extends Mechanic {

    /**
     * Check if implementation is able to move or not. If can move, call to {@link Movable#move()}
     */
    void tryMove();

    /**
     * Performs moving
     */
    void move();
}
