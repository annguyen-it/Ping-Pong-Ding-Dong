package v2.component;

import v2.mechanics.Movable;
import v2.sound.GameSoundPlayer;

public abstract class MovableGameObject extends GameObject implements Movable {

    protected int dx;
    protected int dy;

    public MovableGameObject(int x, int y) {
        super(x, y);
    }

    public MovableGameObject(int x, int y, GameSoundPlayer soundPlayer){
        super(x, y, soundPlayer);
    }

    public int getDx() {
        return dx;
    }

    public int getDy() {
        return dy;
    }
}
