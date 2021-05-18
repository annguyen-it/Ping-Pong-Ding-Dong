package main.java.mvc.game.mechanics.common.collide;

import main.java.mvc.game.component.gameObject.GameObject;

/**
 * Colliding mechanic for GameObject
 * @see main.java.mvc.game.mechanics.common.collide.Collide
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
     * @param causeObject GameObject to collide
     */
    void collide(GameObject causeObject);
}
