package v2.controller;

import v2.model.EnterNameDialogModel;
import v2.model.GameModel;
import v2.model.MenuModel;
import v2.view.GameView;
import v2.view.MenuView;

import javax.swing.*;

public class MenuController extends Controller<MenuView, MenuModel> {

    public MenuController(FlowController flowController) {
        super(flowController, new MenuView(), new MenuModel());
    }

    @Override
    public void initEvent() {
        addPlayButtonEvent();
        addHelpButtonEvent();
        addExitButtonEvent();
    }

    private void addPlayButtonEvent() {
        MenuView view = getView();

        view.getPlayButton().addActionListener(e -> {
            int result = JOptionPane.showConfirmDialog(
                    null,
                    view.getPlayDialog(),
                    "Fill in your names",
                    JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE
            );

            if (result == JOptionPane.OK_OPTION) {
                String playerName1 = view.getPlayerNameTextField1().getText();
                String playerName2 = view.getPlayerNameTextField2().getText();

                EnterNameDialogModel model = new EnterNameDialogModel(playerName1, playerName2);
                GameView gameView = new GameView(model);
                GameModel gameModel = new GameModel();

                GameController gameController = new GameController(flowController, gameView, gameModel);

                gameView.setController(gameController);

                switchController(gameController);
            }
        });
    }

    private void addHelpButtonEvent() {
        MenuView view = getView();
        String[] play = { "OK" };

        view.getHelpButton().addActionListener(e ->
                JOptionPane.showOptionDialog(
                        null,
                        view.getHelpDialog(),
                        "Help",
                        JOptionPane.OK_CANCEL_OPTION,
                        JOptionPane.PLAIN_MESSAGE,
                        null,
                        play,
                        null)
        );
    }

    private void addExitButtonEvent() {
        MenuView view = getView();

        view.getExitButton().addActionListener(e -> {
            int output = JOptionPane.showConfirmDialog(
                    view,
                    "Do you want to exit",
                    " ",
                    JOptionPane.YES_NO_OPTION);

            if (output == JOptionPane.YES_OPTION) {
                System.exit(0);
            }
        });
    }
}
