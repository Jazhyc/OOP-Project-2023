package inheritamon.view.combat;
import javax.swing.*;
import java.awt.*;

public class PokemonDisplayPanel extends JPanel {

    private JTextArea textArea;

    public PokemonDisplayPanel() {
        
        // Change the color to red
        setBackground(Color.RED);

    }

    public void setTextToDisplay(String text) {
        textArea.setText(text);
    }
    
    
}
