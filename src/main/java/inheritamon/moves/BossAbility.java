package inheritamon.moves;
import java.util.*;
import inheritamon.pokemon.Pokemon;

/**
 * A class specifically for bosses to use
 */
public class BossAbility extends NormalAbility {

    private Integer turnCounter = 0;

    public BossAbility(HashMap<String, String> moveData) {
        super(moveData);
    }

    @Override
    public void useMove(Pokemon target, Pokemon user) {

        // Use a different damage formula maybe?
        

    }
    
}
