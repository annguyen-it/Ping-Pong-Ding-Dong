package game;


import game.information.Choose;

import javax.swing.*;



public class Home extends JPanel {
    private static final long serialVersionUID = 1L;

    public static JPanel home = new JPanel();
    public static JLayeredPane layeredPane = new JLayeredPane();

    public Home(JFrame game) {
        initClient(game);
        home = this;
    }

    private void initClient(JFrame game) {
        home.setLayout(null);

        setBack();
        game.setVisible(true);
        game.setContentPane(home);

        layeredPane.setBounds(0,0,Game.WIDTH,Game.HEIGHT);
        home.add(layeredPane);
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

    public void setBack(){
        JLabel background = new JLabel(new ImageIcon("Client.jpg"));
        home.add(background);
    }
}