package srcs.tile;

import java.io.IOException;
import java.awt.Color;
import java.awt.Graphics2D;
import javax.imageio.ImageIO;

import srcs.GamePanel;

public class WalkCount extends Tile{
    
    GamePanel gp;
    public Tile[] tile;

    public WalkCount(GamePanel gp) {
        this.gp = gp;
    }

    public void draw(Graphics2D g2, int numOfWalks) {

        g2.setFont(g2.getFont().deriveFont((float)gp.tileSize));
        g2.setColor(Color.WHITE);
        g2.drawString(String.format("%04d", numOfWalks), gp.tileSize * (gp.maxScreenCol - 4 +1), gp.tileSize * gp.maxScreenRow - (gp.tileSize / 8));
    }
}
