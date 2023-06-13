package inheritamon.view.combat.actions;

import javax.swing.*;

import inheritamon.model.BattleHandler;
import inheritamon.model.data.DataHandler;
import inheritamon.controller.GameController;

import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * @author Jeremias
 *         The panel for the different actions
 */
public class ActionPanel extends JPanel {

    private final int NUMBER_OF_PANELS = 3;

    // Create an array for 3 panels to store the different actions
    private JPanel[] panels = new JPanel[NUMBER_OF_PANELS];

    private BufferedImage background;

    /**
     * Constructor for the ActionPanel class
     * 
     * @param battleHandler    The battle handler
     * @param battleController The battle controller
     */
    public ActionPanel(BattleHandler battleHandler, GameController battleController) {

        DataHandler dataHandler = DataHandler.getInstance();
        background = dataHandler.getBackground("greenPanel");

        // Use a grid bag layout for overlapping panels
        setLayout(new GridBagLayout());

        // Create gbc
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weighty = 1;
        gbc.weightx = 1;
        gbc.fill = GridBagConstraints.BOTH;

        // Create the panels for the different actions
        panels[0] = new MovePanel(battleHandler, battleController);
        panels[1] = new ItemsPanel(battleHandler, battleController);
        panels[2] = new PokemonSelectionPanel(battleHandler, battleController);

        // Make the pokemon selection and items panel invisible
        panels[1].setVisible(false);
        panels[2].setVisible(false);

        // Add the panels to the action panel
        for (JPanel panel : panels) {
            add(panel, gbc);
        }

    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(background, 0, 0, getWidth(), getHeight(), null);
    }

    /**
     * Change the panel visibility to the given index and make all other panels
     * invisible
     * 
     * @param index The index of the panel to make visible
     */
    public void changePanelVisibilityTo(int index) {

        // Make all panels invisible
        for (JPanel panel : panels) {
            panel.setVisible(false);
        }

        // Make the panel at the given index visible
        panels[index].setVisible(true);
    }

}
