package inheritamon.view.world;

import java.util.ArrayList;

import inheritamon.model.GameModel;
import inheritamon.model.player.Player;
import inheritamon.model.world.entity.*;
import inheritamon.model.world.tile.TileManager;

/**
 * @author Stanislav
 * A class to check for collisions
 */
public class CollisionChecker {

    private WorldPanel gp;
    private TileManager tileM;
    private PlayerAvatar playerAvatar;
    private int tileSize = 48;

    /**
     * Constructor for the collision checker
     * @param tileM The tile manager
     * @param gp The world panel
     */
    public CollisionChecker(TileManager tileM, WorldPanel gp) {
        this.tileM = tileM;
        this.gp = gp;
    }

    /**
     * Setter for the player
     * @param playerAvatar The player
     */
    public void setPlayer(PlayerAvatar playerAvatar) {
        this.playerAvatar = playerAvatar;
    }

    /**
     * Checks for interactions with the player
     */
    public void checkPlayerInteraction() {

        int entityLeftWorldX = playerAvatar.worldX + playerAvatar.collisionArea.x;
        int entityRightWorldX = playerAvatar.worldX + playerAvatar.collisionArea.x + playerAvatar.collisionArea.width;
        int entityTopWorldY = playerAvatar.worldY + playerAvatar.collisionArea.y;
        int entityBottomWorldY = playerAvatar.worldY + playerAvatar.collisionArea.y + playerAvatar.collisionArea.height;

        int entityLeftCol = entityLeftWorldX/tileSize;
        int entityRightCol = entityRightWorldX/tileSize;
        int entityTopRow = entityTopWorldY/tileSize;
        int entityBottomRow = entityBottomWorldY/tileSize;

        int tileNum1;

        switch (playerAvatar.direction) {
            case "up":
                entityTopRow = (entityTopWorldY - playerAvatar.speed)/tileSize;
                tileNum1 = tileM.mapTileNum[entityLeftCol][entityTopRow];
                if(tileM.tile[tileNum1].canInteract()) {
                    playerAvatar.collisionOn = true;
                    
                    // Call the interact method of the tile
                    tileM.tile[tileNum1].interact();
                    

                }
                break;
            case "down":
                entityBottomRow = (entityBottomWorldY + playerAvatar.speed)/tileSize;
                tileNum1 = tileM.mapTileNum[entityLeftCol][entityBottomRow];
                if(tileM.tile[tileNum1].canInteract()) {
                    playerAvatar.collisionOn = true;

                    // Call the interact method of the tile
                    tileM.tile[tileNum1].interact();
                    

                }
                break;
            case "left":
                entityLeftCol = (entityLeftWorldX - playerAvatar.speed)/tileSize;
                tileNum1 = tileM.mapTileNum[entityLeftCol][entityTopRow];
                if(tileM.tile[tileNum1].canInteract()) {
                    playerAvatar.collisionOn = true;

                    // Call the interact method of the tile
                    tileM.tile[tileNum1].interact();

                }
                break;
            case "right":
                entityRightCol = (entityRightWorldX + playerAvatar.speed)/tileSize;
                tileNum1 = tileM.mapTileNum[entityRightCol][entityTopRow];
                if(tileM.tile[tileNum1].canInteract()) {
                    playerAvatar.collisionOn = true;

                    // Call the interact method of the tile
                    tileM.tile[tileNum1].interact();
                    
                }
                break;
        }

        
    }

    /**
     * Checks for collisions with the player
     * @param entity The entity to check for collisions with
     */
    public void checkTile(Entity entity){

        Player playerData = GameModel.getInstance().getPlayerData();
        ArrayList<Player.TrainerAbility> abilities = playerData.getAbilities();

        int entityLeftWorldX = entity.worldX + entity.collisionArea.x;
        int entityRightWorldX = entity.worldX + entity.collisionArea.x + entity.collisionArea.width;
        int entityTopWorldY = entity.worldY + entity.collisionArea.y;
        int entityBottomWorldY = entity.worldY + entity.collisionArea.y + entity.collisionArea.height;

        int entityLeftCol = entityLeftWorldX/tileSize;
        int entityRightCol = entityRightWorldX/tileSize;
        int entityTopRow = entityTopWorldY/tileSize;
        int entityBottomRow = entityBottomWorldY/tileSize;

        int tileNum1, tileNum2;

        switch (entity.direction) {
            case "up":
                entityTopRow = (entityTopWorldY - entity.speed)/tileSize;
                tileNum1 = tileM.mapTileNum[entityLeftCol][entityTopRow];
                tileNum2 = tileM.mapTileNum[entityRightCol][entityTopRow];
                if(tileM.tile[tileNum1].collision == true || tileM.tile[tileNum2].collision == true) {

                    // Set collision to true unless the player has the required ability
                    if (tileM.tile[tileNum1].canPass(abilities) || tileM.tile[tileNum2].canPass(abilities)) {
                        entity.collisionOn = false;
                    } else {
                        entity.collisionOn = true;
                    }

                }
                break;
            case "down":
                entityBottomRow = (entityBottomWorldY + entity.speed)/tileSize;
                tileNum1 = tileM.mapTileNum[entityLeftCol][entityBottomRow];
                tileNum2 = tileM.mapTileNum[entityRightCol][entityBottomRow];
                if(tileM.tile[tileNum1].collision == true || tileM.tile[tileNum2].collision == true) {
                    if (tileM.tile[tileNum1].canPass(abilities) || tileM.tile[tileNum2].canPass(abilities)) {
                        entity.collisionOn = false;
                    } else {
                        entity.collisionOn = true;
                    }
                }
                break;
            case "left":
                entityLeftCol = (entityLeftWorldX - entity.speed)/tileSize;
                tileNum1 = tileM.mapTileNum[entityLeftCol][entityTopRow];
                tileNum2 = tileM.mapTileNum[entityLeftCol][entityBottomRow];
                if(tileM.tile[tileNum1].collision == true || tileM.tile[tileNum2].collision == true) {
                    if (tileM.tile[tileNum1].canPass(abilities) || tileM.tile[tileNum2].canPass(abilities)) {
                        entity.collisionOn = false;
                    } else {
                        entity.collisionOn = true;
                    }
                }
                break;
            case "right":
                entityRightCol = (entityRightWorldX + entity.speed)/tileSize;
                tileNum1 = tileM.mapTileNum[entityRightCol][entityTopRow];
                tileNum2 = tileM.mapTileNum[entityRightCol][entityBottomRow];
                if(tileM.tile[tileNum1].collision == true || tileM.tile[tileNum2].collision == true) {
                    if (tileM.tile[tileNum1].canPass(abilities) || tileM.tile[tileNum2].canPass(abilities)) {
                        entity.collisionOn = false;
                    } else {
                        entity.collisionOn = true;
                    }
                }
                break;
        }
    }

    /**
     * Checks for collisions with objects
     * @param entity The entity to check for collisions with
     * @param player Whether the entity is the player or not
     * @return The index of the object collided with
     */
    public int checkObject(Entity entity, boolean player) {

        int index = 999;

        for(int i = 0; i < gp.obj.length; i++) {
            if(gp.obj[i] != null) {
                //Get entity's solid area position
                entity.collisionArea.x = entity.worldX + entity.collisionArea.x;
                entity.collisionArea.y = entity.worldY + entity.collisionArea.y;

                //Get the object's solid area position
                gp.obj[i].solidArea.x = gp.obj[i].worldX + gp.obj[i].solidArea.x;
                gp.obj[i].solidArea.y = gp.obj[i].worldY + gp.obj[i].solidArea.y;

                switch (entity.direction) {
                    case "up":
                        entity.collisionArea.y -= entity.speed;
                        if(entity.collisionArea.intersects(gp.obj[i].solidArea)) {
                            if(gp.obj[i].collision = true) {
                                entity.collisionOn = true;
                            }
                            if(player == true) {
                                index = i;
                            }
                        }
                        break;
                    case "down":
                        entity.collisionArea.y += entity.speed;
                        if(entity.collisionArea.intersects(gp.obj[i].solidArea)) {
                            if(gp.obj[i].collision = true) {
                                entity.collisionOn = true;
                            }
                            if(player == true) {
                                index = i;
                            }
                        }
                        break;
                    case "left":
                        entity.collisionArea.x -= entity.speed;
                        if(entity.collisionArea.intersects(gp.obj[i].solidArea)) {
                            if(gp.obj[i].collision = true) {
                                entity.collisionOn = true;
                            }
                            if(player == true) {
                                index = i;
                            }
                        }
                        break;
                    case "right":
                        entity.collisionArea.x += entity.speed;
                        if(entity.collisionArea.intersects(gp.obj[i].solidArea)) {
                            if(gp.obj[i].collision = true) {
                                entity.collisionOn = true;
                            }
                            if(player == true) {
                                index = i;
                            }
                        }
                        break;
                }
                entity.collisionArea.x = entity.solidAreaDefaultX;
                entity.collisionArea.y = entity.solidAreaDefaultY;
                gp.obj[i].solidArea.x = gp.obj[i].solidAreaDefaultX;
                gp.obj[i].solidArea.y = gp.obj[i].solidAreaDefaultY;
            }
        }

        return index;
    }


}
