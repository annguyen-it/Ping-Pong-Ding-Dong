package main.java.mvc.game.element.component.gameObject.movable;

import main.java.mvc.game.element.function.intangible.Vector;

public abstract class AllDirectionMovableGameObject extends MovableGameObject {

    public AllDirectionMovableGameObject(int x, int y, Vector vector, double speed){
        super(x, y, vector, speed);
    }
}
