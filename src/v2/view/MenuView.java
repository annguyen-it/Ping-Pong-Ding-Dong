package v2.view;

import v2.Game;
import v2.JTextFieldLimit;

import javax.swing.*;
import java.awt.*;

public class MenuView extends View {

    JLabel background;
    JLabel buttonsWrapper = new JLabel();

    private final JButton helpButton = new JButton("HELP");
    private final JButton exitButton = new JButton("EXIT");
    private final JButton playButton = new JButton("PLAY");

    private final JPanel playDialog = new JPanel();
    private final JTextField playerNameTextField1 = new JTextField(new JTextFieldLimit(10), "Player 1", 10);
    private final JTextField playerNameTextField2 = new JTextField(new JTextFieldLimit(10), "Player 2", 10);

    private final JLabel helpDialog = new JLabel("Di chuyen Paddle de Ball khong bi roi ra ngoai");

    public MenuView() { super(); }

    //region getter

    public JButton getHelpButton() {
        return helpButton;
    }

    public JButton getExitButton() {
        return exitButton;
    }

    public JButton getPlayButton() {
        return playButton;
    }

    public JPanel getPlayDialog() {
        return playDialog;
    }

    public JTextField getPlayerNameTextField1() {
        return playerNameTextField1;
    }

    public JTextField getPlayerNameTextField2() {
        return playerNameTextField2;
    }

    //endregion

    //region UI

    @Override
    public void initUI() {
        setLayout(null);
        setupBackground();
        setupButtons();
        setupDialogs();
    }

    //Mau nen cho dialog
    public void setupDialogs() {
        setupPlayDialog();
        setupHelpDialog();
        setupDialogUI();
    }

    private void setupPlayDialog() {
        playDialog.setPreferredSize(new Dimension(400, 50));
        playDialog.add(new JLabel("Player 1:"));
        playDialog.add(playerNameTextField1);
        playDialog.add(Box.createHorizontalStrut(15)); // a spacer
        playDialog.add(new JLabel("Player 2:"));
        playDialog.add(playerNameTextField2);
        playDialog.setBackground(v2.Game.orangeColor);
    }

    private void setupHelpDialog() {
        helpDialog.setPreferredSize(new Dimension(400, 75));
        helpDialog.setHorizontalAlignment(CENTER);
    }

    private void setupDialogUI() {
        UIManager UI = new UIManager();
        //  UI.put("text", new ColorUIResource(255,0,0));
        // UI.getLookAndFeelDefaults().put("Panel.background",new ColorUIResource(255,0,0));
        UIManager.put("OptionPane.background", v2.Game.orangeColor);
        UIManager.put("Panel.background", v2.Game.orangeColor);
        UIManager.put("Button.background", Color.ORANGE);
    }

    private void setupButtons() {
        positionButtons();
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

        button.setBorder(BorderFactory.createLineBorder(v2.Game.blueColor, 2));
        button.setFont(new Font("Algerian", Font.PLAIN, 30));
        button.setForeground(v2.Game.blueColor);
        button.setBackground(v2.Game.orangeColor);

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

    //    @Override
    //    public void initEvent() {
    //        addPlayButtonEvent();
    //        addHelpButtonEvent();
    //        addExitButtonEvent();
    //    }


    //    private void addPlayButtonEvent() {
    //        playButton.addActionListener(e -> {
    //            JTextField playerNameTextField1 = new JTextField(new JTextFieldLimit(10), "Player 1", 10);
    //            JTextField playerNameTextField2 = new JTextField(new JTextFieldLimit(10), "Player 2", 10);
    //
    //
    //            JPanel dialog = new JPanel();
    //            dialog.setPreferredSize(new Dimension(400, 50));
    //            dialog.add(new JLabel("Player 1:"));
    //            dialog.add(playerNameTextField1);
    //            dialog.add(Box.createHorizontalStrut(15)); // a spacer
    //            dialog.add(new JLabel("Player 2:"));
    //            dialog.add(playerNameTextField2);
    //            dialog.setBackground(v2.Game.orangeColor);
    //
    //
    //            int result = JOptionPane.showConfirmDialog(
    //                    null,
    //                    dialog,
    //                    "Fill in your names",
    //                    JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE
    //            );
    //
    //            if (result == JOptionPane.OK_OPTION) {
    //                String playerName1 = playerNameTextField1.getText();
    //                String playerName2 = playerNameTextField2.getText();
    //
    //                EnterNameDialogModel model = new EnterNameDialogModel(playerName1, playerName2);
    //
    //                controller.setModel(new GameModel(controller));
    //                controller.setView(new GameView(controller, model));
    //            }
    //        });
    //    }

    //    private void addHelpButtonEvent() {
    //        helpButton.addActionListener(e -> {
    //            String[] play = { "     OK     " };
    //
    //            JLabel helpp = new JLabel("Di chuyen Paddle de Ball khong bi roi ra ngoai");
    //            helpp.setPreferredSize(new Dimension(400, 75));
    //            helpp.setHorizontalAlignment(CENTER);
    //            JOptionPane.showOptionDialog(
    //                    null,
    //                    helpp,
    //                    "Help",
    //                    JOptionPane.OK_CANCEL_OPTION,
    //                    JOptionPane.PLAIN_MESSAGE,
    //                    null,
    //                    play,
    //                    0);
    //        });
    //    }

    //    private void addExitButtonEvent() {
    //        exitButton.addActionListener(e -> {
    //            int output = JOptionPane.showConfirmDialog(
    //                    this,
    //                    "Do you want to exit",
    //                    " ",
    //                    JOptionPane.YES_NO_OPTION);
    //
    //            if (output == JOptionPane.YES_OPTION) {
    //                System.exit(0);
    //            }
    //        });
    //    }

    //endregion
}
