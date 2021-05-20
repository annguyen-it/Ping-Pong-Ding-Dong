package main.java.mvc.game.mechanics.paddle;

import main.java.mvc.game.mechanics.common.move.Stoppable;
import main.java.mvc.game.mechanics.common.transform.Resizeable;

/**
 * List all mechanics of {@code Paddle}.
 */
public interface PaddleMechanics extends
        PaddleWallCollide,
        PaddleChangeSpeed,
        Stoppable,
        Resizeable { }
