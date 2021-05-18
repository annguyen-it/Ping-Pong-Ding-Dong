package main.java.mvc.game.component.gameObject.movable.paddle;

import main.java.mvc.game.board.GameSide;

public class LeftPaddle extends Paddle {

    public static final int INITIAL_X = DISTANCE_TO_CROSS;

    public LeftPaddle() {
        super(GameSide.Side.left, INITIAL_X, INITIAL_Y);
    }
}
