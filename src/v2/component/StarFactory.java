package v2.component;

import java.util.Calendar;

public class StarFactory {

    private static final int STAR_EXISTING_TIME = 10000;
    private static final int TIME_WITHOUT_STAR = 15000;

    private Star star;
    private long lastStarDisappearTime;

    public StarFactory() { }

    public Star getStar() {
        return star;
    }

    public void update() {
        long currentTime = Calendar.getInstance().getTimeInMillis();

        if (star != null){
            if (currentTime - star.getAppearTime() >= STAR_EXISTING_TIME){
                deleteStar();
            }
        }
        else if (currentTime - lastStarDisappearTime >= TIME_WITHOUT_STAR) {
            createStar();
        }
    }

    private void createStar() {
        star = new Star();
    }

    public void deleteStar() {
        star = null;
        lastStarDisappearTime = Calendar.getInstance().getTimeInMillis();
    }
}
