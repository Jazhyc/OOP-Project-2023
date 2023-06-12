package inheritamon.model;

import inheritamon.model.pokemon.types.*;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;

import inheritamon.model.data.DataHandler;

/**
 * @author Jeremias
 * A class to represent the main logic of the game
 */
public class GameModel {

    // Emergency code to account for the world not adhering to the MVC pattern
    // This should be removed in the future when the world refactor is complete
    private static PlayerData playerData;
    public static PlayerData getPlayerData() {
        return playerData;
    }

    // Same for the battle handler
    private static BattleHandler battleHandler;
    public static BattleHandler getBattleHandler() {
        return battleHandler;
    }

    /**
     * Enum to represent the states that the game can be in
     */
    public static enum GameState {
        SELECT_STARTER, GAME_START, BATTLE, MAIN_MENU
    }

    private ArrayList<PropertyChangeListener> gameStateListeners = new ArrayList<>();
    private PropertyChangeListener rosterListener;

    /**
     * Creates a new game model
     * @param battleHandlerObject The battle handler
     */
    public GameModel(BattleHandler battleHandlerObject) {
        battleHandler = battleHandlerObject;
        setUpBattleStateListener();
    }

    /**
     * Creates a a new player and notifies the listeners that the game state has changed
     */
    public void startNewGame() {
        playerData = new PlayerData();
        notifyGameStateListeners(GameState.SELECT_STARTER);
    }

    /**
     * Loads the player data and notifies the listeners that the game state has changed
     */
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

    /**
     * Starts a battle with a random pokemon
     */
    public void startBattle() {
        DataHandler dataHandler = DataHandler.getInstance();
        Pokemon groudon = new RandomPokemon(dataHandler.getPokemonData("Groudon"));

        if (playerData.getRoster().allFainted()) {
            System.out.println("All pokemon fainted");
            return;
        }

        battleHandler.startBattle(playerData, groudon);
    }

    /**
     * Adds the starting pokemon and perk to the player data
     * @param pokemon The starting pokemon
     * @param perk The starting perk
     */
    public void addStarterData(String pokemon, String perk) {
        playerData.addStarterData(pokemon, perk);
        notifyGameStateListeners(GameState.GAME_START);
        notifyRosterListener();
    }

    /**
     * Adds a listener to the game state listeners which will be notified when the game state changes
     * @param listener The listener to add
     */
    public void addGameStateListener(PropertyChangeListener listener) {
        gameStateListeners.add(listener);
    }

    /**
     * Adds a listener to the roster listener which will be notified when the roster changes
     * @param listener The listener to add
     */
    public void addRosterListener(PropertyChangeListener listener) {
        rosterListener = listener;
    }

    private void notifyGameStateListeners(GameState event) {
        for (PropertyChangeListener listener : gameStateListeners) {
            
            // Pass the event to the listener
            listener.propertyChange(new PropertyChangeEvent(this, "gameState", null, event));

        }
    }

    private void notifyRosterListener() {
        rosterListener.propertyChange(new PropertyChangeEvent(this, "roster", null, playerData.getRoster().getArray()));
    }

    /**
     * Saves the player data and world data
     */
    public void saveGame() {
        DataHandler dataHandler = DataHandler.getInstance();
        dataHandler.saveState(playerData, "playerData");

        // Save the world data later
    }

    /**
     * Changes the game state to the main menu
     */
    public void returnToMainMenu() {
        notifyGameStateListeners(GameState.MAIN_MENU);
    }

    private void setUpBattleStateListener() {
        battleHandler.addListener("battleState", e -> {

            // Implement more functionality later
            notifyRosterListener();

        });
    }
    
}
