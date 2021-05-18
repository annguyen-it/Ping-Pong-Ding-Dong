package main.java.mvc.game.mechanics.ball;

import main.java.mvc.game.element.component.gameObject.GameObject;
import main.java.mvc.game.element.component.gameObject.immovable.star.Pickup;
import main.java.mvc.game.element.component.gameObject.movable.paddle.Paddle;
import main.java.mvc.game.mechanics.common.move.ChangeSpeed;

/**
 * Mechanic to change speed of ball
 *
 * @see main.java.mvc.game.mechanics.common.move.ChangeSpeed
 * @see main.java.mvc.game.element.component.gameObject.GameObject
 */
interface BallChangeSpeed extends ChangeSpeed<GameObject> {

    /**
     * Receives a cause object, then call to specific method to change the speed of ball.
     *
     * @param causeObject Object which makes ball change its speed
     * @see main.java.mvc.game.element.component.gameObject.movable.paddle.Paddle
     * @see main.java.mvc.game.element.component.gameObject.immovable.star.Pickup
     */
    @Override
    default void changeSpeed(GameObject causeObject) {
        if (causeObject instanceof Paddle) {
            changeSpeed((Paddle) causeObject);
        }
        else if (causeObject instanceof Pickup) {
            changeSpeed((Pickup) causeObject);
        }
    }

    /**
     * Ball speed is change by paddle
     *
     * @param paddle Paddle
     */
    void changeSpeed(Paddle paddle);

    /**
     * Ball is change by Pickup
     *
     * @param pickup Pickup
     */
    default void changeSpeed(Pickup pickup){
        speedUp();
    }

    @Override
    default void speedDown(){ }
}
