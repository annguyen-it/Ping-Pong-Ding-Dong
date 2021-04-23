package v2.mechanics;

import v2.component.GameObject;

interface ChangeSpeed<T extends GameObject> extends Mechanic {
    void changeSpeed(T causeObject);
}
