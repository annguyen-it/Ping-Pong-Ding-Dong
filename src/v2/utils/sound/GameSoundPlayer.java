package v2.utils.sound;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

public class GameSoundPlayer {

    private final Map<String, AudioSet> audioSets = new HashMap<>();
    private boolean isMute = false;

    private static final File ballCollideAudioFile = new File("resources/audio/ball-collide.wav");
    private static final File wallCollideAudioFile = new File("resources/audio/Wallcollide.wav");
    private static final File missFile = new File("resources/audio/miss.wav");
    private static final File joinGameAudioFile = new File("resources/audio/JoinGame.wav");
    private static final File starCollideAudioFile = new File("resources/audio/starCollide.wav");

    public GameSoundPlayer() {
        loadAudioSets();
    }

    private void loadAudioSets() {
        audioSets.put("ballCollide", new AudioSet(ballCollideAudioFile));
        audioSets.put("wallCollide", new AudioSet(wallCollideAudioFile));
        audioSets.put("miss", new AudioSet(missFile));
        audioSets.put("joinGame", new AudioSet(joinGameAudioFile));
        audioSets.put("starCollide", new AudioSet(starCollideAudioFile));
    }

    public void toggle(){
        isMute = !isMute;
    }

    public void ballCollide() {
        if(isMute==false){
            audioSets.get("ballCollide").play();
        }
    }

    public void wallCollide() {
        if(isMute==false) {
            audioSets.get("wallCollide").play();
        }
    }

    public void miss() {
        if (isMute==false) {
            audioSets.get("miss").play();
        }
    }

    public void starCollide() {
        if(isMute==false) {
            audioSets.get("starCollide").play();
        }
    }

    public void joinGame() {
        if(isMute==false) {
            audioSets.get("joinGame").play();
        }
    }
}
