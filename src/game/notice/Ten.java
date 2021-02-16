package game.notice;

import game.board.Board;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import javax.swing.border.LineBorder;
import java.awt.Font;
import java.awt.Button;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;


public class Ten extends JDialog {

    private final JPanel contentPanel = new JPanel();
    private JTextField txtPlayer_1;
    private JTextField txtPlayer;
    private JTextField txtPlayer_2;
    private JTextField txtPlayer_3;
    private JButton button_1;
    private JTextField txtEnterYourNames;
    private boolean playGame = false;

    private String name1 = "Player 1";
    private String name2 = "Player 2";

    // private boolean getPlay = false;

    /**
     * Create the dialog.
     */
    public Ten(JFrame game, JPanel client) {
        Color borColor = new Color(0, 21, 232);
        Color oranColor = new Color(251, 111, 0);
        setBounds(100, 100, 450, 300);
        getContentPane().setLayout(new BorderLayout());
        contentPanel.setBackground(oranColor);
        contentPanel.setBorder(new LineBorder(borColor, 2, true));
        getContentPane().add(contentPanel, BorderLayout.CENTER);
        setLocationRelativeTo(null);
        setResizable(false);
        contentPanel.setLayout(null);

        txtPlayer_1 = new JTextField("Player 1");
        txtPlayer_1.setBounds(110, 64, 241, 45);
        txtPlayer_1.setHorizontalAlignment(SwingConstants.CENTER);
        txtPlayer_1.setFont(new Font("Tahoma", Font.PLAIN, 12));
        txtPlayer_1.setBackground(new Color(255, 153, 0));
        contentPanel.add(txtPlayer_1);
        txtPlayer_1.setBorder(BorderFactory.createLineBorder(borColor, 2));

        txtPlayer = new JTextField("PLAYER 1 :");
        txtPlayer.setBounds(10, 52, 96, 30);
        txtPlayer.setDropMode(DropMode.INSERT);
        txtPlayer.setHorizontalAlignment(SwingConstants.CENTER);
        txtPlayer.setFont(new Font("Tahoma", Font.BOLD, 10));
        txtPlayer.setBackground(oranColor);
        txtPlayer.setEditable(false);
        contentPanel.add(txtPlayer);
        txtPlayer.setColumns(10);
        txtPlayer.setForeground(Color.WHITE);
        txtPlayer.setBorder(BorderFactory.createLineBorder(borColor, 2));

        txtPlayer_2 = new JTextField();
        txtPlayer_2.setBounds(110, 145, 241, 46);
        txtPlayer_2.setText("Player 2");
        txtPlayer_2.setHorizontalAlignment(SwingConstants.CENTER);
        txtPlayer_2.setFont(new Font("Tahoma", Font.PLAIN, 12));
        txtPlayer_2.setBackground(new Color(255, 153, 0));
        contentPanel.add(txtPlayer_2);
        txtPlayer_2.setBorder(BorderFactory.createLineBorder(borColor, 2));

        txtPlayer_3 = new JTextField();
        txtPlayer_3.setBounds(10, 133, 96, 30);
        txtPlayer_3.setText("PLAYER 2 :");
        txtPlayer_3.setHorizontalAlignment(SwingConstants.CENTER);
        txtPlayer_3.setForeground(Color.WHITE);
        txtPlayer_3.setFont(new Font("Tahoma", Font.BOLD, 10));
        txtPlayer_3.setEditable(false);
        txtPlayer_3.setBackground(oranColor);
        contentPanel.add(txtPlayer_3);
        txtPlayer_3.setBorder(BorderFactory.createLineBorder(borColor, 2));


        button_1 = new JButton("LET PLAY");
        button_1.setForeground(borColor);
        button_1.setFont(new Font("Tahoma", Font.BOLD, 10));
        button_1.setBackground(oranColor);
        button_1.setBounds(281, 220, 103, 30);
        button_1.setBorder(BorderFactory.createLineBorder(borColor, 2));
        contentPanel.add(button_1);


        txtEnterYourNames = new JTextField("ENTER YOUR NAMES");
        txtEnterYourNames.setBackground(oranColor);
        txtEnterYourNames.setForeground(Color.WHITE);
        txtEnterYourNames.setEditable(false);
        txtEnterYourNames.setFont(new Font("Tahoma", Font.BOLD, 20));
        txtEnterYourNames.setHorizontalAlignment(SwingConstants.CENTER);
        txtEnterYourNames.setBounds(110, 10, 230, 30);
        txtEnterYourNames.setBorder(null);
        contentPanel.add(txtEnterYourNames);


        button_1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                name1 = txtPlayer_1.getText();
                name2 = txtPlayer_2.getText();

                Board board = new Board();

                System.out.println(client);

                game.remove(client);
                game.add(board);

//                frame1.setVisible(false);
//                frame2.add(new Board());
//                frame2.setVisible(true);
//                frame2.setResizable(false);
//                frame2.setLocationRelativeTo(null);
//                frame2.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            }
        });
    }

    public boolean getPlay() {
        return playGame;
    }

    public String getName1() {
        return name1;
    }

    public String getName2() {
        return name2;
    }

}
