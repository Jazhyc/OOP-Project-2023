package inheritamon.view.combat.actions;

import javax.swing.*;

import inheritamon.model.BattleHandler;
import inheritamon.model.data.DataHandler;
import inheritamon.controller.BattleController;

import java.awt.*;
import java.awt.image.BufferedImage;

public class ActionPanel extends JPanel {

    // Create an array for 3 panels to store the different actions
    private JPanel[] panels = new JPanel[3];

    private BufferedImage background;

    public ActionPanel(BattleHandler battleHandler, BattleController battleController) {

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
        panels[1] = new PokemonSelectionPanel();
        panels[2] = new ItemsPanel();

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
    
}
