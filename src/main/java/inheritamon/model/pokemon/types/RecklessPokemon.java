package inheritamon.model.pokemon.types;

import java.util.*;

import inheritamon.model.data.DataHandler;

/**
 * @author Jeremias
 * A pokemon that prioritises damage over all else
 */
public class RecklessPokemon extends Pokemon {

    /**
     * Constructor for the RecklessPokemon class
     *
     * @param pokemonData The data of the pokemon
     */
    public RecklessPokemon(HashMap<String, String> pokemonData) {
        super(pokemonData);
    }

    /**
     * Uses a move based on the pokemon's current stats
     *
     * @param targetStats The stats of the target pokemon
     */
    @Override
    public String useMove(HashMap<String, Integer> targetStats) {

        // Always use the move with the highest modifier
        String move = "";

        // Get the highest modifier
        Integer highestModifier = 0;
        DataHandler dataHandler = DataHandler.getInstance();

        for (String moveName : this.moves) {

            HashMap<String, String> moveData =
                    dataHandler.getMoveData(moveName);

            Integer modifier = Integer.parseInt(moveData.get("Modifier"));

            if (modifier > highestModifier &&
                    !moveData.get("Type").equals("Healing")) {
                highestModifier = modifier;
                move = moveName;
            }

        }

        return move;

    }

}
