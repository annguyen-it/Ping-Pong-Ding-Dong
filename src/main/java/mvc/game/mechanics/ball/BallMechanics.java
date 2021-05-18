package main.java.mvc.game.mechanics.ball;

import main.java.mvc.game.mechanics.common.move.OutTheBoard;
import main.java.mvc.game.mechanics.common.collide.WallCollide;
import main.java.mvc.game.mechanics.common.transform.Resizeable;

/**
 * List all mechanics of ball.
 *
 * @see BallChangeSpeed
 * @see BallChangeSpeed
 * @see BallCollide
 * @see main.java.mvc.game.mechanics.common.move.OutTheBoard
 * @see main.java.mvc.game.mechanics.common.collide.WallCollide
 * @see main.java.mvc.game.mechanics.common.transform.Resizeable
 */
public interface BallMechanics extends
        BallChangeSpeed,
        BallChangeDirection,
        BallCollide,
        OutTheBoard,
        WallCollide,
        BallResize { }
