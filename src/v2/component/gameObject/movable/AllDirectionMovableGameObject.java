package v2.component.gameObject.movable;

import v2.component.intangible.Vector;

public abstract class AllDirectionMovableGameObject extends MovableGameObject {

    public AllDirectionMovableGameObject(int x, int y, Vector vector, double speed){
        super(x, y, vector, speed);
    }
}
