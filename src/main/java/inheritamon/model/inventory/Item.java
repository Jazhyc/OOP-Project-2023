package inheritamon.model.inventory;

import java.util.HashMap;
import inheritamon.model.pokemon.types.*;

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

    private String itemSprite;
    public String getItemSprite() {
        return itemSprite;
    }

    public Item(HashMap<String, String> itemData) {
        itemName = itemData.get("Name");
        itemDescription = itemData.get("Description");
        itemSprite = itemData.get("Sprite");
    }

    //? Maybe an abstract class would be better for items? We could use it to make a distinction between normal items and balls
    /**
     * Method that uses an item on a pokemon
     * @param enemyPokemon
     * @param playerPokemon
     * @return
     */
    public int useItem(Pokemon enemyPokemon, Pokemon playerPokemon) {
        return 0;
    }

}
