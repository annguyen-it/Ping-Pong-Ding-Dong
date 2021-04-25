package v2.component.gameObject.movable.paddle;

public class LeftPaddle extends Paddle {

    public static final int INITIAL_LEFT_PADDLE_X = DISTANCE_TO_CROSS;

    public LeftPaddle() {
        super(INITIAL_LEFT_PADDLE_X, INITIAL_PADDLE_Y);
    }
}
