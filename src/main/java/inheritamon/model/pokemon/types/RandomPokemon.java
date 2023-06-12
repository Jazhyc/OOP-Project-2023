package inheritamon.model.pokemon.types;

import java.util.*;

/**
 * @author Jeremias
 * A pokemon that selects a random move to use
 */
public class RandomPokemon extends Pokemon {

    /**
     * Constructor for the RandomPokemon class
     * @param data The data of the pokemon
     */
    public RandomPokemon(HashMap<String, String> data) {
        super(data);
    }

    /**
     * Uses a move based on the pokemon's current stats
     * @param targetStats The stats of the target pokemon
     */
    @Override
    public String useMove(HashMap<String, Integer> targetStats) {

        // Return a random move from the moves ArrayList
        String move = moves.get((int) (Math.random() * moves.size()));

        System.out.println(stringStats.get("Name") + " used " + move + "!");

        return move;

    }
    
}
