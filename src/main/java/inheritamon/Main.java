package inheritamon;
import inheritamon.controller.MainMenuController;
import inheritamon.model.*;
import inheritamon.model.data.*;
import inheritamon.model.pokemon.*;
import inheritamon.model.pokemon.moves.*;
import inheritamon.view.combat.*;
import inheritamon.view.world.*;
import inheritamon.view.menu.*;
import inheritamon.model.inventory.*;
import inheritamon.view.inventory.*;
import inheritamon.controller.*;

import java.util.*;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class Main {
    public static void main(String[] args) {
        
        // Create the data object and load all the move data
        DataHandler dataHandler = new DataHandler();
        HashMap<String, NormalAbility> moveData = dataHandler.getAllAbilities();
        HashMap<String, HashMap<String, BufferedImage>> characterImages = dataHandler.getCharacterImages();


        // Create a frame to display the game
        JFrame frame = new JFrame("Inheritamon");

        // Use a border layout
        frame.setLayout(new BoxLayout(frame.getContentPane(), BoxLayout.Y_AXIS));

        // Create a Panel for the main menu
        MainMenuController mainMenuController = new MainMenuController(frame);
        MainMenu mainMenu = new MainMenu(mainMenuController);
        frame.add(mainMenu);


        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1024, 720);
        frame.setVisible(true);

        Pokemon groudon = new Pokemon(dataHandler.getCharacterData("Groudon"));
        Pokemon charizard = new Pokemon(dataHandler.getCharacterData("Charizard"));

        BattleHandler battleHandler = new BattleHandler(charizard, groudon, moveData);
        // battleHandler.startBattle();

        Item potion = new Item(dataHandler.getItemData("Potion"));
        Item inheritaball = new Item(dataHandler.getItemData("Inheritaball"));
        Inventory inventory = new Inventory();
        inventory.addItem(potion);
        inventory.addItem(inheritaball);
        inventory.printInventory();

    }
}