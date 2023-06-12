package inheritamon.view.world;

import inheritamon.model.entity.*;

public class CollisionChecker {

    WorldPanel gp;
    public CollisionChecker(WorldPanel gp) {
        this.gp = gp;
    }

    public void checkInteraction(Entity player) {

        int entityLeftWorldX = player.worldX + player.collisionArea.x;
        int entityRightWorldX = player.worldX + player.collisionArea.x + player.collisionArea.width;
        int entityTopWorldY = player.worldY + player.collisionArea.y;
        int entityBottomWorldY = player.worldY + player.collisionArea.y + player.collisionArea.height;

        int entityLeftCol = entityLeftWorldX/gp.tileSize;
        int entityRightCol = entityRightWorldX/gp.tileSize;
        int entityTopRow = entityTopWorldY/gp.tileSize;
        int entityBottomRow = entityBottomWorldY/gp.tileSize;

        int tileNum1;

        switch (player.direction) {
            case "up":
                entityTopRow = (entityTopWorldY - player.speed)/gp.tileSize;
                tileNum1 = gp.tileM.mapTileNum[entityLeftCol][entityTopRow];
                if(gp.tileM.tile[tileNum1].canInteract()) {
                    
                    // Call the interact method of the tile
                    gp.tileM.tile[tileNum1].interact();
                    

                }
                break;
            case "down":
                entityBottomRow = (entityBottomWorldY + player.speed)/gp.tileSize;
                tileNum1 = gp.tileM.mapTileNum[entityLeftCol][entityBottomRow];
                if(gp.tileM.tile[tileNum1].canInteract()) {
                    player.collisionOn = true;

                    // Call the interact method of the tile
                    gp.tileM.tile[tileNum1].interact();
                    

                }
                break;
            case "left":
                entityLeftCol = (entityLeftWorldX - player.speed)/gp.tileSize;
                tileNum1 = gp.tileM.mapTileNum[entityLeftCol][entityTopRow];
                if(gp.tileM.tile[tileNum1].canInteract()) {
                    player.collisionOn = true;

                    // Call the interact method of the tile
                    gp.tileM.tile[tileNum1].interact();

                }
                break;
            case "right":
                entityRightCol = (entityRightWorldX + player.speed)/gp.tileSize;
                tileNum1 = gp.tileM.mapTileNum[entityRightCol][entityTopRow];
                if(gp.tileM.tile[tileNum1].canInteract()) {
                    player.collisionOn = true;

                    // Call the interact method of the tile
                    gp.tileM.tile[tileNum1].interact();
                    
                }
                break;
        }

        
    }

    public void checkTile(Entity entity){

        int entityLeftWorldX = entity.worldX + entity.collisionArea.x;
        int entityRightWorldX = entity.worldX + entity.collisionArea.x + entity.collisionArea.width;
        int entityTopWorldY = entity.worldY + entity.collisionArea.y;
        int entityBottomWorldY = entity.worldY + entity.collisionArea.y + entity.collisionArea.height;

        int entityLeftCol = entityLeftWorldX/gp.tileSize;
        int entityRightCol = entityRightWorldX/gp.tileSize;
        int entityTopRow = entityTopWorldY/gp.tileSize;
        int entityBottomRow = entityBottomWorldY/gp.tileSize;

        int tileNum1, tileNum2;

        switch (entity.direction) {
            case "up":
                entityTopRow = (entityTopWorldY - entity.speed)/gp.tileSize;
                tileNum1 = gp.tileM.mapTileNum[entityLeftCol][entityTopRow];
                tileNum2 = gp.tileM.mapTileNum[entityRightCol][entityTopRow];
                if(gp.tileM.tile[tileNum1].collision == true || gp.tileM.tile[tileNum2].collision == true) {
                    entity.collisionOn = true;
                }
                break;
            case "down":
                entityBottomRow = (entityBottomWorldY + entity.speed)/gp.tileSize;
                tileNum1 = gp.tileM.mapTileNum[entityLeftCol][entityBottomRow];
                tileNum2 = gp.tileM.mapTileNum[entityRightCol][entityBottomRow];
                if(gp.tileM.tile[tileNum1].collision == true || gp.tileM.tile[tileNum2].collision == true) {
                    entity.collisionOn = true;
                }
                break;
            case "left":
                entityLeftCol = (entityLeftWorldX - entity.speed)/gp.tileSize;
                tileNum1 = gp.tileM.mapTileNum[entityLeftCol][entityTopRow];
                tileNum2 = gp.tileM.mapTileNum[entityLeftCol][entityBottomRow];
                if(gp.tileM.tile[tileNum1].collision == true || gp.tileM.tile[tileNum2].collision == true) {
                    entity.collisionOn = true;
                }
                break;
            case "right":
                entityRightCol = (entityRightWorldX + entity.speed)/gp.tileSize;
                tileNum1 = gp.tileM.mapTileNum[entityRightCol][entityTopRow];
                tileNum2 = gp.tileM.mapTileNum[entityRightCol][entityBottomRow];
                if(gp.tileM.tile[tileNum1].collision == true || gp.tileM.tile[tileNum2].collision == true) {
                    entity.collisionOn = true;
                }
                break;
        }
    }

}
