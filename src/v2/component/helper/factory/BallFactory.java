package v2.component.helper.factory;

import v2.board.GameSide;
import v2.component.gameObject.movable.ball.Ball;
import v2.utils.sound.GameSoundPlayer;

import java.util.ArrayList;
import java.util.List;

public class BallFactory {

    private final List<Ball> list = new ArrayList<>();

    private final GameSoundPlayer soundPlayer;

    public BallFactory(GameSoundPlayer soundPlayer) {
        this.soundPlayer = soundPlayer;
        create();
    }

    public void create() {
        list.add(new Ball(soundPlayer));
    }

    public void create(GameSide.Side ballDirection) {
        list.set(0, new Ball(soundPlayer, ballDirection));
    }

    public void add(Ball ball){
        list.add(ball);
    }

    public List<Ball> getBalls() {
        return list;
    }

    public boolean hasOnlyOne(){
        return list.size() == 1;
    }
}