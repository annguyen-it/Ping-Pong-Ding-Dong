package v2.mechanics;

public interface VerticalMoveOnly extends Movable {
    int dx = 0;

    void willMoveUp();
    void willMoveDown();
    void stopAtTopBorder();
    void stopAtBottomBorder();
}
