package main.java.mvc.game.component.gameObject.movable;

import main.java.mvc.game.component.intangible.Vector;

public abstract class AllDirectionMovableGameObject extends MovableGameObject {

    public AllDirectionMovableGameObject(int x, int y, Vector vector, double speed){
        super(x, y, vector, speed);
    }
}
