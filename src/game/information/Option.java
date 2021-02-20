package game.information;

import game.Game;
import game.Home;
import game.board.Board;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Option extends JPanel {

    private JButton btContinue;
    private JButton btNewGame;
    private JButton btGoHome;

    public Option(){ initOption(); }


    private void initOption(){
        setBounds((Game.WIDTH-400)/2,(Game.HEIGHT-190)/2,400,190);
        setBackground(Player.oranColor);
        setBorder(BorderFactory.createLineBorder(Player.borColor, 2));
        setLayout(null);

        btContinue = new JButton("CONTINUE");
        setButton(btContinue,10);

        btNewGame = new JButton("NEW GAME");
        setButton(btNewGame,70);

        btGoHome = new JButton("GO TO HOME");
        setButton(btGoHome,130);


        btContinue.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

            }
        });


        btNewGame.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

            }
        });

        btGoHome.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Home.switchPanels(new Choose());
            }
        });
    }
    //Custom button
    private void setButton(JButton button, int y){
        button.setForeground(Player.borColor);
        button.setFont(new Font("Tahoma", Font.BOLD, 10));
        button.setBackground(Player.oranColor);
        button.setBounds(10, y, 380, 50);
        button.setBorder(BorderFactory.createLineBorder(Player.borColor, 2));
        add(button);
    }

}
