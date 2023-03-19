package srcs.entity;

import javax.imageio.ImageIO;

import java.util.Random;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;

import srcs.GamePanel;

public class Monster extends Entity {
    
    GamePanel gp;
    int number;
    boolean animating = false;
    
    public Monster(GamePanel gp, int number) {

        this.gp = gp;
        this.number = number;

        setDefaultValues();
        getMonsterImage();
    }
    void setDefaultValues() {

        x = gp.map.monsterPos[number][0];
        y = gp.map.monsterPos[number][1];
        realX = x * gp.tileSize;
        realY = y * gp.tileSize;
        speed = 1;
        direction = "down";
    }
    void getMonsterImage() {

        try {
            up1 = ImageIO.read(getClass().getResourceAsStream("../../images/monster/monster.png"));
        } catch(IOException e) {
            e.printStackTrace();
        }
    }
    int random() {

        Random random = new Random();
        int n = random.nextInt(4);
        return n;
    }
    public void update() {

        if (!animating) {

            System.out.println("Monster number: " + number);
            System.out.println("randoming");
            int random_number = random();
            System.out.println("get random is " + random_number);

            animating = true;
            if (random_number == 0) {
                direction = "up";
                y -= 1;
                if (gp.tileM.mapTiles[y][x].collision) {
                    y += 1;
                }
            }
            else if (random_number == 1) {
                direction = "down";
                y += 1;
                if (gp.tileM.mapTiles[y][x].collision) {
                    y -= 1;
                }
            }
            else if (random_number == 2) {
                direction = "left";
                x -= 1;
                if (gp.tileM.mapTiles[y][x].collision) {
                    x += 1;
                }
            }
            else if (random_number == 3) {
                direction = "right";
                x += 1;
                if (gp.tileM.mapTiles[y][x].collision) {
                    x -= 1;
                }
            }
        }

        if (animating) {

            if (direction == "up") {
                realY -= speed;
                if (realY <= y * gp.tileSize)
                    animating = false;
            }
            else if (direction == "down") {
                realY += speed;
                if (realY >= y * gp.tileSize)
                    animating = false;
            }
            else if (direction == "left") {
                realX -= speed;
                if (realX <= x * gp.tileSize)
                    animating = false;
            }
            else if (direction == "right") {
                realX += speed;
                if (realX >= x * gp.tileSize)
                    animating = false;
            }
        }
    }
    public void draw(Graphics2D g2) {

        BufferedImage image = up1;
        g2.drawImage(image, realX, realY, gp.tileSize, gp.tileSize, null);
    }
}
