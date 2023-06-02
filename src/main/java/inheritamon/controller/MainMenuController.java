package inheritamon.controller;

import javax.swing.*;

import inheritamon.view.combat.*;

public class MainMenuController {

    private JFrame mainFrame;

    public MainMenuController(JFrame frame) {
        this.mainFrame = frame;
    }

    public void startGame() {
        // Create a Panel for the main menu
        DialoguePanel dialoguePanel = new DialoguePanel();
        dialoguePanel.setTextToDisplay("This Works");
        mainFrame.add(dialoguePanel);
        mainFrame.revalidate();
    }
    
}
