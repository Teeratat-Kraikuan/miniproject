package srcs;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;

import srcs.entity.Player;
import srcs.entity.Monster;
import srcs.entity.Collectible;
import srcs.entity.Exit;
import srcs.tile.TileManager;
import srcs.tile.WalkCount;

public class GamePanel extends JPanel implements Runnable {

    // SCREEN SETTING
    final int originalTileSize = 32;
    final int scale = 2;

    public final int tileSize = originalTileSize * scale;
    public final int maxScreenCol;
    public final int maxScreenRow;
    final int screenWidth;
    final int screenHeight;

    // FPS
    final int FPS = 60;

    public Map map;
    public TileManager tileM;
    public Exit exit;
    public Collectible[] collectible;
    public Monster monster[];
    KeyHandler keyH;
    Thread gameThread;
    Player player;
    WalkCount walkcount;

    public GamePanel(Map map) {

        this.map = map;
        maxScreenCol = map.width;
        maxScreenRow = map.height + 1;
        screenWidth = tileSize * maxScreenCol;
        screenHeight = tileSize * maxScreenRow;

        tileM = new TileManager(this);
        keyH = new KeyHandler();
        player = new Player(this, keyH);
        monster = new Monster[map.numOfMonsters];
        for (int i = 0; i < map.numOfMonsters; i++) {
            monster[i] = new Monster(this, i);
        }
        exit = new Exit(this);
        collectible = new Collectible[map.numOfItems];
        for (int i = 0; i < map.numOfItems; i++) {
            collectible[i] = new Collectible(this, i);
        }
        walkcount = new WalkCount(this);

        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.black);
        this.setDoubleBuffered(true);
        this.addKeyListener(keyH);
        this.setFocusable(true);
    }
    public void startGameThread() {
        
        gameThread = new Thread(this);
        gameThread.start();
    }
    @Override
    public void run() {

        // Delay to protect race condition.
        try {
            Thread.sleep(10);
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        double drawInterval = 1000000000/FPS;
        double delta = 0;
        long lastTime = System.nanoTime();
        long currentTime;

        while (gameThread != null) {

            currentTime = System.nanoTime();

            delta += (currentTime - lastTime) / drawInterval;
            lastTime = currentTime;

            if (delta >= 1) {
                update();
                repaint();
                delta--;
            }
        }
    }
    public void update() {

        player.update();
        for (int i = 0; i < map.numOfMonsters; i++) {
            monster[i].update();
        }
    }
    @Override
    public void paintComponent(Graphics g) {
        
        super.paintComponent(g);

        Graphics2D g2 = (Graphics2D)g;

        tileM.draw(g2);
        walkcount.draw(g2, player.countWalks);
        for (int i = 0; i < map.numOfItems; i++) {
            collectible[i].draw(g2);
        }
        exit.draw(g2);
        for (int i = 0; i < map.numOfMonsters; i++) {
            monster[i].draw(g2);
        }
        player.draw(g2);


        g2.dispose();
    }
    
}
