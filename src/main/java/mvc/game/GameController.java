package main.java.mvc.game;

import main.java.mvc.game.keyListener.GameAdapter;
import main.java.mvc.common.Controller;
import main.java.mvc.common.flow.FlowController;
import main.java.mvc.menu.MenuController;
import main.java.utils.database.Database;
import main.java.utils.database.dto.PlayerInfo;
import main.java.utils.image.ImagePathProvider;

import javax.swing.*;
import java.awt.*;
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

    private final Timer gameTimer = new Timer(GAME_DELAY, this);
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
    }

    @Override
    public void drawUI() {
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

    private void saveResultToDatabase() {
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

    private void reset() {
        isStarted = false;

        model.reset();
        view.repaint();
    }

    private void switchToMenuController() {
        model.getSoundPlayer().stopBackgroundAudio();
        switchController(new MenuController(flowController));
    }

    private void showPauseDialog() {
        JButton btnHome = new JButton();
        JButton btnContinue = new JButton();
        JButton btnReplay = new JButton();
        JButton btnMute = new JButton();

        JButton[] options = { btnHome, btnContinue, btnReplay, btnMute };
        String[] btnImagePath = {
                ImagePathProvider.Game.Dialog.home,
                ImagePathProvider.Game.Dialog.continue_,
                ImagePathProvider.Game.Dialog.replay,
                model.getSoundPlayer().isMute()
                        ? ImagePathProvider.Game.Dialog.volumeOff
                        : ImagePathProvider.Game.Dialog.volumeOn
        };

        for (int i = 0; i < options.length; i++) {
            options[i].setIcon(new ImageIcon(btnImagePath[i]));
            options[i].setOpaque(false);
            options[i].setBorderPainted(false);
        }

        btnHome.addActionListener(e -> {
            switchToMenuController();
            reset();
            closeDialogByButton(btnHome);
        });
        btnContinue.addActionListener(e -> {
            resume();
            closeDialogByButton(btnContinue);
        });
        btnReplay.addActionListener(e -> {
            reset();
            closeDialogByButton(btnReplay);
        });
        btnMute.addActionListener(e -> {
            model.getSoundPlayer().toggle();
            btnMute.setIcon(new ImageIcon(
                    model.getSoundPlayer().isMute()
                            ? ImagePathProvider.Game.Dialog.volumeOff
                            : ImagePathProvider.Game.Dialog.volumeOn));
        });

        JOptionPane.showOptionDialog(
                null,
                "",
                "",
                JOptionPane.OK_CANCEL_OPTION,
                JOptionPane.PLAIN_MESSAGE,
                null,
                options,
                options[1]
        );

        resume();
    }

    private void closeDialogByButton(JButton button) {
        Window w = SwingUtilities.getWindowAncestor(button);
        w.setVisible(false);
        w.dispose();
    }

    private void showGameOverDialog() {
        JButton btnHome = new JButton();
        JButton btnNewGame = new JButton();

        JButton[] options = { btnHome, btnNewGame };
        String[] btnImagePath = {
                ImagePathProvider.Game.Dialog.home,
                ImagePathProvider.Game.Dialog.replay,
        };

        for (int i = 0; i < options.length; i++) {
            options[i].setIcon(new ImageIcon(btnImagePath[i]));
            options[i].setOpaque(false);
            options[i].setBorderPainted(false);
        }

        btnHome.addActionListener(e -> {
            switchToMenuController();
            closeDialogByButton(btnHome);
        });
        btnNewGame.addActionListener(e -> {
            reset();
            closeDialogByButton(btnNewGame);
        });

        JOptionPane.showOptionDialog(
                null,
                new JLabel("Congratulations " + getWinnerName() + " !"),
                "",
                JOptionPane.OK_CANCEL_OPTION,
                JOptionPane.PLAIN_MESSAGE,
                null,
                options,
                options[0]
        );

        reset();
    }

    private String getWinnerName() {
        return model.getLeftPaddle().getScore() > model.getRightPaddle().getScore()
                ? getView().getLeftPlayerName()
                : getView().getRightPlayerName();
    }
}
