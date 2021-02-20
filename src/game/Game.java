package game;

import game.board.Board;

import javax.swing.*;
import java.awt.*;

public class Game extends JFrame {
    //public static JFrame game = new JFrame();
    public static final int WIDTH = 1200;
    public static final int HEIGHT = 800;
    private JLabel background;
    private Image img;
    public Game() {
        initUI();
       // game = this;
    }

    private void initUI() {

        add(new Home(this));
        setVisible(true);
        setTitle("Application");
        setSize(WIDTH, HEIGHT);
        setResizable(false);


        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

//    public void paint (Graphics g){
//        ImageIcon img = new ImageIcon("Client.jpg");
//        g.drawImage(img, 0, 0, this);
//    }



    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            Game app = new Game();
            app.setVisible(true);
        });
    }
}
