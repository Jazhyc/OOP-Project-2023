package inheritamon.view.combat.actions;

import inheritamon.controller.BattleController;
import inheritamon.model.BattleHandler;
import inheritamon.model.data.DataHandler;
import inheritamon.model.inventory.Inventory;
import inheritamon.model.inventory.PlayerRoster;
import inheritamon.model.pokemon.types.PlayerPokemon;
import inheritamon.model.pokemon.types.Pokemon;
import inheritamon.view.SoundHandler;

import javax.swing.*;
import java.awt.*;
import java.awt.color.ColorSpace;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.awt.image.ColorConvertOp;

public class ItemsPanel extends JPanel {
    private static final int SPRITE_SIZE = 100;
    private Inventory inventory;
    private SoundHandler soundHandler;

    public ItemsPanel(BattleHandler battleHandler, BattleController battleController) {

        GridLayout gridLayout = new GridLayout(2, 3);
        setLayout(gridLayout);

        setUpListener(battleHandler, battleController);
        soundHandler = SoundHandler.getInstance();

    }

    private void setUpListener(BattleHandler battleHandler, BattleController battleController) {

        DataHandler dataHandler = DataHandler.getInstance();

        battleHandler.addInventoryListener(e -> {
            inventory = (Inventory) e.getNewValue();

            // Remove all components
            removeAll();

            // Loop over the array using index
            for (int i = 0; i < inventory.getSize(); i++) {

                // Skip if i is greater than the length of the array
                if (i >= inventory.getSize()) {

                    // Add an empty label
                    add(new JLabel());

                    continue;
                }

                // image to display: How?

                // Create a label with the image and increase the size
                JLabel label = new JLabel(); //needs image ;)


                final int selectionIndex = i;

                // Add a mouse adapter to the label
                label.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        // Use the item if clicked
                        // ???
                    }
                });

                // Add the button to the panel
                //add(label);
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

    
}
