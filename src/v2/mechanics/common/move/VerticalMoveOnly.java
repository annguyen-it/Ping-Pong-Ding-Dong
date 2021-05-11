package v2.mechanics.common.move;

public interface VerticalMoveOnly extends Movable {
    void willMoveUp();
    void willMoveDown();
    void stopAtTopBorder();
    void stopAtBottomBorder();
}
