package v2.component.helper;

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
        createBall();
    }

    public void createBall() {
        list.add(new Ball(soundPlayer));
    }

    public void createBall(GameSide.Side ballDirection) {
        list.set(0, new Ball(soundPlayer, ballDirection));
    }

    public List<Ball> getBalls() {
        return list;
    }

    public boolean isSingleBall(){
        return list.size() == 1;
    }
}