package inheritamon.view.combat.display;

import inheritamon.model.data.DataHandler;
import inheritamon.view.combat.display.BattleDisplayPanel.DisplayType;

import java.awt.image.BufferedImage;
import javax.swing.*;
import java.util.*;
import java.awt.*;

public class SpritePanel extends JPanel {

    private volatile BufferedImage imageToDisplay;
    private DisplayType type;
    private int spriteSize = 256;
    private int yOffset = 25;

    // Reference to the data handler
    private HashMap<String, HashMap<String, BufferedImage>> pokemonImages = new HashMap<String, HashMap<String, BufferedImage>>();

    public SpritePanel(String pokemon, DisplayType type) {

        // Set the type
        this.type = type;
        pokemonImages = DataHandler.getInstance().getCharacterImages();
        setRequiredImage(pokemon);
        
        
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        // Calculate the x and y coordinates to center the image
        int spriteX = (getWidth() - spriteSize) / 2;
        int spriteY = (getHeight() - spriteSize) / 2;

        g.drawImage(imageToDisplay, spriteX, spriteY + yOffset, spriteSize, spriteSize, null);
    }

    // Useful for the player since they can switch pokemon
    public void setRequiredImage(String pokemon) {

        // If the type is enemy, simply get the front image.
        // Otherwise get the back image.
        if (type == DisplayType.ENEMY) {
            imageToDisplay = pokemonImages.get(pokemon).get("front");
        } else {
            imageToDisplay = pokemonImages.get(pokemon).get("back");
        }

        revalidate();
        repaint();
    }
    
}
