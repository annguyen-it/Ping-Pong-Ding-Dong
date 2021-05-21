package main.java.mvc.menu.sound;

import main.java.utils.sound.SoundPathProvider;
import main.java.utils.sound.SoundPlayer;

import java.util.AbstractMap;
import java.util.Map;

public class MenuSoundPlayer extends SoundPlayer {

    private static final Map<String, String> SOUND_MAP = Map.ofEntries(
            new AbstractMap.SimpleEntry<>("background", SoundPathProvider.Menu.background)
    );

    public MenuSoundPlayer() {
        super(SOUND_MAP);
    }

    public void playBackgroundAudio() {
        audioSets.get("background").playLoop();
    }

    public void stopBackgroundAudio() {
        audioSets.get("background").stop();
    }
}
