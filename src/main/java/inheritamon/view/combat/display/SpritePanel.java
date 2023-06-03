package inheritamon.view.combat.display;

import inheritamon.model.BattleHandler;
import inheritamon.model.data.DataHandler;
import inheritamon.view.combat.display.BattleDisplayPanel.DisplayType;

import java.awt.image.BufferedImage;
import javax.swing.*;
import java.util.*;
import java.awt.*;

public class SpritePanel extends JPanel {

    private static final int SHADOW_SIZE = 40;
    private static final int SHADOW_DISPLACEMENT = 10;
    private BufferedImage imageToDisplay;
    private DisplayType type;
    private static final int SPRITE_SIZE = 256;
    private static final int Y_OFFSET = 25;

    // Reference to the data handler
    private HashMap<String, HashMap<String, BufferedImage>> pokemonImages = new HashMap<String, HashMap<String, BufferedImage>>();

    public SpritePanel(BattleHandler battleHandler, DisplayType type) {

        // Set the type
        this.type = type;
        pokemonImages = DataHandler.getInstance().getCharacterImages();
        // setRequiredImage(battleHandler.getCurrentPokemonName(type));
        setUp(battleHandler);

        setOpaque(false);
        
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        // Calculate the x and y coordinates to center the image
        int spriteX = (getWidth() - SPRITE_SIZE) / 2;
        int spriteY = (getHeight() - SPRITE_SIZE) / 2;

        // If the type is the player, draw the image at the bottom
        if (type == DisplayType.PLAYER) {
            spriteY = getHeight() - SPRITE_SIZE;
        }

        // Add a shadow under the image if the type is enemy
        if (type == DisplayType.ENEMY) {
            g.setColor(Color.BLACK);
            
            // Draw an oval with a gradient
            g.fillOval(spriteX, spriteY + SPRITE_SIZE - SHADOW_SIZE + SHADOW_DISPLACEMENT, SPRITE_SIZE, SHADOW_SIZE);
            g.setColor(new Color(0, 0, 0, 0));
            g.fillOval(spriteX, spriteY + SPRITE_SIZE - SHADOW_SIZE + SHADOW_DISPLACEMENT, SPRITE_SIZE, SHADOW_SIZE);

        }

        g.drawImage(imageToDisplay, spriteX, spriteY + Y_OFFSET, SPRITE_SIZE, SPRITE_SIZE, null);

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

    private void setUp(BattleHandler battleHandler) {

        battleHandler.addPokemonSpriteListener(e -> {

            // Use a ternary operator to determine what the event name should be based on the type
            String eventName = type == DisplayType.PLAYER ? "playerSprite" : "enemySprite";
            
            // Check if the event is for the right pokemon
            if (e.getPropertyName().equals(eventName)) {

                // Get the right pokemon name
                String pokemonName = battleHandler.getCurrentPokemonName(type);
                System.out.println("Pokemon name: " + pokemonName);
                setRequiredImage(pokemonName);

                // Repaint the panel
                revalidate();
                repaint();

            }

        });
    }
    
}
