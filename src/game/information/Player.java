package game.information;

import game.Game;
import game.Home;
import game.board.Board;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Player extends JPanel{

    public static String name1;
    public static String name2;

    public static final Color borColor = new Color(0, 21, 232);
    public static final Color oranColor = new Color(251, 111, 0);


    private JTextField txtTitle;

    private JTextField txtName1;
    private JTextField txtName2;

    private JTextField txtPlayer1;
    private JTextField txtPlayer2;

    private JButton btPlayGame;
    private JButton btBack;



    public Player(){ initPlay();
        }

    private void initPlay(){
        setBounds((Game.WIDTH-400)/2, 250, 400, 270);
        setBackground(oranColor);
        setBorder(new LineBorder(borColor, 2, true));
        setLayout(null);


        txtTitle = new JTextField("ENTER YOUR NAMES");
        setTitle(txtTitle);

        txtName1 = new JTextField("Player 1");
        setName(txtName1,64);


        txtName2 = new JTextField("Player 2");
        setName(txtName2, 146);


        txtPlayer1 = new JTextField("PLAYER 1 :");
        setPlayer(txtPlayer1, 51);

        txtPlayer2 = new JTextField("PLAYER 2 :");
        setPlayer(txtPlayer2, 133);


        btPlayGame = new JButton("LET'S PLAY");
        setButton(btPlayGame,280);

        btBack = new JButton("< Back");
        setButton(btBack,20);


        btPlayGame.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Home.switchPanels(new Board());


                name1 = txtName1.getText();
                name2 = txtName2.getText();
            }
        });


        btBack.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) { Home.switchPanels(new Choose()); }
        });

    }



    //Custom Title
    private void setTitle(JTextField txtTitle){
        txtTitle.setBackground(oranColor);
        txtTitle.setForeground(Color.WHITE);
        txtTitle.setEditable(false);
        txtTitle.setFont(new Font("Tahoma", Font.BOLD, 20));
        txtTitle.setHorizontalAlignment(SwingConstants.CENTER);
        txtTitle.setBounds(110, 10, 230, 30);
        txtTitle.setBorder(null);
        add(txtTitle);
    }

    private void setName(JTextField txtName, int y){
        txtName.setBounds(110, y, 240, 45);
        txtName.setHorizontalAlignment(SwingConstants.CENTER);
        txtName.setFont(new Font("Tahoma", Font.PLAIN, 12));
        txtName.setBackground(new Color(255, 153, 0));
        txtName.setBorder(BorderFactory.createLineBorder(borColor, 2));
        add(txtName);
    }

    private void setPlayer(JTextField txtPlayer, int y){
        txtPlayer.setBounds(10, y, 96, 30);
        txtPlayer.setHorizontalAlignment(SwingConstants.CENTER);
        txtPlayer.setFont(new Font("Tahoma", Font.BOLD, 10));
        txtPlayer.setBackground(oranColor);
        txtPlayer.setEditable(false);
        txtPlayer.setForeground(Color.WHITE);
        txtPlayer.setBorder(BorderFactory.createLineBorder(borColor, 2));
        add(txtPlayer);
    }

    private void setButton(JButton button, int x){
        button.setForeground(borColor);
        button.setFont(new Font("Tahoma", Font.BOLD, 10));
        button.setBackground(oranColor);
        button.setBounds(x, 220, 100, 30);
        button.setBorder(BorderFactory.createLineBorder(borColor, 2));
        add(button);
    }
}
