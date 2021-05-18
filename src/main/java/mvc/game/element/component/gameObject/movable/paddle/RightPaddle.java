package main.java.mvc.game.element.component.gameObject.movable.paddle;

import main.java.App;
import main.java.mvc.game.element.function.intangible.GameSide.Side;

public class RightPaddle extends Paddle {

    //  Subtract by 15 to make distance from paddle to cross of right paddle equals left one. I don't know why, but it just works.
    private static final int INITIAL_X = App.WIDTH - INITIAL_WIDTH - DISTANCE_TO_CROSS - 15;

    public RightPaddle() {
        super(Side.right, INITIAL_X);
    }
}