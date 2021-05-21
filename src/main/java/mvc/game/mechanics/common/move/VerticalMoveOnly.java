package main.java.mvc.game.mechanics.common.move;

/**
 * Mechanic to control moving direction of {@code VerticalOnlyMovableGameObject} implementation
 * @see main.java.mvc.game.elements.component.gameObject.movable.VerticalOnlyMovableGameObject
 */
public interface VerticalMoveOnly extends Movable {

    /**
     * Control implementation to move up
     * <p>
     * Only if it does not collides top wall
     * </p>
     */
    void willMoveUp();

    /**
     * Control implementation to move down
     * <p>
     * Only if it does not collides bottom wall
     * </p>
     */
    void willMoveDown();

    /**
     * Make sure inheritance always in playground when it tries to overlaps top border
     */
    void stopAtTopBorder();

    /**
     * Make sure inheritance always in playground when it tries to overlaps bottom border
     */
    void stopAtBottomBorder();
}
