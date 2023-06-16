package inheritamon.model.world.tile;

import java.awt.image.BufferedImage;
import java.util.ArrayList;

import inheritamon.model.player.Player.TrainerAbility;

/**
 * @author Stanislav
 * A class to represent the tiles in the world
 */
public class Tile {

    public BufferedImage image;
    public boolean collision = false;
    public boolean canInteract = false;
    private String type;

    /**
     * Constructor for the tile
     * @param image The image of the tile
     * @param collision Whether the tile is collidable
     * @param canInteract Whether the tile can be interacted with
     * @param type The type of the tile
     */
    public Tile(BufferedImage image, boolean collision, boolean canInteract, String type) {
        this.image = image;
        this.collision = collision;
        this.canInteract = canInteract;
        this.type = type;
    }

    /**
     * Action to perform when interacting with the tile
     */
    public void interact() {
        System.out.println("Interacting with tile");
    }

    /**
     * Getter for the collision
     * @return Whether the tile is collidable
     */
    public boolean canInteract() {
        return canInteract;
    }

    /**
     * Getter for the type
     * @return The type of the tile
     */
    public String getType() {
        return type;
    }

    /**
     * Checks if the tile can be passed by the player
     * @param abilities The abilities of the player
     * @return Whether the tile can be passed
     */
    public boolean canPass(ArrayList<TrainerAbility> abilities) {
        
        // Check if the ability matches the tile type
        for (TrainerAbility ability : abilities) {
            if (ability.toString().equals(type)) {
                return true;
            }
        }

        return false;

    }

}
