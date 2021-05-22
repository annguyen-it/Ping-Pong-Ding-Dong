package main.java.mvc.game.mechanics.paddle;

import main.java.mvc.game.mechanics.common.collide.WallCollide;

/**
 * Changing speed mechanic for {@code Paddle}
 * <p>
 * We don't need to implement {{@link #wallCollide()} ()}} in {@code Paddle}, so override them right in this interface layer
 * </p>
 *
 * @see main.java.mvc.game.elements.component.gameObject.movable.verticalOnlyMovableGameObject.paddle.Paddle
 */
public interface PaddleWallCollide extends WallCollide {

    @Override
    default void wallCollide() { }
}
