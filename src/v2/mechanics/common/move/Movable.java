package v2.mechanics.common.move;

import v2.mechanics.Mechanic;

public interface Movable extends Mechanic {

    void tryMove();
    void move();
}
