package main.java.mvc.game.mechanics.common.move;

/**
 * Mechanic to stop a {@code MovableGameObject} implementation
 *
 * @see main.java.mvc.game.elements.component.gameObject.movable.MovableGameObject
 */
public interface Stoppable {

    /**
     * Performs stopping
     */
    void stop();
}
