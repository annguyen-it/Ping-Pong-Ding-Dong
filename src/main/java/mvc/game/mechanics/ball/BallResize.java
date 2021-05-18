package main.java.mvc.game.mechanics.ball;

import main.java.mvc.game.mechanics.common.transform.Resizeable;

public interface BallResize extends Resizeable {
    @Override
    default void sizeDown() { }
}
