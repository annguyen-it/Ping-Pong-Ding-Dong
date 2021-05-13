package v2.controller;

import v2.Game;
import v2.board.GameAdapter;
import v2.model.EnterNameDialogModel;
import v2.model.GameModel;
import v2.utils.database.Database;
import v2.utils.database.dto.PlayerInfo;
import v2.view.GameView;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

public class GameController extends Controller<GameView, GameModel> implements ActionListener {

    public static final int GAME_DELAY = 5;

    private static final int leftUp = KeyEvent.VK_W;
    private static final int leftDown = KeyEvent.VK_S;
    private static final int rightUp = KeyEvent.VK_UP;
    private static final int rightDown = KeyEvent.VK_DOWN;
    private static final int escape = KeyEvent.VK_ESCAPE;
    private static final int space = KeyEvent.VK_SPACE;

    private Timer gameTimer;

    private boolean isStarted = false;

    public GameController(FlowController flowController, GameView view, GameModel model) {
        super(flowController, view, model);
    }

    public boolean isStarted() {
        return isStarted;
    }

    @Override
    public void initEvent() {
        view.addKeyListener(new GameAdapter(this));
        gameTimer = new Timer(GAME_DELAY, this);
    }

    @Override
    protected void drawUI() {
        super.drawUI();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        model.update();
        view.repaint();
    }

    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();
        switch (key) {
            case space:
                start();
                break;

            case leftUp:
                model.getLeftPaddle().willMoveUp();
                break;

            case leftDown:
                model.getLeftPaddle().willMoveDown();
                break;

            case rightUp:
                model.getRightPaddle().willMoveUp();
                break;

            case rightDown:
                model.getRightPaddle().willMoveDown();
                break;

            case escape:
                pause();
                break;
        }
    }

    public void keyReleased(KeyEvent e) {
        int key = e.getKeyCode();
        switch (key) {
            case leftUp:
            case leftDown:
                model.getLeftPaddle().stop();
                break;

            case rightUp:
            case rightDown:
                model.getRightPaddle().stop();
                break;
        }
    }

    public void over() {
        gameTimer.stop();
        saveResultToDatabase();
        showOverDialog();
    }

    public void saveResultToDatabase() {
        if (Database.connected()) {
            int leftPaddleScore = model.getLeftPaddle().getScore();
            int rightPaddleScore = model.getRightPaddle().getScore();

            boolean leftIsWinner = leftPaddleScore > rightPaddleScore;

            PlayerInfo leftPlayer = new PlayerInfo(getView().getLeftPlayerName(), leftPaddleScore, leftIsWinner);
            PlayerInfo rightPlayer = new PlayerInfo(getView().getRightPlayerName(), rightPaddleScore, !leftIsWinner);
            
            Database.createPlayer(leftPlayer);
            Database.createPlayer(rightPlayer);

            Database.createHistory(leftPlayer, rightPlayer);
        }
    }


    private void start() {
        if (!isStarted) {
            gameTimer.start();
            isStarted = true;
        }
    }

    private void pause() {
        gameTimer.stop();
        showPauseDialog();
    }

    private void resume() {
        if (isStarted) {
            gameTimer.restart();
        }
    }

    private void restart() {
        EnterNameDialogModel model = new EnterNameDialogModel(MenuController.playerName1, MenuController.playerName2);
        GameView gameView = new GameView(model);
        GameModel gameModel = new GameModel();

        GameController gameController = new GameController(flowController, gameView, gameModel);
        gameModel.setController(gameController);
        gameView.setController(gameController);

        switchController(gameController);
    }

    private void switchToMenuController() {
        switchController(new MenuController(flowController));
    }

    private void showPauseDialog() {
        String[] options = {};
        JOptionPane.showOptionDialog(
                null,
                view.getEscGame(),
                "",
                JOptionPane.OK_CANCEL_OPTION,
                JOptionPane.PLAIN_MESSAGE,
                null,options,null
        );
    }

    private void showOverDialog() {
        String[] dialogOptions = {};
        JOptionPane.showOptionDialog(
                null,
                view.getOverGame(),
                "",
                JOptionPane.OK_CANCEL_OPTION,
                JOptionPane.PLAIN_MESSAGE,
                null, dialogOptions
                ,null

        );

    }

    public String getNameWinner(){
        return model.getLeftPaddle().getScore()>model.getRightPaddle().getScore()?getView().getLeftPlayerName():getView().getRightPlayerName();
    }

    public Boolean IsMute(){
        return model.getSoundPlayer().isMute();
    }

    public void addInitEvent(){
        addBtnHomeEvent();
        addBtnContinueEvent();
        addBtnNewGameEvent();
        addBtnMuteEvent();
    }

    private void addBtnHomeEvent(){
        view.getBtnHome().addActionListener(e -> {
            switchToMenuController();
        });
    }

    private void addBtnContinueEvent(){
        view.getBtnContinue().addActionListener(e -> {
            resume();
        });
    }

    private void addBtnNewGameEvent(){
        view.getBtnNewGame().addActionListener(e -> {
            restart();
        });
    }

    private void addBtnMuteEvent(){
        view.getBtnMute().addActionListener(e -> {
            model.getSoundPlayer().toggle();
        });
    }

}

