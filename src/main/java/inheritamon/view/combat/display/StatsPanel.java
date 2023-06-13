package inheritamon.view.combat.display;

import inheritamon.view.combat.display.BattleDisplayPanel.DisplayType;
import inheritamon.model.BattleHandler;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.lang.reflect.*;

/**
 * @author Jeremias
 *         Panel for displaying the HP and MP of the player and the enemy
 */
public class StatsPanel extends JPanel {

    private final int DURATION = 30;
    private final int BAR_OFFSET = 10;
    private final int REFRESH_RATE = 10;
    
    private float HPRatio = 1;
    private float MPRatio = 1;

    private final int Y_OFFSET = 10;
    private final int BAR_HEIGHT = 20;
    private final int BORDER_PADDING = 1;
    private final float BORDER_THICKNESS = 3;
    private final int LABEL_OFFSET = 18;
    private DisplayType type;

    /**
     * Constructor for the StatsPanel class
     * @param type The type of the panel
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
        g2.setStroke(new BasicStroke(BORDER_THICKNESS));
        g.setColor(Color.BLACK);
        g.drawRect(hpBarCenterX - BORDER_PADDING, centerY - BAR_HEIGHT + Y_OFFSET - BORDER_PADDING,
                barWidth + BORDER_PADDING, BAR_HEIGHT * 2 + BORDER_PADDING);

        // Draw the bars
        g.setColor(Color.GREEN);
        g.fillRect(hpBarCenterX, centerY - BAR_HEIGHT + Y_OFFSET, (int) (barWidth * HPRatio), BAR_HEIGHT);
        g.setColor(Color.BLUE);
        g.fillRect(mpBarCenterX, centerY + Y_OFFSET, (int) (barWidth * MPRatio), (BAR_HEIGHT));

        // Add labels HP and MP to the bars
        g.setColor(Color.BLACK);
        g.setFont(new Font("Arial", Font.BOLD, 20));
        g.drawString("HP", hpBarCenterX + BAR_OFFSET, centerY - BAR_HEIGHT + Y_OFFSET + LABEL_OFFSET);
        g.drawString("MP", mpBarCenterX + BAR_OFFSET, centerY + Y_OFFSET + LABEL_OFFSET);

    }

    private void setUp(BattleHandler battleHandler) {

        battleHandler.addListener("stat", e -> {

            // Use a ternary operator to determine what the event name should be based on
            // the type
            String eventName = type == DisplayType.PLAYER ? "playerStats" : "enemyStats";

            // Check if the event is for the right pokemon
            if (e.getPropertyName().equals(eventName)) {

                // Convert e into and array of ints and get the values
                int[] stats = (int[]) e.getNewValue();

                // Calculate the ratios, let the minimum be 0
                float newHPRatio = Math.max((float) stats[0] / stats[1], 0);
                float newMPRatio = Math.max((float) stats[2] / stats[3], 0);

                // Tween the values
                tweenValue(HPRatio, newHPRatio, DURATION, "HPRatio");
                tweenValue(MPRatio, newMPRatio, DURATION, "MPRatio");

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
     * @param duration      The duration of the tween
     * @param attributeName The name of the attribute to tween, uses reflection for
     *                      versatility
     */
    private void tweenValue(float oldValue, float newValue, int duration, String attributeName) {
        // Calculate the difference between the values
        float difference = newValue - oldValue;

        // Calculate the amount to change the value by each frame
        float changePerMoment = difference / duration;

        // Create a new timer to tween the value
        Timer timer = new Timer(REFRESH_RATE, null);
        timer.addActionListener(new ActionListener() {
            float incrementValue = oldValue;
            int elapsed = 0;

            @Override
            public void actionPerformed(ActionEvent e) {
                // Add the change per frame to the old value
                incrementValue += changePerMoment;

                // Set the attribute value using reflection
                try {
                    Field field = StatsPanel.this.getClass().getDeclaredField(attributeName);
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
                if (elapsed >= duration) {
                    timer.stop();
                }
            }
        });

        // Start the timer
        timer.start();
    }
}
