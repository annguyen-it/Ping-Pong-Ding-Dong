package main.java.mvc.game.mechanics.paddle;

import main.java.mvc.game.elements.component.gameObject.GameObject;
import main.java.mvc.game.mechanics.common.move.ChangeSpeed;

/**
 * Changing speed mechanic for {@code Paddle}
 * <p>
 * We don't need to implement {@link #changeSpeed(main.java.mvc.game.elements.component.gameObject.GameObject)}
 * and {{@link #speedUp()}} in {@code Paddle}, so override them right in this interface layer
 * </p>
 *
 * @see main.java.mvc.game.elements.component.gameObject.movable.verticalOnlyMovableGameObject.paddle.Paddle
 */
interface PaddleChangeSpeed extends ChangeSpeed<GameObject> {

    @Override
    default void changeSpeed(GameObject causeObject) { }

    @Override
    default void speedUp() { }
}
