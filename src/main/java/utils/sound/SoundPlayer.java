package main.java.utils.sound;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

abstract public class SoundPlayer {

    protected final Map<String, AudioSet> audioSets = new HashMap<>();

    public SoundPlayer(Map<String, String> soundMap) {
        loadAudioSets(soundMap);
    }

    public void loadAudioSets(Map<String, String> soundMap) {
        for (var item : soundMap.entrySet()) {
            audioSets.put(item.getKey(), new AudioSet(new File(item.getValue())));
        }
    }
}
