package inheritamon.view.combat;

import javax.swing.*;
import java.awt.*;

public class InventoryPanel extends JPanel {
    private JTextArea textArea;

    public InventoryPanel() {
        setLayout(new BorderLayout());
        textArea = new JTextArea();
        textArea.setEditable(false);
        textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true);
        JScrollPane scrollPane = new JScrollPane(textArea);
        scrollPane.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        add(scrollPane, BorderLayout.EAST);
    }
    public void setTextToDisplay(String text) {
        textArea.setText(text);
    }
}
