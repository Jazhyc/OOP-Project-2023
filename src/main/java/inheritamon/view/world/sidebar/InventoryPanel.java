package inheritamon.view.world.sidebar;

import inheritamon.model.GameModel;
import inheritamon.model.data.DataHandler;
import inheritamon.model.inventory.Inventory;
import inheritamon.model.inventory.Item;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.image.BufferedImage;

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
                    continue;
                }

                addItemToPanel(inventory, gbc, i);

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

        DataHandler dataHandler = DataHandler.getInstance();
        String spriteName = dataHandler.getItemData(item.getItemName()).get("Sprite");
        BufferedImage itemImage = dataHandler.getItemSprite(spriteName);
        
        int spriteSize = 100;
        JLabel imageLabel = new JLabel(
                new ImageIcon(
                        itemImage.getScaledInstance(spriteSize, spriteSize,
                                Image.SCALE_DEFAULT)));
        add(imageLabel, gbc);

        // Configure the name
        gbc.gridx = 1;
        JLabel itemName = new JLabel(item.getItemName());
        itemName.setForeground(Color.WHITE);
        itemName.setHorizontalAlignment(JLabel.CENTER);
        itemName.setFont(new Font("Arial", Font.BOLD, 20));
        add(itemName, gbc);

        // Configure the description
        gbc.gridx = 2;
        JLabel itemDescription = new JLabel(item.getItemDescription());
        itemDescription.setForeground(Color.WHITE);
        itemDescription.setHorizontalAlignment(JLabel.CENTER);
        itemDescription.setFont(new Font("Arial", Font.BOLD, 20));
        add(itemDescription, gbc);

    }
}

