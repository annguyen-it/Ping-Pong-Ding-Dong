package v2.component.gameObject.immovable.bonus;


import v2.component.gameObject.immovable.star.StarType;
import v2.controller.GameController;

import java.awt.*;
import java.util.AbstractMap;
import java.util.Map;


public class Bonus {

    private static final Map<StarType, Color> colorMap = Map.ofEntries(
            new AbstractMap.SimpleEntry<>(StarType.bigBall, Color.red)
    );

    private final StarType starType;
    private int timeLeft = 4400;

    public Bonus(StarType starType) {
        this.starType = starType;
    }

    public void setTimeLeft(int timeLeft) {
        this.timeLeft = timeLeft;
    }

    public int getTimeLeft() {
        return timeLeft;
    }

    public Color getColor() {
        return colorMap.get(starType);
    }

    public void decreaseTimeLeft() {
        timeLeft -= GameController.GAME_DELAY;
    }

    //    public void countdownTimeLeft(){
    //            t = new Timer(4400, e -> {
    //                this.timeLeft -=1;
    //        });
    //    }

    //    public Timer getTimer(){
    //        return this.t;
    //    }

    //    public void countdownTimeLeft() throws InterruptedException {
    //        while (timeLeft>0){
    //            this.timeLeft-=1;
    //            Thread.sleep(2000);
    //        }
    //    }
    //    public long getTimeLeft(){
    //        long currentTime = Calendar.getInstance().getTimeInMillis();
    //        timeLeft = timeExist - currentTime;
    //    }

}
