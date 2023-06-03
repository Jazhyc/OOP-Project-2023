package inheritamon.view.combat.display;

import inheritamon.view.combat.display.BattleDisplayPanel.DisplayType;

import javax.swing.*;
import java.awt.*;

public class PokemonPanel extends JPanel {

    // Display the pokemon differently depending on whether it is the player's or the enemy's
    public PokemonPanel(DisplayType type) {

        // Use a grid bag layout
        setLayout(new GridBagLayout());

        // Use a ternary operator to set the y position of the stats panel and sprite panel
        int statsPanelY = (type == DisplayType.PLAYER) ? 0 : 1;
        int spritePanelY = (type == DisplayType.PLAYER) ? 1 : 0;

        // Add the stats panel and sprite panel to the Pokemon panel
        addStatsPanel(statsPanelY, type);
        addSpritePanel(spritePanelY, type);

    }

    private void addStatsPanel(int yPosition, DisplayType type) {

        StatsPanel statsPanel = new StatsPanel(10, 10, 10, 10, "Charizard", type);

        // GridBagConstraints, make the stats panel smaller than the sprite panel
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = yPosition;
        gbc.gridwidth = 1;
        gbc.weighty = 0.5;
        gbc.weightx = 1;
        gbc.fill = GridBagConstraints.BOTH;
        add(statsPanel, gbc);

    }

    private void addSpritePanel(int yPosition, DisplayType type) {

        SpritePanel spritePanel = new SpritePanel("Charizard", type);

        // GridBagConstraints, make the sprite panel larger than the stats panel
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = yPosition;
        gbc.gridheight = 1;
        gbc.weighty = 2.5;
        gbc.weightx = 1;
        gbc.fill = GridBagConstraints.BOTH;

        add(spritePanel, gbc);

    }

    
}
