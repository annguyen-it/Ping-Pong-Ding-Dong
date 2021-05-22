package main.java.mvc.game.mechanics.ball;

import main.java.mvc.game.mechanics.common.transform.Resizeable;

/**
 * Resizing mechanic of {@code Ball}
 *
 * <p>
 * {@code Ball} is only being increase speed, so we don't need to implement sizeDown()
 * </p>
 *
 * @see main.java.mvc.game.elements.component.gameObject.movable.allDirectionMovableGameObject.ball.Ball
 */
public interface BallResize extends Resizeable {

    @Override
    default void sizeDown() { }
}
