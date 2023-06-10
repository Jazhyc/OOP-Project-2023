package inheritamon.controller;

import inheritamon.model.data.WorldCoordinates;
import inheritamon.model.inventory.Inventory;

/**
 * @author Bernardus Brouwer
 * Class used to control the player character in the game.
 */

// Commented out for now
public abstract class PokemonTrainer {
    private String username;
    private TrainerRegion region;
    private TrainerAbility ability;
    private WorldCoordinates saveLocation;
    public PokemonTrainer(String username, TrainerRegion region) {
        this.username = username;
        this.region = region;
    }
    public String getUsername() {
        return username;
    }
    public TrainerRegion getRegion() {
        return region;
    }
    public TrainerAbility getTrainerAbility() {
        return ability;
    }
    public void setTrainerAbility() {}
    public void setSaveLocation(WorldCoordinates saveLocation) {
        this.saveLocation = saveLocation;
    }

}
