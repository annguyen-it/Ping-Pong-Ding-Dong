package main.java.mvc.game.mechanics.common.collide;

import main.java.mvc.game.element.component.gameObject.GameObject;

/**
 * Colliding mechanic for {@code GameObject}
 *
 * @see main.java.mvc.game.mechanics.ball.BallCollide
 */
public interface GameObjectCollide extends Collide {

    /**
     * Check whether if the collision will happen
     *
     * @param object {@code GameObject}
     *
     * @return Implementing object and param can collide or not
     */
    boolean willCollide(GameObject object);

    /**
     * Performs a collision between the implementing object and objectCollide
     *
     * @param causeObject {@code GameObject} to collide
     */
    void collide(GameObject causeObject);
}
