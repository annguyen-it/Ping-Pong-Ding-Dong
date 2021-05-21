package main.java.mvc.game.elements.component.helper.factory;

import main.java.mvc.game.elements.function.intangible.GameSide.Side;
import main.java.mvc.game.elements.component.gameObject.movable.ball.Ball;
import main.java.mvc.game.sound.GameSoundPlayer;

import java.util.ArrayList;
import java.util.List;

public class BallFactory {

    private final List<Ball> ballList = new ArrayList<>();

    private final GameSoundPlayer soundPlayer;

    public BallFactory(GameSoundPlayer soundPlayer) {
        this.soundPlayer = soundPlayer;
        create();
    }

    private void create() {
        ballList.add(new Ball(soundPlayer));
    }

    public void create(Side ballDirection) {
        ballList.set(0, new Ball(ballDirection, soundPlayer));
    }

    public void add(Ball ball){
        ballList.add(ball);
    }

    public List<Ball> getBallList() {
        return ballList;
    }

    public boolean hasOnlyOne(){
        return ballList.size() == 1;
    }

    public void clear(){
        ballList.clear();
    }
}