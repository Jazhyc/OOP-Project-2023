package inheritamon.model.tile;

import java.awt.image.BufferedImage;

// This needs to be encapsulated in a class
// No publics attributes
public class Tile {

    public BufferedImage image;
    public boolean collision = false;
    public boolean canInteract = false;
    private boolean isInteracting = false;

    public Tile(BufferedImage image, boolean collision, boolean canInteract) {
        this.image = image;
        this.collision = collision;
        this.canInteract = canInteract;
    }

    // This should be overridden
    public void interact() {
        System.out.println("Interacting with tile");
    }

    public boolean canInteract() {
        return canInteract && !isInteracting;
    }

    public void setIsInteracting(boolean isInteracting) {
        this.isInteracting = isInteracting;
    }
}
