package inheritamon.view;

import java.util.HashMap;
import javax.sound.sampled.*;

import inheritamon.model.data.DataHandler;

public class SoundHandler {

    // Singleton instance for now at least
    private static SoundHandler soundHandler;

    private HashMap<String, Clip> soundClips = new HashMap<String, Clip>();
    private HashMap<String, Clip> musicClips = new HashMap<String, Clip>();

    private Clip currentMusic;

    /**
     * The constructor for the SoundHandler class
     */
    private SoundHandler() {

        DataHandler dataHandler = DataHandler.getInstance();

        HashMap<String, AudioInputStream> musicInput = dataHandler.getAudios("Music");
        HashMap<String, AudioInputStream> soundInput = dataHandler.getAudios("Sounds");

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

    public static SoundHandler getInstance() {
        if (soundHandler == null) {
            soundHandler = new SoundHandler();
        }
        return soundHandler;
    }

    public void playMusic(String key) {
        if (currentMusic != null) {
            currentMusic.stop();
        }
        currentMusic = musicClips.get(key);

        // Lower volume
        FloatControl gainControl = (FloatControl) currentMusic.getControl(FloatControl.Type.MASTER_GAIN);
        gainControl.setValue(-10.0f);

        currentMusic.loop(Clip.LOOP_CONTINUOUSLY);
    }

    public void playSound(String key) {
        Clip clip = soundClips.get(key);
        clip.setFramePosition(0);
        
        // Lower volume
        FloatControl gainControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
        gainControl.setValue(-10.0f);
        clip.start();
    }
    
}
