package inheritamon.view.world;

import inheritamon.model.data.DataHandler;
import inheritamon.model.world.entity.Entity;

import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * @author Stanislav
 * A class to represent the player avatar
 */
public class PlayerAvatar extends Entity {

    WorldPanel gp;
    PlayerKeyHandler keyH;

    public final int screenX;
    public final int screenY;

    int hasKey = 0;

    /**
     * Constructor for the player avatar
     * @param gp the world panel
     * @param keyH the key handler
     */
    public PlayerAvatar(WorldPanel gp, PlayerKeyHandler keyH) {
        this.gp = gp;
        this.keyH = keyH;

        screenX = gp.screenWidth / 2 - (gp.tileSize / 2);
        screenY = gp.screenHeight / 2 - (gp.tileSize / 2);

        // Smaller collision area for the character
        collisionArea = new Rectangle();
        collisionArea.x = 8;
        collisionArea.y = 16;
        collisionArea.width = 32;
        collisionArea.height = 32;

        setDefaultValues();
        getPlayerImage();
    }

    /**
     * Sets the default values for the player avatar
     */
    public void setDefaultValues() {
        worldX = gp.tileSize * 20;
        worldY = gp.tileSize * 35;
        speed = 4;
        direction = "down";
    }

    /**
     * Gets the player image
     */
    public void getPlayerImage() {

        DataHandler dh = DataHandler.getInstance();

        // load all character sprites
        up1 = dh.getCharacterTexture("Back 1");
        up2 = dh.getCharacterTexture("Back 2");
        down1 = dh.getCharacterTexture("Front 1");
        down2 = dh.getCharacterTexture("Front 2");
        right1 = dh.getCharacterTexture("Right 1");
        right2 = dh.getCharacterTexture("Right 2");
        left1 = dh.getCharacterTexture("Left 1");
        left2 = dh.getCharacterTexture("Left 2");

    }

    /**
     * Updates the position of the player avatar
     */
    public void update() {

        boolean upPressed = keyH.isUpPressed();
        boolean downPressed = keyH.isDownPressed();
        boolean leftPressed = keyH.isLeftPressed();
        boolean rightPressed = keyH.isRightPressed();

        if (upPressed == true || downPressed == true || leftPressed == true || rightPressed == true) {
            if (upPressed == true) {
                direction = "up";
            } else if (downPressed == true) {
                direction = "down";
            } else if (leftPressed == true) {
                direction = "left";
            } else if (rightPressed == true) {
                direction = "right";
            }

            // Check tile collision
            collisionOn = false;
            gp.cChecker.checkTile(this);

            //Check object collision
            int objectIndex = gp.cChecker.checkObject(this, true);
            pickUpObject(objectIndex);

            // If collision is false, the player can move
            if (collisionOn == false) {
                switch (direction) {
                    case "up":
                        worldY -= speed;
                        break;
                    case "down":
                        worldY += speed;
                        break;
                    case "left":
                        worldX -= speed;
                        break;
                    case "right":
                        worldX += speed;
                        break;
                }
            }

            // Switches the animation for the character sprite (when faced in a certain
            // direction)
            spriteCounter++;
            if (spriteCounter > 12) {
                if (spriteNum == 1) {
                    spriteNum = 2;
                } else if (spriteNum == 2) {
                    spriteNum = 1;
                }
                spriteCounter = 0;
            }
        }
    }

    /**
     * Picks up an object
     * @param i the index of the object
     */
    public void pickUpObject(int i) {
        if(i != 999) {

            String objectName = gp.obj[i].name;

            switch (objectName) {
                case "Key":
                    hasKey++;
                    gp.obj[i] = null;
                    break;
                case "Door":
                    if(hasKey > 0) {
                        gp.obj[i] = null;
                        hasKey--;
                    }
                    break;
            }
        }
    }

    /**
     * Draws the player avatar
     * @param g2
     */
    public void draw(Graphics2D g2) {
        // g2.setColor(Color.white);
        // g2.fillRect(screenX, screenY, gp.tileSize, gp.tileSize);

        BufferedImage image = null;
        // determine the proper facing based on the input
        switch (direction) {
            case "up":
                if (spriteNum == 1) {
                    image = up1;
                }
                if (spriteNum == 2) {
                    image = up2;
                }
                break;
            case "down":
                if (spriteNum == 1) {
                    image = down1;
                }
                if (spriteNum == 2) {
                    image = down2;
                }
                ;
                break;
            case "right":
                if (spriteNum == 1) {
                    image = right1;
                }
                if (spriteNum == 2) {
                    image = right2;
                }
                break;
            case "left":
                if (spriteNum == 1) {
                    image = left1;
                }
                if (spriteNum == 2) {
                    image = left2;
                }
                break;
        }
        // draw character
        g2.drawImage(image, screenX, screenY, gp.tileSize, gp.tileSize, null);
    }
}
