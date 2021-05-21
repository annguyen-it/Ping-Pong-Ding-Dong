package main.java.mvc.game.sound;

import main.java.utils.sound.SoundPathProvider;
import main.java.utils.sound.SoundPlayer;

import java.util.AbstractMap;
import java.util.Map;

public class GameSoundPlayer extends SoundPlayer {

    private boolean isMute = false;

    private static final Map<String, String> SOUND_MAP = Map.ofEntries(
            new AbstractMap.SimpleEntry<>("background", SoundPathProvider.Game.background),
            new AbstractMap.SimpleEntry<>("ballCollidePaddle", SoundPathProvider.Game.ballCollidePaddle),
            new AbstractMap.SimpleEntry<>("ballCollidePickup", SoundPathProvider.Game.ballCollidePickup),
            new AbstractMap.SimpleEntry<>("ballCollideWall", SoundPathProvider.Game.ballCollideWall),
            new AbstractMap.SimpleEntry<>("joinGame", SoundPathProvider.Game.joinGame),
            new AbstractMap.SimpleEntry<>("lostBall", SoundPathProvider.Game.lostBall)
    );

    public GameSoundPlayer() {
        super(SOUND_MAP);
    }

    public boolean isMute() {
        return isMute;
    }

    public void toggle() {
        isMute = !isMute;

        if (isMute) {
            audioSets.get("background").stop();
        }
        else {
            audioSets.get("background").play();
        }
    }

    public void ballCollidePaddle() {
        if (!isMute) {
            audioSets.get("ballCollidePaddle").play();
        }
    }

    public void ballCollideWall() {
        if (!isMute) {
            audioSets.get("ballCollideWall").play();
        }
    }

    public void lostBall() {
        if (!isMute) {
            audioSets.get("lostBall").play();
        }
    }

    public void ballCollidePickup() {
        if (!isMute) {
            audioSets.get("ballCollidePickup").play();
        }
    }

    public void joinGame() {
        if (!isMute) {
            audioSets.get("joinGame").play();
        }
    }

    public void playBackgroundAudio() {
        if (!isMute) {
            audioSets.get("background").playLoop();
        }
    }

    public void stopBackgroundAudio() {
        audioSets.get("background").stop();
    }
}
