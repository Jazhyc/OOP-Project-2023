package inheritamon.view.world;

import java.util.ArrayList;

import inheritamon.model.GameModel;
import inheritamon.model.player.PlayerData;
import inheritamon.model.world.entity.*;
import inheritamon.model.world.tile.TileManager;

/**
 * @author Stanislav
 */
public class CollisionChecker {

    private WorldPanel gp;
    private TileManager tileM;
    private Player player;
    private int tileSize = 48;

    public CollisionChecker(TileManager tileM, WorldPanel gp) {
        this.tileM = tileM;
        this.gp = gp;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public void checkPlayerInteraction() {

        int entityLeftWorldX = player.worldX + player.collisionArea.x;
        int entityRightWorldX = player.worldX + player.collisionArea.x + player.collisionArea.width;
        int entityTopWorldY = player.worldY + player.collisionArea.y;
        int entityBottomWorldY = player.worldY + player.collisionArea.y + player.collisionArea.height;

        int entityLeftCol = entityLeftWorldX/tileSize;
        int entityRightCol = entityRightWorldX/tileSize;
        int entityTopRow = entityTopWorldY/tileSize;
        int entityBottomRow = entityBottomWorldY/tileSize;

        int tileNum1;

        switch (player.direction) {
            case "up":
                entityTopRow = (entityTopWorldY - player.speed)/tileSize;
                tileNum1 = tileM.mapTileNum[entityLeftCol][entityTopRow];
                if(tileM.tile[tileNum1].canInteract()) {
                    player.collisionOn = true;
                    
                    // Call the interact method of the tile
                    tileM.tile[tileNum1].interact();
                    

                }
                break;
            case "down":
                entityBottomRow = (entityBottomWorldY + player.speed)/tileSize;
                tileNum1 = tileM.mapTileNum[entityLeftCol][entityBottomRow];
                if(tileM.tile[tileNum1].canInteract()) {
                    player.collisionOn = true;

                    // Call the interact method of the tile
                    tileM.tile[tileNum1].interact();
                    

                }
                break;
            case "left":
                entityLeftCol = (entityLeftWorldX - player.speed)/tileSize;
                tileNum1 = tileM.mapTileNum[entityLeftCol][entityTopRow];
                if(tileM.tile[tileNum1].canInteract()) {
                    player.collisionOn = true;

                    // Call the interact method of the tile
                    tileM.tile[tileNum1].interact();

                }
                break;
            case "right":
                entityRightCol = (entityRightWorldX + player.speed)/tileSize;
                tileNum1 = tileM.mapTileNum[entityRightCol][entityTopRow];
                if(tileM.tile[tileNum1].canInteract()) {
                    player.collisionOn = true;

                    // Call the interact method of the tile
                    tileM.tile[tileNum1].interact();
                    
                }
                break;
        }

        
    }

    public void checkTile(Entity entity){

        PlayerData playerData = GameModel.getInstance().getPlayerData();
        ArrayList<PlayerData.TrainerAbility> abilities = playerData.getAbilities();

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
