package v2.component.helper.model;

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

    public Vector(double degree) {
        if (degree == 90){
            x = 0;
            y = -1;
            return;
        }

        if (degree == 270){
            x = 0;
            y = 1;
            return;
        }

        alpha = degree;

        double tanAlpha = Math.tan(Math.toRadians(degree));

        x = 1.0/Math.sqrt(Math.pow(tanAlpha, 2) + 1);
        y = Math.abs(x*Math.tan(tanAlpha));

        if (90 < alpha && alpha < 270) {
            x *= -1;
        }

        if (0 <= alpha && alpha <= 180) {
            y *= -1;
        }
    }

    public double getAlpha() {
        return Math.acos(y/x);
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public Vector getReflection() {
        return new Vector(360 - alpha);
    }

    public int compareOpenAngle(Vector v) {
        if (((0 <= alpha && alpha <= 90) || (270 <= alpha && alpha < 360)) &&
            ((0 <= v.alpha && v.alpha <= 90) || (270 <= v.alpha && v.alpha < 360))) {

            double a = alpha <= 90 ? alpha : 360 - alpha;
            double av = v.alpha <= 90 ? v.alpha : 360 - v.alpha;

            return Double.compare(a, av);
        }
        else if ((90 < alpha && alpha < 270) &&
                 ((90 < v.alpha && v.alpha < 270))) {

            double a = alpha <= 180 ? 180 - alpha : alpha - 180;
            double av = v.alpha <= 180 ? 180 - v.alpha : v.alpha - 180;

            return Double.compare(a, av);
        }

        return 0;
    }
}
