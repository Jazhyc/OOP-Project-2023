package inheritamon.model.data.language;

import java.util.*;

import java.beans.*;
import inheritamon.model.data.DataHandler;

/**
 * @Author Jeremias
 *         A class to handle the language configuration of the game
 *         Uses the singleton pattern
 */
public class LanguageConfiguration {

    // Singleton pattern
    private static LanguageConfiguration instance = null;

    /**
     * Returns the instance of the language configuration
     * 
     * @return The instance of the language configuration
     */
    public static LanguageConfiguration getInstance() {
        if (instance == null) {
            instance = new LanguageConfiguration();
        }
        return instance;
    }

    /**
     * Enum to represent the languages that the game can be in
     */
    public static enum Language {
        EN, NL
    }

    private Language selectedLanguage;

    // Arraylist of listeners
    private ArrayList<PropertyChangeListener> listeners = new ArrayList<>();

    private HashMap<String, HashMap<String, String>> stringMap = new HashMap<String, HashMap<String, String>>();
    private HashMap<String, HashMap<String, String[]>> optionMap = new HashMap<String, HashMap<String, String[]>>();

    private LanguageConfiguration() {
        selectedLanguage = Language.EN;

        DataHandler dataHandler = DataHandler.getInstance();
        HashMap<String, HashMap<String, String>> languageData = dataHandler.getLanguageData();

        // Loop over all keys in language data and put them in the appropriate hashmap
        // based on the type column
        // If the type is array, segment the string into an array and put it in the
        // optionMap
        for (String key : languageData.keySet()) {
            HashMap<String, String> data = languageData.get(key);
            String type = data.get("Type");
            if (type.equals("Array")) {

                // Split the EN and NL strings into arrays
                String[] en = data.get("EN").split(";");
                String[] nl = data.get("NL").split(";");

                // Create a hashmap to store the arrays
                HashMap<String, String[]> optionMapData = new HashMap<String, String[]>();
                optionMapData.put("EN", en);
                optionMapData.put("NL", nl);
                optionMap.put(key, optionMapData);
            } else {
                stringMap.put(key, data);
            }
        }

    }

    /**
     * Switches the language and notifies the listeners
     * Used by the language button in the main menu
     */
    public void switchLanguage() {

        // If en switch to nl and vice versa
        if (selectedLanguage == Language.EN) {
            selectedLanguage = Language.NL;
        } else {
            selectedLanguage = Language.EN;
        }

        notifyListeners(new PropertyChangeEvent(this, "Language", null, selectedLanguage.toString()));

    }

    /**
     * Adds a listener to the list of listeners
     * 
     * @param listener The listener to add
     */
    public void addLanguageChangeListener(PropertyChangeListener listener) {

        listeners.add(listener);

    }

    private void notifyListeners(PropertyChangeEvent event) {
        for (PropertyChangeListener listener : listeners) {
            listener.propertyChange(event);
        }
    }

    /**
     * Used to get an array of options for menus with multiple strings to display
     * 
     * @param key The key of the menu to get the options for
     * @return An array of options
     */
    public String[] getOptions(String key) {
        String[] options = optionMap.get(key).get(selectedLanguage.toString());
        return Arrays.copyOf(options, options.length);
    }

    /**
     * Used to get a string to display
     * 
     * @param key The key of the string to get
     * @return The string to display
     */
    public String getText(String key) {
        return stringMap.get(key).get(selectedLanguage.toString());
    }

    /**
     * Used to get the name of pokemon's move in the selected language
     * 
     * @param moveName The name of the move
     * @return The name of the move in the selected language
     */
    public String getLocalMoveName(String moveName) {

        HashMap<String, String> moveData = DataHandler.getInstance().getMoveData(moveName);
        return moveData.get(selectedLanguage.toString());

    }

}
