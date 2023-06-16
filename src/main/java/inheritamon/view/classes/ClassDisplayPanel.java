package inheritamon.view.classes;

import javax.swing.*;
import java.awt.*;
import java.awt.image.*;

import inheritamon.model.data.DataHandler;
import inheritamon.model.data.language.*;

/**
 * @author Bernard
 * Panel that displays the details of a class
 */
public class ClassDisplayPanel extends JPanel
        implements LanguageChangeListener {

    /**
     * The labels for the class display
     */
    private final JLabel[] labels = new JLabel[3];

    /**
     * The perk of the class
     */
    private final String perk;

    /**
     * Constructor for the class display panel
     * @param pokemon The pokemon of the class
     * @param perk The perk of the class
     */
    public ClassDisplayPanel(String pokemon, String perk) {

        this.perk = perk;

        setLayout(new GridLayout(4, 1));

        LanguageConfiguration config = LanguageConfiguration.getInstance();

        DataHandler dataHandler = DataHandler.getInstance();
        addLabels(pokemon, perk, config, dataHandler);

        // Set the font to use
        int fontSize = 20;
        Font font = new Font("Arial", Font.BOLD, fontSize);

        // Center the labels
        for (JLabel label : labels) {
            label.setHorizontalAlignment(JLabel.CENTER);
            label.setFont(font);
            label.setAlignmentX(JLabel.CENTER_ALIGNMENT);
        }

        addLanguageListener();

    }

    private void addLabels(String pokemon, String perk,
                           LanguageConfiguration config,
                           DataHandler dataHandler) {
        BufferedImage pokemonImage =
                dataHandler.getPokemonSprite(pokemon).get("front");

        // Create the image label
        int spriteSize = 150;
        JLabel imageLabel = new JLabel(
                new ImageIcon(
                        pokemonImage.getScaledInstance(spriteSize, spriteSize,
                                Image.SCALE_DEFAULT)));

        // Center the image
        imageLabel.setAlignmentX(JLabel.CENTER_ALIGNMENT);

        // Create the labels with the font

        JLabel pokemonLabel = new JLabel(pokemon);
        JLabel perkLabel = new JLabel(config.getText(perk));
        JLabel perkDescription =
                new JLabel(config.getText(perk + "Description"));

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
