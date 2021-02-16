package game;

import game.notice.Ten;

import java.awt.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import javax.swing.border.Border;


public class Client extends JFrame  {
    public static final JButton play = new JButton("PLAY");;
    public JLabel label= new JLabel();
    public JLabel label2= new JLabel();

    public String name1 = "Player 1";
    public String name2 = "Player 2";

    public Client(){
        initClient();
    }
    private void initClient() {
        Ten dialog;
        JFrame frame1 = new JFrame();
        JFrame frame2 = new JFrame();
      //  JFrame frame3 = new JFrame();
        frame1.setSize(Game.WIDTH, Game.HEIGHT);
        frame2.setSize(Game.WIDTH, Game.HEIGHT);

        ImageIcon image = new ImageIcon("Client.jpg");
        JLabel background = new JLabel ("", image,JLabel.CENTER);
        background.setBounds(0, 0, Game.WIDTH, Game.HEIGHT);

        frame1.add(label);


        JButton help = new JButton("HELP");
        JButton exit = new JButton("EXIT");


        JLabel play1 = new JLabel();
        setButton(play,play1,(Game.WIDTH-300)/2,370,0);
        setButton(help,play1,(Game.WIDTH-300)/2,370,100);
        setButton(exit,play1,(Game.WIDTH-300)/2,370,200);

        label.add(background);
        label.setVisible(true);

        frame1.setResizable(false);
        frame1.setVisible(true);
        frame1.setLocationRelativeTo(null);
        frame1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


        //EXIT
        exit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int output = JOptionPane.showConfirmDialog(frame1
                        , "Do you want to exit"
                        ," "
                        ,JOptionPane.YES_NO_OPTION);

                if(output == JOptionPane.YES_OPTION){
                        System.exit(0);
                }else if(output == JOptionPane.NO_OPTION){

                }
            }
        });
        //Huong dan choi game
        help.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String[] playy ={"Play","Cancel"};
                int option = JOptionPane.showOptionDialog(null, "Di chuyen Paddle de Ball khong bi roi ra ngoai", "Help", JOptionPane.OK_CANCEL_OPTION, JOptionPane.INFORMATION_MESSAGE,null,playy,0);
                if (option == JOptionPane.OK_OPTION)
                {
                    frame1.setVisible(false);
                    frame2.add(new Board());
                    frame2.setVisible(true);
                    frame2.setResizable(false);
                    frame2.setLocationRelativeTo(null);
                    frame2.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

                }

            }
        });

        play.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            // Dien ten nguoi choi
                Ten dialog = new Ten();
                dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
                dialog.setVisible(true);

                if(dialog.getPlay()==true){
                    frame1.setVisible(false);
                    frame2.add(new Board());
                    frame2.setVisible(true);
                    frame2.setResizable(false);
                    frame2.setLocationRelativeTo(null);
                    frame2.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                }


            }

//                int option = JOptionPane.showConfirmDialog(null, message, "Enter your name", JOptionPane.OK_OPTION);
//                if (option == JOptionPane.OK_OPTION)
//                {
//                    //luu ten nguoi choi
//                    name1 = field1.getText();
//                    name2 = field2.getText();
//
//                    //Play game

//                    frame1.setVisible(false);
//                    frame2.add(new Board());
//                    frame2.setVisible(true);
//                    frame2.setResizable(false);
//                    frame2.setLocationRelativeTo(null);
//                    frame2.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);




           // }
        });

    }
    public void setButton(JButton button, JLabel labelx, int x, int yl,int yb ) {

        label.add(labelx);
        labelx.setBounds(x, yl, 300, 300);
        labelx.add(button);
        button.setBounds(0,yb,300,50);
        button.setFont(new Font("Algerian",Font.PLAIN,30));
        Color myColor = new Color(251, 111, 0);
        Color borColor = new Color(0, 21, 232);
        button.setBackground(myColor);
        button.setForeground(borColor);
        button.setBorder(BorderFactory.createLineBorder(borColor,2));
    }

}