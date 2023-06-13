package inheritamon.view.combat.actions;

import inheritamon.model.BattleHandler;
import inheritamon.controller.*;
import inheritamon.view.*;
import inheritamon.model.data.language.*;

import java.awt.*;
import java.util.*;
import java.awt.event.*;
import javax.swing.*;

/**
 * @author Jeremias
 *         The MovePanel class is responsible for displaying the moves of the
 *         current pokemon
 */
public class MovePanel extends JPanel implements LanguageChangeListener {

    // Create an arrayList for the buttons
    private ArrayList<JLabel> buttonLabels = new ArrayList<JLabel>();
    private String[] moveList = new String[4];

    private SoundHandler soundHandler;

    /**
     * Constructor for the MovePanel class
     * 
     * @param battleHandler    The battle handler
     * @param battleController The battle controller
     */
    public MovePanel(BattleHandler battleHandler, GameController battleController) {

        // Use a grid layout with a scroll pane
        setLayout(new GridLayout(4, 1));
        setOpaque(false);
        setUp(battleHandler, battleController);
        soundHandler = SoundHandler.getInstance();

        addLanguageListener();
    };

    private void setUp(BattleHandler battleHandler, GameController battleController) {

        // Add a move listener to the battle handler
        battleHandler.addListener("moves", e -> {

            // Print the moves if e contains the moves attribute which is an array of
            // strings
            if (e.getPropertyName().equals("moves")) {

                // Clear the buttons
                buttonLabels.clear();

                // Clear the panel
                removeAll();

                // Get the moves from the event
                moveList = (String[]) e.getNewValue();

                // Print the moves
                for (String move : moveList) {

                    addButton(battleController, move);
                }

                revalidate();
                repaint();

            }

        });

    }

    private void addButton(GameController battleController, String move) {
        String moveName = LanguageConfiguration.getInstance().getLocalMoveName(move);
        JLabel button = new JLabel(moveName);

        buttonLabels.add(button);

        // Center the button and increase font size
        button.setHorizontalAlignment(JLabel.CENTER);
        button.setFont(new Font("Arial", Font.BOLD, 20));
        button.setForeground(Color.WHITE);

        // Add a mouse listener to the button
        button.addMouseListener(new MouseAdapter() {

            @Override
            public void mouseEntered(MouseEvent e) {
                button.setForeground(Color.YELLOW);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                button.setForeground(Color.WHITE);
            }

            @Override
            public void mouseClicked(MouseEvent e) {
                battleController.selectMove(move);
                soundHandler.playSound("select");
            }

        });

        add(button);
    }

    /**
     * Add a language listener to the panel
     */
    public void addLanguageListener() {

        LanguageConfiguration config = LanguageConfiguration.getInstance();
        config.addLanguageChangeListener(e -> {

            // Loop through the buttons and change the text
            for (int i = 0; i < buttonLabels.size(); i++) {
                buttonLabels.get(i).setText(config.getLocalMoveName(moveList[i]));
            }

        });

    }

}
