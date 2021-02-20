package game.board;

import game.Game;
import game.elements.Ball;
import game.elements.paddle.*;


import javax.swing.*;
import java.awt.Graphics;
//import java.awt.Toolkit;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Board extends JPanel implements ActionListener {

    private static final int DELAY = 2;

    private static final int INITIAL_BALL_X = 588;
    private static final int INITIAL_BALL_Y = 388;

    private Paddle leftPaddle, rightPaddle;
    private Ball ball;



    public Board() {
        initBoard();
    }

    private void initBoard() {
        setBounds(0, 0, Game.WIDTH, Game.HEIGHT);

        leftPaddle = new LeftPaddle();
        rightPaddle = new RightPaddle();

        ball = new Ball(INITIAL_BALL_X, INITIAL_BALL_Y);

        addKeyListener(new TAdapter(leftPaddle, rightPaddle));
        setBackground(Color.BLACK);
        setFocusable(true);

        Timer timer = new Timer(DELAY, this);
        timer.start();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        doDrawing(g);

        //? Khong biet co can thiet hay khong
//        Toolkit.getDefaultToolkit().sync();
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
}
