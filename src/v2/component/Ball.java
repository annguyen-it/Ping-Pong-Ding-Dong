package v2.component;

import v2.Game;
import v2.component.paddle.Paddle;
import v2.sound.GameSoundPlayer;

public class Ball extends GameObject {

    private static final int INITIAL_BALL_X = 588;
    private static final int INITIAL_BALL_Y = 388;

    public static final int BALL_SIZE = 24;

    private static int ballSize = BALL_SIZE;

    public static int getBallSize() {
        return ballSize;
    }

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
        if (0 <= nextPosY && nextPosY + ballSize + 40 <= Game.HEIGHT) {
            y += dy;
        }
        else {
            wallCollide();
        }
    }

    public void wallCollide() {
        soundPlayer.soundPlayer(GameSoundPlayer.wallCollideAudioFile,GameSoundPlayer.wallCollide);
        dy *= -1; }

    public void paddleCollide() {
        soundPlayer.soundPlayer(GameSoundPlayer.ballCollideAudioFile,GameSoundPlayer.ballCollide);
        dx *= -1;
    }

    public void starCollide(){
        soundPlayer.soundPlayer(GameSoundPlayer.starCollideAudioFile,GameSoundPlayer.starCollide);
    }

    public Paddle isOutTheBoard(Paddle paddleLeft, Paddle paddleRight) {
        if (x < 0) { soundPlayer.soundPlayer(GameSoundPlayer.missFile,GameSoundPlayer.miss); return paddleLeft; }
        if (x + ballSize > Game.WIDTH) { soundPlayer.soundPlayer(GameSoundPlayer.missFile,GameSoundPlayer.miss); return paddleRight; }

        return null;
    }

    public void upsizeBall(){
        ballSize += 10;
    }
}