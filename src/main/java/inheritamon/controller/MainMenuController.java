package inheritamon.controller;

import inheritamon.model.GameModel;
import inheritamon.view.menu.RegionSelectionWindow;

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
        RegionSelectionWindow regionSelectionWindow = new RegionSelectionWindow(gameModel);
        gameModel.startNewGame();
    }

    public void continueGame() {
        
    }
    
}
