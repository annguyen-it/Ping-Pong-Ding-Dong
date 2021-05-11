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
        model.updatePaddles();
        model.updateBall();
        model.updateStar();
        model.updateBonus();

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

    private void addPauseEvent() {
        int output = showPauseDialog();

        switch (output) {
            case 0:
                switchToMenuController();
                break;

            case 2:
                restart();
                break;

            case 3:
                model.getSoundPlayer().toggle();

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
        addPauseEvent();
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

    private int showPauseDialog() {
        String[] options = {"Home", "Continue", "New Game", "Mute"};
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

    private void showOverDialog() {
        String[] dialogOptions = {"NewGame", "Home"};
        int result=JOptionPane.showOptionDialog(
                null,
                new JLabel("Congratulations "  + getNameWinner() + " !"),
                "",
                JOptionPane.OK_CANCEL_OPTION,
                JOptionPane.PLAIN_MESSAGE,
                null, dialogOptions
                ,null

        );

        if(result==0){restart();}
        if(result==1){switchToMenuController(); }
    }

    public String getNameWinner(){
        return model.getLeftPaddle().getScore()>model.getRightPaddle().getScore()?getView().getLeftPlayerName():getView().getRightPlayerName();
    }
}
