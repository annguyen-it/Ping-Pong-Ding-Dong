package v2.view;

import v2.Game;
import v2.JTextFieldLimit;

import javax.swing.*;
import javax.swing.plaf.ColorUIResource;
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

    public JButton getHelpButton() { return helpButton; }

    public JButton getExitButton() { return exitButton; }

    public JButton getPlayButton() { return playButton; }

    public JPanel getPlayDialog() { return playDialog; }

    public JTextField getPlayerNameTextField1() { return playerNameTextField1; }

    public JTextField getPlayerNameTextField2() { return playerNameTextField2; }

    public JLabel getHelpDialog() { return helpDialog; }

    //endregion

    //region UI

    @Override
    public void initUI() {
        setLayout(null);
        setupUI();
        setupBackground();
        setupButtons();
        setupDialogs();
    }


    private void setupUI() {
        UIManager.put("OptionPane.background", v2.Game.orangeColor);
        UIManager.put("Panel.background", v2.Game.orangeColor);
        UIManager.put("Button.background", Color.ORANGE);
        UIManager.put("Button.focus", new ColorUIResource(new Color(0, 0, 0, 0)));
    }


    private void setupBackground() {
        ImageIcon image = new ImageIcon("resources/img/Client.jpg");
        background = new JLabel("", image, JLabel.CENTER);
        background.setBounds(0, 0, Game.WIDTH, Game.HEIGHT);

        add(background);
    }


    private void setupButtons() {
        setupButton(playButton, 0);
        setupButton(helpButton, 100);
        setupButton(exitButton, 200);
    }

    private void setupButton(JButton button, int yb) {
        buttonsWrapper.setBounds(450, 370, 300, 300);
        buttonsWrapper.add(button);

        button.setBounds(0, yb, 300, 50);

        button.setBorder(BorderFactory.createLineBorder(v2.Game.blueColor, 2));
        button.setFont(new Font("Algerian", Font.PLAIN, 30));
        button.setForeground(v2.Game.blueColor);
        button.setBackground(v2.Game.orangeColor);

        add(buttonsWrapper);
    }

    public void setupDialogs() {
        setupPlayDialog();
        setupHelpDialog();
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
    //endregion
}
