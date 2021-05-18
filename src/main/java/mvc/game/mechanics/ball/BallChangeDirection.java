package main.java.mvc.game.mechanics.ball;

import main.java.mvc.game.element.component.gameObject.movable.paddle.LeftPaddle;
import main.java.mvc.game.element.component.gameObject.movable.paddle.Paddle;
import main.java.mvc.game.element.component.gameObject.movable.paddle.RightPaddle;
import main.java.mvc.game.mechanics.common.move.ChangeDirection;

/**
 * Mechanic to change direction of ball.
 *
 * @see main.java.mvc.game.mechanics.common.move.ChangeDirection
 * @see main.java.mvc.game.element.component.gameObject.movable.paddle.Paddle
 */
public interface BallChangeDirection extends ChangeDirection<Paddle> {

    /**
     * Receives a paddle, then call to specific method to change the speed of ball.
     *
     * @param paddle Object which makes ball change its speed
     * @see main.java.mvc.game.element.component.gameObject.movable.paddle.LeftPaddle
     * @see main.java.mvc.game.element.component.gameObject.movable.paddle.RightPaddle
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
