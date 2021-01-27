package game.elements.paddle;

import game.elements.Ball;
import game.Game;
import game.Score;

import java.awt.event.KeyEvent;

public class LeftPaddle extends Paddle {

    private static final int INITIAL_LEFT_PADDLE_X = DISTANCE_TO_CROSS;
    private static final int SCORE_LEFT_X = Game.WIDTH/2 - 150;
    private static final int SCORE_LEFT_Y = Game.HEIGHT/2 - 40;

    public LeftPaddle() {
        super(INITIAL_LEFT_PADDLE_X, INITIAL_PADDLE_Y, new Score(SCORE_LEFT_X, SCORE_LEFT_Y));
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();
        //
        //        if (key == KeyEvent.VK_SPACE) {
        //            fire();
        //        }
        //
        //        if (key == KeyEvent.VK_LEFT) {
        //            dx = -3;
        //        }
        //
        //        if (key == KeyEvent.VK_RIGHT) {
        //            dx = 3;
        //        }

        if (key == KeyEvent.VK_W) {
            dy = -PADDLE_SPEED;
        }

        if (key == KeyEvent.VK_S) {
            dy = PADDLE_SPEED;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        int key = e.getKeyCode();

        //        if (key == KeyEvent.VK_SPACE) {
        //            fire();
        //        }
        //
        //        if (key == KeyEvent.VK_LEFT) {
        //            dx = 0;
        //        }
        //
        //        if (key == KeyEvent.VK_RIGHT) {
        //            dx = 0;
        //        }

        if (key == KeyEvent.VK_W) {
            dy = 0;
        }

        if (key == KeyEvent.VK_S) {
            dy = 0;
        }
    }

    @Override
    public boolean isTouched(Ball ball) {
        int ballX = ball.getX();
        int ballY = ball.getY();
        int ballDx = ball.getDx();

        return x < ballX && ballX <= x + PADDLE_WIDTH &&
                y <= ballY && ballY <= y + PADDLE_HEIGHT &&
                ballDx < 0;
    }
}
