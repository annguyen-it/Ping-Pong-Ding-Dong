package v2.component.gameObject.movable.paddle;

import v2.board.GameSide;

public class LeftPaddle extends Paddle {

    public static final int INITIAL_X = DISTANCE_TO_CROSS;

    public LeftPaddle() {
        super(GameSide.Side.left, INITIAL_X, INITIAL_Y);
    }
}
