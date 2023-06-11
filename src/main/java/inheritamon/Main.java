package inheritamon;
import inheritamon.model.*;
import inheritamon.view.combat.*;
import inheritamon.view.menu.*;
import inheritamon.controller.*;
import inheritamon.view.world.*;
import inheritamon.view.SoundHandler;

import javax.swing.*;

public class Main {

    public static final int SCREEN_WIDTH = 1366;
    public static final int SCREEN_HEIGHT = 768;
    public static void main(String[] args) {

        SoundHandler soundHandler = SoundHandler.getInstance();
        soundHandler.playMusic("StartMenu");

        // Create a frame to display the game
        JFrame frame = new JFrame("Inheritamon");

        // Use a border layout
        frame.setLayout(new BoxLayout(frame.getContentPane(), BoxLayout.Y_AXIS));

        BattleHandler battleHandler = new BattleHandler();
        GameModel gameModel = new GameModel(battleHandler);
        BattleController battleController = new BattleController(battleHandler, gameModel);

        // Create the game panel
        GamePanel gamePanel = new GamePanel(battleController, gameModel);
        gamePanel.setVisible(false);
        frame.add(gamePanel);

        MenuController menuController = new MenuController(gameModel);
        MainMenuPanel mainMenu = new MainMenuPanel(menuController);
        frame.add(mainMenu);

        ClassSelectionPanel classSelectionPanel = new ClassSelectionPanel(gameModel, menuController);
        frame.add(classSelectionPanel);
        classSelectionPanel.setVisible(false);

        // Create a Panel for the combat screen
        BattlePanel battlePanel = new BattlePanel(battleController, battleHandler, gamePanel);
        frame.add(battlePanel);
        battlePanel.setVisible(false);

        // Prevent the user from resizing the window
        // Panel shows up after everything is loaded
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(SCREEN_WIDTH, SCREEN_HEIGHT);
        frame.setVisible(true);

    }
}