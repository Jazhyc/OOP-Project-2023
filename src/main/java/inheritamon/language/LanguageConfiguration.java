package inheritamon.language;

import java.util.*;
import java.beans.*;
import inheritamon.model.data.DataHandler;

public class LanguageConfiguration {

    // Singleton pattern
    private static LanguageConfiguration instance = null;

    // get instance
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

    // Function to set the language
    public void setLanguage(Language language) {
        selectedLanguage = language;
        System.out.println("Language set to " + language);
        notifyListeners(new PropertyChangeEvent(this, "language", null, language));
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
    
}
