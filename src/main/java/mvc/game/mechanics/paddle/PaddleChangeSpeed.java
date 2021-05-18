package main.java.mvc.game.mechanics.paddle;

import main.java.mvc.game.component.gameObject.GameObject;
import main.java.mvc.game.mechanics.common.move.ChangeSpeed;

interface PaddleChangeSpeed extends ChangeSpeed<GameObject> {

    @Override
    default void changeSpeed(GameObject causeObject) { }

    @Override
    default void speedUp() { }
}
