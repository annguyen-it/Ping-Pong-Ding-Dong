package game.elements;

import game.Game;
import game.elements.paddle.Paddle;

import java.awt.Color;
import java.awt.Graphics;

public class Ball extends Element {

    public static final int BALL_SIZE = 24;

    public Ball(int x, int y) {
        super(x, y);
        dx = 3;
        dy = 8;
    }

    @Override
    public void paintComponent(Graphics g) {
        g.setColor(Color.white);
        g.fillOval(x, y, BALL_SIZE, BALL_SIZE);
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

    public void wallCollide(){
        dy *= -1;
    }

    public void paddleCollide(){
        dx *= -1;
    }

    public Paddle isOutTheBoard(Paddle paddleLeft, Paddle paddleRight){
        if (x < 0) return paddleLeft;
        if (x + BALL_SIZE > Game.WIDTH) return paddleRight;
        return null;
    }
}