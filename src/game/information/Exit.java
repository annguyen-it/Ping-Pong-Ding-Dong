package game.information;

import game.Game;
import game.Home;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Exit extends JPanel {

    private JTextField txtExit;
    private JButton btYes;
    private JButton btNo;


    public Exit(){ initExit(); }


    private void initExit(){


        setBounds((Game.WIDTH-400)/2,280,400,150);
        setBackground(Player.oranColor);
        setBorder(BorderFactory.createLineBorder(Player.borColor, 2));
        setLayout(null);

        txtExit = new JTextField("Are you sure to exit game ?");
        setText(txtExit);

        btYes = new JButton("YES");
        setButton(btYes,280);

        btNo = new JButton("NO");
        setButton(btNo,20);

        btYes.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        btNo.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Home.switchPanels(new Choose());
            }
        });
    }

    //Custom text
    private void setText(JTextField txtExit){
        txtExit.setFont(new Font("Tahoma", Font.PLAIN, 15));
        txtExit.setForeground(Color.WHITE);
        txtExit.setBackground(Player.oranColor);
        txtExit.setBorder(null);
        txtExit.setHorizontalAlignment(SwingConstants.CENTER);
        txtExit.setBounds(20, 20, 360, 50);
        add(txtExit);
    }

    //Custom button
    private void setButton(JButton button, int x){
        button.setForeground(Color.WHITE);
        button.setFont(new Font("Tahoma", Font.BOLD, 10));
        button.setBackground(Player.oranColor);
        button.setBounds(x, 100, 100, 30);
        button.setBorder(BorderFactory.createLineBorder(Player.borColor, 2));
        add(button);
    }
}
