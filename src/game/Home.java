package game;


import game.board.Board;
import game.information.Choose;

import javax.swing.*;


public class Home extends JPanel {
    private static final long serialVersionUID = 1L;

    public static JLabel label = new JLabel();
    public static JLabel labelGame = new JLabel();
    public static JLayeredPane layeredPane = new JLayeredPane();

    public Home(JFrame game) {
        initClient(game);
        //home = this;
    }

    private void initClient(JFrame game) {
        //home.setLayout(null);
        game.setVisible(true);
        //game.setContentPane(home);
        label= new JLabel(new ImageIcon("Client.jpg"));
        game.setContentPane(label);

        layeredPane.setBounds(0,0,Game.WIDTH,Game.HEIGHT);
        label.add(layeredPane);
        layeredPane.add(new Choose());
        layeredPane.setFocusable(true);
        layeredPane.requestFocus();
    }

    public static void switchPanels(JPanel panel){
        layeredPane.removeAll();
        layeredPane.add(panel);
        layeredPane.repaint();
        layeredPane.revalidate();
    }
    public static void playGame(){
        label.removeAll();
        label.add(new Board());
        label.revalidate();
        label.repaint();
    }

}