package main.java.mvc.game.element.component.gameObject.movable.paddle;

import main.java.mvc.game.element.function.intangible.GameSide.Side;

/**
 * Class {@code LeftPaddle} represents the left paddle in playground
 */
public class LeftPaddle extends Paddle {

    /**
     * Initial position of the paddle on x coordinate
     */
    private static final int INITIAL_X = DISTANCE_TO_CROSS;

    public LeftPaddle() {
        super(Side.left, INITIAL_X);
    }
}
