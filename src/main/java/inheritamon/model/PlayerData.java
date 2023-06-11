package inheritamon.model;

import inheritamon.model.data.DataHandler;
import inheritamon.model.inventory.*;
import inheritamon.model.pokemon.types.PlayerPokemon;

import java.io.Serializable;
import java.util.*;

/**
 * Class that holds all the data of the player
 */
public class PlayerData implements Serializable {

    public static enum TrainerAbility {
        CLIMBER, SWIMMER, RICH;
    }

    private PlayerRoster roster;
    private Inventory inventory;
    private ArrayList<TrainerAbility> abilities = new ArrayList<TrainerAbility>();

    public PlayerData() {
        roster = new PlayerRoster();
        inventory = new Inventory();
    }

    public PlayerRoster getRoster() {
        return roster;
    }

    public Inventory getInventory() {
        return inventory;
    }

    public ArrayList<TrainerAbility> getAbilities() {
        return abilities;
    }

    public void addAbility(TrainerAbility ability) {
        abilities.add(ability);
    }

    public void addStarterData(String pokemon, String perk) {

        DataHandler dataHandler = DataHandler.getInstance();

        roster.addPokemon(new PlayerPokemon(dataHandler.getPokemonData(pokemon)));
        
        // Convert the perk into a trainer ability
        TrainerAbility ability = TrainerAbility.valueOf(perk.toUpperCase());

        // If the ability is rich, add 1000 coins to the inventory
        if (ability == TrainerAbility.RICH) {
            inventory.addCoins(1000);
        }

        addAbility(ability);

        System.out.println("Added starter data");
        
    }

    
}
