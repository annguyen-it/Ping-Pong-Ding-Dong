package v2.mechanics.ball;

import v2.mechanics.common.move.OutTheBoard;
import v2.mechanics.common.collide.WallCollide;
import v2.mechanics.common.transform.Resizeable;

/**
 * List all mechanics of ball.
 *
 * @see BallChangeSpeed
 * @see BallChangeSpeed
 * @see BallCollide
 * @see v2.mechanics.common.move.OutTheBoard
 * @see v2.mechanics.common.collide.WallCollide
 * @see v2.mechanics.common.transform.Resizeable
 */
public interface BallMechanics extends
        BallChangeSpeed,
        BallChangeDirection,
        BallCollide,
        OutTheBoard,
        WallCollide,
        Resizeable {

    @Override
    default void sizeDown() { }
}
