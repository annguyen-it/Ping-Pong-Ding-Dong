package main.java.mvc.game.elements.component.gameObject.movable.verticalOnlyMovableGameObject.paddle;

import main.java.App;
import main.java.mvc.game.elements.function.intangible.GameSide.Side;

/**
 * Class {@code RightPaddle} represents the right paddle in playground
 */
public class RightPaddle extends Paddle {

    /**
     * Initial position of the paddle on x coordinate
     * <p>
     * Subtract by 15 to make distance from paddle to cross of right paddle equals left one. I don't know why, but it just works.
     * </p>
     */
    private static final int INITIAL_X = App.WIDTH - INITIAL_WIDTH - DISTANCE_TO_CROSS - 15;

    public RightPaddle() {
        super(Side.right, INITIAL_X);
    }
}