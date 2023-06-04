package inheritamon.model.data;

public class SoundHandler {

    // Singleton instance
    private static SoundHandler soundHandler;

    /**
     * The constructor for the SoundHandler class
     */
    private SoundHandler() {

    }

    public SoundHandler getInstance() {
        if (soundHandler == null) {
            soundHandler = new SoundHandler();
        }
        return soundHandler;
    }
    
}
