package game.board;

import javax.swing.JFrame;
import java.awt.*;

public class Score extends JFrame {

    private int score = 0;
    private final int x;
    private final int y;

    public Score(int x, int y) throws HeadlessException {
        this.x = x;
        this.y = y;
    }

    public void paintComponent(Graphics g) {
        g.setFont(new Font("Serif", Font.PLAIN, 80));
        g.setColor(Color.gray);
        g.drawString(Integer.toString(score), x, y);
    }

    public void increase(){
        score++;
    }
}
