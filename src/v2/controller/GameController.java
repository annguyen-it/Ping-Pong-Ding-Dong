package v2.controller;

import v2.board.GameAdapter;
import v2.model.GameModel;
import v2.view.GameView;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

public class GameController extends Controller<GameView, GameModel> implements ActionListener {

    private static final int DELAY = 1;

    private static final int leftUp = KeyEvent.VK_W;
    private static final int leftDown = KeyEvent.VK_S;
    private static final int rightUp = KeyEvent.VK_UP;
    private static final int rightDown = KeyEvent.VK_DOWN;
    private static final int escape = KeyEvent.VK_ESCAPE;

    private Timer timer;
    private boolean isPausing = false;

    public GameController(FlowController flowController, GameView view, GameModel model) {
        super(flowController, view, model);
    }

    @Override
    public void initEvent() {
        view.addKeyListener(new GameAdapter(this));
    }

    @Override
    protected void drawUI() {
        super.drawUI();
        timer = new Timer(DELAY, this);
        timer.start();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        model.updatePaddles();
        model.updateBall();

        view.repaint();
    }

    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();
        switch (key){
            case leftUp:
                model.getLeftPaddle().willUp();
                break;

            case leftDown:
                model.getLeftPaddle().willDown();
                break;

            case rightUp:
                model.getRightPaddle().willUp();
                break;

            case rightDown:
                model.getRightPaddle().willDown();
                break;

            case escape:
                pauseOrResume();
                break;
        }
    }

    public void keyReleased(KeyEvent e) {
        int key = e.getKeyCode();
        switch (key){
            case leftUp:
            case leftDown:
                model.getLeftPaddle().willStop();
                break;

            case rightUp:
            case rightDown:
                model.getRightPaddle().willStop();
                break;

            case escape:
//                model.getLeftPaddle().willUp();
                break;
        }
    }

    private void pauseOrResume(){
        if (isPausing) {
            resume();
        }
        else {
            pause();
        }
    }

    private void pause(){
        timer.stop();
        isPausing = true;
    }

    private void resume(){
        timer.restart();
        isPausing = false;
    }
}
