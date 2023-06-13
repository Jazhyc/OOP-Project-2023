package inheritamon.model.pokemon.moves;

import java.util.HashMap;
import inheritamon.model.data.DataHandler;

import inheritamon.model.pokemon.types.Pokemon;

/**
 * @Author Jeremias
 *         An abstract class for all moves
 */
public abstract class Ability {

    protected HashMap<String, Integer> numericalStats = new HashMap<String, Integer>();
    protected HashMap<String, String> stringStats = new HashMap<String, String>();

    /**
     * The method to use the move
     * 
     * @param target The target of the move
     * @return The damage dealt / health restored
     */
    public abstract int executeMove(Pokemon target, Pokemon user);

    /**
     * A method to set up the move
     * 
     * @param moveData The data of the move
     */
    public void setUp(HashMap<String, String> moveData) {
        // Loop through the data and put it into the correct HashMap
        for (String key : moveData.keySet()) {

            // Use the isNumeric method to check if the value is a number
            if (DataHandler.isNumeric(moveData.get(key))) {
                numericalStats.put(key, Integer.parseInt(moveData.get(key)));
            } else {
                stringStats.put(key, moveData.get(key));
            }

        }
    }

    /**
     * The constructor for the Move class
     * 
     * @param moveData The data of the move
     */
    public Ability(HashMap<String, String> moveData) {
        setUp(moveData);
    }

}
