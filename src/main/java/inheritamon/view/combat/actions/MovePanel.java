package inheritamon.view.combat.actions;

import javax.swing.*;

import inheritamon.model.BattleHandler;
import inheritamon.controller.*;

import java.awt.*;
import java.util.*;

public class MovePanel extends JPanel {

    // Create an arrayList for the buttons
    private ArrayList<JButton> buttons = new ArrayList<>();

    public MovePanel(BattleHandler battleHandler, BattleController battleController) {

        // Use a grid layout with a scroll pane
        setLayout(new GridLayout(4, 1));
        JScrollPane scrollPane = new JScrollPane(this);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);


        setUp(battleHandler, battleController);

    }

    private void setUp(BattleHandler battleHandler, BattleController battleController) {

        // Add a move listener to the battle handler
        battleHandler.addMoveListener(e -> {
            
            // Print the moves if e contains the moves attribute which is an array of strings
            if (e.getPropertyName().equals("moves")) {

                // Clear the buttons
                buttons.clear();
                
                // Get the moves from the event
                String[] moves = (String[]) e.getNewValue();

                // Print the moves
                for (String move : moves) {
                    JButton button = new JButton(move);

                    // Add an action listener to the button
                    button.addActionListener(selectionEvent -> {
                        // Get the move from the button
                        String selectedMove = button.getText();

                        // Use the move
                        battleController.selectMove(selectedMove);
                    });

                    buttons.add(button);
                    add(button);
                }

                revalidate();
                repaint();

            }
            
        });

    }
    
}
