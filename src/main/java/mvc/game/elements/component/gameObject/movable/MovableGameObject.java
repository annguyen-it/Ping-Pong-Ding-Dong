package main.java.mvc.game.elements.component.gameObject.movable;

import main.java.mvc.game.elements.component.gameObject.GameObject;
import main.java.mvc.game.mechanics.common.move.Movable;
import main.java.mvc.game.elements.function.intangible.Vector;

/**
 * Class {@code ImmovableGameObject} represents GameObject that can move
 * <p>
 * Because it can move, so it must have direction and speed to move
 * </p>
 */
public abstract class MovableGameObject extends GameObject implements Movable {

    /**
     * {@code Vector} represents the direction to move
     *
     * @see main.java.mvc.game.elements.function.intangible.Vector
     */
    protected Vector vector;
    protected double speed;

    public MovableGameObject(int x, int y, Vector vector, double speed) {
        super(x, y);
        this.vector = vector;
        this.speed = speed;
    }

    public Vector getVector() {
        return vector;
    }

    public double getSpeed() {
        return speed;
    }

    /**
     * Move to other position
     * <p>
     * Distance moved on x coordinate is defined equal to speed * vector.x <br>
     * Distance moved on y coordinate is defined equal to speed * vector.y
     *
     * @see main.java.mvc.game.elements.function.intangible.Vector
     * </p>
     */
    @Override
    public void move() {
        x += speed*vector.getX();
        y += speed*vector.getY();
    }
}
