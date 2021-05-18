package main.java.mvc.game.element.component.gameObject.movable.paddle;

import main.java.mvc.game.element.function.intangible.GameSide.Side;

public class LeftPaddle extends Paddle {

    private static final int INITIAL_X = DISTANCE_TO_CROSS;

    public LeftPaddle() {
        super(Side.left, INITIAL_X);
    }
}
