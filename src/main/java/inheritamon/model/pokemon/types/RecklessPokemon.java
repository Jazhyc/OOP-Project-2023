package inheritamon.model.pokemon.types;
import java.util.*;

import inheritamon.model.data.DataHandler;

/**
 * @author Jeremias
 * A pokemon that prioritises damage over all else
 */
public class RecklessPokemon extends Pokemon {

    public RecklessPokemon(HashMap<String, String> pokemonData) {
        super(pokemonData);
    }

    @Override
    public String useMove(HashMap<String, Integer> targetStats) {

        // Always use the move with the highest modifier
        String move = "";

        // Get the highest modifier
        Integer highestModifier = 0;
        DataHandler dataHandler = DataHandler.getInstance();

        for (String moveName : this.moves) {

            HashMap<String, String> moveData = dataHandler.getMoveData(moveName);

            Integer modifier = Integer.parseInt(moveData.get("Modifier"));

            if (modifier > highestModifier && !moveData.get("Type").equals("Healing")) {
                highestModifier = modifier;
                move = moveName;
            }

        }

        return move;

    }
    
}
