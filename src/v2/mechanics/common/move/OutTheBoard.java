package v2.mechanics.common.move;

import v2.board.GameSide.Side;
import v2.mechanics.Mechanic;

/**
 * Mechanic to determine implementation is out of the board.
 * <p>
 * We just care about horizontal axis
 *
 * @see v2.mechanics.Mechanic
 */
public interface OutTheBoard extends Mechanic {

    /**
     * Determine the side of implementation out of the board
     *
     * @return The side of implementation out of the board. If it isn't out of the board, return unknown
     * @see v2.board.GameSide.Side
     */
    Side isOutTheBoard();
}