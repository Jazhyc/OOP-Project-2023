package inheritamon.view.combat;

import javax.swing.*;

import inheritamon.model.data.DataHandler;
import inheritamon.view.combat.actions.ActionPanel;
import inheritamon.controller.BattleController;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.*;


public class ChoicePanel extends JPanel {

    JButton buttons[] = new JButton[4];

    // Create an array for storing the name of the buttons
    String buttonNames[] = {"Fight", "Items", "Pokemon", "Run"};
    private int buttonWidth = 40;

    private HashMap<String, BufferedImage> buttonIcons = new HashMap<String, BufferedImage>();

    // Icons obtained from 
    // <a href="https://www.flaticon.com/free-icons/fight" title="fight icons">Fight icons created by dDara - Flaticon</a>
    // https://www.pngwing.com/en/free-png-iskzk
    // https://www.pngwing.com/en/free-png-xbwgg

    public ChoicePanel(BattleController controller, ActionPanel actionPanel) {
        
        // Use a grid bag layout for maximum customization
        setLayout(new GridBagLayout());
        buttonIcons = DataHandler.getInstance().getIcons();

        // Put 4 buttons in the array and make them occupy the whole width
        for (int i = 0; i < 4; i++) {
            buttons[i] = new JButton(buttonNames[i]);
            // Add the image before the button
            // Resize the image to 50x50
            BufferedImage icon = buttonIcons.get(buttonNames[i]);
            Image scaledIcon = icon.getScaledInstance(buttonWidth, buttonWidth, Image.SCALE_SMOOTH);

            // Make buttons call the choose method in the controller
            final int index = i;
            buttons[i].addActionListener(e -> controller.selectChoice(buttonNames[index]));

            addButton(i);
            
            addIcon(i, scaledIcon);
        }

        // Add a border
        setBorder(BorderFactory.createLineBorder(Color.BLACK, 4));

    }

    private void addButton(int i) {
        // Give more space to the buttons using gcd
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = i;
        gbc.weightx = 0.25;
        gbc.weighty = 0.25;
        gbc.fill = GridBagConstraints.BOTH;
        add(buttons[i], gbc);
    }

    private void addIcon(int i, Image scaledIcon) {
        GridBagConstraints gbc;
        // Use GCD to add the image to the left of the button
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = i;
        gbc.weightx = 0.15;
        gbc.weighty = 0.25;
        gbc.fill = GridBagConstraints.BOTH;
        add(new JLabel(new ImageIcon(scaledIcon)), gbc);
    }
    
}
