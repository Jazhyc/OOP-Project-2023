package inheritamon.model.inventory;

// This is not required anymore
public class Potion extends Item {
    String itemName = new String("Healing potion");
    String itemDescription = new String("Heals your pokemon for 20 hp");
    @Override
    public void execute() {

    }
}
