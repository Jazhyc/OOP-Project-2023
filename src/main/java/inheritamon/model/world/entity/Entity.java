package inheritamon.model.world.entity;

import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * @author Stanislav
 */
public class Entity {
    public int worldX, worldY;
    public int speed;
    public BufferedImage up1, up2, down1, down2, right1, right2, left1, left2;
    public String direction;
    public int spriteCounter = 0;
    public int spriteNum = 1;
    public Rectangle collisionArea;
    public boolean collisionOn = false;
    public int solidAreaDefaultX;
    public int solidAreaDefaultY;

    public String getDirection() {
        return direction;
    }
}
