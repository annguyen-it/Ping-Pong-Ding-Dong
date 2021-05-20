package main.java.mvc.game.mechanics.ball;

import main.java.mvc.game.element.component.gameObject.movable.paddle.LeftPaddle;
import main.java.mvc.game.element.component.gameObject.movable.paddle.Paddle;
import main.java.mvc.game.element.component.gameObject.movable.paddle.RightPaddle;
import main.java.mvc.game.mechanics.common.move.ChangeDirection;

/**
 * Mechanic to change direction of {@code Ball}.
 *
 * @see main.java.mvc.game.element.component.gameObject.movable.ball.Ball
 */
public interface BallChangeDirection extends ChangeDirection<Paddle> {

    /**
     * Receives a {@code Paddle}, then call to specific method to change the direction of {@code Ball}.
     *
     * @param paddle The {@code Paddle} that makes ball change its direction
     *
     * @see main.java.mvc.game.element.component.gameObject.movable.paddle.Paddle
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
     * Perform the change of direction of {@code Ball} cause by {@code LeftPaddle}.
     *
     * @param paddle {@code LeftPaddle}
     */
    void changeDirection(LeftPaddle paddle);

    /**
     * Perform the change of direction of {@code Ball} cause by {@code RightPaddle}.
     *
     * @param paddle {@code RightPaddle}
     */
    void changeDirection(RightPaddle paddle);
}
