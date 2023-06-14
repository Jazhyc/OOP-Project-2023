package inheritamon.view.combat.display;

import inheritamon.view.combat.display.BattleDisplayPanel.DisplayType;
import inheritamon.model.BattleHandler;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.lang.reflect.*;

/**
 * @author Jeremias
 * Panel for displaying the HP and MP of the player and the enemy
 */
public class StatsPanel extends JPanel {

    /**
     * The duration of the HP and MP bar animations
     */
    private final int DURATION = 30;

    private float HPRatio = 1;
    private float MPRatio = 1;

    /**
     * The type of the panel, whether it is for the player or the enemy
     */
    private final DisplayType type;

    /**
     * Constructor for the StatsPanel class
     *
     * @param type          The type of the panel
     * @param battleHandler The battle handler
     */
    public StatsPanel(DisplayType type, BattleHandler battleHandler) {
        this.type = type;
        setUp(battleHandler);
        setOpaque(false);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        // Calculate the width of the bars based on the window width
        int barWidth = getWidth() / 2;

        // Calculate the center
        int centerX = getWidth() / 2;
        int centerY = getHeight() / 2;

        // Perform calculations for the center of the bars based on width
        int hpBarCenterX = centerX - (barWidth / 2);
        int mpBarCenterX = centerX - (barWidth / 2);

        // Add a border around the bars
        Graphics2D g2 = (Graphics2D) g;
        float borderThickness = 3;
        g2.setStroke(new BasicStroke(borderThickness));
        g.setColor(Color.BLACK);
        int borderPadding = 1;
        int barHeight = 20;
        int yOffset = 10;
        g.drawRect(hpBarCenterX - borderPadding,
                centerY - barHeight + yOffset - borderPadding,
                barWidth + borderPadding, barHeight * 2 + borderPadding);

        // Draw the bars
        g.setColor(Color.GREEN);
        g.fillRect(hpBarCenterX, centerY - barHeight + yOffset,
                (int) (barWidth * HPRatio), barHeight);
        g.setColor(Color.BLUE);
        g.fillRect(mpBarCenterX, centerY + yOffset, (int) (barWidth * MPRatio),
                (barHeight));

        // Add labels HP and MP to the bars
        g.setColor(Color.BLACK);
        g.setFont(new Font("Arial", Font.BOLD, 20));
        int labelOffset = 18;
        int barOffset = 10;
        g.drawString("HP", hpBarCenterX + barOffset,
                centerY - barHeight + yOffset + labelOffset);
        g.drawString("MP", mpBarCenterX + barOffset,
                centerY + yOffset + labelOffset);

    }

    private void setUp(BattleHandler battleHandler) {

        battleHandler.addListener("stat", e -> {

            // Use a ternary operator to determine what the event name should be based on
            // the type
            String eventName =
                    type == DisplayType.PLAYER ? "playerStats" : "enemyStats";

            // Check if the event is for the right pokemon
            if (e.getPropertyName().equals(eventName)) {

                // Convert e into and array of ints and get the values
                int[] stats = (int[]) e.getNewValue();

                // Calculate the ratios, let the minimum be 0
                float newHPRatio = Math.max((float) stats[0] / stats[1], 0);
                float newMPRatio = Math.max((float) stats[2] / stats[3], 0);

                // Tween the values
                tweenValue(HPRatio, newHPRatio, "HPRatio");
                tweenValue(MPRatio, newMPRatio, "MPRatio");

                // Repaint the panel
                revalidate();
                repaint();

            }

        });
    }

    /**
     * Tween a value from one value to another over a duration
     *
     * @param oldValue      The old value
     * @param newValue      The new value
     * @param attributeName The name of the attribute to tween, uses reflection for
     *                      versatility
     */
    private void tweenValue(float oldValue, float newValue,
                            String attributeName) {
        // Calculate the difference between the values
        float difference = newValue - oldValue;

        // Calculate the amount to change the value by each frame
        float changePerMoment = difference / DURATION;

        // Create a new timer to tween the value
        int refreshRate = 10;
        Timer timer = new Timer(refreshRate, null);
        timer.addActionListener(new ActionListener() {
            private float incrementValue = oldValue;
            private int elapsed = 0;

            @Override
            public void actionPerformed(ActionEvent e) {
                // Add the change per frame to the old value
                incrementValue += changePerMoment;

                // Set the attribute value using reflection
                try {
                    Field field = StatsPanel.this.getClass()
                            .getDeclaredField(attributeName);
                    field.setAccessible(true);
                    field.set(StatsPanel.this, incrementValue);
                } catch (NoSuchFieldException | IllegalAccessException ex) {
                    ex.printStackTrace();
                }

                // Repaint the panel
                revalidate();
                repaint();

                // Increment the elapsed time
                elapsed++;

                // Stop the timer if the duration has elapsed
                if (elapsed >= DURATION) {
                    timer.stop();
                }
            }
        });

        // Start the timer
        timer.start();
    }
}
