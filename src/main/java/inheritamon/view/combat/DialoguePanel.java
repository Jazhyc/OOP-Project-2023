package inheritamon.view.combat;
import javax.swing.*;
import java.awt.*;

public class DialoguePanel extends JPanel {

    private JTextArea textArea;

    public DialoguePanel() {
        setLayout(new BorderLayout());

        // Use a bold font
        Font font = new Font("Arial", Font.BOLD, 16);
        textArea = new JTextArea();
        textArea.setFont(font);
        textArea.setEditable(false);
        textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true);

        // Put the text in the center of the screen
        textArea.setAlignmentX(Component.CENTER_ALIGNMENT);

        JScrollPane scrollPane = new JScrollPane(textArea);
        scrollPane.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        add(scrollPane, BorderLayout.CENTER);

    }

    public void setTextToDisplay(String text) {
        textArea.setText(text);
    }
    
    
}
