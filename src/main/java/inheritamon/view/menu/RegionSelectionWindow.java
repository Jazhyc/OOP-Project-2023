package inheritamon.view.menu;

import inheritamon.controller.MainMenuController;
import inheritamon.controller.TrainerAbility;
import inheritamon.controller.TrainerRegion;
import inheritamon.model.GameModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class RegionSelectionWindow extends JPanel {
    public RegionSelectionWindow(GameModel gameModel) {
        JFrame frame = new JFrame("Select Region");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new FlowLayout());

        for (TrainerRegion region : TrainerRegion.values()) {
            JButton button = new JButton(region.toString());
            button.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseEntered(MouseEvent e) {
                    button.setForeground(Color.RED);
                }

                @Override
                public void mouseExited(MouseEvent e) {
                    button.setForeground(Color.BLACK);
                }

                @Override
                public void mouseClicked(MouseEvent e) {
                    gameModel.spawnPlayer("Ash", region, TrainerAbility.SWIMMING);
                    frame.setVisible(false);
//                    gamePanel.setVisible(true);
                }
            });
            frame.add(button);
        }

        frame.pack();
//        gamePanel.setVisible(false);
        frame.setVisible(true);
    }
}
