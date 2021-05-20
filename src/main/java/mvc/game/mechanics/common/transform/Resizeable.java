package main.java.mvc.game.mechanics.common.transform;

import main.java.mvc.game.mechanics.Mechanic;

/**
 * Resizing mechanic for {@code GameObject} instance
 *
 * @see main.java.mvc.game.mechanics.Mechanic
 */
public interface Resizeable extends Mechanic {

    /**
     * Increase size
     */
    void sizeUp();

    /**
     * Decrease size
     */
    void sizeDown();

    /**
     * Decrease size
     */
    void returnInitialSize();
}
