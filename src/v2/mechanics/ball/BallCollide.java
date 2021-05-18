package v2.mechanics.ball;

import v2.component.gameObject.GameObject;
import v2.component.gameObject.immovable.star.Pickup;
import v2.component.gameObject.movable.paddle.LeftPaddle;
import v2.component.gameObject.movable.paddle.Paddle;
import v2.component.gameObject.movable.paddle.RightPaddle;
import v2.mechanics.common.collide.GameObjectCollide;

/**
 * Colliding mechanic of ball
 *
 * @see v2.mechanics.common.collide.Collide
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
     * @see v2.component.gameObject.movable.paddle.Paddle
     * @see v2.component.gameObject.immovable.star.Pickup
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
     * @see v2.component.gameObject.movable.paddle.LeftPaddle
     * @see v2.component.gameObject.movable.paddle.RightPaddle
     * @see v2.component.gameObject.immovable.star.Pickup
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
