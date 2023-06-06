package inheritamon.controller;

import inheritamon.model.data.WorldCoordinates;
import inheritamon.model.inventory.Inventory;

// Commented out for now
public abstract class PokemonTrainer {
    private String username;
    private TrainerRegion region;
    private TrainerAbility ability;
    private Inventory inventory;
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
