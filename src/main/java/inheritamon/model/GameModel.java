package inheritamon.model;

import inheritamon.model.pokemon.types.RandomPokemon;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;

import inheritamon.model.data.DataHandler;

/**
 * @author Jeremias
 * A class to represent the main logic of the game
 */
public class GameModel {

    public static enum GameState {
        SELECT_STARTER, GAME_START, BATTLE, MAIN_MENU
    }

    private PlayerData playerData;
    private BattleHandler battleHandler;
    private ArrayList<PropertyChangeListener> gameStateListeners = new ArrayList<>();

    // Constructor for a new game
    public GameModel(BattleHandler battleHandler) {
        this.battleHandler = battleHandler;
    }

    public void startNewGame() {
        playerData = new PlayerData();
        notifyGameStateListeners(GameState.SELECT_STARTER);
    }

    public void continueGame() {
        DataHandler dataHandler = DataHandler.getInstance();
        playerData = (PlayerData) dataHandler.loadState("playerData");


        // Check if player data is null
        if (playerData == null) {
            System.out.println("Player data is null!");
            notifyGameStateListeners(GameState.MAIN_MENU);
            return;
        }

        notifyGameStateListeners(GameState.GAME_START);

        // Load world later
    }

    public void startBattle() {
        DataHandler dataHandler = DataHandler.getInstance();
        RandomPokemon groudon = new RandomPokemon(dataHandler.getPokemonData("Groudon"));

        if (playerData.getRoster().allFainted()) {
            System.out.println("All pokemon fainted");
            return;
        }

        battleHandler.startBattle(playerData, groudon);
    }

    public void addStarterData(String pokemon, String perk) {
        playerData.addStarterData(pokemon, perk);
        notifyGameStateListeners(GameState.GAME_START);
    }

    public void addGameStateListener(PropertyChangeListener listener) {
        gameStateListeners.add(listener);
    }

    public void notifyGameStateListeners(GameState event) {
        for (PropertyChangeListener listener : gameStateListeners) {
            
            // Pass the event to the listener
            listener.propertyChange(new PropertyChangeEvent(this, "gameState", null, event));

        }
    }

    public void saveGame() {
        DataHandler dataHandler = DataHandler.getInstance();
        dataHandler.saveState(playerData, "playerData");

        // Save the world data later
    }

    public void returnToMainMenu() {
        notifyGameStateListeners(GameState.MAIN_MENU);
    }
    
}
