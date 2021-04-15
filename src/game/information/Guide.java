package game.information;

import game.Game;
import game.Home;
import game.board.Board;
import game.information.Player;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Guide extends JPanel {

    private JTextField txtHelp;
    private JTextArea txtGuide;
    private JButton btPlayGame;
    private JButton btBack;

    public Guide(){
        initGuide();
    }

    private void initGuide(){
        setBackground(Player.oranColor);
        setBounds((Game.WIDTH-450)/2, 250, 450, 300);
        setBorder(BorderFactory.createLineBorder(Player.borColor, 2));
        setLayout(null);

        txtHelp = new JTextField("HELP");
        setTitle(txtHelp);

        txtGuide = new JTextArea("Hay di chuyen thanh Paddle de qua bong cua ban khong bi bay ra ngoai nhe. Nho an that nhieu ngoi sao de co them suc manh nhe");
        setGuide(txtGuide);

        btPlayGame = new JButton("LET'S PLAY");
        setButton(btPlayGame, 310);

        btBack = new JButton("< Back");
        setButton(btBack, 40);

        btPlayGame.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) { Home.switchPanels(new Board()); }
        });

        btBack.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Home.switchPanels(new Choose());
            }
        });


    }

    //Custom Title
    private void setTitle(JTextField txtHelp){
        txtHelp.setForeground(Color.WHITE);
        txtHelp.setBackground(Player.oranColor);
        txtHelp.setEditable(false);
        txtHelp.setFont(new Font("Tahoma", Font.BOLD, 20));
        txtHelp.setHorizontalAlignment(SwingConstants.CENTER);
        txtHelp.setBorder(null);
        txtHelp.setBounds(105, 10,230 ,30);
        add(txtHelp);
    }

//    Custom Help
    private void setGuide(JTextArea txtGuide){
        txtGuide.setFont(new Font("Tahoma", Font.PLAIN, 15));
        txtGuide.setForeground(Color.WHITE);
        txtGuide.setBackground(Player.oranColor);
        txtGuide.setWrapStyleWord(true);
        txtGuide.setLineWrap(true);
        txtGuide.setBounds(61, 64, 297, 160);
        add(txtGuide);
    }

    //Custom button
    private void setButton(JButton button, int x){
        button.setForeground(Player.borColor);
        button.setFont(new Font("Tahoma", Font.BOLD, 10));
        button.setBackground(Player.oranColor);
        button.setBounds(x, 250, 100, 30);
        button.setBorder(BorderFactory.createLineBorder(Player.borColor, 2));
        add(button);
    }
}
