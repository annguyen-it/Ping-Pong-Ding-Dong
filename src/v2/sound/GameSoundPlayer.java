package v2.sound;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.File;
import java.io.IOException;

public class GameSoundPlayer {

    private static final File ballCollideAudioFile = new File("resources/audio/ball-collide.wav");
    Clip ballCollide;

    public GameSoundPlayer() {
        preloadAll();
    }

    public void ballCollide() {
        try {
            AudioInputStream stream = getStream(ballCollideAudioFile);
            DataLine.Info info = getInfo(stream);
            ballCollide = (Clip) AudioSystem.getLine(info);
            ballCollide.open(stream);
        } catch (LineUnavailableException | IOException | UnsupportedAudioFileException e) {
            e.printStackTrace();
        }

        play(ballCollide);
    }

    private void play(Clip clip) {
        clip.start();
    }

    private void preloadAll() {
        preloadBallCollide(ballCollideAudioFile);
    }

    private AudioInputStream getStream(File file) throws UnsupportedAudioFileException, IOException {
        return AudioSystem.getAudioInputStream(file);
    }

    private DataLine.Info getInfo(AudioInputStream stream) {
        AudioFormat format = stream.getFormat();
        return new DataLine.Info(Clip.class, format);
    }

    private void preloadBallCollide(File file) {
        try {
            AudioInputStream stream = getStream(file);
            DataLine.Info info = getInfo(stream);
            ballCollide = (Clip) AudioSystem.getLine(info);
            ballCollide.open(stream);
        } catch (LineUnavailableException | IOException | UnsupportedAudioFileException e) {
            e.printStackTrace();
        }
    }
}
