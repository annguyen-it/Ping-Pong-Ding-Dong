package v2.component;

public abstract class GameObject {

    protected int x;
    protected int y;
    protected int dx;
    protected int dy;

    public GameObject(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public abstract void move();

    public int getX() {
        return x;
    }

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
