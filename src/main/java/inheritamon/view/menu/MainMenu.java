package inheritamon.view.menu;

import javax.swing.*;

import inheritamon.controller.MainMenuController;
import inheritamon.language.LanguageConfiguration;
import inheritamon.language.LanguageConfiguration.Language;

import java.awt.*;
import java.awt.event.*;

public class MainMenu extends JPanel {

    private JLabel titleLabel;
    private JButton startButton;

    public MainMenu(MainMenuController controller, JPanel gamePanel) {

        setLayout(new GridBagLayout());

        // Get the configuration object
        LanguageConfiguration config = LanguageConfiguration.getInstance();

        titleLabel = new JLabel("Inheritamon");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 36));
        titleLabel.setHorizontalAlignment(JLabel.CENTER);
        addTitle();
        
        startButton = new JButton("Start");

        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
    
                setVisible(false);
                setEnabled(false);

                gamePanel.setVisible(true);
                controller.startGame();
            }
        });

        addStartButton();

        // Set the background color
        setBackground(Color.WHITE);

        // Add a button to the top right that switches between the words EN and NL
        JButton languageButton = new JButton("EN");
        languageButton.addActionListener(new ActionListener() {
            @Override
            // Add more code for implementing the language switch
            public void actionPerformed(ActionEvent e) {
                if (languageButton.getText().equals("EN")) {
                    languageButton.setText("NL");
                    
                    // Set the language to Dutch
                    config.setLanguage(Language.NL);

                } else {
                    languageButton.setText("EN");
                    config.setLanguage(Language.EN);
                }
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

    private void addStartButton() {
        // Set the size of the start button
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.weightx = 1.0;
        gbc.weighty = 0.0;
        gbc.fill = GridBagConstraints.NONE;
        gbc.insets = new Insets(10, 0, 50, 0);
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.ipadx = 50;
        gbc.ipady = 10;
        add(startButton, gbc);
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
    
}
