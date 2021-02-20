package game.information;

import game.Game;
import game.Home;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Choose extends JPanel {


    private JButton play;
    private JButton exit;
    private JButton help;


    public Choose(){ initChoose(); }

    private  void initChoose(){

        setBounds((Game.WIDTH-300)/2,400,300,600);
        setLayout(null);

        play = new JButton("PLAY");
        help = new JButton("HELP");
        exit = new JButton("EXIT");

        setButtons(play,  help, exit);

        // Dien ten nguoi choi
        play.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Home.switchPanels(new Player());
            }
        });

        //Huong dan choi game
        help.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Home.switchPanels(new Guide());
            }
        });

        //Thoat game
        exit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Home.switchPanels(new Exit());
            }
        });


    }

    //Dinh dang button
    private void setButton(JButton button,  int y ) {
        add(button);
        button.setBounds(0,y,300,50);
        button.setFont(new Font("Algerian",Font.PLAIN,30));
        Color myColor = new Color(251, 111, 0);
        button.setBackground(myColor);

    }

    private void setButtons(JButton play, JButton help, JButton exit){
        setButton(play,0);
        setButton(help,100);
        setButton(exit,200);
    }
}
