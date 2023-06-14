package inheritamon.view.combat;

import javax.swing.*;

import inheritamon.controller.*;
import inheritamon.model.BattleHandler;
import inheritamon.view.SoundHandler;
import inheritamon.view.combat.actions.ActionPanel;
import inheritamon.view.world.GamePanel;
import inheritamon.view.combat.display.BattleDisplayPanel;

import java.awt.*;

/**
 * @author Jeremias
 * The main panel for the battle screen
 */
public class BattlePanel extends JPanel {

    // Create variables for the panels that will be displayed
    private BattleDisplayPanel battleDisplayPanel;
    private ChoicePanel choicePanel;
    private ActionPanel actionPanel;
    private DialoguePanel dialoguePanel;
    private GamePanel gamePanel;
    private SoundHandler soundHandler;

    /**
     * Constructor for the BattlePanel class
     *
     * @param battleController The game controller
     * @param battleHandler    The battle handler
     * @param gamePanel        The game panel
     */
    public BattlePanel(GameController battleController,
                       BattleHandler battleHandler, GamePanel gamePanel) {

        this.gamePanel = gamePanel;

        // Use a grid bag layout for maximum customization
        setLayout(new GridBagLayout());

        addPokemonDisplayPanel(battleHandler);

        addActionPanel(battleHandler, battleController);

        addChoicePanel(battleController, actionPanel);

        addDialoguePanel(battleHandler);

        setUpListener(battleHandler);

        soundHandler = SoundHandler.getInstance();

    }

    private void addDialoguePanel(BattleHandler battleHandler) {
        GridBagConstraints gbc;
        // Put the dialogue Panel in between the Pokemon Display Panel and the Choice
        // Panel
        // Yellow
        dialoguePanel = new DialoguePanel(battleHandler);
        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        gbc.weighty = 0.04;
        gbc.weightx = 1;
        gbc.fill = GridBagConstraints.BOTH;
        add(dialoguePanel, gbc);
    }

    private void addActionPanel(BattleHandler battleHandler,
                                GameController battleController) {
        GridBagConstraints gbc;
        // Put the Action Panel in the bottom right of the screen
        // Black
        actionPanel = new ActionPanel(battleHandler, battleController);
        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 2;
        gbc.weighty = 0.25;
        gbc.weightx = 0.5;
        gbc.fill = GridBagConstraints.BOTH;
        add(actionPanel, gbc);
    }

    private void addChoicePanel(GameController battleController,
                                ActionPanel actionPanel) {
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

    private void addPokemonDisplayPanel(BattleHandler battleHandler) {
        // Put the Pokemon Display Panel in the top half of the screen
        // Red
        battleDisplayPanel = new BattleDisplayPanel(battleHandler);
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weighty = 0.5;
        gbc.gridwidth = 2;
        gbc.weightx = 1;
        gbc.fill = GridBagConstraints.BOTH;
        add(battleDisplayPanel, gbc);
    }

    private void setUpListener(BattleHandler battleHandler) {
        // Add a listener to the battleHandler to update the display
        battleHandler.addListener("battleState", e -> {

            // Get the value of e
            String battleState = (String) e.getNewValue();

            if (battleState == "Start") {
                setUpBattleView();
            } else {
                setUpGameView();
            }

        });
    }

    /**
     * Sets up the battle view by making the battle panel visible and the game panel
     * invisible
     */
    public void setUpBattleView() {
        soundHandler.playMusic("Battle");
        setVisible(true);
        gamePanel.setVisible(false);
    }

    /**
     * Sets up the game view by making the battle panel invisible and the game panel
     * visible
     */
    public void setUpGameView() {
        setVisible(false);
        gamePanel.setVisible(true);
        soundHandler.playMusic("StartMenu");
    }

}
