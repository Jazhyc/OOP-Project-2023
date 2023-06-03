package inheritamon.view.combat;

import javax.swing.*;

import inheritamon.controller.*;
import inheritamon.view.combat.actions.ActionPanel;
import inheritamon.view.combat.display.BattleDisplayPanel;

import java.awt.*;

/**
 * The main panel for the battle screen
 */
public class BattlePanel extends JPanel{

    // Create variables for the panels that will be displayed
    private BattleDisplayPanel battleDisplayPanel;
    private ChoicePanel choicePanel;
    private ActionPanel actionPanel;
    private DialoguePanel dialoguePanel;

    public BattlePanel(BattleController battleController) {

        // Use a grid bag layout for maximum customization
        setLayout(new GridBagLayout());
        
        addPokemonDisplayPanel();

        addActionPanel();

        addChoicePanel(battleController, actionPanel);

        addDialoguePanel();


    }

    private void addDialoguePanel() {
        GridBagConstraints gbc;
        // Put the dialogue Panel in between the Pokemon Display Panel and the Choice Panel
        // Yellow
        dialoguePanel = new DialoguePanel();
        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        gbc.weighty = 0.04;
        gbc.weightx = 1;
        gbc.fill = GridBagConstraints.BOTH;
        add(dialoguePanel, gbc);
    }

    private void addActionPanel() {
        GridBagConstraints gbc;
        // Put the Action Panel in the bottom right of the screen
        // Black
        actionPanel = new ActionPanel();
        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 2;
        gbc.weighty = 0.25;
        gbc.weightx = 0.5;
        gbc.fill = GridBagConstraints.BOTH;
        add(actionPanel, gbc);
    }

    private void addChoicePanel(BattleController battleController, ActionPanel actionPanel) {
        GridBagConstraints gbc;
        // Put the Choice Panel in the bottom left of the screen
        // Blue
        choicePanel = new ChoicePanel(battleController, actionPanel);
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridheight = 2;
        gbc.weighty = 0.25;
        gbc.weightx = 0.2;
        gbc.fill = GridBagConstraints.BOTH;
        add(choicePanel, gbc);
    }

    private void addPokemonDisplayPanel() {
        // Put the Pokemon Display Panel in the top half of the screen
        // Red
        battleDisplayPanel = new BattleDisplayPanel();
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weighty = 0.5;
        gbc.gridwidth = 2;
        gbc.weightx = 1;
        gbc.fill = GridBagConstraints.BOTH;
        add(battleDisplayPanel, gbc);
    }
    
}
