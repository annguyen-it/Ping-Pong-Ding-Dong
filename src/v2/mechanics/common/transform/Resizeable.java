package v2.mechanics.common.transform;

import v2.mechanics.Mechanic;

/**
 * Resizing mechanic
 * @see v2.mechanics.Mechanic
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
}
