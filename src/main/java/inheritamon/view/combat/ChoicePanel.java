package inheritamon.view.combat;

import javax.swing.*;

import inheritamon.model.data.DataHandler;
import inheritamon.view.combat.actions.ActionPanel;
import inheritamon.controller.BattleController;

import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.util.*;


public class ChoicePanel extends JPanel {

    JLabel labels[] = new JLabel[4];
    JLabel icons[] = new JLabel[4];

    private BufferedImage backgroundImage;

    // Create an array for storing the name of the buttons
    String buttonNames[] = {"Fight", "Items", "Pokemon", "Run"};
    private int buttonWidth = 40;

    private HashMap<String, BufferedImage> buttonIcons = new HashMap<String, BufferedImage>();

    // Icons obtained from 
    // <a href="https://www.flaticon.com/free-icons/fight" title="fight icons">Fight icons created by dDara - Flaticon</a>
    // https://www.pngwing.com/en/free-png-iskzk
    // https://www.pngwing.com/en/free-png-xbwgg

    public ChoicePanel(BattleController controller, ActionPanel actionPanel) {

        DataHandler dataHandler = DataHandler.getInstance();
        backgroundImage = dataHandler.getBackground("choiceMenu");
        
        // Use a grid bag layout for maximum customization
        setLayout(new GridBagLayout());
        buttonIcons = DataHandler.getInstance().getIcons();

        // Put 4 buttons in the array and make them occupy the whole width
        for (int i = 0; i < 4; i++) {

            labels[i] = new JLabel(buttonNames[i]);
            // Add the image before the button
            // Resize the image to 50x50
            BufferedImage icon = buttonIcons.get(buttonNames[i]);
            Image scaledIcon = icon.getScaledInstance(buttonWidth, buttonWidth, Image.SCALE_SMOOTH);

            addJLabel(i);
            addIcon(i, scaledIcon);
        }

        // Add a mouse listener to handle the movements and clicks
        // Override the default methods in swing
        addMouseMotionListener(new MouseMotionAdapter() {
            
            @Override
            public void mouseMoved(MouseEvent e) {
                // Get the position of the mouse
                int x = e.getX();
                int y = e.getY();

                // split the panel into 4 parts and change the color of the button depending on which part the mouse is in
                // Use a loop
                for (int i = 0; i < 4; i++) {
                    // Get the bounds of the button, it contains both the label and the image
                    Rectangle bounds = labels[i].getBounds();

                    // Create an another bounds for the image
                    Rectangle imageBounds = icons[i].getBounds();

                    // Check if the mouse is in the bounds
                    if (bounds.contains(x, y) || imageBounds.contains(x, y)) {
                        // Change the color of the button
                        labels[i].setForeground(Color.YELLOW);
                    } else {
                        // Change the color of the button
                        labels[i].setForeground(Color.WHITE);
                    }
                }
            }

        });

        addMouseListener(new MouseAdapter() {
            
            @Override
            public void mouseClicked(MouseEvent e) {

                // Get the position of the mouse
                int x = e.getX();
                int y = e.getY();

                // split the panel into 4 parts and change the color of the button depending on which part the mouse is in
                // Use a loop
                for (int i = 0; i < 4; i++) {
                    // Get the bounds of the button
                    Rectangle bounds = labels[i].getBounds();
                    Rectangle imageBounds = icons[i].getBounds();
                    // Check if the mouse is in the bounds
                    if (bounds.contains(x, y) || imageBounds.contains(x, y)) {
                        
                        System.out.println("Clicked " + buttonNames[i]);
                        
                    }
                }

            }

        });

        // Add a border
        setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));

    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        // Make the background image fill the entire panel
        g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), null);
    }

    private void addJLabel(int i) {
        // Give more space to the buttons using gcd
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = i;
        gbc.weightx = 0.25;
        gbc.weighty = 0.25;
        gbc.fill = GridBagConstraints.BOTH;

        // Center the text
        labels[i].setHorizontalAlignment(JLabel.CENTER);

        // Use a white font
        labels[i].setForeground(Color.WHITE);
        // Use a bigger font
        labels[i].setFont(new Font("Arial", Font.BOLD, 20));

        add(labels[i], gbc);
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
        
        // Create a new label for the image
        icons[i] = new JLabel(new ImageIcon(scaledIcon));
        // Add the image to the panel
        add(icons[i], gbc);
    }
    
}
