package game;

import javax.swing.JFrame;
import java.awt.EventQueue;

public class Game extends JFrame {

    public static final int WIDTH = 1200;
    public static final int HEIGHT = 800;

    public Game() {
        initUI();
    }

    private void initUI() {
        add(new Client());

        setTitle("Application");
        setSize(WIDTH, HEIGHT);
        setResizable(false);

        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            Game app = new Game();
            app.setVisible(true);
        });
    }
}
