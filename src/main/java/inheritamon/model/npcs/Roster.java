package inheritamon.model.npcs;

import java.util.ArrayList;

import inheritamon.model.npcs.types.Pokemon;

import java.io.Serializable;

/**
 * @author Jeremias
 * A class to represent the roster of a trainer.
 * 
 * The only trainer in the game is the player. 
 * However, this class is designed to be used for other trainers as well.
 */
public class Roster implements Serializable {

    /**
     * The pokemons of the player.
     */
    private final ArrayList<Pokemon> roster;

    /**
     * The maximum number of pokemon in the roster.
     */
    public static final int MAX_POKEMON = 6;

    /**
     * Constructor for the PlayerRoster class.
     */
    public Roster() {
        roster = new ArrayList<>();
    }

    /**
     * Adds a pokemon to the roster.
     *
     * @param pokemon The pokemon to add
     */
    public void addPokemon(Pokemon pokemon) {

        // Check if the roster is full
        if (roster.size() >= MAX_POKEMON) {
            System.out.println("Roster is full!");
            return;
        }

        // Add the pokemon to the roster
        roster.add(pokemon);

    }

    /**
     * Get a pokemon from the roster.
     *
     * @param index The index of the pokemon
     * @return The pokemon at the index
     */
    public Pokemon getPokemon(int index) {

        // Check if the index is valid
        if (index < 0 || index >= roster.size()) {
            System.out.println("Invalid index!");
            return null;
        }

        // Return the pokemon at the index
        return roster.get(index);

    }

    /**
     * Get the roster as an array.
     *
     * @return The roster as an array
     */
    public Pokemon[] getArray() {
        return roster.toArray(new Pokemon[0]);
    }

    /**
     * Revitalize all pokemon in the roster, fully healing them.
     */
    public void revitalizeAll() {

        // Loop over the roster
        for (Pokemon pokemon : roster) {

            // Revitalize the pokemon
            pokemon.revitalize();

        }

    }

    /**
     * Check if all pokemon in the roster are fainted.
     *
     * @return True if all pokemon are fainted, false otherwise
     */
    public boolean allFainted() {

        // Loop over the roster
        for (Pokemon pokemon : roster) {

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
     * @return The index of the first alive pokemon, -1 if all pokemon are fainted
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
