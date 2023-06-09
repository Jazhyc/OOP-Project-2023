package inheritamon.view.menu;

import javax.swing.*;

import inheritamon.controller.MainMenuController;
import inheritamon.model.language.*;
import inheritamon.view.SoundHandler;

import java.awt.*;
import java.awt.event.*;

public class MainMenu extends JPanel implements LanguageChangeListener {

    private JLabel titleLabel;
    private SoundHandler soundHandler;
    private JPanel gamePanel;

    private JButton[] buttons = new JButton[3];

    public MainMenu(MainMenuController controller, JPanel gamePanel) {

        this.gamePanel = gamePanel;
        addLanguageListener();

        setLayout(new GridBagLayout());

        soundHandler = SoundHandler.getInstance();

        // Get the configuration object
        LanguageConfiguration config = LanguageConfiguration.getInstance();

        titleLabel = new JLabel("Inheritamon");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 36));
        titleLabel.setHorizontalAlignment(JLabel.CENTER);
        addTitle();

        addButtons(config, controller);

        // Set the background color
        setBackground(Color.WHITE);

        // Add a button to the top right that switches between the words EN and NL
        JButton languageButton = new JButton("EN");
        languageButton.addActionListener(new ActionListener() {
            @Override
            // Add more code for implementing the language switch
            public void actionPerformed(ActionEvent e) {
                config.switchLanguage();

                // Switch the text of the button
                String buttonText = languageButton.getText();
                if (buttonText.equals("EN")) {
                    languageButton.setText("NL");
                } else {
                    languageButton.setText("EN");
                }
                soundHandler.playSound("select");
            }
        });

        addLanguageSwitch(languageButton);
    }

    private void addLanguageSwitch(JButton languageButton) {
        // Add button to the top right, create a new GridBagConstraints object
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 2;
        gbc.gridy = 0;
        gbc.insets = new Insets(20, 20, 20, 20);
        gbc.weightx = 0.0;
        gbc.weighty = 0.0;
        add(languageButton, gbc);
    }

    private void addButtons(LanguageConfiguration config, MainMenuController controller) {
        
        // Get the strings from config
        String[] buttonStrings = config.getOptions("MainMenu");

        // Create a new GridBagConstraints object
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.insets = new Insets(20, 20, 20, 20);
        gbc.weightx = 0.0;
        gbc.weighty = 0.0;

        // Create the buttons and add them one after the other
        for (int i = 0; i < buttonStrings.length; i++) {
            buttons[i] = new JButton(buttonStrings[i]);

            final int index = i;

            buttons[i].addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    soundHandler.playSound("select");
                    // Use case statement and i to determine which button was pressed
                    switch (index) {
                        case 0:
                            controller.startGame();
                            setVisible(false);
                            gamePanel.setVisible(true);
                            break;
                        case 1:
                            // Continue button
                            break;
                        case 2:
                            // Exit button
                            System.exit(0);
                            break;
                    }
                }
            });

            // Change gbc to add the button to the panel
            gbc.gridy++;

            add(buttons[i], gbc);
        }

    }

    private void addTitle() {
        // Add the title label to the center of the panel
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.fill = GridBagConstraints.BOTH;
        add(titleLabel, gbc);
    }

    public void addLanguageListener() {
        LanguageConfiguration config = LanguageConfiguration.getInstance();
        
        config.addLanguageChangeListener(e -> {
            System.out.println("Language changed");
            String[] buttonStrings = config.getOptions("MainMenu");
            for (int i = 0; i < buttonStrings.length; i++) {
                buttons[i].setText(buttonStrings[i]);
            }
        });
    }
    
}
