package main.java.mvc.game.mechanics.ball;

import main.java.mvc.game.elements.component.gameObject.movable.verticalOnlyMovableGameObject.paddle.Paddle;
import main.java.mvc.game.mechanics.common.move.ChangeSpeed;

/**
 * Mechanic to change speed of {@code Ball}
 * <p>
 * {@code Ball} is only being decrease speed, so we don't need to implement speedDown()
 * </p>
 */
interface BallChangeSpeed extends ChangeSpeed<Paddle> {

    @Override
    default void speedDown() { }
}
