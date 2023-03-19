package srcs.tile;

import java.awt.Color;
import java.awt.Graphics2D;

import srcs.GamePanel;

public class WalkCount {
    
    GamePanel gp;

    public WalkCount(GamePanel gp) {

        this.gp = gp;
    }

    public void draw(Graphics2D g2, int numOfWalks) {

        g2.setFont(g2.getFont().deriveFont((float)gp.tileSize));
        g2.setColor(Color.WHITE);
        g2.drawString(String.format("%04d", numOfWalks), gp.tileSize * (gp.maxScreenCol - 4 +1), gp.tileSize * gp.maxScreenRow - (gp.tileSize / 8));
    }
}
