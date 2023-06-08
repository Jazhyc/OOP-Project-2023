package inheritamon;
import inheritamon.model.*;
import inheritamon.model.data.*;
import inheritamon.model.pokemon.*;
import inheritamon.model.pokemon.types.*;
import inheritamon.view.combat.BattlePanel;
import inheritamon.view.menu.*;
import inheritamon.model.inventory.*;
import inheritamon.controller.*;
import inheritamon.view.world.*;

import java.util.*;
import javax.swing.*;

import static inheritamon.controller.TrainerRegion.KANTO;

public class Main {
    private static Locale currentLocale;
    private static volatile GameState currentState;
    public static void setLocale(Locale locale) {
        currentLocale = locale;
    }
    public static void setState(GameState state) {
        currentState = state;
    }
    public GameState getState() {
       return currentState;
    }

    public static final int SCREEN_WIDTH = 1366;
    public static final int SCREEN_HEIGHT = 768;
    public static void main(String[] args) {
        
        // Create the data object and load all the move data
        DataHandler dataHandler = DataHandler.getInstance();

        // Create a frame to display the game
        JFrame frame = new JFrame("Inheritamon");
        
        // Prevent the user from resizing the window
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(SCREEN_WIDTH, SCREEN_HEIGHT);
        frame.setVisible(true);


        // Use a border layout
        frame.setLayout(new BoxLayout(frame.getContentPane(), BoxLayout.Y_AXIS));

        PlayerPokemon blastoise = new PlayerPokemon(dataHandler.getCharacterData("Blastoise"));
        PlayerPokemon charizard = new PlayerPokemon(dataHandler.getCharacterData("Charizard"));

        // Make an array of pokemon called playerpokemons
        PlayerRoster playerRoster = new PlayerRoster();
        playerRoster.addPokemon(blastoise);
        playerRoster.addPokemon(charizard);


        BattleHandler battleHandler = new BattleHandler();

        // Create a Panel for the main menu
        BattleController battleController = new BattleController(battleHandler);

        // Create the game panel
        GamePanel gamePanel = new GamePanel();
        gamePanel.setVisible(false);
        frame.add(gamePanel);

        MainMenuController mainMenuController = new MainMenuController();
        MainMenu mainMenu = new MainMenu(mainMenuController, gamePanel);
        frame.add(mainMenu);

        // Create a Panel for the combat screen
        BattlePanel battlePanel = new BattlePanel(battleController, battleHandler);
        frame.add(battlePanel);
        battlePanel.setVisible(false);

        Item potion = new Item(dataHandler.getItemData("Potion"));
        Item inheritaball = new Item(dataHandler.getItemData("Inheritaball"));
        Inventory inventory = new Inventory();
        inventory.addItem(potion);
        inventory.addItem(inheritaball);
        inventory.printInventory();

        String username = "Ash";
        TrainerRegion region = KANTO;
//        TrainerAbility ability = SWIMMING;
        WorldCoordinates currentLocation = new WorldCoordinates(0,0);

        // Comment for now
        // PokemonTrainer user = new PokemonTrainer(username, region) {
        //     @Override
        //     public void savePlayerState() {
        //         setSaveLocation(currentLocation);
        //     }
        // };
    }
}