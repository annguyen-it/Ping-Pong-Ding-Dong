package v2.mechanics.common.collide;

/**
 * Mechanic to handle collision between implementation and wall.
 * <p>
 * We just care about vertical axis
 *
 * @see v2.mechanics.common.collide.Collide
 */
public interface WallCollide extends Collide {

    /**
     * Check if implementing object collides the wall or not
     */
    boolean willWallCollide();

    /**
     * Perform collision to wall
     */
    void wallCollide();
}
