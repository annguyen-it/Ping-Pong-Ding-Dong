package main.java.mvc.game.mechanics.paddle;

import main.java.mvc.game.mechanics.common.collide.WallCollide;
import main.java.mvc.game.mechanics.common.move.Stoppable;
import main.java.mvc.game.mechanics.common.transform.Resizeable;

public interface PaddleMechanics extends
        Stoppable,
        WallCollide,
        PaddleChangeSpeed,
        Resizeable {

    @Override
    default void wallCollide() { }
}
