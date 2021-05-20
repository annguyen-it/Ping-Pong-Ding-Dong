package main.java.mvc.game.mechanics.ball;

import main.java.mvc.game.element.component.gameObject.GameObject;
import main.java.mvc.game.element.component.gameObject.immovable.pickup.Pickup;
import main.java.mvc.game.element.component.gameObject.movable.paddle.LeftPaddle;
import main.java.mvc.game.element.component.gameObject.movable.paddle.Paddle;
import main.java.mvc.game.element.component.gameObject.movable.paddle.RightPaddle;
import main.java.mvc.game.mechanics.common.collide.GameObjectCollide;

/**
 * Colliding mechanic of {@code Ball}
 *
 * @see main.java.mvc.game.element.component.gameObject.movable.ball.Ball
 */
public interface BallCollide extends GameObjectCollide {

    /**
     * Perform collision with {@code Paddle}
     *
     * @param paddle {@code Paddle}
     */
    void collide(Paddle paddle);

    /**
     * Perform collision with {@code Pickup}
     *
     * @param pickup {@code Pickup}
     */
    void collide(Pickup pickup);

    /**
     * Check whether if ball collides {@code LeftPaddle}
     *
     * @param paddle {@code LeftPaddle}
     *
     * @return {@code Ball} and param collide or not
     */
    boolean willCollide(LeftPaddle paddle);

    /**
     * Check whether if ball collides {@code RightPaddle}
     *
     * @param paddle {@code RightPaddle}
     *
     * @return {@code Ball} and param collide or not
     */
    boolean willCollide(RightPaddle paddle);

    /**
     * Check whether if ball collides LeftPaddle
     *
     * @param pickup Pickup
     *
     * @return Ball and pickup collide or not
     */
    boolean willCollide(Pickup pickup);

    /**
     * Receives a colliding object, then call to specific method to perform the collision.
     *
     * @param causeObject {@code GameObject} which cause collision
     *
     * @see main.java.mvc.game.element.component.gameObject.movable.paddle.Paddle
     * @see main.java.mvc.game.element.component.gameObject.immovable.pickup.Pickup
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
     * Receives a {@code GameObject}, then call to specific method to check whether if ball collides GameObject
     *
     * @param object {@code GameObject} which needs to check
     *
     * @see main.java.mvc.game.element.component.gameObject.movable.paddle.LeftPaddle
     * @see main.java.mvc.game.element.component.gameObject.movable.paddle.RightPaddle
     * @see main.java.mvc.game.element.component.gameObject.immovable.pickup.Pickup
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
