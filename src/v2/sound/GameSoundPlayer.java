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

    public static final File ballCollideAudioFile = new File("resources/audio/ball-collide.wav");
    public static final File wallCollideAudioFile = new File("resources/audio/Wallcollide.wav");
    public static final File missFile = new File("resources/audio/miss.wav");
    public static final File joinGameAudioFile = new File("resources/audio/JoinGame.mp3");
    public static final File starCollideAudioFile = new File("resources/audio/starCollide.wav");



    public static final Clip ballCollide = null;
    public static final Clip wallCollide = null;
    public static final Clip miss = null;
    public static final Clip joinGame = null;
    public static final Clip starCollide = null;


    public GameSoundPlayer() {
        preloadAll();
    }

    public void preloadAll() {
        preloadBallCollide(ballCollideAudioFile, ballCollide);
        preloadBallCollide(wallCollideAudioFile,wallCollide);
        preloadBallCollide(missFile,miss);

    }

    public void preloadBallCollide(File file, Clip clip) {
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

//    public void ballCollide() {
//        try {
//            AudioInputStream stream = getStream(ballCollideAudioFile);
//            DataLine.Info info = getInfo(stream);
//            ballCollide = (Clip) AudioSystem.getLine(info);
//            ballCollide.open(stream);
//        } catch (LineUnavailableException | IOException | UnsupportedAudioFileException e) {
//            e.printStackTrace();
//        }
//
//        play(ballCollide);
//    }
public void soundPlayer(File file, Clip clip) {
    try {
        AudioInputStream stream = getStream(file);
        DataLine.Info info = getInfo(stream);
        clip = (Clip) AudioSystem.getLine(info);
        clip.open(stream);
    } catch (LineUnavailableException | IOException | UnsupportedAudioFileException e) {
        e.printStackTrace();
    }

    play(clip);
}
    private void play(Clip clip) {
        clip.start();
    }
}
