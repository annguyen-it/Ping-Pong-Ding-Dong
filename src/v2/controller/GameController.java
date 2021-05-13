package v2.controller;

import v2.board.GameAdapter;
import v2.model.EnterNameDialogModel;
import v2.model.GameModel;
import v2.utils.database.Database;
import v2.utils.database.dto.PlayerInfo;
import v2.view.GameView;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

public class GameController extends Controller<GameView, GameModel> implements ActionListener {

    public static final int GAME_DELAY = 5;

    private static final int LEFT_UP = KeyEvent.VK_W;
    private static final int LEFT_DOWN = KeyEvent.VK_S;
    private static final int RIGHT_UP = KeyEvent.VK_UP;
    private static final int RIGHT_DOWN = KeyEvent.VK_DOWN;
    private static final int ESC = KeyEvent.VK_ESCAPE;
    private static final int SPACE = KeyEvent.VK_SPACE;

    private static final int HOME_BUTTON = 0;
    private static final int CONTINUE_BUTTON = 1;
    private static final int NEW_GAME_BUTTON = 2;
    private static final int MUTE_BUTTON = 3;

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
            case SPACE:
                start();
                break;

            case LEFT_UP:
                model.getLeftPaddle().willMoveUp();
                break;

            case LEFT_DOWN:
                model.getLeftPaddle().willMoveDown();
                break;

            case RIGHT_UP:
                model.getRightPaddle().willMoveUp();
                break;

            case RIGHT_DOWN:
                model.getRightPaddle().willMoveDown();
                break;

            case ESC:
                pause();
                break;
        }
    }

    public void keyReleased(KeyEvent e) {
        int key = e.getKeyCode();
        switch (key) {
            case LEFT_UP:
            case LEFT_DOWN:
                model.getLeftPaddle().stop();
                break;

            case RIGHT_UP:
            case RIGHT_DOWN:
                model.getRightPaddle().stop();
                break;
        }
    }

    public void over() {
        gameTimer.stop();
        saveResultToDatabase();
        showGameOverDialog();
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

    private void addEventToPauseDialog() {
        int output = showPauseDialog();

        switch (output) {
            case HOME_BUTTON:
                switchToMenuController();
                break;

            case NEW_GAME_BUTTON:
                restart();
                break;

            case MUTE_BUTTON:
                model.getSoundPlayer().toggle();
                resume();
                break;

            case CONTINUE_BUTTON:
            default:
                resume();
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
        addEventToPauseDialog();
    }

    private void resume() {
        if (isStarted) {
            gameTimer.restart();
        }
    }

    private void restart() {
        isStarted = false;

        model.reset();
        view.repaint();
    }

    private void switchToMenuController() {
        switchController(new MenuController(flowController));
    }

    private int showPauseDialog() {
        String[] options = { "Home", "Continue", "New Game", "Mute" };
        return JOptionPane.showOptionDialog(
                null,
                "Do you want exit this game? ",
                "",
                JOptionPane.OK_CANCEL_OPTION,
                JOptionPane.PLAIN_MESSAGE,
                null,
                options,
                options[1]
        );
    }

    private void showGameOverDialog() {
        String[] options = { "NewGame", "Home" };
        int result = JOptionPane.showOptionDialog(
                null,
                new JLabel("Congratulations " + getNameWinner() + " !"),
                "",
                JOptionPane.OK_CANCEL_OPTION,
                JOptionPane.PLAIN_MESSAGE,
                null,
                options,
                null
        );

        if (result == 0) {
            restart();
        }
        else {
            model.reset();
            switchToMenuController();
        }
    }

    public String getNameWinner() {
        return model.getLeftPaddle().getScore() > model.getRightPaddle().getScore() ?
                getView().getLeftPlayerName() :
                getView().getRightPlayerName();
    }
}
