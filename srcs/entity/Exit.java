package srcs.entity;

import javax.imageio.ImageIO;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;

import srcs.GamePanel;

public class Exit extends Entity {
    
    GamePanel gp;

    public Exit(GamePanel gp) {

        this.gp = gp;

        setDefaultValues();
        getExitImage();
    }
    void setDefaultValues() {

        x = gp.map.exitPos[0];
        y = gp.map.exitPos[1]; 
        realX = x * gp.tileSize;
        realY = y * gp.tileSize;
    }
    void getExitImage() {

        try {
            up1 = ImageIO.read(getClass().getResourceAsStream("../../images/exit/exit.png"));
        } catch(IOException e) {
            e.printStackTrace();
        }
    }
    public void draw(Graphics2D g2) {

        BufferedImage image = up1;
        g2.drawImage(image, realX, realY, gp.tileSize, gp.tileSize, null);
    }
}
