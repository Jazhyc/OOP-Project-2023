package inheritamon.view.combat.actions;

import javax.swing.*;
import java.awt.*;

public class ActionPanel extends JPanel {

    // Create an array for 3 panels to store the different actions
    private JPanel[] panels = new JPanel[3];

    public ActionPanel() {
        
        // Use a grid bag layout for overlapping panels
        setLayout(new GridBagLayout());

        // Create gbc
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weighty = 1;
        gbc.weightx = 1;
        gbc.fill = GridBagConstraints.BOTH;

        // Create the panels for the different actions
        panels[0] = new MovePanel();
        panels[1] = new PokemonSelectionPanel();
        panels[2] = new ItemsPanel();

        // Make the pokemon selection and items panel invisible
        panels[1].setVisible(false);
        panels[2].setVisible(false);

        // Add the panels to the action panel
        for (JPanel panel : panels) {
            add(panel, gbc);
        }

    }
    
}
