package inheritamon.controller;

import javax.swing.*;

import inheritamon.model.BattleHandler;
import inheritamon.view.combat.*;
import inheritamon.view.world.*;
import inheritamon.model.pokemon.*;
import inheritamon.model.pokemon.types.Pokemon;


public class MainMenuController {

    private JFrame mainFrame;
    private BattleController battleController;
    private BattleHandler battleHandler;
    private PlayerRoster playerRoster;
    private Pokemon enemyPokemon;

    public MainMenuController(JFrame frame, BattleController battleController, BattleHandler battleHandler, PlayerRoster playerRoster, Pokemon enemyPokemon) {
        this.mainFrame = frame;
        this.battleController = battleController;
        this.battleHandler = battleHandler;

        // This will change
        this.playerRoster = playerRoster;
        this.enemyPokemon = enemyPokemon;
    }

    public void startGame() {

        // // Implement the map here for now
        // GamePanel gamePanel = new GamePanel();
        // mainFrame.add(gamePanel);
        // mainFrame.revalidate();

        // Create a Panel for the main menu, the battle panel constructor will take more arguments
        BattlePanel battlePanel = new BattlePanel(battleController, battleHandler);
        mainFrame.add(battlePanel);
        mainFrame.revalidate();

        // // Create a thread for the battle to not interfere with the main thread
        // //? Is this the best way to do this?
        battleHandler.startBattle(playerRoster, enemyPokemon);

    }
    
}
