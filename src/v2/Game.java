package v2;

import v2.controller.Controller;

import javax.swing.*;

public class Game extends JFrame {
    JLayeredPane layer = new JLayeredPane();

    public static final int WIDTH = 1200;
    public static final int HEIGHT = 800;

    public Game() {
        setupGame();

        Controller controller = new Controller(layer);
        controller.setup();
    }

    private void setupGame() {
        add(layer);

        setTitle("Application");
        setSize(WIDTH, HEIGHT);
        setResizable(false);

        setVisible(true);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
