package inheritamon;
import inheritamon.model.*;
import inheritamon.model.data.*;
import inheritamon.model.pokemon.*;
import inheritamon.model.pokemon.moves.*;
import inheritamon.view.combat.*;

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
        dialoguePanel.setTextToDisplay("A wild Groudon appeared!");

        // Add dialogue panel to the bottom of the screen
        frame.add(pokemonPanel);
        frame.add(dialoguePanel);



        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1024, 720);
        frame.setVisible(true);

        Pokemon groudon = new Pokemon(dataHandler.getCharacterData("Groudon"));
        Pokemon charizard = new Pokemon(dataHandler.getCharacterData("Charizard"));

        BattleHandler battleHandler = new BattleHandler(charizard, groudon, moveData);
        battleHandler.startBattle();

    }
}