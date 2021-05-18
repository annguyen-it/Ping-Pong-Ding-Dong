package main.java.mvc.game.component.gameObject.movable;

import main.java.mvc.game.component.gameObject.GameObject;
import main.java.mvc.game.mechanics.common.move.Movable;
import main.java.mvc.game.component.intangible.Vector;

public abstract class MovableGameObject extends GameObject implements Movable {

    protected Vector vector;
    protected double speed;

    public MovableGameObject(int x, int y, Vector vector, double speed) {
        super(x, y);
        this.vector = vector;
        this.speed = speed;
    }

    @Override
    public void move() {
        x += speed*vector.getX();
        y += speed*vector.getY();
    }

    public Vector getVector() {
        return vector;
    }

    public double getSpeed() {
        return speed;
    }
}
