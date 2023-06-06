package inheritamon.view.world;

import inheritamon.controller.PlayerKeyHandler;
import inheritamon.model.entity.Entity;

import javax.imageio.ImageIO;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Player extends Entity {

    GamePanel gp;
    PlayerKeyHandler keyH;

    public final int screenX;
    public final int screenY;

    public Player(GamePanel gp, PlayerKeyHandler keyH){
        this.gp = gp;
        this.keyH = keyH;

        screenX = gp.screenWidth/2 - (gp.tileSize/2);
        screenY = gp.screenHeight/2 - (gp.tileSize/2);

        //Smaller collision area for the character
        collisionArea = new Rectangle();
        collisionArea.x = 8;
        collisionArea.y = 16;
        collisionArea.width = 32;
        collisionArea.height = 32;

        setDefaultValues();
        getPlayerImage();
    }
    public void setDefaultValues(){
        worldX = gp.tileSize * 16;
        worldY = gp.tileSize * 11;
        speed = 4;
        direction = "down";
    }
    public void getPlayerImage() {
        try {
            //load all character sprites
            up1 = ImageIO.read(getClass().getResourceAsStream("/characterTextures/Back 1.png"));
            up2 = ImageIO.read(getClass().getResourceAsStream("/characterTextures/Back 2.png"));
            down1 = ImageIO.read(getClass().getResourceAsStream("/characterTextures/Front 1.png"));
            down2 = ImageIO.read(getClass().getResourceAsStream("/characterTextures/Front 2.png"));
            right1 = ImageIO.read(getClass().getResourceAsStream("/characterTextures/Right 1.png"));
            right2 = ImageIO.read(getClass().getResourceAsStream("/characterTextures/Right 2.png"));
            left1 = ImageIO.read(getClass().getResourceAsStream("/characterTextures/Left 1.png"));
            left2 = ImageIO.read(getClass().getResourceAsStream("/characterTextures/Left 2.png"));

        } catch(IOException e) {
            e.printStackTrace();
        }
    }

    public void update(){

        if(keyH.upPressed == true || keyH.downPressed == true || keyH.leftPressed == true || keyH.rightPressed == true) {
            if (keyH.upPressed == true) {
                direction = "up";
            } else if (keyH.downPressed == true) {
                direction = "down";
            } else if (keyH.leftPressed == true) {
                direction = "left";
            } else if (keyH.rightPressed == true) {
                direction = "right";
            }


            //Check tile collision
            collisionOn = false;
            gp.cChecker.checkTile(this);

            //If collision is false, the player can move
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

            //Switches the animation for the character sprite (when faced in a certain direction)
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
    public void draw(Graphics2D g2){
        //g2.setColor(Color.white);
        //g2.fillRect(screenX, screenY, gp.tileSize, gp.tileSize);

        BufferedImage image = null;
        //determine the proper facing based on the input
        switch (direction) {
            case "up":
                if(spriteNum == 1) {
                    image = up1;
                }
                if(spriteNum == 2) {
                    image = up2;
                }
                break;
            case "down":
                if(spriteNum == 1) {
                    image = down1;
                }
                if(spriteNum == 2) {
                    image = down2;
                };
                break;
            case "right":
                if(spriteNum == 1) {
                    image = right1;
                }
                if(spriteNum == 2) {
                    image = right2;
                }
                break;
            case "left":
                if(spriteNum == 1) {
                    image = left1;
                }
                if(spriteNum == 2) {
                    image = left2;
                }
                break;
        }
        //draw character
        g2.drawImage(image, screenX, screenY, gp.tileSize, gp.tileSize, null);
    }
}
