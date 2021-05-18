package main.java.mvc.game.mechanics.ball;

import main.java.mvc.game.element.component.gameObject.movable.paddle.Paddle;
import main.java.mvc.game.mechanics.common.move.ChangeSpeed;

/**
 * Mechanic to change speed of ball
 *
 * @see main.java.mvc.game.mechanics.common.move.ChangeSpeed
 * @see main.java.mvc.game.element.component.gameObject.GameObject
 */
interface BallChangeSpeed extends ChangeSpeed<Paddle> {

    @Override
    default void speedDown(){ }
}
