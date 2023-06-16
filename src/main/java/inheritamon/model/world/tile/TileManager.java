package inheritamon.model.world.tile;

import inheritamon.model.GameModel;
import inheritamon.model.data.DataHandler;
import inheritamon.view.SoundHandler;
import inheritamon.view.world.WorldPanel;

import java.awt.Graphics2D;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * @author Stanislav
 */
public class TileManager {

    public Tile[] tile;
    public int mapTileNum[][];
    WorldPanel gp;

    public TileManager(WorldPanel gp) {

        this.gp = gp;

        tile = new Tile[14];
        mapTileNum = new int[gp.maxWorldCol][gp.maxWorldRow];

        getTileImage();
        loadMap("/maps/Map.csv");
    }

    public void getTileImage() {

        DataHandler dh = DataHandler.getInstance();
        GameModel gm = GameModel.getInstance();
        SoundHandler sh = SoundHandler.getInstance();

        tile[0] = new Tile(dh.getTileImage("GrassTile"), false, false, "PASS");

        tile[1] = new Tile(dh.getTileImage("ShallowWaterTile"), true, false,
                "SWIMMER");

        tile[2] = new Tile(dh.getTileImage("RockTile"), true, false, "CLIMBER");

        tile[3] = new Tile(dh.getTileImage("SandTile"), false, false, "PASS");

        tile[4] = new Tile(dh.getTileImage("TreeTile"), true, false, "NotPass");

        tile[5] = new Tile(dh.getTileImage("GrassTile"), false, false, "PASS");

        tile[6] = new Tile(dh.getTileImage("GrassTile"), false, false, "PASS");

        tile[7] = new Tile(dh.getTileImage("GrassTile"), false, false, "PASS");

        tile[8] =
                new Tile(dh.getTileImage("WaterTile2"), true, false, "SWIMMER");

        tile[9] = new Tile(dh.getTileImage("WorldBorder"), true, false,
                "NotPass");

        tile[10] = new Tile(dh.getTileImage("FloorTile"), false, false, "PASS");

        tile[11] =
                new Tile(dh.getTileImage("WallTile"), true, false, "NotPass");

        // 27 due to a problem with the map maker
        tile[12] = new Tile(dh.getTileImage("Enemy"), true, true, "NotPass") {
            @Override
            public void interact() {
                gm.startPokemonBattle("random");
            }
        };

        tile[13] = new Tile(dh.getTileImage("RestoreCenter"), true, true,
                "NotPass") {
            @Override
            public void interact() {
                gm.revitalizePokemon();
                sh.playSound("select");
            }
        };


    }

    public void loadMap(String filePath) {
        try {
            InputStream is = getClass().getResourceAsStream(filePath);
            BufferedReader br = new BufferedReader(new InputStreamReader(is));

            int col = 0;
            int row = 0;

            while (row < gp.maxWorldRow) {

                String line = br.readLine();
                String numbers[] = line.split(",");

                col = 0;  // Reset column index for each row
                while (col < gp.maxWorldCol && col < numbers.length) {
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

        while (worldCol < gp.maxWorldCol && worldRow < gp.maxWorldRow) {

            int tileNum = mapTileNum[worldCol][worldRow];

            int worldX = worldCol * gp.tileSize;
            int worldY = worldRow * gp.tileSize;
            int screenX = worldX - gp.playerAvatar.worldX + gp.playerAvatar.screenX;
            int screenY = worldY - gp.playerAvatar.worldY + gp.playerAvatar.screenY;

            // Improve game performance
            if (worldX + gp.tileSize > gp.playerAvatar.worldX - gp.playerAvatar.screenX &&
                    worldX - gp.tileSize <
                            gp.playerAvatar.worldX + gp.playerAvatar.screenX &&
                    worldY + gp.tileSize >
                            gp.playerAvatar.worldY - gp.playerAvatar.screenY &&
                    worldY - gp.tileSize <
                            gp.playerAvatar.worldY + gp.playerAvatar.screenY) {
            }

            g2.drawImage(tile[tileNum].image, screenX, screenY, gp.tileSize,
                    gp.tileSize, null);

            worldCol++;

            if (worldCol == gp.maxWorldCol) {
                worldCol = 0;
                worldRow++;
            }
        }

    }

}