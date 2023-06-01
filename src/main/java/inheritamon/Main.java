package inheritamon;
import inheritamon.model.*;
import inheritamon.model.data.*;
import inheritamon.model.inventory.Potion;
import inheritamon.model.pokemon.*;
import inheritamon.model.pokemon.moves.*;
import inheritamon.view.combat.*;
import inheritamon.model.inventory.Inventory;
import inheritamon.model.inventory.Item;

import java.util.*;
import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        
        // Create the data object and load all the move data
        DataHandler dataHandler = new DataHandler();
        HashMap<String, NormalAbility> moveData = dataHandler.getAllAbilities();

        // Create a frame to display the battle
        JFrame frame = new JFrame("Inheritamon");

        // Use a border layout
        frame.setLayout(new BoxLayout(frame.getContentPane(), BoxLayout.Y_AXIS));

        // Create a panel to display the battle
        PokemonBattlePanel pokemonPanel = new PokemonBattlePanel();
        DialoguePanel dialoguePanel = new DialoguePanel();
        InventoryPanel inventoryPanel = new InventoryPanel();
        dialoguePanel.setTextToDisplay("Inventory:");
        dialoguePanel.setTextToDisplay("A wild Groudon appeared!");

        // Add dialogue panel to the bottom of the screen
        frame.add(pokemonPanel);
        frame.add(dialoguePanel);

        // Add inventory panel to the right of the screen
        frame.add(inventoryPanel);



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