package inheritamon.model.inventory;

/**
 * Class that holds all the pokemon and items of the player
 */
public class PlayerData {

    private PlayerRoster roster;
    private Inventory inventory;

    public PlayerData() {
        roster = new PlayerRoster();
        inventory = new Inventory();
    }

    public PlayerRoster getRoster() {
        return roster;
    }

    public Inventory getInventory() {
        return inventory;
    }
    
}
