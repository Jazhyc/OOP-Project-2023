package inheritamon.view.combat;
import javax.swing.*;
import java.awt.*;

public class DialoguePanel extends JPanel {

    private String textToDisplay = "A wild Pokemon appeared!";

    public DialoguePanel() {

        // Set color to White
        setBackground(Color.WHITE);

    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        
        // Create a border around the panel
        setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY, 3));

        displayText(g);

    }

    private void displayText(Graphics g) {
        // Display the text by painting it on the panel
        // Perform calculations to center the text
        g.setColor(Color.BLACK);

        // What font to use
        g.setFont(new Font("Lucida Sans Regular", Font.PLAIN, 28));
        
        // Get the width and height of the text
        FontMetrics metrics = g.getFontMetrics();
        int textWidth = metrics.stringWidth(textToDisplay);
        int textHeight = metrics.getHeight();

        // Get the position of the text
        int x = (getWidth() - textWidth) / 2;
        int y = (getHeight() - textHeight) / 2 + metrics.getAscent();

        // Draw the text
        g.drawString(textToDisplay, x, y);
    }

    public void setTextToDisplay(String text) {
        textToDisplay = text;
    }
    
    
}
