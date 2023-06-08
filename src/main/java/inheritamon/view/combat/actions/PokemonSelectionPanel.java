package inheritamon.view.combat.actions;

import inheritamon.controller.*;
import inheritamon.model.*;
import inheritamon.model.data.DataHandler;
import inheritamon.model.pokemon.*;


import javax.swing.*;
import java.awt.*;
import java.awt.image.*;
import java.awt.event.*;

public class PokemonSelectionPanel extends JPanel {

    private Pokemon[] playerPokemon;

    public PokemonSelectionPanel(BattleHandler battleHandler, BattleController battleController) {

        // Use a grid layout
        setLayout(new GridLayout(1, 6));

        setUpListener(battleHandler);
        
    }

    private void setUpListener(BattleHandler battleHandler) {

        DataHandler dataHandler = DataHandler.getInstance();

        battleHandler.addPlayerRosterListener(e -> {
            playerPokemon = (Pokemon[]) e.getNewValue();

            // Remove all components
            removeAll();

            // Loop over the array using index
            for (int i = 0; i < playerPokemon.length; i++) {

                BufferedImage imageToDisplay = dataHandler.getPokemonSprite(playerPokemon[i].getName()).get("front");

                // Create a label with the image and increase the size
                JLabel label = new JLabel(new ImageIcon(imageToDisplay.getScaledInstance(200, 200, Image.SCALE_DEFAULT)));
                final int selectionIndex = i;

                // Add a mouse adapter to the label
                label.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent e) {

                        // Swap the pokemon
                        battleHandler.changeActivePokemon(selectionIndex);

                    }
                });

                // Add the button to the panel
                add(label);

            }
        });

    }

    
}