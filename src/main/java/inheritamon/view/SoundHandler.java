package inheritamon.view;

import java.util.HashMap;
import javax.sound.sampled.*;

import inheritamon.model.data.DataHandler;

/**
 * @author Jeremias
 * The SoundHandler class is responsible for loading and playing all the
 * sounds and music in the game.
 * It is a singleton class.
 */
public class SoundHandler {

    // Singleton instance
    private static SoundHandler soundHandler;

    /**
     * The sound effects in the game as clips
     */
    private final HashMap<String, Clip> soundClips = new HashMap<>();

    /**
     * The music in the game as clips
     */
    private final HashMap<String, Clip> musicClips = new HashMap<>();

    private Clip currentMusic;

    /**
     * The constructor for the SoundHandler class
     */
    private SoundHandler() {

        DataHandler dataHandler = DataHandler.getInstance();

        HashMap<String, AudioInputStream> musicInput =
                dataHandler.getAudios("Music");
        HashMap<String, AudioInputStream> soundInput =
                dataHandler.getAudios("Sounds");

        // Create the music clips
        for (String key : musicInput.keySet()) {
            try {
                Clip clip = AudioSystem.getClip();
                clip.open(musicInput.get(key));
                musicClips.put(key, clip);
            } catch (Exception e) {
                System.out.println("Error loading music clip: " + key);
            }
        }

        // Create the sound clips
        for (String key : soundInput.keySet()) {
            try {
                Clip clip = AudioSystem.getClip();
                clip.open(soundInput.get(key));
                soundClips.put(key, clip);
            } catch (Exception e) {
                System.out.println("Error loading sound clip: " + key);
            }
        }

    }

    /**
     * Returns the instance of the SoundHandler
     *
     * @return The instance of the SoundHandler
     */
    public static SoundHandler getInstance() {
        if (soundHandler == null) {
            soundHandler = new SoundHandler();
        }
        return soundHandler;
    }

    /**
     * Plays the music with the given key
     * Will stop the current music if there is one playing
     *
     * @param key The name of the music to play
     */
    public void playMusic(String key) {
        if (currentMusic != null) {
            currentMusic.stop();
        }
        currentMusic = musicClips.get(key);
        currentMusic.setFramePosition(0);

        // Lower volume
        FloatControl gainControl = (FloatControl) currentMusic.getControl(
                FloatControl.Type.MASTER_GAIN);
        gainControl.setValue(-10.0f);

        currentMusic.loop(Clip.LOOP_CONTINUOUSLY);
    }

    /**
     * Plays the sound effect with the given key
     *
     * @param key The name of the sound effect to play
     */
    public void playSound(String key) {
        Clip clip = soundClips.get(key);
        clip.setFramePosition(0);

        // Lower volume
        FloatControl gainControl =
                (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
        gainControl.setValue(-10.0f);
        clip.start();
    }

}
