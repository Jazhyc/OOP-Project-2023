package inheritamon.model.data;
import java.util.*;

public class Discourse {
    private Locale currentLocale;
    private ResourceBundle messages;

    public Discourse(Locale locale){
        currentLocale = locale;
        messages = ResourceBundle.getBundle("Text", currentLocale);
    }
    public String getMessage(String key){
        return messages.getString(key);
    }
}
