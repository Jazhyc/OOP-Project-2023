package inheritamon.view.menu;

import javax.swing.*;

import inheritamon.model.data.language.*;

public class ClassDisplayPanel extends JPanel implements LanguageChangeListener {

    private JLabel[] labels = new JLabel[3];

    private String perk;

    public ClassDisplayPanel(String pokemon, String perk) {

        this.perk = perk;

        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        LanguageConfiguration config = LanguageConfiguration.getInstance();

        JLabel pokemonLabel = new JLabel(pokemon);
        JLabel perkLabel = new JLabel(config.getText(perk));
        JLabel perkDescription = new JLabel(config.getText(perk + "Description"));

        // Add the labels to the array
        labels[0] = pokemonLabel;
        labels[1] = perkLabel;
        labels[2] = perkDescription;

        // Put the labels in the center of the panel
        pokemonLabel.setAlignmentX(JLabel.CENTER_ALIGNMENT);
        perkLabel.setAlignmentX(JLabel.CENTER_ALIGNMENT);
        perkDescription.setAlignmentX(JLabel.CENTER_ALIGNMENT);

        add(pokemonLabel);
        add(perkLabel);
        add(perkDescription);

        addLanguageListener();

    }

    @Override
    public void addLanguageListener() {

        LanguageConfiguration config = LanguageConfiguration.getInstance();

        config.addLanguageChangeListener(e -> {
            // Update the labels
            labels[1].setText(config.getText(perk));
            labels[2].setText(config.getText(perk + "Description"));
        });

    }
    
}
