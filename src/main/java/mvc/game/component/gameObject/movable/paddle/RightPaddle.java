package main.java.mvc.game.component.gameObject.movable.paddle;

import main.java.App;
import main.java.mvc.game.board.GameSide;

public class RightPaddle extends Paddle {

    private static final int INITIAL_X = App.WIDTH - INITIAL_WIDTH - DISTANCE_TO_CROSS - 15;

    public RightPaddle() {
        super(GameSide.Side.right, INITIAL_X, INITIAL_Y);
    }
}