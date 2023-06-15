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
     * Method that uses an item on a pokemon
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
