package main.java.mvc.game.mechanics.common.transform;

import main.java.mvc.game.mechanics.Mechanic;

/**
 * Resizing mechanic
 *
 * @see main.java.mvc.game.mechanics.Mechanic
 */
public interface Resizeable extends Mechanic {

    /**
     * Increase size
     */
    void sizeUp();

    void sizeDown();

    /**
     * Decrease size
     */
    void returnInitialSize();
}
