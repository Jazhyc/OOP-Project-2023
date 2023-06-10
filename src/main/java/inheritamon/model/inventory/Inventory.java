package inheritamon.model.inventory;
import java.util.ArrayList;

/**
 * @author Jona Janssen
 * Class that controls player inventory. Items are stored in an array
 */
public class Inventory {
    private int INITIAL_SIZE = 6;

    private int size = INITIAL_SIZE;

    ArrayList <Item> inventory = new ArrayList<Item>(size);

    public int getSize() {
        return size;
    }

    private void setSize(int newSize) {
        size = newSize;
    }

    public void addItem(Item item) {
        inventory.add(item);
    }

    public void removeItem(int index) {
        if (inventory.get(index) == null) {
            return;
        } else {
            inventory.remove(index);
        }
    }

    public void printInventory() {
        System.out.println("Inventory: ");
        for (int i = 0; i < inventory.size(); i++) {
            System.out.print("item " + i + ": " + inventory.get(i).getItemName());
            System.out.println(" - " + inventory.get(i).getItemDescription());
        }
    }

}

