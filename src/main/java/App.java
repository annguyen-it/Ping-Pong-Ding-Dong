package main.java;

import main.java.mvc.common.flow.FlowController;
import main.java.utils.database.Database;
import main.java.thread.DatabaseThread;

import javax.swing.*;
import java.awt.*;
import java.sql.SQLException;

public class App extends JFrame {

    JLayeredPane layer = new JLayeredPane();

    public static final int WIDTH = 1200;
    public static final int HEIGHT = 800;
    public static final Color orangeColor = new Color(251, 111, 0);
    public static final Color blueColor = new Color(0, 21, 232);

    public App() {
        setupGame();

        DatabaseThread dbThread = new DatabaseThread();
        dbThread.start();
    }

    public void start() {
        FlowController controller = new FlowController(layer);
        controller.init();
    }

    public void over() {
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
