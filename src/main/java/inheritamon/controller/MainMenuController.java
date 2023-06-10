package inheritamon.controller;

import inheritamon.model.GameModel;

/**
 * @author Jeremias
 * Controller for the main menu
 */
public class MainMenuController {

    private GameModel gameModel;

    public MainMenuController(GameModel gameModel) {
        this.gameModel = gameModel;
    }

    public void startGame() {
        gameModel.startNewGame();
    }

    public void continueGame() {
        
    }
    
}
