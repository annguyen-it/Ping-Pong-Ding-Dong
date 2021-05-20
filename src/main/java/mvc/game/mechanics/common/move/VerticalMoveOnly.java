package main.java.mvc.game.mechanics.common.move;

public interface VerticalMoveOnly extends Movable {

    /**
     * Control instance to move up
     * <p>
     * Only if it does not collides top wall
     * </p>
     */
    void willMoveUp();

    /**
     * Control instance to move down
     * <p>
     * Only if it does not collides bottom wall
     * </p>
     */
    void willMoveDown();

    /**
     * <p>
     * Make sure inheritance always in playground when it tries to overlaps top border
     * </p>
     */
    void stopAtTopBorder();

    /**
     * <p>
     * Make sure inheritance always in playground when it tries to overlaps bottom border
     * </p>
     */
    void stopAtBottomBorder();
}
