package inheritamon.model.pokemon.types;
import java.util.*;

/**
 * A pokemon that plays it safe by healing whenever it is low HP
 */
public class AttritionPokemon extends Pokemon {

    public AttritionPokemon(HashMap<String, String> pokemonData) {
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
