package main.java.mvc.game.mechanics.common.move;

import main.java.mvc.game.board.GameSide.Side;
import main.java.mvc.game.mechanics.Mechanic;

/**
 * Mechanic to determine implementation is out of the board.
 * <p>
 * We just care about horizontal axis
 *
 * @see main.java.mvc.game.mechanics.Mechanic
 */
public interface OutTheBoard extends Mechanic {

    /**
     * Determine the side of implementation out of the board
     *
     * @return The side of implementation out of the board. If it isn't out of the board, return unknown
     * @see main.java.mvc.game.board.GameSide.Side
     */
    Side isOutTheBoard();
}