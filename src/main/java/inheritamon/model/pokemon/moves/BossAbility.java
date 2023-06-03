package inheritamon.model.pokemon.moves;
import java.util.*;
import inheritamon.model.pokemon.Pokemon;

/**
 * A class specifically for bosses to use
 */
public class BossAbility extends NormalAbility {

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
