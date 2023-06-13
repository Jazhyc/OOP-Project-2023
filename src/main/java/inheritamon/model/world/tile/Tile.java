package inheritamon.model.world.tile;

import java.awt.image.BufferedImage;
import java.util.ArrayList;

import inheritamon.model.PlayerData.TrainerAbility;

// This needs to be encapsulated in a class
// No publics attributes
public class Tile {

    public BufferedImage image;
    public boolean collision = false;
    public boolean canInteract = false;
    private String type;

    public Tile(BufferedImage image, boolean collision, boolean canInteract, String type) {
        this.image = image;
        this.collision = collision;
        this.canInteract = canInteract;
        this.type = type;
    }

    // This should be overridden
    public void interact() {
        System.out.println("Interacting with tile");
    }

    public boolean canInteract() {
        return canInteract;
    }

    public String getType() {
        return type;
    }

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
