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
        SELECT_STARTER, GAME_START, Battle, Menu
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
    
}
