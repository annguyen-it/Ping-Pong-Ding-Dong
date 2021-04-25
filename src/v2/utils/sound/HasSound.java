package v2.utils.sound;

/**
 * Interface to implement game sound player
 *
 * @see v2.utils.sound.GameSoundPlayer
 */
public interface HasSound {

    /**
     * Set sound player for implementations of this interface
     * @param soundPlayer Game sound player
     */
    void setSoundPlayer(GameSoundPlayer soundPlayer);
}
