package main.java.mvc.game.mechanics.common.move;

public interface VerticalMoveOnly extends Movable {
    void willMoveUp();
    void willMoveDown();
    void stopAtTopBorder();
    void stopAtBottomBorder();
}
