package v2;

import v2.controller.FlowController;

import javax.swing.*;
import java.awt.*;

public class Game extends JFrame {
    JLayeredPane layer = new JLayeredPane();

    public static final int WIDTH = 1200;
    public static final int HEIGHT = 800;
    public static final Color orangeColor = new Color(251, 111, 0);
    public static final Color blueColor = new Color(0, 21, 232);

    public Game() {
        setupGame();

        FlowController controller = new FlowController(layer);
        controller.init();
    }

    private void setupGame() {
        add(layer);

        setTitle("Ping pong");
        setSize(WIDTH, HEIGHT);
        setResizable(false);

        setVisible(true);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
