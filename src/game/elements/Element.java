package game.elements;

import javax.swing.JFrame;
import java.awt.Graphics;

public abstract class Element extends JFrame {

    protected int x;
    protected int y;
    protected int dx;
    protected int dy;

    public Element(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public abstract void paintComponent(Graphics g);

    public abstract void move();

    @Override
    public int getX() {
        return x;
    }

    @Override
    public int getY() {
        return y;
    }

    public int getDx() {
        return dx;
    }

    public int getDy() {
        return dy;
    }
}
