package main.java.mvc.game.mechanics.common.move;

import main.java.mvc.game.mechanics.Mechanic;

public interface Movable extends Mechanic {

    void tryMove();
    void move();
}
