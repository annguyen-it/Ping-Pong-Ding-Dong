package main.java.mvc.game.mechanics.ball;

import main.java.mvc.game.component.gameObject.GameObject;
import main.java.mvc.game.component.gameObject.immovable.star.Pickup;
import main.java.mvc.game.component.gameObject.movable.paddle.LeftPaddle;
import main.java.mvc.game.component.gameObject.movable.paddle.Paddle;
import main.java.mvc.game.component.gameObject.movable.paddle.RightPaddle;
import main.java.mvc.game.mechanics.common.collide.GameObjectCollide;

/**
 * Colliding mechanic of ball
 *
 * @see main.java.mvc.game.mechanics.common.collide.Collide
 */
public interface BallCollide extends GameObjectCollide {

    /**
     * Perform collision with Paddle
     *
     * @param paddle Paddle
     */
    void collide(Paddle paddle);

    /**
     * Perform collision with Paddle
     *
     * @param pickup Pickup
     */
    void collide(Pickup pickup);

    /**
     * Check whether if ball collides LeftPaddle
     *
     * @param paddle LeftPaddle
     * @return Ball and paddle collide or not
     */
    boolean willCollide(LeftPaddle paddle);

    /**
     * Check whether if ball collides RightPaddle
     *
     * @param paddle RightPaddle
     * @return Ball and paddle collide or not
     */
    boolean willCollide(RightPaddle paddle);

    /**
     * Check whether if ball collides LeftPaddle
     *
     * @param pickup Pickup
     * @return Ball and star collide or not
     */
    boolean willCollide(Pickup pickup);

    /**
     * Receives a colliding object, then call to specific method to perform the collision.
     *
     * @param causeObject Object which cause collision
     * @see main.java.mvc.game.component.gameObject.movable.paddle.Paddle
     * @see main.java.mvc.game.component.gameObject.immovable.star.Pickup
     */
    @Override
    default void collide(GameObject causeObject) {
        if (causeObject instanceof Paddle) {
            collide((Paddle) causeObject);
        }
        else if (causeObject instanceof Pickup) {
            collide((Pickup) causeObject);
        }
    }

    /**
     * Receives a colliding object, then call to specific method to check whether if ball collides GameObject
     *
     * @param object Object which needs to check
     * @see main.java.mvc.game.component.gameObject.movable.paddle.LeftPaddle
     * @see main.java.mvc.game.component.gameObject.movable.paddle.RightPaddle
     * @see main.java.mvc.game.component.gameObject.immovable.star.Pickup
     */
    @Override
    default boolean willCollide(GameObject object) {
        if (object instanceof LeftPaddle) {
            willCollide((LeftPaddle) object);
        }
        else if (object instanceof RightPaddle) {
            willCollide((RightPaddle) object);
        }
        else if (object instanceof Pickup) {
            willCollide((Pickup) object);
        }

        return false;
    }
}
