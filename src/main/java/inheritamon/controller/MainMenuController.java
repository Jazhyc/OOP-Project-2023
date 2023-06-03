package inheritamon.controller;

import javax.swing.*;

import inheritamon.view.combat.*;


public class MainMenuController {

    private JFrame mainFrame;
    private BattleController battleController;

    public MainMenuController(JFrame frame, BattleController battleController) {
        this.mainFrame = frame;
        this.battleController = battleController;
    }

    public void startGame() {
        // Create a Panel for the main menu, the battle panel constructor will take more arguments
        BattlePanel battlePanel = new BattlePanel(battleController);
        mainFrame.add(battlePanel);
        mainFrame.revalidate();
    }
    
}
