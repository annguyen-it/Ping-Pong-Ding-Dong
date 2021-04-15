package v2.view;

import game.Game;
import v2.JTextFieldLimit;
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
        setupDialog();
    }

    //Mau nen cho dialog
    public void setupDialog(){
        UIManager UI = new UIManager();
        //  UI.put("text", new ColorUIResource(255,0,0));
        // UI.getLookAndFeelDefaults().put("Panel.background",new ColorUIResource(255,0,0));
        UI.put("OptionPane.background", v2.Game.orangeColor);
        UI.put("Panel.background", v2.Game.orangeColor);
        UI.put("Button.background", Color.ORANGE);

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

    @Override
    public void initEvent() {
        addPlayButtonEvent();
        addHelpButtonEvent();
        addExitButtonEvent();
    }



    private void addPlayButtonEvent() {
        playButton.addActionListener(e -> {
            JTextField playerNameTextField1 = new JTextField(new JTextFieldLimit(10),"Player 1",10);
            JTextField playerNameTextField2 = new JTextField(new JTextFieldLimit(10),"Player 2",10);


            JPanel dialog = new JPanel();
            dialog.setPreferredSize(new Dimension(400,50));
            dialog.add(new JLabel("Player 1:"));
            dialog.add(playerNameTextField1);
            dialog.add(Box.createHorizontalStrut(15)); // a spacer
            dialog.add(new JLabel("Player 2:"));
            dialog.add(playerNameTextField2);
            dialog.setBackground(v2.Game.orangeColor);


            int result = JOptionPane.showConfirmDialog(
                    null,
                    dialog,
                    "Fill in your names",
                    JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE
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
            String[] play = { "     OK     " };

            JLabel helpp = new JLabel("Di chuyen Paddle de Ball khong bi roi ra ngoai");
            helpp.setPreferredSize(new Dimension(400,75));
            helpp.setHorizontalAlignment(CENTER);
            JOptionPane.showOptionDialog(
                    null,
                    helpp,
                    "Help",
                    JOptionPane.OK_CANCEL_OPTION,
                    JOptionPane.PLAIN_MESSAGE,
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
