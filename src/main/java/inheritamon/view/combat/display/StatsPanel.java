package inheritamon.view.combat.display;

import inheritamon.view.combat.display.BattleDisplayPanel.DisplayType;
import inheritamon.model.BattleHandler;

import javax.swing.*;
import java.awt.*;


public class StatsPanel extends JPanel {
    
    // Set default values to represent a full bar
    // The user won't notice
    private float hpRatio = 1;
    private float mpRatio = 1;

    private int yOffset = 10;
    private int barWidth = 300;
    private int barHeight = 20;
    private int borderPadding = 1;
    private float thickness = 3;
    private int labelOffset = 18;
    private DisplayType type;

    public StatsPanel(DisplayType type, BattleHandler battleHandler) {
        this.type = type;
        setUp(battleHandler);
        setOpaque(false);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        // Calculate the width of the bars based on the window width
        barWidth = getWidth() / 2;

        // Calculate the center
        int centerX = getWidth() / 2;
        int centerY = getHeight() / 2;

        // Perform calculations for the center of the bars based on width
        int hpBarCenterX = centerX - (barWidth / 2);
        int mpBarCenterX = centerX - (barWidth / 2);

        // Add a border around the bars
        Graphics2D g2 = (Graphics2D) g;
        g2.setStroke(new BasicStroke(thickness));
        g.setColor(Color.BLACK);
        g.drawRect(hpBarCenterX - borderPadding, centerY - barHeight + yOffset - borderPadding, barWidth + borderPadding, barHeight * 2 + borderPadding);

        // Draw the bars
        g.setColor(Color.GREEN);
        g.fillRect(hpBarCenterX, centerY - barHeight + yOffset, (int) (barWidth * hpRatio), barHeight);
        g.setColor(Color.BLUE);
        g.fillRect(mpBarCenterX, centerY + yOffset, (int) (barWidth * mpRatio), (barHeight));

        // Add labels HP and MP to the bars
        g.setColor(Color.BLACK);
        g.setFont(new Font("Arial", Font.BOLD, 20));
        g.drawString("HP", hpBarCenterX + 10, centerY - barHeight + yOffset + labelOffset);
        g.drawString("MP", mpBarCenterX + 10, centerY + yOffset + labelOffset);

    }

    private void setUp(BattleHandler battleHandler) {

        battleHandler.addStatListener(e -> {

            // Use a ternary operator to determine what the event name should be based on the type
            String eventName = type == DisplayType.PLAYER ? "playerStats" : "enemyStats";
            
            // Check if the event is for the right pokemon
            if (e.getPropertyName().equals(eventName)) {

                // Convert e into and array of ints and get the values
                int[] stats = (int[]) e.getNewValue();
                
                // Calculate the ratios, let the minimum be 0
                hpRatio = Math.max((float) stats[0] / stats[1], 0);
                mpRatio = Math.max((float) stats[2] / stats[3], 0);

                // Repaint the panel
                revalidate();
                repaint();

            }

        });
    }

}
