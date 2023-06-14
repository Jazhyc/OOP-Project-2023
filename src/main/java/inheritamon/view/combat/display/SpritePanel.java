package inheritamon.view.combat.display;

import inheritamon.model.BattleHandler;
import inheritamon.model.data.DataHandler;
import inheritamon.view.combat.display.BattleDisplayPanel.DisplayType;

import java.awt.image.BufferedImage;
import javax.swing.*;
import java.util.*;
import java.awt.*;

/**
 * @author Jeremias
 * The SpritePanel class is responsible for displaying the sprites of
 * the pokemon during battle
 */
public class SpritePanel extends JPanel {

    private BufferedImage imageToDisplay;

    /**
     * The DisplayType enum is used to determine whether the pokemon is the player's.
     */
    private final DisplayType type;

    /**
     * The pokemonImages HashMap contains all the pokemon sprites
     */
    private final HashMap<String, HashMap<String, BufferedImage>> pokemonImages;

    /**
     * Constructor for the SpritePanel class
     *
     * @param battleHandler The battle handler
     * @param type          Whether the panel is for the player or the enemy
     */
    public SpritePanel(BattleHandler battleHandler, DisplayType type) {

        // Set the type
        this.type = type;
        pokemonImages = DataHandler.getInstance().getAllCharacterSprites();
        // setRequiredImage(battleHandler.getCurrentPokemonName(type));
        setUp(battleHandler);

        setOpaque(false);

    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        // Calculate sprite size based on window width
        double spriteScaleFactor = 2.5;
        int spriteSize = (int) (getWidth() / spriteScaleFactor);

        // Calculate the x and y coordinates to center the image
        int spriteX = (getWidth() - spriteSize) / 2;
        int spriteY = (getHeight() - spriteSize) / 2;

        // If the type is the player, draw the image at the bottom
        if (type == DisplayType.PLAYER) {
            spriteY = getHeight() - spriteSize;
        }

        // Add a shadow under the image if the type is enemy
        if (type == DisplayType.ENEMY) {
            g.setColor(Color.BLACK);

            // Draw an oval with a gradient
            int shadowSize = 40;
            double shadowPlacement = 0.85;
            g.fillOval(spriteX, (int) (spriteY + spriteSize * shadowPlacement),
                    spriteSize, shadowSize);
            g.setColor(new Color(0, 0, 0, 0));
            double shadowScale = 0.8;
            g.fillOval(spriteX, (int) (spriteY + spriteSize * shadowScale),
                    spriteSize, shadowSize);

        }

        int yOffset = 25;
        g.drawImage(imageToDisplay, spriteX, spriteY + yOffset, spriteSize,
                spriteSize, null);

    }

    private void setRequiredImage(String pokemon) {

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

        battleHandler.addListener("pokemonSprite", e -> {

            // Use a ternary operator to determine what the event name should be based on
            // the type
            String eventName =
                    type == DisplayType.PLAYER ? "playerSprite" : "enemySprite";

            // Check if the event is for the right pokemon
            if (e.getPropertyName().equals(eventName)) {

                // Get the right pokemon name
                String pokemonName = e.getNewValue().toString();
                System.out.println("Pokemon name: " + pokemonName);
                setRequiredImage(pokemonName);

                // Repaint the panel
                revalidate();
                repaint();

            }

        });
    }

}
