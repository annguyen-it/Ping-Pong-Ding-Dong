package v2.mechanics.paddle;

import v2.mechanics.common.collide.WallCollide;
import v2.mechanics.common.move.Stoppable;
import v2.mechanics.common.transform.Resizeable;

public interface PaddleMechanics extends
        Stoppable,
        WallCollide,
        PaddleChangeSpeed,
        Resizeable {

    @Override
    default void wallCollide() { }
}
