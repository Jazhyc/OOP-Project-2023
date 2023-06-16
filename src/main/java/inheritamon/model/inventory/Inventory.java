package inheritamon.model.inventory;

import java.util.ArrayList;
import java.io.Serializable;

import inheritamon.model.data.DataHandler;

/**
 * @author Jona Janssen
 * Class that controls player inventory. Items are stored in an array
 */
public class Inventory implements Serializable {
    private int INITIAL_SIZE = 6;

    private int size = INITIAL_SIZE;

    ArrayList <Item> inventory = new ArrayList<Item>();
    private int coins = 0;

    public Inventory() {

        DataHandler dataHandler = DataHandler.getInstance();
        // Starting items
        Item potion = new Item(dataHandler.getItemData("Potion"));
        Item inheritaball = new Item(dataHandler.getItemData("Inheritaball"));
        Item inheritaball2 = new Item(dataHandler.getItemData("Inheritaball"));
        addItem(potion);
        addItem(inheritaball);
        addItem(inheritaball2);

    }

    /**
     * @return current inventory size
     */
    public int getSize() {
        
        // Return the number of items in the arraylist
        return inventory.size();

    }

    /**
     * @return maximum inventory size
     */
    public int getMaxSize() {
        return size;
    }

    /**
     * Adds item (see item class) to the inventory
     * @param item
     */
    public void addItem(Item item) {
        inventory.add(item);
    }

    /**
     * Removes item at an index from the inventory
     * @param index
     */
    public void removeItem(int index) {
        if (inventory.get(index) == null) {
            return;
        } else {
            inventory.remove(index);
        }
    }

    /**
     * @return the item at a certain index in the inventory
     * @param index
     */
    public Item getItem(int index) {
        return inventory.get(index);
    }

    /**
     * Print the inventory in text (for debugging)
     */
    public void printInventory() {
        System.out.println("Inventory: ");
        for (int i = 0; i < inventory.size(); i++) {
            System.out.print("item " + i + ": " + inventory.get(i).getItemName());
            System.out.println(" - " + inventory.get(i).getItemDescription());
        }
    }

    /**
     * Adds an amount of coins to the inventory
     * @param amount
     */
    public void addCoins(int amount) {
        coins += amount;
    }

    /**
     * Removes an amount of coins from the inventory
     * @param amount
     */
    public void removeCoins(int amount) {
        
        // Only remove coins if the player has enough coins
        if (coins >= amount) {
            coins -= amount;
        }

    }

    /**
     * @return the amount of coins currently in the inventory
     */
    public int getCoins() {
        return coins;
    }

}

