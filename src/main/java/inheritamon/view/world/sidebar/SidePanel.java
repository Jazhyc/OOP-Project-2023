package inheritamon.view.world.sidebar;

import inheritamon.controller.GameController;
import inheritamon.model.data.language.LanguageChangeListener;
import inheritamon.model.data.language.LanguageConfiguration;
import inheritamon.view.SoundHandler;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.Rectangle;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * @author Jeremias
 * The SidePanel class is responsible for displaying the menu on the
 * left side in game.
 */
public class SidePanel extends JPanel implements LanguageChangeListener {

    private ArrayList<String> options = new ArrayList<>();
    private ArrayList<JLabel> buttonLabels = new ArrayList<>();

    private SoundHandler soundHandler;
    private JPanel pokemonDataPanel;
    private JPanel inventoryPanel;
    private GameController gameController;

    /**
     * Constructor for the SidePanel class.
     *
     * @param gameController   The game controller
     * @param pokemonDataPanel The panel that displays the pokemon data
     * @param inventoryPanel The panel that displays inventory data
     */
    public SidePanel(GameController gameController, JPanel pokemonDataPanel, JPanel inventoryPanel) {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        addLanguageListener();

        soundHandler = SoundHandler.getInstance();
        this.inventoryPanel = inventoryPanel;
        this.pokemonDataPanel = pokemonDataPanel;
        this.gameController = gameController;

        LanguageConfiguration config = LanguageConfiguration.getInstance();

        options.addAll(Arrays.asList(config.getOptions("SidePanel")));

        // Create a font for the menu
        int titleSize = 40;
        Font font = new Font("Arial", Font.BOLD, titleSize);

        // Add The text "Menu" to the top of the panel
        JLabel menuLabel = new JLabel("Inheritamon");
        menuLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        menuLabel.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));
        menuLabel.setForeground(Color.WHITE);
        menuLabel.setFont(font);
        add(menuLabel);

        // Use a bold font
        int fontSize = 20;
        Font optionFont = new Font("Arial", Font.BOLD, fontSize);

        addLabels(optionFont);

        // Use a black background
        setBackground(Color.BLACK);

        addMotionListener();

        addClickListener();
    }

    private void addLabels(Font optionFont) {
        // Create a JLabel for each option
        for (String option : options) {

            // Center the labels and add them to the panel
            JLabel label = new JLabel(option);
            label.setAlignmentX(Component.CENTER_ALIGNMENT);

            // Add some padding
            label.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));

            // Make the font white
            label.setForeground(Color.WHITE);

            label.setFont(optionFont);
            add(label);

            // Add the label to the list of labels
            buttonLabels.add(label);

        }
    }

    private void addClickListener() {
        addMouseListener(new MouseAdapter() {

            // Add a check for mouse clicks
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

                        handleButtonPress(i);
                        soundHandler.playSound("select");

                    }
                }
            }

        });
    }

    private void addMotionListener() {
        addMouseMotionListener(new MouseMotionAdapter() {

            @Override
            public void mouseMoved(MouseEvent e) {

                int x = e.getX();
                int y = e.getY();

                // Use a for loop to check if the mouse is within the bounds of a button
                for (JLabel button : buttonLabels) {

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
    }

    private void handleButtonPress(int i) {
        // Use a switch statement to check which button was clicked
        switch (i) {
            case 0:
                System.out.println("Demo Battle");
                gameController.beginRandomBattle();
                break;
            case 1:
                // Open the items menu
                System.out.println("Items");
                inventoryPanel.setVisible(!inventoryPanel.isVisible());
                pokemonDataPanel.setVisible(false);
                break;
            case 2:
                // Open the pokemon menu
                System.out.println("Pokemon");
                pokemonDataPanel.setVisible(!pokemonDataPanel.isVisible());
                inventoryPanel.setVisible(false);
                break;
            case 3:
                // Save the game
                System.out.println("Save");
                gameController.saveGame();
                break;
            case 4:
                // Minimize the menu
                System.out.println("Minimize Menu");
                setVisible(false);
                pokemonDataPanel.setVisible(false);
                inventoryPanel.setVisible(false);
                break;
            case 5:
                System.out.println("To Title");
                setVisible(false);
                pokemonDataPanel.setVisible(false);
                inventoryPanel.setVisible(false);
                gameController.returnToMainMenu();
                break;
            case 6:
                // Exit the game
                System.out.println("Exit Game");
                gameController.saveGame();
                System.exit(0);
                break;
        }
    }

    /**
     * Add a language change listener to the side panel.
     */
    public void addLanguageListener() {
        LanguageConfiguration config = LanguageConfiguration.getInstance();

        config.addLanguageChangeListener(e -> {
            options.clear();

            // Combine these two lines into one
            options.addAll(Arrays.asList(config.getOptions("SidePanel")));

            // Update the labels
            for (int i = 0; i < options.size(); i++) {
                buttonLabels.get(i).setText(options.get(i));
            }
        });

    }

    /**
     * Hide the panels that are displayed to the right of the side panel.
     */
    public void hideDataPanels() {
        pokemonDataPanel.setVisible(false);
        inventoryPanel.setVisible(false);
    }

}
