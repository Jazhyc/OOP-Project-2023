package inheritamon.controller;

import inheritamon.model.BattleHandler;
import inheritamon.model.GameModel;
import inheritamon.model.npcs.types.PlayerPokemon;

/**
 * @author Jeremias
 * Controller for the general game state and battle.
 */
public class GameController {

    /**
     * The battle handler which can be used to get information about the battle.
     * Used to pass information to the battle handler like player moves.
     */
    private final BattleHandler battleHandler;

    /**
     * The game model which can be used to get information about the game
     * state. Used to pass information to the game model like starting a battle.
     */
    private final GameModel gameModel;

    /**
     * Constructor for the GameController
     *
     * @param battleHandler The battle handler
     * @param gameModel     The game model
     */
    public GameController(BattleHandler battleHandler, GameModel gameModel) {
        this.battleHandler = battleHandler;
        this.gameModel = gameModel;
    }

    /**
     * Selects a move for the player and passes it to the battle handler
     *
     * @param move The move to select in the form of a string
     */
    public void selectMove(String move) {

        PlayerPokemon playerPokemon = battleHandler.getActivePlayerPokemon();
        playerPokemon.selectMove(move);

    }

    /**
     * Selects a pokemon for the player and passes it to the battle handler, used
     * for switching pokemon
     *
     * @param index The index of the pokemon to switch to
     */
    public void selectPokemon(int index) {

        PlayerPokemon playerPokemon = battleHandler.getActivePlayerPokemon();
        playerPokemon.selectMove("switch " + index);
    }

    /**
     * Selects an item for the player and passes it to the battle handler
     *
     * @param item The item to select in the form of a string
     */
    public void selectItem(Integer item) {
        System.out.println("You selected " + item);
        PlayerPokemon playerPokemon = battleHandler.getActivePlayerPokemon();
        playerPokemon.selectMove("item " + item);
    }

    /**
     * Begins the battle
     */
    public void beginRandomBattle() {

        String[] types = {"random", "attrition", "reckless"};

        // Get a random type
        gameModel.startPokemonBattle(
                types[(int) (Math.random() * types.length)]);
    }

    /**
     * Requests the model to save the game
     */
    public void saveGame() {
        gameModel.saveGame();
    }

    /**
     * Requests the model to change the game state to the main menu
     */
    public void returnToMainMenu() {
        gameModel.returnToMainMenu();
    }

}
