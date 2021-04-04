package game;

import game.board.Board;

import javax.swing.*;
import java.awt.EventQueue;
import java.util.Stack;

public class Game extends JFrame {
    Stack<JPanel> panelStack = new Stack<>();

    public static final int WIDTH = 1200;
    public static final int HEIGHT = 800;

    public Game() {
        initUI();
    }

    private void initUI() {
        JLayeredPane frame = new JLayeredPane();
        Client firstClient = new Client(frame);

        frame.add(firstClient);
        panelStack.add(firstClient);

        add(frame);

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
