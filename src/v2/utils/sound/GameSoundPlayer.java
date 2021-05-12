package v2.utils.sound;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

public class GameSoundPlayer {

    private final Map<String, AudioSet> audioSets = new HashMap<>();
    private boolean isMute = false;

    private static final File ballCollidePaddleAudioFile = new File("resources/audio/ball-collide-paddle.wav");
    private static final File ballCollideWallAudioFile = new File("resources/audio/ball-collide-wall.wav");
    private static final File lostBallFile = new File("resources/audio/lost-ball.wav");
    private static final File joinGameAudioFile = new File("resources/audio/join-game.wav");
    private static final File ballCollideStarAudioFile = new File("resources/audio/ball-collide-star.wav");

    public GameSoundPlayer() {
        loadAudioSets();
    }

    private void loadAudioSets() {
        audioSets.put("ballCollidePaddle", new AudioSet(ballCollidePaddleAudioFile));
        audioSets.put("ballCollideWall", new AudioSet(ballCollideWallAudioFile));
        audioSets.put("lost-ball", new AudioSet(lostBallFile));
        audioSets.put("joinGame", new AudioSet(joinGameAudioFile));
        audioSets.put("ballCollideStar", new AudioSet(ballCollideStarAudioFile));
    }

    public void toggle(){
        isMute = !isMute;
    }

    public void ballCollidePaddle() {
        if(!isMute){
            audioSets.get("ballCollidePaddle").play();
        }
    }

    public void ballCollideWall() {
        if(!isMute) {
            audioSets.get("ballCollideWall").play();
        }
    }

    public void lostBall() {
        if (!isMute) {
            audioSets.get("lost-ball").play();
        }
    }

    public void ballCollideStar() {
        if(!isMute) {
            audioSets.get("ballCollideStar").play();
        }
    }

    public void joinGame() {
        if(!isMute) {
            audioSets.get("joinGame").play();
        }
    }
}
