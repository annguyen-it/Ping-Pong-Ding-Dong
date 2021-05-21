package main.java.mvc.game.elements.component.gameObject.immovable;

import main.java.mvc.game.elements.component.gameObject.GameObject;

/**
 * Class {@code ImmovableGameObject} represents GameObject that cannot move
 */
public abstract class ImmovableGameObject extends GameObject {

    /**
     * Constructor with coordinates of GameObject
     * @param x x coordinate
     * @param y y coordinate
     */
    public ImmovableGameObject(int x, int y) {
        super(x, y);
    }
}
