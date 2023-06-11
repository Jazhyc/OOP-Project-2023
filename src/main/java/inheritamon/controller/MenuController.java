package inheritamon.controller;

import inheritamon.model.GameModel;

/**
 * @author Jeremias
 * Controller for the main menu and starter selection menu
 */
public class MenuController {

    private GameModel gameModel;

    public MenuController(GameModel gameModel) {
        this.gameModel = gameModel;
    }

    public void startGame() {
        gameModel.startNewGame();
    }

    public void handleStartingClass(String pokemon, String perk) {
        System.out.println("Starting class: " + pokemon + " " + perk);
        gameModel.addStarterData(pokemon, perk);
    }

    public void continueGame() {
        gameModel.continueGame();
    }
    
}
