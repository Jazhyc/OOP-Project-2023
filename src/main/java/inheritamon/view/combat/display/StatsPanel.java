package inheritamon.view.combat.display;

import javax.swing.*;
import java.awt.*;


public class StatsPanel extends JPanel {
    
    private int pokemonHP;
    private int pokemonMaxHP;
    private int pokemonMP;
    private int pokemonMaxMP;
    private String pokemonName;

    public StatsPanel(int pokemonHP, int pokemonMaxHP, int pokemonMP, int pokemonMaxMP, String pokemonName) {
        this.pokemonHP = pokemonHP;
        this.pokemonMaxHP = pokemonMaxHP;
        this.pokemonMP = pokemonMP;
        this.pokemonMaxMP = pokemonMaxMP;
        this.pokemonName = pokemonName;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        // Calculate the center
        int centerX = getWidth() / 2;
        int centerY = getHeight() / 2;

        g.drawString("HP: " + pokemonHP + "/" + pokemonMaxHP, centerX, centerY);
        g.drawString("MP: " + pokemonMP + "/" + pokemonMaxMP, centerX, centerY + 10);
        g.drawString("Name: " + pokemonName, centerX, centerY + 20);
    }

}
