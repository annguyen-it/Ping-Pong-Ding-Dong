package v2;

import v2.controller.FlowController;
import v2.utils.database.Database;
import v2.thread.DatabaseThread;

import javax.swing.*;
import java.awt.*;
import java.sql.SQLException;

public class Game extends JFrame {
    JLayeredPane layer = new JLayeredPane();

    public static final int WIDTH = 1200;
    public static final int HEIGHT = 800;
    public static final Color orangeColor = new Color(251, 111, 0);
    public static final Color blueColor = new Color(0, 21, 232);

    public Game() {
        setupGame();

        DatabaseThread dbThread = new DatabaseThread();
        dbThread.start();

        FlowController controller = new FlowController(layer);
        controller.init();

        try {
            Database.disconnect();
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
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
