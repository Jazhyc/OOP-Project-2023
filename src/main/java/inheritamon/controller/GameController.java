package inheritamon.controller;

import inheritamon.model.BattleHandler;
import inheritamon.model.GameModel;
import inheritamon.model.pokemon.types.PlayerPokemon;

/**
 * @Author Jeremias
 * Controller for the battle
 */
public class GameController {

    private BattleHandler battleHandler;
    private GameModel gameModel;
    
    public GameController(BattleHandler battleHandler, GameModel gameModel) {
        this.battleHandler = battleHandler;
        this.gameModel = gameModel;
    }

    public void selectChoice(String choice) {
        System.out.println("You selected " + choice);
    }

    public void selectMove(String move) {
       
        PlayerPokemon playerPokemon = battleHandler.getActivePlayerPokemon();
        playerPokemon.selectMove(move);

    }

    public void selectPokemon(int index) {

        // Check if it's the player's turn
        if (!battleHandler.isPlayerTurn()) {
            return;
        }
        
        PlayerPokemon playerPokemon = battleHandler.getActivePlayerPokemon();
        playerPokemon.selectMove("switch " + index);
    }

    public void selectItem(Integer item) {
        System.out.println("You selected " + item);
        PlayerPokemon playerPokemon = battleHandler.getActivePlayerPokemon();
        playerPokemon.selectMove("item " + item);
    }

    public void beginBattle() {
        gameModel.startBattle();
    }

    public void saveGame() {
        gameModel.saveGame();
    }

    public void returnToMainMenu() {
        gameModel.returnToMainMenu();
    }

}
