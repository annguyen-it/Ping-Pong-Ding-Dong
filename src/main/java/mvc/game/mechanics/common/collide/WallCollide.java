package main.java.mvc.game.mechanics.common.collide;

import main.java.mvc.game.elements.function.intangible.GameSide.Side;

/**
 * Mechanic to handle collision between implementation and wall.
 * <p>We just care about vertical axis</p>
 *
 * @see main.java.mvc.game.mechanics.paddle.PaddleWallCollide
 * @see main.java.mvc.game.mechanics.ball.BallMechanics
 */
public interface WallCollide extends Collide {

    /**
     * Check if implementing object collides the wall or not
     */
    Side willWallCollide();

    /**
     * Perform collision to wall
     */
    void wallCollide();
}
