package game.elements.paddle;

import game.elements.Ball;
import game.elements.Element;
import game.Game;
import game.Score;

import java.awt.Graphics;
import java.awt.Color;
import java.awt.event.KeyEvent;

public abstract class Paddle extends Element {

    protected static final int PADDLE_WIDTH = 16;
    protected static final int PADDLE_HEIGHT = 100;
    protected static final int DISTANCE_TO_CROSS = 30;
    protected static final int INITIAL_PADDLE_Y = Game.HEIGHT / 2 - PADDLE_HEIGHT / 2;
    protected static final int PADDLE_SPEED = 4;
    protected Score score;

    public Paddle(int x, int y, Score score) {
        super(x, y);
        this.score = score;
    }

    @Override
    public void paintComponent(Graphics g) {
        g.setColor(Color.cyan);
        g.fillRect(x, y, PADDLE_WIDTH, PADDLE_HEIGHT);
    }

    @Override
    public void move() {
        int nextPos = y + dy;
        if (0 <= nextPos && nextPos + PADDLE_HEIGHT + 40 <= Game.HEIGHT) {
            y += dy;
        }
        else if (nextPos < 0) {
            y = 0;
        }
        else {
            y = Game.HEIGHT - PADDLE_HEIGHT - 40;
        }
    }

    public abstract void keyPressed(KeyEvent e);

    public abstract void keyReleased(KeyEvent e);

    public abstract boolean isTouched(Ball ball);

    public void increaseScore(){
        score.increase();
    }

    public Score getScore() {
        return score;
    }
}
