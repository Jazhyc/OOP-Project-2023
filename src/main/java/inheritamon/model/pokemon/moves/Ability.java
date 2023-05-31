package inheritamon.model.pokemon.moves;

import java.util.HashMap;

import inheritamon.model.pokemon.Pokemon;

/**
 * An interface for all moves
 */
public interface Ability {

    /**
     * The method to use the move
     * @param target The target of the move
     */
    public void useMove(Pokemon target, Pokemon user);

    /**
     * A method to set up the move
     * @param moveData The data of the move
     */
    public void setUp(HashMap<String, String> moveData);
    
}
