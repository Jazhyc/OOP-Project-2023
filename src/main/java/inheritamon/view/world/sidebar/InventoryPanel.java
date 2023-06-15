package inheritamon.view.world.sidebar;
import inheritamon.controller.GameController;
import inheritamon.model.BattleHandler;
import inheritamon.model.GameModel;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import inheritamon.model.GameModel;
import inheritamon.model.data.DataHandler;
import inheritamon.model.inventory.Inventory;
import inheritamon.model.inventory.Item;
import inheritamon.model.npcs.types.Pokemon;

import java.awt.image.*;

/**
 * @author Jona Janssen
 * Panel that displays the current inventory
 */
public class InventoryPanel extends JPanel {

    private Inventory inventory;

    /**
     * Constructor for the InventoryPanel
     *
     * @param gameModel The game model
     */
    public InventoryPanel(GameModel gameModel) {

        // Make the background black
        setBackground(Color.BLACK);
        setUpListener(gameModel);

        // Use a grid bag layout
        setLayout(new GridBagLayout());
    }

    /**
     * Sets up listeners, and builds the panel by calling addItemToPanel on all inventory items
     * @param gameModel
     */
    private void setUpListener(GameModel gameModel) {

        DataHandler dataHandler = DataHandler.getInstance();

        gameModel.addItemListener(e -> {
            inventory = (Inventory) e.getNewValue();

            // Remove all components
            removeAll();
            
            // Use a gbc to position the components
            // Images are on the left, names on the right
            GridBagConstraints gbc = new GridBagConstraints();
            gbc.weightx = 0.5;
            gbc.anchor = GridBagConstraints.LINE_START;
            gbc.fill = GridBagConstraints.BOTH;

            // Loop over the array using index
            for (int i = 0; i < inventory.getMaxSize(); i++) {

                // Skip if i is greater than the length of the array
                if (i >= inventory.getSize()) {

                    addItemToPanel(inventory, gbc, i);
                    
                    continue;
                }



            }
        });

    }

    /**
     * Adds an individual item to the inventory panel
     * @param inventory
     * @param gbc
     * @param i
     */
    private void addItemToPanel(Inventory inventory, GridBagConstraints gbc, int i) {
        Item item = inventory.getItem(i);

        System.out.println(item.getItemName());

        gbc.gridy = i + 1;

        gbc.gridx = 0;
        
        int spriteSize = 100;
//        JLabel imageLabel = new JLabel(
//                new ImageIcon(
//                        itemImage.getScaledInstance(spriteSize, spriteSize,
//                                Image.SCALE_DEFAULT)));
//        add(imageLabel, gbc);

        // Configure the name
        gbc.gridx = 1;
        JLabel itemName = new JLabel(item.getItemName());
        itemName.setForeground(Color.WHITE);
        itemName.setHorizontalAlignment(JLabel.CENTER);
        itemName.setFont(new Font("Arial", Font.BOLD, 20));
        add(itemName, gbc);

    }
}

