package inheritamon.view.world;

import javax.swing.*;
import java.awt.*;

import inheritamon.Main;
import inheritamon.controller.GameController;
import inheritamon.model.GameModel;
import inheritamon.model.GameModel.GameState;
import inheritamon.view.combat.BattlePanel;
import inheritamon.view.world.sidebar.*;

/**
 * @author Jeremias
 */
public class GamePanel extends JPanel {

    WorldPanel worldPanel;
    SidePanel sidePanel;
    BattlePanel battlePanel;
    PokemonDataPanel pokemonDataPanel;

    public GamePanel(GameController battleController, GameModel gameModel) {

        int screenWidth = Main.SCREEN_WIDTH;
        int screenHeight = Main.SCREEN_HEIGHT;

        // Use a layout that won't stretch the panels
        setLayout(new BorderLayout());
        
        // Create a layered pane to hold the game panel and the side panel
        JLayeredPane layeredPane = new JLayeredPane();

        // Add the panels to the layered pane
        pokemonDataPanel = new PokemonDataPanel(gameModel);
        sidePanel = new SidePanel(battleController, pokemonDataPanel);
        worldPanel = new WorldPanel(sidePanel);

        // Add the panels to the layered pane and set the side panel to be 1/4 of the width of the game panel
        layeredPane.add(worldPanel, 0);
        layeredPane.add(sidePanel, 1);
        layeredPane.add(pokemonDataPanel, 1);

        sidePanel.setBounds(0, 0, screenWidth / 4, screenHeight);
        pokemonDataPanel.setBounds(screenWidth / 4, 0, screenWidth / 2, screenHeight);

        worldPanel.setBounds(0, 0, screenWidth, screenHeight);

        // This should be removed
        worldPanel.startGameThread();

        // Set the layers of the panels so that the side panel is on top of the game panel
        layeredPane.setLayer(worldPanel, 0);
        layeredPane.setLayer(sidePanel, 1);
        layeredPane.setLayer(pokemonDataPanel, 1);

        // Make the side panel invisible
        sidePanel.setVisible(false);
        pokemonDataPanel.setVisible(false);


        // Add the layered pane to the game panel
        add(layeredPane);

        setUpListener(gameModel);

    }

    private void setUpListener(GameModel gameModel) {

        gameModel.addGameStateListener(e -> {
        
            if (e.getNewValue() == GameState.GAME_START) {
                setVisible(true);
            } else {
                setVisible(false);
            }

        });

    }
    
}
