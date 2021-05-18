package main.java.mvc.game.element.function.intangible;

public class GameSide {
    public enum Side {
        left,
        right,
        unknown
    }

    public static Side getOpposite(Side side) {
        if (side == Side.left) {
            return Side.right;
        }

        if (side == Side.right) {
            return Side.left;
        }

        return side;
    }
}

