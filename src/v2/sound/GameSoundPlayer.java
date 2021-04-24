package v2.sound;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

public class GameSoundPlayer {

    private final Map<String, AudioSet> audioSets = new HashMap<>();

    private static final File ballCollideAudioFile = new File("resources/audio/ball-collide.wav");
    private static final File wallCollideAudioFile = new File("resources/audio/Wallcollide.wav");
    private static final File missFile = new File("resources/audio/miss.wav");
    private static final File joinGameAudioFile = new File("resources/audio/JoinGame.mp3");
    private static final File starCollideAudioFile = new File("resources/audio/starCollide.wav");

    public GameSoundPlayer() {
        loadAudioSets();
    }

    private void loadAudioSets() {
        audioSets.put("ballCollide", new AudioSet(ballCollideAudioFile));
        audioSets.put("wallCollide", new AudioSet(wallCollideAudioFile));
        audioSets.put("miss", new AudioSet(missFile));
//        audioSets.put("joinGame", new AudioSet(joinGameAudioFile));
        audioSets.put("starCollide", new AudioSet(starCollideAudioFile));
    }

    public void ballCollide() {
        audioSets.get("ballCollide").play();
    }

    public void wallCollide() {
        audioSets.get("wallCollide").play();
    }

    public void miss() {
        audioSets.get("miss").play();
    }

    public void starCollide() {
        audioSets.get("starCollide").play();
    }

    public void joinGame() {
//        audioSets.get("joinGame").play();
    }
}
