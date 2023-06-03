package inheritamon.view.combat.actions;

import javax.swing.*;
import java.awt.*;

public class PokemonSelectionPanel extends JPanel {

    public PokemonSelectionPanel() {

        // Use a grid layout
        setLayout(new GridLayout(1, 1));
        
        // Add a button that occupies the entire panel
        JButton button = new JButton("Pokemon Selection");

        // If the button is pressed, print a message
        button.addActionListener(e -> System.out.println("Pokemon Selection"));

        add(button);

    }
    
}
