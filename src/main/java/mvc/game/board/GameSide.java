package main.java.mvc.game.board;

public class GameSide {
    public enum Side {
        left,
        right,
        unknown
    }

    public static Side opposite(Side side) {
        if (side == Side.left) {
            return Side.right;
        }

        if (side == Side.right) {
            return Side.left;
        }

        return side;
    }
}

