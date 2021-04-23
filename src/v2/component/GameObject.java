package v2.component;

import v2.mechanics.Movable;
import v2.sound.GameSoundPlayer;

public abstract class GameObject implements Movable {

    protected int x;
    protected int y;
    protected int dx;
    protected int dy;

    protected final GameSoundPlayer soundPlayer;

    public GameObject(int x, int y){
        this(x, y, null);
    }

    public GameObject(int x, int y, GameSoundPlayer soundPlayer) {
        this.x = x;
        this.y = y;
        this.soundPlayer = soundPlayer;
    }

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
