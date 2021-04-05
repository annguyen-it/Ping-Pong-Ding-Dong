package v2.view;

import game.Game;
import v2.controller.Controller;
import v2.model.EnterNameDialogModel;
import v2.model.GameModel;

import javax.swing.*;
import java.awt.*;

public class MenuView extends View {

    JLabel background;
    JLabel buttonsWrapper = new JLabel();
    JButton helpButton;
    JButton exitButton;
    JButton playButton;

    public MenuView(Controller controller) {
        super(controller);
    }

    //region UI

    @Override
    public void initUI() {
        setLayout(null);
        setupButtons();
        setupBackground();
    }

    private void setupButtons() {
        addButtons();
        positionButtons();
    }

    private void addButtons() {
        helpButton = new JButton("HELP");
        exitButton = new JButton("EXIT");
        playButton = new JButton("PLAY");
    }

    private void positionButtons() {
        positionButton(playButton, 0);
        positionButton(helpButton, 100);
        positionButton(exitButton, 200);
    }

    private void positionButton(JButton button, int yb) {
        buttonsWrapper.setBounds(450, 370, 300, 300);
        buttonsWrapper.add(button);

        button.setBounds(0, yb, 300, 50);
        Color borColor = new Color(0, 21, 232);

        button.setBorder(BorderFactory.createLineBorder(borColor, 2));
        button.setFont(new Font("Algerian", Font.PLAIN, 30));
        button.setForeground(borColor);
        button.setBackground(new Color(251, 111, 0));

        add(buttonsWrapper);
    }

    private void setupBackground() {
        ImageIcon image = new ImageIcon("Client.jpg");
        background = new JLabel("", image, JLabel.CENTER);
        background.setBounds(0, 0, Game.WIDTH, Game.HEIGHT);

        add(background);
    }

    //endregion

    //region Events

    @Override
    public void initEvent() {
        addPlayButtonEvent();
        addHelpButtonEvent();
        addExitButtonEvent();
    }

    private void addPlayButtonEvent() {
        playButton.addActionListener(e -> {
            JTextField playerNameTextField1 = new JTextField("Player 1");
            JTextField playerNameTextField2 = new JTextField("Player 2");

            JPanel dialog = new JPanel();
            dialog.add(new JLabel("x:"));
            dialog.add(playerNameTextField1);
            dialog.add(Box.createHorizontalStrut(15)); // a spacer
            dialog.add(new JLabel("y:"));
            dialog.add(playerNameTextField2);

            int result = JOptionPane.showConfirmDialog(
                    null,
                    dialog,
                    "message",
                    JOptionPane.OK_CANCEL_OPTION
            );

            if (result == JOptionPane.OK_OPTION){
                String playerName1 = playerNameTextField1.getText();
                String playerName2 = playerNameTextField2.getText();

                EnterNameDialogModel model = new EnterNameDialogModel(playerName1, playerName2);

                controller.setModel(new GameModel(controller));
                controller.setView(new GameView(controller, model));
            }
        });
    }

    private void addHelpButtonEvent() {
        helpButton.addActionListener(e -> {
            String[] play = { "Play", "Cancel" };
            JOptionPane.showOptionDialog(
                    null,
                    "Di chuyen Paddle de Ball khong bi roi ra ngoai",
                    "Help",
                    JOptionPane.OK_CANCEL_OPTION,
                    JOptionPane.INFORMATION_MESSAGE,
                    null,
                    play,
                    0);
        });
    }

    private void addExitButtonEvent() {
        exitButton.addActionListener(e -> {
            int output = JOptionPane.showConfirmDialog(
                    this,
                    "Do you want to exit",
                    " ",
                    JOptionPane.YES_NO_OPTION);

            if (output == JOptionPane.YES_OPTION) {
                System.exit(0);
            }
        });
    }

    //endregion
}
