package main.java.mvc.game.mechanics.paddle;

import main.java.mvc.game.mechanics.common.collide.WallCollide;

public interface PaddleWallCollide extends WallCollide {

    @Override
    default void wallCollide() { }
}
