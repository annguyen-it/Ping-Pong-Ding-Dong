package main.java.mvc.game.mechanics.ball;

import main.java.mvc.game.mechanics.common.move.OutTheBoard;
import main.java.mvc.game.mechanics.common.collide.WallCollide;

/**
 * List all mechanics of {@code Ball}
 */
public interface BallMechanics extends
        BallChangeSpeed,
        BallChangeDirection,
        BallCollide,
        OutTheBoard,
        WallCollide,
        BallResize { }
