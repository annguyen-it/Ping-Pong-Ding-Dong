package v2.mechanics.ball;

import v2.component.gameObject.movable.paddle.LeftPaddle;
import v2.component.gameObject.movable.paddle.Paddle;
import v2.component.gameObject.movable.paddle.RightPaddle;
import v2.mechanics.common.move.ChangeDirection;

/**
 * Mechanic to change direction of ball.
 *
 * @see v2.mechanics.common.move.ChangeDirection
 * @see v2.component.gameObject.movable.paddle.Paddle
 */
public interface BallChangeDirection extends ChangeDirection<Paddle> {

    /**
     * Receives a paddle, then call to specific method to change the speed of ball.
     *
     * @param paddle Object which makes ball change its speed
     * @see v2.component.gameObject.movable.paddle.LeftPaddle
     * @see v2.component.gameObject.movable.paddle.RightPaddle
     */
    @Override
    default void changeDirection(Paddle paddle) {

        if (paddle instanceof LeftPaddle) {
            changeDirection((LeftPaddle) paddle);
        }
        else if (paddle instanceof RightPaddle) {
            changeDirection((RightPaddle) paddle);
        }
    }

    /**
     * Ball speed is changed by LeftPaddle.
     *
     * @param paddle LeftPaddle
     */
    void changeDirection(LeftPaddle paddle);

    /**
     * Ball speed is changed by RightPaddle.
     *
     * @param paddle RightPaddle
     */
    void changeDirection(RightPaddle paddle);
}
