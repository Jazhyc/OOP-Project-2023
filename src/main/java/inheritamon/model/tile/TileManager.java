package inheritamon.model.tile;

import inheritamon.model.data.DataHandler;
import inheritamon.view.world.WorldPanel;

import java.awt.*;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

public class TileManager {

    WorldPanel gp;
    public Tile[] tile;
    public int mapTileNum[][];

    public TileManager(WorldPanel gp) {

        this.gp = gp;

        tile = new Tile[15];
        mapTileNum = new int[gp.maxWorldCol][gp.maxWorldRow];

        getTileImage();
        loadMap("/maps/Map.csv");
    }

    public void getTileImage() {

        DataHandler dh = DataHandler.getInstance();

        tile[0] = new Tile(dh.getTileImage("GrassTile"), false, false);

        tile[1] = new Tile(dh.getTileImage("ShallowWaterTile"), false, false);

        tile[2] = new Tile(dh.getTileImage("RockTile"), false, false);

        tile[3] = new Tile(dh.getTileImage("SandTile"), false, false);

        tile[4] = new Tile(dh.getTileImage("TreeTile"), true, false);

        tile[5] = new Tile(dh.getTileImage("GrassTile"), false, false);

        tile[6] = new Tile(dh.getTileImage("GrassTile"), false, false);

        tile[7] = new Tile(dh.getTileImage("GrassTile"), false, false);

        tile[8] = new Tile(dh.getTileImage("WaterTile2"), false, false);

        tile[9] = new Tile(dh.getTileImage("WorldBorder"), true, false);

        tile[10] = new Tile(dh.getTileImage("FloorTile"), false, false);

        tile[11] = new Tile(dh.getTileImage("WallTile"), true, false);


//        tile[2] = new Tile(dh.getCharacterTexture("Front 1"), true, true) {
//            @Override
//            public void interact() {
//                System.out.println("Interacting with Entity");
//
//
//
//            }
//        };

    }

    public void loadMap(String filePath) {
        try {
            InputStream is = getClass().getResourceAsStream(filePath);
            BufferedReader br = new BufferedReader(new InputStreamReader(is));

            int col = 0;
            int row = 0;

            while(row < gp.maxWorldRow) {

                String line = br.readLine();
                   String numbers[] = line.split(",");

                col = 0;  // Reset column index for each row
                while(col < gp.maxWorldCol && col < numbers.length) {
                    int num = Integer.parseInt(numbers[col]);

                    mapTileNum[col][row] = num;
                    col++;
                }

                row++;
            }
            br.close();
        } catch (Exception e) {

        }
    }
        public void draw(Graphics2D g2) {
            //2.drawImage(tile[0].image, 0, 0, gp.tileSize, gp.tileSize, null);
            int worldCol = 0;
            int worldRow = 0;

            while(worldCol < gp.maxWorldCol && worldRow < gp.maxWorldRow) {

                int tileNum = mapTileNum[worldCol][worldRow];

                int worldX = worldCol * gp.tileSize;
                int worldY = worldRow * gp.tileSize;
                int screenX = worldX - gp.player.worldX + gp.player.screenX;
                int screenY = worldY - gp.player.worldY + gp.player.screenY;

                // Improve game performance
                if(worldX + gp.tileSize > gp.player.worldX - gp.player.screenX && worldX - gp.tileSize < gp.player.worldX + gp.player.screenX &&
                        worldY + gp.tileSize > gp.player.worldY - gp.player.screenY && worldY - gp.tileSize < gp.player.worldY + gp.player.screenY) {
                }

                g2.drawImage(tile[tileNum].image, screenX, screenY, gp.tileSize, gp.tileSize, null);

                worldCol++;

                if(worldCol == gp.maxWorldCol) {
                    worldCol = 0;
                    worldRow++;
                }
            }

        }

}