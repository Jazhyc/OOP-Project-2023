package inheritamon.model.pokemon.types;

import java.util.HashMap;

/**
 * A pokemon that specialises in giving the player a hard time
 */
public class Boss extends Pokemon {

    public Boss(HashMap<String, String> pokemonData) {
        super(pokemonData);
    }

    @Override
    public String useMove(HashMap<String, Integer> targetStats) {

        // Return a random move from the moves ArrayList
        String move = moves.get((int) (Math.random() * moves.size()));

        System.out.println(stringStats.get("Name") + " used " + move + "!");

        return move;

    }
    
}
