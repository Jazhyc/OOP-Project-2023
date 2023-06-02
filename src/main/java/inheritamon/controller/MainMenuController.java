package inheritamon.controller;

import java.util.HashMap;

import javax.swing.*;

import inheritamon.model.BattleHandler;
import inheritamon.view.combat.*;
import java.awt.image.*;


public class MainMenuController {

    private JFrame mainFrame;

    public MainMenuController(JFrame frame) {
        this.mainFrame = frame;
    }

    public void startGame() {
        // Create a Panel for the main menu, the battle panel constructor will take more arguments
        BattlePanel battlePanel = new BattlePanel();
        mainFrame.add(battlePanel);
        mainFrame.revalidate();
    }
    
}
