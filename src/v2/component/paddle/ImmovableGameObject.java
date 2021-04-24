package v2.component.paddle;

import v2.component.GameObject;
import v2.sound.GameSoundPlayer;

public class ImmovableGameObject extends GameObject {

    public ImmovableGameObject(int x, int y) {
        super(x, y);
    }

    public ImmovableGameObject(int x, int y, GameSoundPlayer soundPlayer) {
        super(x, y, soundPlayer);
    }
}
