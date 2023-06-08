package inheritamon.model.pokemon.types;
import java.util.*;

/**
 * A pokemon that prioritises damage over all else
 */
public class RecklessPokemon extends Pokemon {

    public RecklessPokemon(HashMap<String, String> pokemonData) {
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
