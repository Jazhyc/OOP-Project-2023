package inheritamon.view.menu;

import inheritamon.controller.MenuController;
import inheritamon.model.GameModel;
import inheritamon.model.GameModel.GameState;
import inheritamon.view.SoundHandler;

import javax.swing.*;
import java.awt.event.*;
import java.awt.*;

/**
 * Class that splits into 3 panels for the player to choose their starter
 */
public class ClassSelectionPanel extends JPanel {

    /**
     * The number of panels, one for each class
     */
    private final int NUMBER_OF_PANELS = 3;

    /**
     * The starting pokemon for each class
     */
    private final String[] startingPokemon = {"Charizard", "Blastoise", "Sceptile"};

    /**
     * The starting perks for each class
     */
    private final String[] startingPerks = {"Climber", "Swimmer", "Rich"};

    /**
     * The panels for each class
     */
    private final JPanel[] classPanels = new JPanel[NUMBER_OF_PANELS];

    /**
     * Constructor for the class selection panel
     *
     * @param gameModel  The game model
     * @param controller The menu controller
     */
    public ClassSelectionPanel(GameModel gameModel, MenuController controller) {

        setUpListener(gameModel);
        SoundHandler soundHandler = SoundHandler.getInstance();

        // Use a 1 x 3 grid layout
        setLayout(new GridLayout(1, 3));

        for (int i = 0; i < NUMBER_OF_PANELS; i++) {
            addPanel(controller, soundHandler, i);
        }

    }

    private void addPanel(MenuController controller, SoundHandler soundHandler,
                          int i) {
        classPanels[i] =
                new ClassDisplayPanel(startingPokemon[i], startingPerks[i]);
        add(classPanels[i]);

        final int selectionIndex = i;

        // Add a mouse adapter to each panel
        classPanels[i].addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent evt) {
                controller.handleStartingClass(startingPokemon[selectionIndex],
                        startingPerks[selectionIndex]);
                soundHandler.playSound("select");
                setVisible(false);
            }

            // Create a border around the panel when the mouse hovers over it
            @Override
            public void mouseEntered(MouseEvent evt) {
                classPanels[selectionIndex].setBorder(
                        BorderFactory.createLineBorder(Color.BLACK));
            }

            // Remove the border when the mouse leaves the panel
            @Override
            public void mouseExited(MouseEvent evt) {
                classPanels[selectionIndex].setBorder(
                        BorderFactory.createEmptyBorder());
            }
        });
    }

    private void setUpListener(GameModel gameModel) {

        gameModel.addGameStateListener(e -> {

            if (e.getNewValue() == GameState.SELECT_STARTER) {
                setVisible(true);
            }

        });

    }

}
