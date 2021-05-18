package main.java.mvc.game.element.function.intangible;

public class Vector {

    private double x;
    private double y;
    private double alpha;

    public Vector() { }

    /*
                                | 90
                                |
              180               |
                     ___________|___________ 0
                                |
                                |
                                | 270 ~ -90
     */

    public Vector(double alpha) {
        if (alpha >= 360) {
            alpha = 359;
        }

        if (alpha == 90) {
            x = 0;
            y = -1;
            return;
        }

        if (alpha == 270) {
            x = 0;
            y = 1;
            return;
        }

        this.alpha = alpha;

        double tanAlpha = Math.tan(Math.toRadians(alpha));

        x = 1.0/Math.sqrt(Math.pow(tanAlpha, 2) + 1);
        y = Math.abs(x*Math.tan(tanAlpha));

        if (90 < alpha && alpha < 270) {
            x *= -1;
        }

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

    public Vector getReflection() {
        return new Vector(360 - alpha);
    }

    public Vector getOpposite() {
        if (alpha < 180) {
            return new Vector(180 + alpha);
        }

        return new Vector(alpha - 180);
    }

    public boolean isHorizontalVector() {
        return alpha == 0 || alpha == 180;
    }
}
