package v2.component;

import v2.Game;
import v2.component.paddle.Paddle;
import v2.mechanics.BallMechanics;
import v2.sound.GameSoundPlayer;

public class Ball extends GameObject implements BallMechanics {

    private static final int INITIAL_BALL_X = 588;
    private static final int INITIAL_BALL_Y = 388;
    private static final int MAX_SPEED = 12;
    private static final int MIN_SPEED = 5;
    private static final int INITIAL_SPEED = 6;

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
        changeInitialDirection();
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

    public void paddleCollide(Paddle paddle) {
        changeSpeed(paddle);
        dx *= -1;
        soundPlayer.ballCollide();
    }

    public Paddle isOutTheBoard(Paddle paddleLeft, Paddle paddleRight) {
        if (x < 0) { return paddleLeft; }
        if (x + BALL_SIZE > Game.WIDTH) { return paddleRight; }
        return null;
    }

    @Override
    public void changeSpeed(Paddle paddle) {
        int topPaddleY = paddle.y;
        int paddleDiv = Paddle.PADDLE_HEIGHT/9;

        // position:    0   1   2   3   4   5   6   7   8   9
        //              |   |   |   |   |   |   |   |   |   |
        //                2   1   0  -1  -2  -1   0   1   2
        //
        int x = Math.abs(dx);

        //  0-1
        if (y < topPaddleY + paddleDiv) {
            x += 2;
        }
        //  1-2
        else if (y < topPaddleY + 2*paddleDiv) {
            x += 1;
        }
        //  2-3
        else if (y < topPaddleY + 3*paddleDiv) { }
        // 3-4
        else if (y < topPaddleY + 4*paddleDiv) {
            x -= 1;
        }
        // 4-5
        else if (y <= topPaddleY + 5*paddleDiv) {
            x -= 2;
        }
        // 5-6
        else if (y <= topPaddleY + 6*paddleDiv) {
            x -= 1;
        }
        // 6-7
        else if (y <= topPaddleY + 7*paddleDiv) { }
        // 7-8
        else if (y <= topPaddleY + 8*paddleDiv) {
            x += 1;
        }
        else {
            x += 2;
        }

        x = Math.max(x, MIN_SPEED);
        x = Math.min(x, MAX_SPEED);

        dx = dx/Math.abs(dx)*x;
    }


    @Override
    public void changeInitialDirection() {
        if (initialDirection == InitialDirection.left) {
            dx = -INITIAL_SPEED;
        }
        else {
            dx = INITIAL_SPEED;
        }

        dy = INITIAL_SPEED;
    }
}