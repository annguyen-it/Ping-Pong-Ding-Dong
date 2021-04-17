package v2.component;

import v2.Game;
import v2.component.paddle.Paddle;
import v2.sound.GameSoundPlayer;

public class Ball extends GameObject {

    private static final int INITIAL_BALL_X = 588;
    private static final int INITIAL_BALL_Y = 388;

    public static final int BALL_SIZE = 24;

    private final InitialDirection initialDirection;

    public enum InitialDirection {
        left,
        right
    }

    public Ball(GameSoundPlayer soundPlayer) {
        this(soundPlayer, InitialDirection.right);
    }

    public Ball(GameSoundPlayer soundPlayer, InitialDirection initialDirection) {
        super(INITIAL_BALL_X, INITIAL_BALL_Y, soundPlayer);
        this.initialDirection = initialDirection;
        changeDirectionParams();
    }

    private void changeDirectionParams(){
        if (initialDirection == InitialDirection.left){
            dx = -3;
        }
        else {
            dx = 3;
        }

        dy = 8;
    }

    @Override
    public void move() {
        int nextPosX = x + dx;
        int nextPosY = y + dy;

        x = nextPosX;
        if (0 <= nextPosY && nextPosY + BALL_SIZE + 40 <= Game.HEIGHT) {
            y += dy;
        }
        else {
            wallCollide();
        }
    }

    public void wallCollide() { dy *= -1; }

    public void paddleCollide() {
        soundPlayer.ballCollide();
        dx *= -1;
    }

    public Paddle isOutTheBoard(Paddle paddleLeft, Paddle paddleRight) {
        if (x < 0) { return paddleLeft; }
        if (x + BALL_SIZE > Game.WIDTH) { return paddleRight; }
        return null;
    }
}