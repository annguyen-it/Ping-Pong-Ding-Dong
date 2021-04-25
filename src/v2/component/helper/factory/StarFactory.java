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

    private void createStar() {
        star = new Star();
    }
}
