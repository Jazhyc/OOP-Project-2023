package inheritamon.model.world.assets;
import inheritamon.view.world.WorldPanel;

import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * @author Stanislav
 * A class to represent the objects in the world
 */
public class SuperObject {
    public BufferedImage image;
    public String name;
    public boolean collision = false;
    public int worldX, worldY;
    public Rectangle solidArea = new Rectangle(0, 0, 48, 48);
    public int solidAreaDefaultX = 0;
    public int solidAreaDefaultY = 0;
    public void draw(Graphics2D g2, WorldPanel gp) {
        int screenX = worldX - gp.playerAvatar.worldX + gp.playerAvatar.screenX;
        int screenY = worldY - gp.playerAvatar.worldY + gp.playerAvatar.screenY;

        if(worldX + gp.tileSize > gp.playerAvatar.worldX - gp.playerAvatar.screenX && worldX - gp.tileSize < gp.playerAvatar.worldX + gp.playerAvatar.screenX &&
                worldY + gp.tileSize > gp.playerAvatar.worldY - gp.playerAvatar.screenY && worldY - gp.tileSize < gp.playerAvatar.worldY + gp.playerAvatar.screenY) {

            g2.drawImage(image, screenX, screenY, gp.tileSize, gp.tileSize, null);
        }
    }
}
