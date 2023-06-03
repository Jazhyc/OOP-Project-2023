package inheritamon.view.combat.display;

import inheritamon.view.combat.display.BattleDisplayPanel.DisplayType;

import javax.swing.*;
import java.awt.*;


public class StatsPanel extends JPanel {
    
    private int pokemonHP;
    private int pokemonMaxHP;
    private int pokemonMP;
    private int pokemonMaxMP;
    private String pokemonName;
    private int yOffset = 10;
    private int barWidth = 300;
    private int barHeight = 20;
    private int borderPadding = 1;
    private float thickness = 3;
    private int labelOffset = 18;
    private DisplayType type;

    public StatsPanel(int pokemonHP, int pokemonMaxHP, int pokemonMP, int pokemonMaxMP, String pokemonName, DisplayType type) {
        this.pokemonHP = pokemonHP;
        this.pokemonMaxHP = pokemonMaxHP;
        this.pokemonMP = pokemonMP;
        this.pokemonMaxMP = pokemonMaxMP;
        this.pokemonName = pokemonName;
        this.type = type;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        // Calculate the center
        int centerX = getWidth() / 2;
        int centerY = getHeight() / 2;

        // Perform calculations for the center of the bars based on width
        int hpBarCenterX = centerX - (barWidth / 2);
        int mpBarCenterX = centerX - (barWidth / 2);
        int hpRatio = pokemonHP / pokemonMaxHP;
        int mpRatio = pokemonMP / pokemonMaxMP;

        // Add a border around the bars
        Graphics2D g2 = (Graphics2D) g;
        g2.setStroke(new BasicStroke(thickness));
        g.setColor(Color.BLACK);
        g.drawRect(hpBarCenterX - borderPadding, centerY - barHeight + yOffset - borderPadding, barWidth + borderPadding, barHeight * 2 + borderPadding);

        // Draw the bars
        g.setColor(Color.GREEN);
        g.fillRect(hpBarCenterX, centerY - barHeight + yOffset, barWidth * hpRatio, barHeight);
        g.setColor(Color.BLUE);
        g.fillRect(mpBarCenterX, centerY + yOffset, barWidth, barHeight * mpRatio);

        // Add labels HP and MP to the bars
        g.setColor(Color.BLACK);
        g.setFont(new Font("Arial", Font.BOLD, 20));
        g.drawString("HP", hpBarCenterX + 10, centerY - barHeight + yOffset + labelOffset);
        g.drawString("MP", mpBarCenterX + 10, centerY + yOffset + labelOffset);

    }

}
