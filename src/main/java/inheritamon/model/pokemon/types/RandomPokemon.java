package inheritamon.model.pokemon.types;

import java.util.*;

/**
 * @author Jeremias
 * A pokemon that selects a random move to use
 */
public class RandomPokemon extends Pokemon {

    public RandomPokemon(HashMap<String, String> data) {
        super(data);
    }

    @Override
    public String useMove(HashMap<String, Integer> targetStats) {

        // Return a random move from the moves ArrayList
        String move = moves.get((int) (Math.random() * moves.size()));

        System.out.println(stringStats.get("Name") + " used " + move + "!");

        return move;

    }
    
}
