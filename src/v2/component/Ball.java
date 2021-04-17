package v2.component;

import v2.Game;
import v2.component.paddle.Paddle;
import v2.model.GameModel;
import v2.sound.GameSoundPlayer;

import javax.swing.*;
import java.awt.event.KeyEvent;

public class Ball extends GameObject {

    private static final int INITIAL_BALL_X = 588;
    private static final int INITIAL_BALL_Y = 388;
    private final InitialDirection initialDirection;

    public static final int BALL_SIZE = 24;

    int da;
    int db;

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

    // Dung bong
    public void stop() {
        da = dx;
        db = dy;
        dx = 0;
        dy = 0;
    }

    // Tiep tuc bong
    public void continueB() {
        dx = da;
        dy = db;
    }

    public void wallCollide() { dy *= -1; }

    public void paddleCollide() {
        soundPlayer.ballCollide();
        dx *= -1;
    }

    //Nhan esc de thoat game, Nhan space de tiep tuc game
    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();

        if (key == KeyEvent.VK_ESCAPE) {
            if (!GameModel.pause) {
                stop();
                //GameModel.pause = true;

                int output = JOptionPane.showOptionDialog(
                        null,
                        "Do you want exit this game? ",
                        "",
                        JOptionPane.OK_CANCEL_OPTION,
                        JOptionPane.PLAIN_MESSAGE,
                        null, null
                        ,
                        0);

                if (output == JOptionPane.OK_OPTION) {

                }
            }

        }
        if (key == KeyEvent.VK_SPACE) {
            if (!GameModel.pause) {
                stop();
                GameModel.pause = true;
            }
            else {
                continueB();
                GameModel.pause = false;
            }
        }
    }


    public Paddle isOutTheBoard(Paddle paddleLeft, Paddle paddleRight) {
        if (x < 0) { return paddleLeft; }
        if (x + BALL_SIZE > Game.WIDTH) { return paddleRight; }
        return null;
    }
}