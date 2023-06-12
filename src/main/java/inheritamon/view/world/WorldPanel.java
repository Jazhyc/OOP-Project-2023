package inheritamon.view.world;

import inheritamon.model.tile.*;
import inheritamon.view.world.sidebar.SidePanel;

import javax.swing.*;
import java.awt.*;

public class WorldPanel extends JLayeredPane implements Runnable { // has all the function of the JPanel

    //screen settings
    final int originalTileSize = 16; // 16x16 tile
    final int scale = 3;

    public final int tileSize = originalTileSize * scale; // 48x48 tile
    public final int maxScreenCol = 28;
    public final int maxScreenRow = 16;
    public final int screenWidth = tileSize * maxScreenCol;
    public final int screenHeight = tileSize * maxScreenRow;

    //WORLD SETTINGS
    public final int maxWorldCol = 50;
    public final int maxWorldRow = 50;
    public final int worldWidth = tileSize * maxWorldCol;
    public final int worldHeight = tileSize * maxWorldRow;

    int FPS = 60; // the FPS of the game
    TileManager tileM;
    PlayerKeyHandler keyH;
    Thread gameThread; // game time
    public CollisionChecker cChecker;
    public Player player;
    //Default position of the player
    int playerX = 100;
    int playerY = 100;
    int playerSpeed = 4;

    public WorldPanel(SidePanel sidePanel) {
        
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.black);
        this.setDoubleBuffered(true); // enabling this can improve game rendering performance
        this.setFocusable(true);
        this.requestFocus();

        tileM = new TileManager(this);
        cChecker = new CollisionChecker(tileM);
        keyH = new PlayerKeyHandler(this, sidePanel, cChecker);
        player = new Player(this, keyH);
        cChecker.setPlayer(player);

    }

    public void startGameThread() {
        gameThread = new Thread(this);
        gameThread.start();
    }

    @Override // when game thread is called it will automatically be run
    public void run() { // the core of the game

        double drawInterval = 1000000000/FPS;
        double delta = 0;
        long lastTime = System.nanoTime();
        long currentTime; // returns the current value of the running time source

        while(gameThread != null) {

            currentTime = System.nanoTime();

            delta += (currentTime - lastTime) / drawInterval;
            lastTime = currentTime;

            if(delta >= 1){
                update(); // update information about the game
                repaint(); // repaint the screen with the updated information
                delta--;
            }
        }
    }
    
    public void update() {

        player.update();
    }

    @Override
    public void paintComponent(Graphics g) {

        super.paintComponent(g);

        Graphics2D g2 = (Graphics2D) g;

        tileM.draw(g2);

        player.draw(g2);

    }
}
