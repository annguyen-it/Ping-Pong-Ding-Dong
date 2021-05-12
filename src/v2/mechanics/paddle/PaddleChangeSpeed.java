package v2.mechanics.paddle;

import v2.component.gameObject.GameObject;
import v2.mechanics.common.move.ChangeSpeed;

interface PaddleChangeSpeed extends ChangeSpeed<GameObject> {

    @Override
    default void changeSpeed(GameObject causeObject) { }

    @Override
    default void speedUp() { }
}
