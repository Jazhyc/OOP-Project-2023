package inheritamon.model.inventory;

import java.util.ArrayList;

import inheritamon.model.pokemon.types.PlayerPokemon;
import inheritamon.model.data.DataHandler;

/**
 * @author Jeremias
 * A class to represent the player's pokemon roster
 */
public class PlayerRoster {

    // Define an arraylist of pokemon
    private ArrayList<PlayerPokemon> roster;
    public final static int MAX_POKEMON = 6;

    // Constructor for a new game
    //? We don't need one when continuing a game?
    public PlayerRoster() {
        roster = new ArrayList<PlayerPokemon>();

        // Add groudon and blastoise to the roster, will replace this once pokemon selection is implemented
        DataHandler dataHandler = DataHandler.getInstance();
        PlayerPokemon charizard = new PlayerPokemon(dataHandler.getCharacterData("Charizard"));
        PlayerPokemon blastoise = new PlayerPokemon(dataHandler.getCharacterData("Blastoise"));
        addPokemon(charizard);
        addPokemon(blastoise);
    }

    // Function to add a pokemon to the roster
    public void addPokemon(PlayerPokemon pokemon) {

        // Check if the roster is full
        if (roster.size() >= MAX_POKEMON) {
            System.out.println("Roster is full!");
            return;
        }

        // Add the pokemon to the roster
        roster.add(pokemon);

    }

    // Function to get a pokemon from the roster
    public PlayerPokemon getPokemon(int index) {

        // Check if the index is valid
        if (index < 0 || index >= roster.size()) {
            System.out.println("Invalid index!");
            return null;
        }

        // Return the pokemon at the index
        return roster.get(index);

    }

    /**
     * Get the roster as an array
     * @return The roster as an array
     */
    public PlayerPokemon[] getRoster() {
        return roster.toArray(new PlayerPokemon[roster.size()]);
    }

    // Function to revitalize all pokemon
    public void revitalizeAll() {

        // Loop over the roster
        for (PlayerPokemon pokemon : roster) {

            // Revitalize the pokemon
            pokemon.revitalize();

        }

    }

    public boolean allFainted() {
            
        // Loop over the roster
        for (PlayerPokemon pokemon : roster) {

            // Check if the pokemon is fainted
            if (!pokemon.isFainted()) {
                return false;
            }

        }

        return true;
    
    }

    public int getNextPokemon() {
            
        // Loop over the roster
        for (int i = 0; i < roster.size(); i++) {

            // Check if the pokemon is fainted
            if (!roster.get(i).isFainted()) {
                return i;
            }

        }

        return -1;
    }
    
}
