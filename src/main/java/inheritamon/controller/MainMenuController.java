package inheritamon.controller;

import javax.swing.*;

import inheritamon.model.BattleHandler;
import inheritamon.model.data.DataHandler;
import inheritamon.model.pokemon.PlayerPokemon;
import inheritamon.model.pokemon.Pokemon;
import inheritamon.view.combat.*;


public class MainMenuController {

    private JFrame mainFrame;
    private BattleController battleController;
    private BattleHandler battleHandler;

    public MainMenuController(JFrame frame, BattleController battleController, BattleHandler battleHandler) {
        this.mainFrame = frame;
        this.battleController = battleController;
        this.battleHandler = battleHandler;
    }

    public void startGame() {

        // Create a Panel for the main menu, the battle panel constructor will take more arguments
        BattlePanel battlePanel = new BattlePanel(battleController, battleHandler);
        mainFrame.add(battlePanel);
        mainFrame.revalidate();

        // Create a thread for the battle to not interfere with the main thread
        //? Is this the best way to do this?
        new Thread(() -> {
            battleHandler.startBattle();
        }).start();

    }
    
}
