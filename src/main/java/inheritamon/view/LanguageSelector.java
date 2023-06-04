package inheritamon.view;
import inheritamon.GameState;
import inheritamon.Main;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Locale;

import static inheritamon.GameState.LANGUAGE_SELECTION;

public class LanguageSelector {
    public LanguageSelector() {
        Main.setState(LANGUAGE_SELECTION);
        JFrame languageSelectionWindow = new JFrame("Choose your language / Selecteer je taal");
        languageSelectionWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        languageSelectionWindow.setSize(300, 200);
        JButton english = new JButton("English");
        JButton dutch = new JButton("Nederlands");
        english.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Locale locale = new Locale.Builder()
                        .setLanguage("en")
                        .setRegion("NL")
                        .build();
                Main.setLocale(locale);
                SwingUtilities.windowForComponent((Component) e.getSource()).dispose();
                Main.setState(GameState.LOADING_SAVE);
            }
        });

        dutch.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Locale locale = new Locale.Builder()
                        .setLanguage("nl")
                        .setRegion("NL")
                        .build();
                Main.setLocale(locale);
                SwingUtilities.windowForComponent((Component) e.getSource()).dispose();
                Main.setState(GameState.LOADING_SAVE);
            }
        });

        JPanel panel = new JPanel();
        panel.add(english);
        panel.add(dutch);
        languageSelectionWindow.getContentPane().add(panel);
        languageSelectionWindow.setVisible(true);
    }
}
