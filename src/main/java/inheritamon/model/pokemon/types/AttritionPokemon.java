package inheritamon.model.pokemon.types;

import java.util.*;
import inheritamon.model.data.DataHandler;

/**
 * @author Jeremias
 * A pokemon that plays it safe by healing whenever it is low HP
 */
public class AttritionPokemon extends Pokemon {

    public AttritionPokemon(HashMap<String, String> pokemonData) {
        super(pokemonData);
    }

    @Override
    public String useMove(HashMap<String, Integer> targetStats) {

        // If HP is below 50%, use a healing move
        if (this.numericalStats.get("HP") < this.numericalStats.get("MaxHP") / 2) {

            // Get the first healing move
            for (String moveName : this.moves) {

                DataHandler dataHandler = DataHandler.getInstance();
                HashMap<String, String> moveData = dataHandler.getMoveData(moveName);

                // Get the mp cost and current mp
                Integer mpCost = Integer.parseInt(moveData.get("Cost"));
                Integer currentMP = this.numericalStats.get("MP");

                if (moveData.get("Type").equals("Healing") && mpCost <= currentMP) {
                    return moveName;
                }

            }

        }

        // Otherwise, return a random move from the moves ArrayList
        String move = moves.get((int) (Math.random() * moves.size()));
        return move;

    }
    
}
