package inheritamon;
import inheritamon.model.data.*;
import inheritamon.model.moves.*;
import inheritamon.model.pokemon.*;

public class Main {
    public static void main(String[] args) {
        
        DataHandler dataHandler = new DataHandler();
        Pokemon groudon = new Pokemon(dataHandler.getCharacterData("Groudon"));
        Pokemon charizard = new Pokemon(dataHandler.getCharacterData("Charizard"));
        
        String selectedMove = groudon.useMove(charizard.getAllNumericalStats());
        NormalAbility ability = new NormalAbility(dataHandler.getMoveData(selectedMove));
        ability.useMove(charizard, groudon);


    }
}