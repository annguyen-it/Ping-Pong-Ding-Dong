package v2.component.gameObject.movable;

import v2.component.gameObject.GameObject;
import v2.mechanics.common.move.Movable;
import v2.component.helper.model.Vector;

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
}
