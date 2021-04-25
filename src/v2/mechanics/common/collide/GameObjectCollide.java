package v2.mechanics.common.collide;

import v2.component.gameObject.GameObject;

/**
 * Colliding mechanic for GameObject
 * @see v2.mechanics.common.collide.Collide
 */
public interface GameObjectCollide extends Collide {
    /**
     * Check whether if the collision will happen
     * @param object GameObject
     * @return Implementing object and object can collide or not
     */
    boolean willCollide(GameObject object);

    /**
     * Performs a collision between the implementing object and objectCollide
     *
     * @param objectCollide GameObject to collide
     */
    void collide(GameObject objectCollide);
}
