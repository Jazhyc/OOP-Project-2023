package inheritamon.view.combat.actions;

import inheritamon.controller.*;
import inheritamon.model.*;
import inheritamon.model.data.DataHandler;
import inheritamon.model.inventory.PlayerRoster;
import inheritamon.model.pokemon.*;
import inheritamon.model.pokemon.types.*;
import inheritamon.view.SoundHandler;

import javax.swing.*;
import java.awt.*;
import java.awt.image.*;
import java.awt.event.*;
import java.awt.color.*;

public class PokemonSelectionPanel extends JPanel {

    private static final int SPRITE_SIZE = 100;
    private Pokemon[] playerPokemon;
    private SoundHandler soundHandler;

    public PokemonSelectionPanel(BattleHandler battleHandler, BattleController battleController) {

        // Use a grid layout
        // Create a variable first
        GridLayout gridLayout = new GridLayout(2, 3);
        setLayout(gridLayout);

        setUpListener(battleHandler, battleController);
        soundHandler = SoundHandler.getInstance();
        
    }

    private void setUpListener(BattleHandler battleHandler, BattleController battleController) {

        DataHandler dataHandler = DataHandler.getInstance();

        battleHandler.addPlayerRosterListener(e -> {
            playerPokemon = (PlayerPokemon[]) e.getNewValue();

            // Remove all components
            removeAll();

            // Loop over the array using index
            for (int i = 0; i < PlayerRoster.MAX_POKEMON; i++) {

                // Skip if i is greater than the length of the array
                if (i >= playerPokemon.length) {

                    // Add an empty label
                    add(new JLabel());

                    continue;
                }

                BufferedImage imageToDisplay = dataHandler.getPokemonSprite(playerPokemon[i].getName()).get("front");

                // If the pokemon fainted, make the image black and white. Use colorConvertOp
                if (playerPokemon[i].isFainted()) {

                    imageToDisplay = convertToGrayscale(imageToDisplay);

                }

                // Create a label with the image and increase the size
                JLabel label = new JLabel(new ImageIcon(imageToDisplay.getScaledInstance(SPRITE_SIZE, SPRITE_SIZE, Image.SCALE_DEFAULT)));

                if (!playerPokemon[i].isFainted()) {
                    final int selectionIndex = i;

                    // Add a mouse adapter to the label
                    label.addMouseListener(new MouseAdapter() {
                        @Override
                        public void mouseClicked(MouseEvent e) {

                            // Swap the pokemon
                            battleController.selectPokemon(selectionIndex);
                            soundHandler.playSound("select");

                        }
                    });
                }

                // Add the button to the panel
                add(label);

            }
        });

    }

    private BufferedImage convertToGrayscale(BufferedImage imageToDisplay) {
        
        // Make the image transparent
        BufferedImage newImage = new BufferedImage(imageToDisplay.getWidth(), imageToDisplay.getHeight(), BufferedImage.TYPE_INT_ARGB);

        // Create a color convert op
        ColorConvertOp colorConvertOp = new ColorConvertOp(ColorSpace.getInstance(ColorSpace.CS_GRAY), null);

        // Use the filter to convert the image
        colorConvertOp.filter(imageToDisplay, newImage);

        // Set the image to the new image
        imageToDisplay = newImage;
        return imageToDisplay;
    }

    
}