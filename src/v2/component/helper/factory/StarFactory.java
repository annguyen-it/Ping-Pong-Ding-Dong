package v2.component.helper.factory;

import v2.component.gameObject.immovable.star.Star;

import java.util.Calendar;

public class StarFactory {

    private static final int STAR_EXISTING_TIME = 10000;

    private Star star;

    public StarFactory() { }

    public Star getStar() {
        return star;
    }

    public void update() {
        long currentTime = Calendar.getInstance().getTimeInMillis();

        if (star == null || currentTime - star.getAppearTime() >= STAR_EXISTING_TIME) {
            createStar();
        }
    }

    public void createStar() {

        if (star != null) {
            int x, y;
            do {
                x = star.getX();
                y = star.getY();
                star = new Star();
            } while (Math.sqrt(Math.pow(x - star.getX(), 2) + Math.pow(y - star.getY(), 2)) > 100);
        }
        else {
            star = new Star();
        }
    }
}
