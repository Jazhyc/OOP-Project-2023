package inheritamon.model.inventory;

import java.util.HashMap;

/**
 * @author Jona Janssen
 * Item class. Items have names, descriptions and data, which is stored in a csv file (items.csv)
 */
public class Item {
    String itemName = new String();
    public String getItemName() {
        return itemName;
    }
    String itemDescription = new String();
    public String getItemDescription() {
        return itemDescription;
    }

    public Item(HashMap<String, String> itemData) {
        itemName = itemData.get("Name");
        itemDescription = itemData.get("Description");
    }

}
