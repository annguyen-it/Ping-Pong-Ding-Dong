package main.java.mvc.game.element.function.intangible;

/**
 * Class {@code Vector} is used to determined direction in Game
 * <p>
 * {@code Vector} is represented by a vector on Descartes coordinates.
 * The vector starts at point (0, 0) and finishes at a point (x, y) that belongs to circle's circumference having radius = 1.0.
 * Therefore, {@link #x}^2 + {@link #y}^2 = 1.0
 * </p>
 */
public class Vector {

    /**
     * Position on x coordinate of the finish point of vector
     */
    private double x;

    /**
     * Position on y coordinate of the finish point of vector
     */
    private double y;

    /**
     * Angle of vector (measured by degree)
     *
     * <p>
     *     {@code alpha} always in range [0.0, 360) (degrees)
     * </p>
     * <blockquote><pre>
     *
     *
     *                  | 90
     *                  |
     *      180         |
     *       ___________|___________ 0
     *                  |
     *                  |
     *                  | 270
     *
     * </pre></blockquote>
     */
    private double alpha;

    /**
     * Vector zero
     */
    public Vector() { }

    /**
     * Genarate {@code Vector} from an angle
     * @param alpha degree of angle
     */
    public Vector(double alpha) {
        //  Normalize vector
        if (alpha >= 360) {
            alpha = 359;
        }
        else if (alpha < 0){
            alpha = 0;
        }

        //  Direction vertically from down to up
        if (alpha == 90) {
            x = 0;
            y = -1;
            return;
        }

        //  Direction vertically from up to down
        if (alpha == 270) {
            x = 0;
            y = 1;
            return;
        }

        this.alpha = alpha;

        double tanAlpha = Math.tan(Math.toRadians(alpha));

        //  x^2 + y^2 = 1.0
        //  tan(alpha) = y / x

        x = 1.0/Math.sqrt(Math.pow(tanAlpha, 2) + 1);
        y = Math.abs(x*Math.tan(tanAlpha));

        //  Moving from right to left
        if (90 < alpha && alpha < 270) {
            x *= -1;
        }

        //  Moving from up to down, but not vertically
        if (0 <= alpha && alpha <= 180) {
            y *= -1;
        }
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public double getAlpha() {
        return alpha;
    }

    /**
     * Get refection vector
     * @return {@code Vector}
     */
    public Vector getReflection() {
        return new Vector(360 - alpha);
    }

    /**
     * Get opposite vector
     * @return {@code Vector}
     */
    public Vector getOpposite() {
        if (alpha < 180) {
            return new Vector(180 + alpha);
        }

        return new Vector(alpha - 180);
    }

    /**
     * @return true if instance is Horizontal vector, else false.
     */
    public boolean isHorizontalVector() {
        return alpha == 0 || alpha == 180;
    }
}
