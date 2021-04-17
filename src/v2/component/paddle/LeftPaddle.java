package v2.component.paddle;

import v2.Game;
import v2.board.Score;
import v2.component.Ball;
import v2.model.GameModel;

import java.awt.event.KeyEvent;

public class LeftPaddle extends Paddle {

    public static final int INITIAL_LEFT_PADDLE_X = DISTANCE_TO_CROSS;
//    public static final int SCORE_LEFT_X = Game.WIDTH/2 - 150;
//    public static final int SCORE_LEFT_Y = Game.HEIGHT/2 - 40;

    public LeftPaddle() {
        super(INITIAL_LEFT_PADDLE_X, INITIAL_PADDLE_Y);
    }


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
        if(GameModel.pause==false) {
            if (key == KeyEvent.VK_W) {
                dy = -PADDLE_SPEED;
            }


            if (key == KeyEvent.VK_S) {
                dy = PADDLE_SPEED;
            }
        }


    }


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

        return x <= ballX && ballX <= x + PADDLE_WIDTH &&
                y <= ballY + Ball.BALL_SIZE && ballY <= y + PADDLE_HEIGHT &&
                ballDx < 0;

    }

}
