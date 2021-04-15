package v2.board;

import v2.component.Ball;
import v2.component.paddle.Paddle;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class TAdapter extends KeyAdapter {

    private final Paddle leftPaddle, rightPaddle;

    private final Ball ball;

    public TAdapter(Paddle leftPaddle, Paddle rightPaddle, Ball ball) {
        this.leftPaddle = leftPaddle;
        this.rightPaddle = rightPaddle;
        this.ball = ball;
    }

    @Override
    public void keyPressed(KeyEvent e) {
        leftPaddle.keyPressed(e);
        rightPaddle.keyPressed(e);
        ball.keyPressed(e);
    }

    @Override
    public void keyReleased(KeyEvent e) {
        leftPaddle.keyReleased(e);
        rightPaddle.keyReleased(e);
    }
}
