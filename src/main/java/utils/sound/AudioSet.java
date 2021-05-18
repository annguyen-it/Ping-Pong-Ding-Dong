package main.java.utils.sound;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.File;
import java.io.IOException;

public class AudioSet {
    private final File file;
    private Clip clip;

    public AudioSet(File file) {
        this.file = file;
        loadClip();
    }

    public void play() {
        loadClip();
        clip.start();
    }

    private void loadClip(){
        try {
            AudioInputStream stream = getStream(file);
            DataLine.Info info = getInfo(stream);
            clip = (Clip) AudioSystem.getLine(info);
            clip.open(stream);
        } catch (LineUnavailableException | IOException | UnsupportedAudioFileException e) {
            e.printStackTrace();
        }
    }

    private AudioInputStream getStream(File file) throws UnsupportedAudioFileException, IOException {
        return AudioSystem.getAudioInputStream(file);
    }

    private DataLine.Info getInfo(AudioInputStream stream) {
        AudioFormat format = stream.getFormat();
        return new DataLine.Info(Clip.class, format);
    }
}
