package inheritamon.view.world;

import javax.swing.*;
import java.awt.*;

import inheritamon.Main;
import inheritamon.controller.GameController;
import inheritamon.model.GameModel;
import inheritamon.model.GameModel.GameState;
import inheritamon.view.combat.BattlePanel;

/**
 * @author Jeremias
 */
public class GamePanel extends JPanel {

    WorldPanel worldPanel;
    SidePanel sidePanel;
    BattlePanel battlePanel;

    public GamePanel(GameController battleController, GameModel gameModel) {

        int screenWidth = Main.SCREEN_WIDTH;
        int screenHeight = Main.SCREEN_HEIGHT;

        // Use a layout that won't stretch the panels
        setLayout(new BorderLayout());
        
        // Create a layered pane to hold the game panel and the side panel
        JLayeredPane layeredPane = new JLayeredPane();

        // Add the game panel and side panel, game panel is 4 times wider than the side panel
        sidePanel = new SidePanel(battleController);
        worldPanel = new WorldPanel(sidePanel);

        // Add the panels to the layered pane and set the side panel to be 1/4 of the width of the game panel
        layeredPane.add(worldPanel, 0);
        layeredPane.add(sidePanel, 1);
        sidePanel.setBounds(0, 0, screenWidth / 4, screenHeight);
        worldPanel.setBounds(0, 0, screenWidth, screenHeight);

        // This should be removed
        worldPanel.startGameThread();

        // Set the layers of the panels so that the side panel is on top of the game panel
        layeredPane.setLayer(worldPanel, 0);
        layeredPane.setLayer(sidePanel, 1);

        // Make the side panel invisible
        sidePanel.setVisible(false);


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
