package inheritamon.controller;

import inheritamon.model.GameModel;

/**
 * @author Jeremias
 *         Controller for the main menu and starter selection menu
 */
public class MenuController {

    private GameModel gameModel;

    /**
     * Constructor for the MenuController
     * 
     * @param gameModel The game model
     */
    public MenuController(GameModel gameModel) {
        this.gameModel = gameModel;
    }

    /**
     * Requests the game model to start a new game
     */
    public void startGame() {
        gameModel.startNewGame();
    }

    /**
     * Passes the selected starter pokemon and perk to the game model
     * 
     * @param pokemon The selected pokemon
     * @param perk    The selected perk
     */
    public void handleStartingClass(String pokemon, String perk) {
        System.out.println("Starting class: " + pokemon + " " + perk);
        gameModel.addStarterData(pokemon, perk);
    }

    /**
     * Requests the game model to continue the game
     */
    public void continueGame() {
        gameModel.continueGame();
    }

}
