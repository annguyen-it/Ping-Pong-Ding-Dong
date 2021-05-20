package main.java.mvc.game.element.component.gameObject;

/**
 * Class {@code GameObject} represents all Object in Game that interactive each other
 */
public abstract class GameObject {

    /**
     * Position of instance on x coordinate
     */
    protected int x;

    /**
     * Position of instance on y coordinate
     */
    protected int y;

    public GameObject(int x, int y){
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}
