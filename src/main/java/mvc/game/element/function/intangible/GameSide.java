package main.java.mvc.game.element.function.intangible;

/**
 * Class {@code GameSide} handling all information about side of playground
 */
public class GameSide {

    /**
     * Enum {@code Side} represents all side of playground
     */
    public enum Side {
        left,
        right,
        top,
        bottom,
        unknown
    }

    /**
     * @param side {@code Side}
     * @return opposite side of param
     */
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

