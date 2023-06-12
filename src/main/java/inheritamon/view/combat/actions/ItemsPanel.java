package inheritamon.view.combat.actions;

import inheritamon.controller.GameController;
import inheritamon.model.BattleHandler;
import inheritamon.model.data.DataHandler;
import inheritamon.model.inventory.*;
import inheritamon.view.SoundHandler;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;

public class ItemsPanel extends JPanel {
    private final int SPRITE_SIZE = 80;
    private Inventory inventory;
    private SoundHandler soundHandler;

    public ItemsPanel(BattleHandler battleHandler, GameController battleController) {

        GridLayout gridLayout = new GridLayout(2, 3);
        setLayout(gridLayout);

        setUpListener(battleHandler, battleController);
        soundHandler = SoundHandler.getInstance();

    }

    private void setUpListener(BattleHandler battleHandler, GameController battleController) {

        DataHandler dataHandler = DataHandler.getInstance();

        battleHandler.addListener("inventory", e -> {
            inventory = (Inventory) e.getNewValue();

            // Remove all components
            removeAll();

            // Loop over the array using index
            for (int i = 0; i < inventory.getMaxSize(); i++) {

                // Skip if i is greater than the length of the array
                if (i >= inventory.getSize()) {

                    // Add an empty label
                    add(new JLabel());

                    continue;
                }

                Item item = inventory.getItem(i);

                // Get the name of the sprite
                String spriteName = item.getItemSprite();

                // Get the sprite from the data handler
                BufferedImage imageToDisplay = dataHandler.getItemSprite(spriteName);

                // Skip if i is greater than the length of the array
                if (i < inventory.getSize()) {

                    // Add the label with the sprite
                    JLabel label = new JLabel(new ImageIcon(imageToDisplay.getScaledInstance(SPRITE_SIZE, SPRITE_SIZE, Image.SCALE_DEFAULT)));
                    add(label);

                    final int selectionIndex = i;

                    label.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        soundHandler.playSound("select");
                        battleController.selectItem(selectionIndex);
                        }
                    });

                }

            }
        });

    }


}
