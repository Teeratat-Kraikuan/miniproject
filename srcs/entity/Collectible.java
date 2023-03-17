package srcs.entity;

import javax.imageio.ImageIO;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;

import srcs.GamePanel;

public class Collectible extends Entity {

    GamePanel gp;
    public boolean collected = false;
    
    public Collectible(GamePanel gp, int i) {

        this.gp = gp;

        setDefaultValues(i);
        getCollectibleImage();
    }
    void setDefaultValues(int i) {

        x = gp.map.collectiblePos[i][0];
        y = gp.map.collectiblePos[i][1];
        realX = x * gp.tileSize;
        realY = y * gp.tileSize;
    }
    void getCollectibleImage() {

        try {
            up1 = ImageIO.read(getClass().getResourceAsStream("../../images/collectible/banana.png"));
        } catch(IOException e) {
            e.printStackTrace();
        }
    }
    public void draw(Graphics2D g2) {

        BufferedImage image = up1;
        if (!collected)
            g2.drawImage(image, realX, realY, gp.tileSize, gp.tileSize, null);
    }
}
