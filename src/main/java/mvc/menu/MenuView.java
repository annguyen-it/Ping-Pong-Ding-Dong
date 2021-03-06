package main.java.mvc.menu;

import main.java.App;
import main.java.mvc.common.View;
import main.java.utils.image.ImagePathProvider;

import javax.swing.*;
import javax.swing.plaf.ColorUIResource;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.PlainDocument;
import java.awt.*;

public class MenuView extends View {

    private final JLabel background = new JLabel(
            "",
            new ImageIcon(ImagePathProvider.Menu.background),
            JLabel.CENTER
    );

    private final JLabel buttonsWrapper = new JLabel();

    private final JButton playButton = new JButton("PLAY");
    private final JButton helpButton = new JButton("HELP");
    private final JButton rankingButton = new JButton();
    private final JButton exitButton = new JButton("EXIT");

    private final JPanel playDialog = new JPanel();
    private final JTextField playerNameTextField1 = new JTextField(new PlayerNameTextFieldLimit(), "Player 1", 10);
    private final JTextField playerNameTextField2 = new JTextField(new PlayerNameTextFieldLimit(), "Player 2", 10);

    private final JLabel helpDialog = new JLabel("Di chuyen Paddle de Ball khong bi roi ra ngoai");

    private final Font buttonFont = new Font("Algerian", Font.PLAIN, 30);

    public MenuView() {
        super();
    }

    //region getter

    public JButton getHelpButton() { return helpButton; }

    public JButton getExitButton() { return exitButton; }

    public JButton getPlayButton() { return playButton; }

    public JButton getRankingButton() { return rankingButton; }

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
        setupButtons();
        setupDialogs();
        setupBackground();
    }


    private void setupUI() {
        UIManager.put("OptionPane.background", App.primaryColor);
        UIManager.put("Panel.background", App.primaryColor);
        UIManager.put("Button.background", Color.ORANGE);
        UIManager.put("Button.focus", new ColorUIResource(new Color(0, 0, 0, 0)));
    }

    private void setupButtons() {
        buttonsWrapper.setBounds(450, 370, 300, 300);

        setupButton(playButton, 0);
        setupButton(helpButton, 75);
        setupButton(rankingButton, 150);
        setupButton(exitButton, 225);
    }

    private void setupButton(JButton button, int y) {
        buttonsWrapper.add(button);

        button.setBounds(0, y, 300, 50);

        button.setBorder(BorderFactory.createLineBorder(App.secondaryColor, 2));
        button.setFont(buttonFont);
        button.setForeground(App.secondaryColor);
        button.setBackground(App.primaryColor);

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
        playDialog.setBackground(App.primaryColor);
    }

    private void setupHelpDialog() {
        helpDialog.setPreferredSize(new Dimension(400, 75));
        helpDialog.setHorizontalAlignment(CENTER);
    }

    private void setupBackground() {
        background.setBounds(0, 0, App.WIDTH, App.HEIGHT);
        add(background);
    }

    //endregion

    private static class PlayerNameTextFieldLimit extends PlainDocument {

        @Override
        public void insertString(int offset, String str, AttributeSet set) throws BadLocationException {
            if (str != null) {
                if ((getLength() + str.length()) <= 30) {
                    super.insertString(offset, str, set);
                }
            }
        }
    }
}
