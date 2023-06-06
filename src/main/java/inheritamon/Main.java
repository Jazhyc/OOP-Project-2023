package inheritamon;
import inheritamon.model.*;
import inheritamon.model.data.*;
import inheritamon.model.pokemon.*;
import inheritamon.view.LanguageSelector;
import inheritamon.view.menu.*;
import inheritamon.model.inventory.*;
import inheritamon.controller.*;
import inheritamon.view.world.*;

import java.util.*;
import javax.swing.*;

import static inheritamon.GameState.LANGUAGE_SELECTION;
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

//         LanguageSelector languageSelector = new LanguageSelector();

//         while(currentState == LANGUAGE_SELECTION) {
//         }

//         System.out.println(currentLocale.getLanguage() + " " + currentLocale.getCountry());

// //        Locale locale = new Locale("en", "NL"); // set this based on mouse clicks.
//         Discourse textMessages = new Discourse(currentLocale);
//         System.out.println("test");
//         System.out.println(textMessages.getMessage("startMessage")); // replace with graphics.

//        JFrame startScreen = new JFrame("Start Screen");
        
        // Create the data object and load all the move data
        DataHandler dataHandler = DataHandler.getInstance();

        // Create a frame to display the game
        JFrame frame = new JFrame("Inheritamon");

        // Use a border layout
        frame.setLayout(new BoxLayout(frame.getContentPane(), BoxLayout.Y_AXIS));

        Pokemon groudon = new Pokemon(dataHandler.getCharacterData("Groudon"));
        Pokemon charizard = new PlayerPokemon(dataHandler.getCharacterData("Charizard"));

        BattleHandler battleHandler = new BattleHandler(charizard, groudon);

        // Create a Panel for the main menu
        BattleController battleController = new BattleController(battleHandler); // For now
        MainMenuController mainMenuController = new MainMenuController(frame, battleController, battleHandler);
        MainMenu mainMenu = new MainMenu(mainMenuController);
        frame.add(mainMenu);

        // Prevent the user from resizing the window
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(SCREEN_WIDTH, SCREEN_HEIGHT);
        frame.setVisible(true);

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