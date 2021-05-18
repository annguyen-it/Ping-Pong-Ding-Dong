package main.java.mvc.game.element.component.gameObject.movable.paddle;

import main.java.mvc.game.element.function.intangible.GameSide;

public class LeftPaddle extends Paddle {

    public static final int INITIAL_X = DISTANCE_TO_CROSS;

    public LeftPaddle() {
        super(GameSide.Side.left, INITIAL_X, INITIAL_Y);
    }
}
