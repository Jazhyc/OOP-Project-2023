package inheritamon.model.inventory;

import java.util.HashMap;
import java.io.Serializable;

import inheritamon.model.data.DataHandler;
import inheritamon.model.npcs.Roster;
import inheritamon.model.npcs.types.*;

/**
 * @author Jona Janssen
 * Item class. Items have names, descriptions and data, which is stored in a csv file (items.csv)
 */
public class Item implements Serializable {
    String itemName = new String();

    /**
     * Returns item name
     * @return
     */
    public String getItemName() {
        return itemName;
    }
    String itemDescription = new String();

    /**
     * Returns item description
     * @return
     */
    public String getItemDescription() {
        return itemDescription;
    }

    private String itemSprite;

    /**
     * Returns item sprite
     * @return
     */
    public String getItemSprite() {
        return itemSprite;
    }

    private String itemType;

    private int itemAmount;

    public Item(HashMap<String, String> itemData) {
        itemName = itemData.get("Name");
        itemDescription = itemData.get("Description");
        itemSprite = itemData.get("Sprite");
        itemType = itemData.get("Type");
        itemAmount = Integer.parseInt(itemData.get("Amount"));
    }

    /**
     * According to the type of item, give some effect (Use an item). Returns true if a pokemon was caught with this action,
     * returns false otherwise
     *
     * @param enemyPokemon
     * @param playerPokemon
     * @param playerRoster
     * @return
     */
    public boolean useItem(Pokemon enemyPokemon, Pokemon playerPokemon, Roster playerRoster) {
        if (itemType.equals("Healing")) {
            playerPokemon.gainHP(itemAmount);
        }
        if (itemType.equals("Pokeball")) {
            // check if succeeds (enemy has taken damage)
            DataHandler dh = DataHandler.getInstance();
            if (enemyPokemon.getHP() < enemyPokemon.getNumericalStat("MaxHP")) {
                // add pokemon to player roster
                PlayerPokemon newPokemon = new PlayerPokemon(dh.getPokemonData(enemyPokemon.getName()));
                playerRoster.addPokemon(newPokemon);
                return true;
            }
        }
        return false;
    }
}
