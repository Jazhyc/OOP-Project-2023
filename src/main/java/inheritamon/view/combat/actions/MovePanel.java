package inheritamon.view.combat.actions;

import javax.swing.*;

import inheritamon.model.BattleHandler;
import inheritamon.controller.*;

import java.awt.*;
import java.util.*;
import java.awt.event.*;

public class MovePanel extends JPanel {

    // Create an arrayList for the buttons
    private ArrayList<JLabel> buttonLabels = new ArrayList<JLabel>();
    private String[] moveList;

    public MovePanel(BattleHandler battleHandler, BattleController battleController) {

        // Use a grid layout with a scroll pane
        setLayout(new GridLayout(4, 1));
        setOpaque(false);
        setUp(battleHandler, battleController);

        // Add a mouse listener for the buttons
        addMouseListener(new MouseAdapter() {

            @Override
            public void mouseClicked(MouseEvent e) {

                int x = e.getX();
                int y = e.getY();

                // Use a for loop to check if the mouse is within the bounds of a button
                for (int i = 0; i < buttonLabels.size(); i++) {

                    JLabel button = buttonLabels.get(i);

                    // Get the bounds of the button
                    Rectangle bounds = button.getBounds();

                    // Check if the mouse is within the bounds of the button
                    if (bounds.contains(x, y)) {

                        // Get the move name
                        String moveName = moveList[i];

                        // Send the move name to the battle controller
                        battleController.selectMove(moveName);

                    }

                }

            }
        });

        addMouseMotionListener(new MouseMotionAdapter() {
                
            @Override
            public void mouseMoved(MouseEvent e) {

                int x = e.getX();
                int y = e.getY();

                // Use a for loop to check if the mouse is within the bounds of a button
                for (int i = 0; i < buttonLabels.size(); i++) {

                    JLabel button = buttonLabels.get(i);

                    // Get the bounds of the button
                    Rectangle bounds = button.getBounds();

                    // Check if the mouse is within the bounds of the button
                    if (bounds.contains(x, y)) {
                        button.setForeground(Color.YELLOW);
                    } else {
                        button.setForeground(Color.WHITE);
                    }
                }
            }
        });
    };


    private void setUp(BattleHandler battleHandler, BattleController battleController) {

        // Add a move listener to the battle handler
        battleHandler.addMoveListener(e -> {
            
            // Print the moves if e contains the moves attribute which is an array of strings
            if (e.getPropertyName().equals("moves")) {

                // Clear the buttons
                buttonLabels.clear();
                
                // Get the moves from the event
                moveList = (String[]) e.getNewValue();

                // Print the moves
                for (String move : moveList) {
                    JLabel button = new JLabel(move);

                    buttonLabels.add(button);

                    // Center the button and increase font size
                    button.setHorizontalAlignment(JLabel.CENTER);
                    button.setFont(new Font("Arial", Font.BOLD, 20));
                    button.setForeground(Color.WHITE);
                    add(button);
                }

                revalidate();
                repaint();

            }
            
        });

    }
    
}
