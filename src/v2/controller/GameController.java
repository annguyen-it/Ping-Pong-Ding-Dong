package v2.controller;

import v2.board.GameAdapter;
import v2.model.EnterNameDialogModel;
import v2.model.GameModel;
import v2.model.MenuModel;
import v2.view.GameView;
import v2.view.MenuView;

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
    private static final int space = KeyEvent.VK_SPACE;

    private Timer timer;
    private boolean isPausing = false;
    private boolean isStarted = false;

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
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        model.updatePaddles();
        model.updateBall();

        view.repaint();
    }

    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();
        switch (key) {
            case space:
                start();
                break;

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
        switch (key) {
            case leftUp:
            case leftDown:
                model.getLeftPaddle().willStop();
                break;

            case rightUp:
            case rightDown:
                model.getRightPaddle().willStop();
                break;
        }
    }

    private void start() {
        if (!isStarted) {
            timer.start();
            isStarted = true;
        }
    }

    private void pauseOrResume() {
        if (isPausing) {
            resume();

        } else {

            pause();
            addPauseEvent();

        }
    }

    private void addPauseEvent() {
        String[] play = {"Home","Continue", "New Game"};
        int output = JOptionPane.showOptionDialog(
                null,
                "Do you want exit this game? ",
                "",
                JOptionPane.OK_CANCEL_OPTION,
                JOptionPane.PLAIN_MESSAGE,
                null, play
                ,
                play[0]);
        if (output == 0) {
            MenuController menuController = new MenuController(flowController);
            switchController(menuController);
        }
        if(output==1){resume(); }
        if(output ==2){
            EnterNameDialogModel model = new EnterNameDialogModel(MenuController.playerName1, MenuController.playerName2);
            GameView gameView = new GameView(model);
            GameModel gameModel = new GameModel();

            GameController gameController = new GameController(flowController, gameView, gameModel);

            gameView.setController(gameController);

            switchController(gameController);
        }

    }

    private void pause() {
        timer.stop();
        isPausing = true;
    }

    private void resume() {
        timer.restart();
        isPausing = false;
    }
}


