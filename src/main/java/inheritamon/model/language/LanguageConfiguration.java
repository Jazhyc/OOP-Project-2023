package inheritamon.model.language;

import java.util.*;
import java.beans.*;
import inheritamon.model.data.DataHandler;

/**
 * @Author Jeremias
 * A class to handle the language configuration of the game
 */
public class LanguageConfiguration {

    // Singleton pattern
    private static LanguageConfiguration instance = null;

    public static LanguageConfiguration getInstance() {
        if (instance == null) {
            instance = new LanguageConfiguration();
        }
        return instance;
    }

    public enum Language {
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

        // Loop over all keys in language data and put them in the appropriate hashmap based on the type column
        // If the type is array, segment the string into an array and put it in the optionMap
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

    public void switchLanguage() {

        // If en switch to nl and vice versa
        if (selectedLanguage == Language.EN) {
            selectedLanguage = Language.NL;
        } else {
            selectedLanguage = Language.EN;
        }

        notifyListeners(new PropertyChangeEvent(this, "Language", null, selectedLanguage.toString()));

    }

    public void addLanguageChangeListener(PropertyChangeListener listener) {
        
        listeners.add(listener);

    }

    private void notifyListeners(PropertyChangeEvent event) {
        for (PropertyChangeListener listener : listeners) {
            listener.propertyChange(event);
        }
    }

    public String[] getOptions(String key) {
        String[] options = optionMap.get(key).get(selectedLanguage.toString());
        return Arrays.copyOf(options, options.length);
    }

    public String getText(String key) {
        return stringMap.get(key).get(selectedLanguage.toString());
    }
    
}
