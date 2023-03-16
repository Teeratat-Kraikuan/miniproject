package srcs.tile;

import java.io.IOException;
import java.awt.Graphics2D;
import javax.imageio.ImageIO;

import srcs.GamePanel;

public class TileManager {
    
    GamePanel gp;
    public Tile[] tile;
    public Tile[][] mapTiles;
    public int[][] mapTileNum;

    public TileManager(GamePanel gp) {

        this.gp = gp;
        tile = new Tile[2];
        mapTileNum = gp.map.map;
        mapTiles = new Tile[gp.maxScreenRow][gp.maxScreenCol];
        getTileImage();
    }
    public void getTileImage() {

        try {

            tile[0] = new Tile();
            tile[0].image = ImageIO.read(getClass().getResourceAsStream("../../images/tile/grass.png"));

            tile[1] = new Tile();
            tile[1].image = ImageIO.read(getClass().getResourceAsStream("../../images/tile/wall.png"));
            tile[1].collision = true;

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void draw(Graphics2D g2) {

        for (int i = 0; i < gp.maxScreenRow - 1; i++) {
            for (int j = 0; j < gp.maxScreenCol; j++) {
                if (mapTileNum[i][j] == 1) {
                    g2.drawImage(tile[1].image, j * gp.tileSize, i * gp.tileSize, gp.tileSize, gp.tileSize, null);
                    mapTiles[i][j] = tile[1];
                }
                else {
                    g2.drawImage(tile[0].image, j * gp.tileSize, i * gp.tileSize, gp.tileSize, gp.tileSize, null);
                    mapTiles[i][j] = tile[0];
                }
            }
        }
    }
}
