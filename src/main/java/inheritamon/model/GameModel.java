package inheritamon.model;

import inheritamon.model.inventory.PlayerData;
import inheritamon.model.pokemon.types.RandomPokemon;
import inheritamon.model.data.DataHandler;

public class GameModel {

    private PlayerData playerData;
    private BattleHandler battleHandler;

    // Constructor for a new game
    public GameModel(BattleHandler battleHandler) {
        this(new PlayerData(), battleHandler);
    }

    // Constructor for a loaded game
    public GameModel(PlayerData playerData, BattleHandler battleHandler) {
        this.playerData = playerData;
        this.battleHandler = battleHandler;
    }

    public void startBattle() {
        DataHandler dataHandler = DataHandler.getInstance();
        RandomPokemon groudon = new RandomPokemon(dataHandler.getCharacterData("Groudon"));

        if (playerData.getRoster().allFainted()) {
            System.out.println("All pokemon fainted");
            return;
        }
        
        battleHandler.startBattle(playerData, groudon);
    }
    
}
