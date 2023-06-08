package inheritamon.model.pokemon;

import java.util.ArrayList;
import inheritamon.model.pokemon.types.PlayerPokemon;

public class PlayerRoster {

    // Define an arraylist of pokemon
    private ArrayList<PlayerPokemon> roster;
    public final static int MAX_POKEMON = 6;

    public PlayerRoster() {
        roster = new ArrayList<PlayerPokemon>();
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
    
}
