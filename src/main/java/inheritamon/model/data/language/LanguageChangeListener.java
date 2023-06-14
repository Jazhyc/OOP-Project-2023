package inheritamon.model.data.language;

/**
 * @author Jeremias
 * Interface for language change listeners
 * Used to notify the listeners that the language has changed
 */
public interface LanguageChangeListener {

    /**
     * Adds a language change listener to the language configuration
     * which will be notified when the language changes
     */
    public void addLanguageListener();

}
