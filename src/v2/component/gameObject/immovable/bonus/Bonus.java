package v2.component.gameObject.immovable.bonus;


import v2.component.gameObject.movable.ball.Ball;
import java.awt.*;


public class Bonus {


    private int timeLeft;
    private Color color;


    public Bonus(int timeLeft) {
        this.timeLeft = timeLeft;
        setColor();
        //countdownTimeLeft();
    }

    public void setTimeLeft(int timeLeft) {
        this.timeLeft = timeLeft;
    }

    public int getTimeLeft() {
        return timeLeft;
    }

    public Color getColor() {
        return color;
    }

    public void setColor() {
        switch (Ball.checkStarType){
            case 1:
                this.color = Color.BLUE;
            case 2:
                this.color = Color.green;
            case 3:
                this.color = Color.pink;
            case 4:
                this.color =Color.red;
        }
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
