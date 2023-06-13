package inheritamon.view.menu;

import javax.swing.*;
import java.awt.*;
import java.awt.image.*;

import inheritamon.model.data.DataHandler;
import inheritamon.model.data.language.*;

public class ClassDisplayPanel extends JPanel implements LanguageChangeListener {

    private final int FONT_SIZE = 20;

    private final int SPRITE_SIZE = 150;

    private JLabel[] labels = new JLabel[3];

    private String perk;

    public ClassDisplayPanel(String pokemon, String perk) {

        this.perk = perk;

        setLayout(new GridLayout(4, 1));

        LanguageConfiguration config = LanguageConfiguration.getInstance();

        DataHandler dataHandler = DataHandler.getInstance();
        addLabels(pokemon, perk, config, dataHandler);

        // Set the font to use
        Font font = new Font("Arial", Font.BOLD, FONT_SIZE);

        // Center the labels
        for (JLabel label : labels) {
            label.setHorizontalAlignment(JLabel.CENTER);
            label.setFont(font);
            label.setAlignmentX(JLabel.CENTER_ALIGNMENT);
        }

        addLanguageListener();

    }

    private void addLabels(String pokemon, String perk, LanguageConfiguration config,
            DataHandler dataHandler) {
        BufferedImage pokemonImage = dataHandler.getPokemonSprite(pokemon).get("front");

        // Create the image label
        JLabel imageLabel = new JLabel(
                new ImageIcon(pokemonImage.getScaledInstance(SPRITE_SIZE, SPRITE_SIZE, Image.SCALE_DEFAULT)));

        // Center the image
        imageLabel.setAlignmentX(JLabel.CENTER_ALIGNMENT);

        // Create the labels with the font

        JLabel pokemonLabel = new JLabel(pokemon);
        JLabel perkLabel = new JLabel(config.getText(perk));
        JLabel perkDescription = new JLabel(config.getText(perk + "Description"));

        // Add the labels to the array
        labels[0] = pokemonLabel;
        labels[1] = perkLabel;
        labels[2] = perkDescription;

        add(imageLabel);
        add(pokemonLabel);
        add(perkLabel);
        add(perkDescription);
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
