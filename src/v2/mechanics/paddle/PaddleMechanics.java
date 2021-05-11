package v2.mechanics.paddle;

import v2.mechanics.common.collide.WallCollide;
import v2.mechanics.common.move.Stoppable;

public interface PaddleMechanics extends
        Stoppable,
        WallCollide,
        PaddleChangeSpeed {

    @Override
    default void wallCollide() { }
}
