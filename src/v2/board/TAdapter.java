package v2.board;

import v2.component.paddle.Paddle;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class TAdapter extends KeyAdapter {

    private final Paddle leftPaddle, rightPaddle;

    public TAdapter(Paddle leftPaddle, Paddle rightPaddle) {
        this.leftPaddle = leftPaddle;
        this.rightPaddle = rightPaddle;
    }

    @Override
    public void keyPressed(KeyEvent e) {
        leftPaddle.keyPressed(e);
        rightPaddle.keyPressed(e);
    }

    @Override
    public void keyReleased(KeyEvent e) {
        leftPaddle.keyReleased(e);
        rightPaddle.keyReleased(e);
    }
}
