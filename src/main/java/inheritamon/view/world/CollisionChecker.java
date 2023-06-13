package inheritamon.view.world;

import inheritamon.model.PlayerData;
import inheritamon.model.entity.*;
import inheritamon.model.tile.TileManager;

import java.util.ArrayList;

public class CollisionChecker {

    private TileManager tileM;
    private Player player;
    private ArrayList <PlayerData.TrainerAbility> abilities;
    private int tileSize = 48;

    public CollisionChecker(TileManager tileM) {
        this.tileM = tileM;
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
                    entity.collisionOn = true;
                }
                break;
            case "down":
                entityBottomRow = (entityBottomWorldY + entity.speed)/tileSize;
                tileNum1 = tileM.mapTileNum[entityLeftCol][entityBottomRow];
                tileNum2 = tileM.mapTileNum[entityRightCol][entityBottomRow];
                if(tileM.tile[tileNum1].collision == true || tileM.tile[tileNum2].collision == true) {
                    entity.collisionOn = true;
                }
                break;
            case "left":
                entityLeftCol = (entityLeftWorldX - entity.speed)/tileSize;
                tileNum1 = tileM.mapTileNum[entityLeftCol][entityTopRow];
                tileNum2 = tileM.mapTileNum[entityLeftCol][entityBottomRow];
                if(tileM.tile[tileNum1].collision == true || tileM.tile[tileNum2].collision == true) {
                    entity.collisionOn = true;
                }
                break;
            case "right":
                entityRightCol = (entityRightWorldX + entity.speed)/tileSize;
                tileNum1 = tileM.mapTileNum[entityRightCol][entityTopRow];
                tileNum2 = tileM.mapTileNum[entityRightCol][entityBottomRow];
                if(tileM.tile[tileNum1].collision == true || tileM.tile[tileNum2].collision == true) {
                    entity.collisionOn = true;
                }
                break;
        }
    }

}
