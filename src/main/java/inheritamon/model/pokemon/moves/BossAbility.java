package inheritamon.model.pokemon.moves;
import java.util.*;

import inheritamon.model.pokemon.types.Pokemon;

/**
 * @author Jeremias
 * A class specifically for bosses to use. Implements more complex logic than the other moves.
 */
public class BossAbility extends Ability {

    private Integer turnCounter = 0;

    public BossAbility(HashMap<String, String> moveData) {
        super(moveData);
    }

    @Override
    public int executeMove(Pokemon target, Pokemon user) {

        // Use a different damage formula maybe?
        turnCounter++;
        
        return 0;

    }
    
}
