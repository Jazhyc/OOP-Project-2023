package inheritamon.view.combat.display;

import inheritamon.view.combat.display.BattleDisplayPanel.DisplayType;
import inheritamon.model.BattleHandler;

import javax.swing.*;
import java.awt.*;

/**
 * @author Jeremias
 * A panel to display various details of the player's and enemy's pokemon
 */
public class PokemonPanel extends JPanel {

    // Display the pokemon differently depending on whether it is the player's or the enemy's
    /**
     * Constructor for the PokemonPanel class, consists of a StatsPanel and a SpritePanel
     * @param type Whether the panel is for the player or the enemy
     * @param battleHandler The battle handler
     */
    public PokemonPanel(DisplayType type, BattleHandler battleHandler) {

        // Use a grid bag layout
        setLayout(new GridBagLayout());
        
        // Make this panel transparent
        setOpaque(false);

        // Use a ternary operator to set the y position of the stats panel and sprite panel
        int statsPanelY = (type == DisplayType.PLAYER) ? 0 : 1;
        int spritePanelY = (type == DisplayType.PLAYER) ? 1 : 0;

        // Add the stats panel and sprite panel to the Pokemon panel
        addStatsPanel(statsPanelY, type, battleHandler);
        addSpritePanel(spritePanelY, type, battleHandler);

    }

    private void addStatsPanel(int yPosition, DisplayType type, BattleHandler battleHandler) {

        StatsPanel statsPanel = new StatsPanel(type, battleHandler);

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

    private void addSpritePanel(int yPosition, DisplayType type, BattleHandler battleHandler) {

        SpritePanel spritePanel = new SpritePanel(battleHandler, type);

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
