package v2.mechanics.common.move;

import v2.mechanics.common.move.Movable;

public interface VerticalMoveOnly extends Movable {
    void willMoveUp();
    void willMoveDown();
    void stopAtTopBorder();
    void stopAtBottomBorder();
}
