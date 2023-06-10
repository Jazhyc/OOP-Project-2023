package inheritamon;
import inheritamon.model.*;
import inheritamon.model.data.*;
import inheritamon.view.combat.*;
import inheritamon.view.menu.*;
import inheritamon.model.inventory.*;
import inheritamon.controller.*;
import inheritamon.view.world.*;
import inheritamon.view.SoundHandler;

import javax.swing.*;

public class Main {

    public static final int SCREEN_WIDTH = 1366;
    public static final int SCREEN_HEIGHT = 768;
    public static void main(String[] args) {
        
        // Create the data object and load all the move data
        DataHandler dataHandler = DataHandler.getInstance();

        SoundHandler soundHandler = SoundHandler.getInstance();
        soundHandler.playMusic("StartMenu");

        // Create a frame to display the game
        JFrame frame = new JFrame("Inheritamon");

        // Prevent the user from resizing the window
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(SCREEN_WIDTH, SCREEN_HEIGHT);
        frame.setVisible(true);


        // Use a border layout
        frame.setLayout(new BoxLayout(frame.getContentPane(), BoxLayout.Y_AXIS));

        BattleHandler battleHandler = new BattleHandler();
        GameModel gameModel = new GameModel(battleHandler);
        BattleController battleController = new BattleController(battleHandler, gameModel);

        // Create the game panel
        GamePanel gamePanel = new GamePanel(battleController);
        gamePanel.setVisible(false);
        frame.add(gamePanel);

        MainMenuController mainMenuController = new MainMenuController();
        MainMenu mainMenu = new MainMenu(mainMenuController, gamePanel);
        frame.add(mainMenu);

        // Create a Panel for the combat screen
        BattlePanel battlePanel = new BattlePanel(battleController, battleHandler, gamePanel);
        frame.add(battlePanel);
        battlePanel.setVisible(false);

        // Set the battle panel since it was initialized after the game panel
        gamePanel.setBattlePanel(battlePanel);

    }
}