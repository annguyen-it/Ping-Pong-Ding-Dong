package main.java.mvc.game.mechanics.common.move;

import main.java.mvc.game.elements.function.intangible.GameSide.Side;
import main.java.mvc.game.mechanics.Mechanic;

/**
 * Mechanic to determine a {@code GameObject} implementation is out of the board.
 * <p>We just care about horizontal axis</p>
 *
 * @see main.java.mvc.game.elements.component.gameObject.GameObject
 */
public interface OutTheBoard extends Mechanic {

    /**
     * Determine the side of implementation out of the board
     *
     * @return {@code GameSide.Side} of implementation out of the board. If it isn't out of the board, return unknown.
     *
     * @see main.java.mvc.game.elements.function.intangible.GameSide.Side
     */
    Side isOutTheBoard();
}