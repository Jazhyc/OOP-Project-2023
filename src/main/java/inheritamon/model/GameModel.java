package inheritamon.model;

import inheritamon.controller.PokemonTrainer;
import inheritamon.controller.TrainerAbility;
import inheritamon.controller.TrainerRegion;
import inheritamon.model.inventory.PlayerData;
import inheritamon.model.pokemon.types.RandomPokemon;
import inheritamon.model.data.DataHandler;
import inheritamon.view.menu.RegionSelectionWindow;

/**
 * @author Jeremias
 * A class to represent the main logic of the game
 */
public class GameModel {

    private PlayerData playerData;
    private BattleHandler battleHandler;

    private PokemonTrainer player;

    // Constructor for a new game
    public GameModel(BattleHandler battleHandler) {
        this.battleHandler = battleHandler;
    }

    public void startNewGame() {
        RegionSelectionWindow regionSelectionWindow = new RegionSelectionWindow(this);
        playerData = new PlayerData();
    }

    public void spawnPlayer(String username, TrainerRegion region, TrainerAbility ability) {
        player = new PokemonTrainer(username, region) {
        };
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
