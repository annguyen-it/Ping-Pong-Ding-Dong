package v2.mechanics.ball;

import v2.component.gameObject.GameObject;
import v2.component.gameObject.immovable.star.Pickup;
import v2.component.gameObject.movable.paddle.Paddle;
import v2.mechanics.common.move.ChangeSpeed;

/**
 * Mechanic to change speed of ball
 *
 * @see v2.mechanics.common.move.ChangeSpeed
 * @see v2.component.gameObject.GameObject
 */
interface BallChangeSpeed extends ChangeSpeed<GameObject> {

    /**
     * Receives a cause object, then call to specific method to change the speed of ball.
     *
     * @param causeObject Object which makes ball change its speed
     * @see v2.component.gameObject.movable.paddle.Paddle
     * @see v2.component.gameObject.immovable.star.Pickup
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
