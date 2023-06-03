package inheritamon.view.combat.display;
import javax.swing.*;

import inheritamon.model.BattleHandler;

import java.awt.*;

public class BattleDisplayPanel extends JPanel {

    // Display the pokemon differently depending on whether it is the player's or the enemy's
    public static enum DisplayType {
        PLAYER, ENEMY
    }

    public BattleDisplayPanel(BattleHandler battleHandler) {
        
        // Use a 2x1 grid layout
        setLayout(new GridLayout(1, 2));

        // Two pokemon display panels
        PokemonPanel playerPanel = new PokemonPanel(DisplayType.PLAYER, battleHandler);
        PokemonPanel enemyPanel = new PokemonPanel(DisplayType.ENEMY, battleHandler);

        // Add the panels to the display panel
        add(playerPanel);
        add(enemyPanel);

        // Add a border to the display panel with some thickness
        setBorder(BorderFactory.createLineBorder(Color.BLACK, 4));

    }
    
    
}
