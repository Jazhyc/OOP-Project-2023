package inheritamon;
import inheritamon.model.*;
import inheritamon.model.data.*;
import inheritamon.model.pokemon.*;
import inheritamon.model.pokemon.moves.*;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        
        // Create the data object and load all the move data
        DataHandler dataHandler = new DataHandler();
        HashMap<String, NormalAbility> moveData = dataHandler.getAllAbilities();

        Pokemon groudon = new Pokemon(dataHandler.getCharacterData("Groudon"));
        Pokemon charizard = new Pokemon(dataHandler.getCharacterData("Charizard"));

        BattleHandler battleHandler = new BattleHandler(charizard, groudon, moveData);
        battleHandler.startBattle();

    }
}