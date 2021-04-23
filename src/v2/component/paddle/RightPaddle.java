package v2.component.paddle;

import v2.Game;

public class RightPaddle extends Paddle {

    private static final int INITIAL_RIGHT_PADDLE_X = Game.WIDTH - PADDLE_WIDTH - DISTANCE_TO_CROSS - 15;

    public RightPaddle() {
        super(INITIAL_RIGHT_PADDLE_X, INITIAL_PADDLE_Y);
    }
}