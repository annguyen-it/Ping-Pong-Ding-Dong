package game;

import game.elements.Ball;
import game.elements.paddle.*;

import javax.swing.JPanel;
import javax.swing.Timer;
import java.awt.Graphics;
import java.awt.Toolkit;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class Board extends JPanel implements ActionListener {

    private final int DELAY = 2;

    private final int INITIAL_BALL_X = 588;
    private final int INITIAL_BALL_Y = 388;

    private Timer timer;
    private Paddle leftPaddle, rightPaddle;
    private Ball ball;

    public Board() {
        initBoard();
    }

    private void initBoard() {
        addKeyListener(new TAdapter());
        setBackground(Color.BLACK);
        setFocusable(true);

        leftPaddle = new LeftPaddle();
        rightPaddle = new RightPaddle();
        ball = new Ball(INITIAL_BALL_X, INITIAL_BALL_Y);

        timer = new Timer(DELAY, this);
        timer.start();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        doDrawing(g);

        Toolkit.getDefaultToolkit().sync();
    }

    private void doDrawing(Graphics g) {
        leftPaddle.paintComponent(g);
        rightPaddle.paintComponent(g);

        ball.paintComponent(g);

        leftPaddle.getScore().paintComponent(g);
        rightPaddle.getScore().paintComponent(g);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        updatePaddles();
        updateBall();

        repaint();
    }

    private void updatePaddles() {
        leftPaddle.move();
        rightPaddle.move();

        if (leftPaddle.isTouched(ball) || rightPaddle.isTouched(ball)) {
            ball.paddleCollide();
        }
    }

    private void updateBall() {
        ball.move();
        Paddle losePaddle = ball.isOutTheBoard(leftPaddle, rightPaddle);

        if (losePaddle == leftPaddle) {
            rightPaddle.increaseScore();
            ball = new Ball(INITIAL_BALL_X, INITIAL_BALL_Y);
        }
        else if (losePaddle == rightPaddle) {
            leftPaddle.increaseScore();
            ball = new Ball(INITIAL_BALL_X, INITIAL_BALL_Y);
        }
    }

    private class TAdapter extends KeyAdapter {

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

}
