package inheritamon.view.inventory;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import inheritamon.model.inventory.Inventory;
import inheritamon.model.inventory.Item;

public class InventoryPanel extends JPanel {
    private JButton addItem(int item) {
        JButton newItem = new JButton();
        newItem.setIcon(getClass().getResource("item0.png"));
        add(newItem);
        revalidate();
        return newItem;
    }

    public InventoryPanel() {
        setLayout(new FlowLayout());
        addItem(0);
    }

    public void drawInventory() {
    }
}
