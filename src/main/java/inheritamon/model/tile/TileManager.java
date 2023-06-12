package inheritamon.model.tile;

import inheritamon.model.data.DataHandler;
import inheritamon.view.world.WorldPanel;
import inheritamon.model.BattleHandler;
import inheritamon.model.GameModel;
import inheritamon.model.PlayerData;
import inheritamon.model.pokemon.types.*;

import java.awt.*;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

public class TileManager {

    WorldPanel gp;
    public Tile[] tile;
    public int mapTileNum[][];

    private PlayerData playerData;
    private BattleHandler battleHandler;

    public TileManager(WorldPanel gp) {

        this.gp = gp;

        tile = new Tile[10];
        mapTileNum = new int[gp.maxWorldCol][gp.maxWorldRow];

        getTileImage();
        loadMap("/maps/map01.txt");
    }

    public void getTileImage() {

        DataHandler dh = DataHandler.getInstance();

        tile[0] = new Tile(dh.getTileImage("Pass"), false, false);

        tile[1] = new Tile(dh.getTileImage("NotPass"), true, false);

        tile[2] = new Tile(dh.getCharacterTexture("Front 1"), true, true) {
            @Override
            public void interact() {
                System.out.println("Interacting with Entity");
                setIsInteracting(true);

                // Emergency fix
                battleHandler = GameModel.getBattleHandler();
                playerData = GameModel.getPlayerData();

                Pokemon enemyPokemon = new RandomPokemon(DataHandler.getInstance().getPokemonData("Groudon"));
                battleHandler.startBattle(playerData, enemyPokemon);

            }
        };

    }

    public void loadMap(String filePath) {
        try {
            InputStream is = getClass().getResourceAsStream(filePath);
            BufferedReader br = new BufferedReader(new InputStreamReader(is));

            int col = 0;
            int row = 0;

            while(row < gp.maxWorldRow) {

                String line = br.readLine();
                String numbers[] = line.split(" ");

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