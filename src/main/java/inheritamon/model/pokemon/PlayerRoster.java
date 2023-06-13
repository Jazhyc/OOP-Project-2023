package inheritamon.model.pokemon;

import java.util.ArrayList;

import inheritamon.model.pokemon.types.PlayerPokemon;

import java.io.Serializable;

/**
 * @author Jeremias
 *         A class to represent the player's pokemon roster
 */
public class PlayerRoster implements Serializable {

    // Define an arraylist of pokemon
    private ArrayList<PlayerPokemon> roster;
    public final static int MAX_POKEMON = 6;

    /**
     * Constructor for the PlayerRoster class
     */
    public PlayerRoster() {
        roster = new ArrayList<PlayerPokemon>();
    }

    /**
     * Adds a pokemon to the roster
     * 
     * @param pokemon The pokemon to add
     */
    public void addPokemon(PlayerPokemon pokemon) {

        // Check if the roster is full
        if (roster.size() >= MAX_POKEMON) {
            System.out.println("Roster is full!");
            return;
        }

        // Add the pokemon to the roster
        roster.add(pokemon);

    }

    /**
     * Get a pokemon from the roster
     * 
     * @param index The index of the pokemon
     * @return The pokemon at the index
     */
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
     * 
     * @return The roster as an array
     */
    public PlayerPokemon[] getArray() {
        return roster.toArray(new PlayerPokemon[roster.size()]);
    }

    /**
     * Revitalize all pokemon in the roster, fully healing them
     */
    public void revitalizeAll() {

        // Loop over the roster
        for (PlayerPokemon pokemon : roster) {

            // Revitalize the pokemon
            pokemon.revitalize();

        }

    }

    /**
     * Check if all pokemon in the roster are fainted
     * 
     * @return True if all pokemon are fainted, false otherwise
     */
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

    /**
     * Get the index of the first alive pokemon
     * 
     * @return
     */
    public int getAlivePokemon() {

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
