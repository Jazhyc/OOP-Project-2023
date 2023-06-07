package inheritamon.view.world;

import javax.swing.*;
import java.awt.event.*;

public class PlayerKeyHandler {

    private boolean upPressed, downPressed, leftPressed, rightPressed;

    public PlayerKeyHandler(JComponent component) {
        InputMap inputMap = component.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW);
        ActionMap actionMap = component.getActionMap();

        // Bind the "W" and "UP" keys to the "up" action
        inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_W, 0), "up pressed");
        inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_UP, 0), "up pressed");
        inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_W, 0, true), "up released");
        inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_UP, 0, true), "up released");
        actionMap.put("up pressed", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                upPressed = true;
            }
        });
        actionMap.put("up released", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                upPressed = false;
            }
        });

        // Bind the "S" and "DOWN" keys to the "down" action
        inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_S, 0), "down pressed");
        inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_DOWN, 0), "down pressed");
        inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_S, 0, true), "down released");
        inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_DOWN, 0, true), "down released");
        actionMap.put("down pressed", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                downPressed = true;
            }
        });
        actionMap.put("down released", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                downPressed = false;
            }
        });

        // Bind the "A" and "LEFT" keys to the "left" action
        inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_A, 0), "left pressed");
        inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_LEFT, 0), "left pressed");
        inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_A, 0, true), "left released");
        inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_LEFT, 0, true), "left released");
        actionMap.put("left pressed", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                leftPressed = true;
            }
        });
        actionMap.put("left released", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                leftPressed = false;
            }
        });

        // Bind the "D" and "RIGHT" keys to the "right" action
        inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_D, 0), "right pressed");
        inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_RIGHT, 0), "right pressed");
        inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_D, 0, true), "right released");
        inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_RIGHT, 0, true), "right released");
        actionMap.put("right pressed", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                rightPressed = true;
            }
        });
        actionMap.put("right released", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                rightPressed = false;
            }
        });
    }

    public boolean isUpPressed() {
        return upPressed;
    }

    public boolean isDownPressed() {
        return downPressed;
    }

    public boolean isLeftPressed() {
        return leftPressed;
    }

    public boolean isRightPressed() {
        return rightPressed;
    }
}
