package inheritamon.view.menu;

import javax.swing.*;

public class ClassDisplayPanel extends JPanel {

    public ClassDisplayPanel(String pokemon, String perk) {

        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        JLabel pokemonLabel = new JLabel(pokemon);
        JLabel perkLabel = new JLabel(perk);

        // Put the labels in the center of the panel
        pokemonLabel.setAlignmentX(JLabel.CENTER_ALIGNMENT);
        perkLabel.setAlignmentX(JLabel.CENTER_ALIGNMENT);

        // Make the labels occupy the entire width of the panel
        pokemonLabel.setMaximumSize(pokemonLabel.getPreferredSize());
        perkLabel.setMaximumSize(perkLabel.getPreferredSize());

        add(pokemonLabel);
        add(perkLabel);

    }
    
}
