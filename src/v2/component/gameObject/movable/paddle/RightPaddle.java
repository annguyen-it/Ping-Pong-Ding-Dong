package v2.component.gameObject.movable.paddle;

import v2.Game;
import v2.board.GameSide;

public class RightPaddle extends Paddle {

    private static final int INITIAL_RIGHT_PADDLE_X = Game.WIDTH - INITIAL_PADDLE_WIDTH - DISTANCE_TO_CROSS - 15;

    public RightPaddle() {
        super(GameSide.Side.right, INITIAL_RIGHT_PADDLE_X, INITIAL_PADDLE_Y);
    }
}