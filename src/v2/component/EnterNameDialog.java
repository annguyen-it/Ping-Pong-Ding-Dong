package v2.component;

import v2.Game;
import v2.controller.Controller;
import v2.model.EnterNameDialogModel;
import v2.model.MenuModel;
import v2.view.GameView;
import v2.view.View;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;

public class EnterNameDialog extends JPanel {

    private final View parentView;

    private JTextField playerNameTextField1;
    private JTextField playerNameTextField2;

    private EnterNameDialogModel model;

    private JButton playButton;

    public EnterNameDialog(View parentView) {
        this.parentView = parentView;

        setup();
        initUI();
        initEvent();
    }

    private void setup() {
        setBounds(Game.WIDTH/2 - 450/2, Game.HEIGHT/2 - 350/2, 450, 300);

        setBackground(Game.orangeColor);
        setBorder(new LineBorder(Game.blueColor, 2, true));
        setLayout(null);
    }

    //region UI

    private void initUI() {
        addDialogTitle();
        addNameArea();
        addPlayButton();

        updateUI();
        repaint();
    }

    //region Init Dialog Title
    private void addDialogTitle() {
        JTextField dialogTitle = new JTextField("ENTER YOUR NAMES");
        dialogTitle.setBackground(Game.orangeColor);
        dialogTitle.setForeground(Color.WHITE);
        dialogTitle.setEditable(false);
        dialogTitle.setFont(new Font("Tahoma", Font.BOLD, 20));
        dialogTitle.setHorizontalAlignment(SwingConstants.CENTER);
        dialogTitle.setBounds(110, 10, 230, 30);
        dialogTitle.setBorder(null);
        add(dialogTitle);
    }
    //endregion

    //region Init Name Area

    private void addNameArea() {
        addPlayerLabels();
        addPlayerNameTextField();
    }

    private void addPlayerLabels() {
        addPlayerLabel(1, 52);
        addPlayerLabel(2, 133);
    }

    private void addPlayerLabel(int player, int y) {
        JTextField playerLabel = new JTextField("PLAYER " + player + ":");
        playerLabel.setBounds(10, y, 96, 30);
        playerLabel.setDropMode(DropMode.INSERT);
        playerLabel.setHorizontalAlignment(SwingConstants.CENTER);
        playerLabel.setFont(new Font("Tahoma", Font.BOLD, 10));
        playerLabel.setBackground(Game.orangeColor);
        playerLabel.setEditable(false);
        add(playerLabel);
        playerLabel.setColumns(10);
        playerLabel.setForeground(Color.WHITE);
        playerLabel.setBorder(BorderFactory.createLineBorder(Game.blueColor, 2));
    }

    private void addPlayerNameTextField() {
        playerNameTextField1 = new JTextField("Player 1");
        playerNameTextField1.setBounds(110, 64, 241, 45);
        playerNameTextField1.setHorizontalAlignment(SwingConstants.CENTER);
        playerNameTextField1.setFont(new Font("Tahoma", Font.PLAIN, 12));
        playerNameTextField1.setBackground(new Color(255, 153, 0));
        add(playerNameTextField1);
        playerNameTextField1.setBorder(BorderFactory.createLineBorder(Game.blueColor, 2));


        playerNameTextField2 = new JTextField("Player 2");
        playerNameTextField2.setBounds(110, 145, 241, 45);
        playerNameTextField2.setHorizontalAlignment(SwingConstants.CENTER);
        playerNameTextField2.setFont(new Font("Tahoma", Font.PLAIN, 12));
        playerNameTextField2.setBackground(new Color(255, 153, 0));
        add(playerNameTextField2);
        playerNameTextField2.setBorder(BorderFactory.createLineBorder(Game.blueColor, 2));
    }

    //endregion

    //region Init play button

    private void addPlayButton() {
        playButton = new JButton("LET PLAY");
        playButton.setForeground(Game.blueColor);
        playButton.setFont(new Font("Tahoma", Font.BOLD, 10));
        playButton.setBackground(Game.orangeColor);
        playButton.setBounds(281, 220, 103, 30);
        playButton.setBorder(BorderFactory.createLineBorder(Game.blueColor, 2));
        add(playButton, Integer.valueOf(1));
    }

    //endregion

    //endregion

    //region Events

    private void initEvent() {
        addPlayButtonEvent();
    }

    private void addPlayButtonEvent() {
        playButton.addActionListener(e -> {
            String playerName1 = playerNameTextField1.getText();
            String playerName2 = playerNameTextField2.getText();

            model = new EnterNameDialogModel(playerName1, playerName2);

            Controller controller = parentView.getController();
            controller.setView(new GameView(controller, model));
            controller.setModel(new MenuModel(controller));
        });
    }

    //endregion
}
