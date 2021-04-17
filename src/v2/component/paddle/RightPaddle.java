package v2.component.paddle;

import v2.Game;
import v2.board.Score;
import v2.component.Ball;
import v2.model.GameModel;

import java.awt.event.KeyEvent;

public class RightPaddle extends Paddle {

    private static final int INITIAL_RIGHT_PADDLE_X = Game.WIDTH - PADDLE_WIDTH - DISTANCE_TO_CROSS - 15;
//    private static final int SCORE_RIGHT_X = Game.WIDTH/2 + 150;
//    private static final int SCORE_RIGHT_Y = Game.HEIGHT/2 - 40;

    public RightPaddle() {
        super(INITIAL_RIGHT_PADDLE_X, INITIAL_PADDLE_Y);
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
            if (key == KeyEvent.VK_UP) {
                dy = -PADDLE_SPEED;
            }

            if (key == KeyEvent.VK_DOWN) {
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

        if (key == KeyEvent.VK_UP) {
            dy = 0;
        }

        if (key == KeyEvent.VK_DOWN) {
            dy = 0;
        }
    }

    @Override
    public boolean isTouched(Ball ball) {
        int ballX = ball.getX();
        int ballY = ball.getY();
        int ballDx = ball.getDx();

        return x <= ballX + Ball.BALL_SIZE && ballX + Ball.BALL_SIZE < x + PADDLE_WIDTH &&
                y <= ballY && ballY <= y + PADDLE_HEIGHT &&
                ballDx > 0;

    }
}