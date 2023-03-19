package srcs.entity;

import javax.imageio.ImageIO;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;

import srcs.GamePanel;
import srcs.KeyHandler;

public class Player extends Entity {
    
    GamePanel gp;
    KeyHandler keyH;
    boolean animating = false;
    boolean died = false;
    int countItems = 0;
    public int countWalks = 0;

    public Player(GamePanel gp, KeyHandler keyH) {

        this.gp = gp;
        this.keyH = keyH;

        setDefaultValues();
        getPlayerImage();
    }
    public void setDefaultValues() {

        x = gp.map.playerPos[0];
        y = gp.map.playerPos[1]; 
        realX = x * gp.tileSize;
        realY = y * gp.tileSize;
        speed = 3;
        direction = "down";
    }
    public void getPlayerImage() {

        try {
            up1 = ImageIO.read(getClass().getResourceAsStream("../../images/player/U1.png"));
            up2 = ImageIO.read(getClass().getResourceAsStream("../../images/player/U2.png"));
            up3 = ImageIO.read(getClass().getResourceAsStream("../../images/player/U3.png"));
            down1 = ImageIO.read(getClass().getResourceAsStream("../../images/player/D1.png"));
            down2 = ImageIO.read(getClass().getResourceAsStream("../../images/player/D2.png"));
            down3 = ImageIO.read(getClass().getResourceAsStream("../../images/player/D3.png"));
            left1 = ImageIO.read(getClass().getResourceAsStream("../../images/player/L1.png"));
            left2 = ImageIO.read(getClass().getResourceAsStream("../../images/player/L2.png"));
            left3 = ImageIO.read(getClass().getResourceAsStream("../../images/player/L3.png"));
            right1 = ImageIO.read(getClass().getResourceAsStream("../../images/player/R1.png"));
            right2 = ImageIO.read(getClass().getResourceAsStream("../../images/player/R2.png"));
            right3 = ImageIO.read(getClass().getResourceAsStream("../../images/player/R3.png"));
        } catch(IOException e) {
            e.printStackTrace();
        }
    }
    public void update() {

        // Check If died do nothing
        if (died)
            return;

        // Check Is Player Collide Monster?
        for (int i = 0; i < gp.map.numOfMonsters; i++) {
            if (x == gp.monster[i].x && y == gp.monster[i].y)
                died = true;
        }

        // Check Exit
        if (!animating && keyH.enterPressed) {
            
            if (x == gp.exit.x && y == gp.exit.y && countItems == gp.map.numOfItems) {
                System.out.println("Exit Game.");
                System.exit(0);
            }
        }

        // Check Collectible
        if (!animating && keyH.spacePressed) {

            for (int i = 0; i < gp.map.numOfItems; i++) {
                if (x == gp.collectible[i].x && y == gp.collectible[i].y) {
                    if (!gp.collectible[i].collected)
                        countItems++;
                    gp.collectible[i].collected = true;
                }
            }
        }

        // Check Direction and Create Animating
        if (!animating && (keyH.upPressed || keyH.downPressed || keyH.leftPressed || keyH.rightPressed)) {

            animating = true;
            if (keyH.upPressed) {
                direction = "up";
                y -= 1;
                countWalks++;
                if (gp.tileM.mapTiles[y][x].collision) {
                    y += 1;
                    animating = false;
                    countWalks--;
                }
            }
            else if (keyH.downPressed) {
                direction = "down";
                y += 1;
                countWalks++;
                if (gp.tileM.mapTiles[y][x].collision) {
                    y -= 1;
                    animating = false;
                    countWalks--;
                }
            }
            else if (keyH.leftPressed) {
                direction = "left";
                x -= 1;
                countWalks++;
                if (gp.tileM.mapTiles[y][x].collision) {
                    x += 1;
                    animating = false;
                    countWalks--;
                }
            }
            else if (keyH.rightPressed) {
                direction = "right";
                x += 1;
                countWalks++;
                if (gp.tileM.mapTiles[y][x].collision) {
                    x -= 1;
                    animating = false;
                    countWalks--;
                }
            }
        }
        
        // Animating
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

            spriteCounter++;
            if (spriteCounter > 12) {
                if (spriteNum == 1) {
                    spriteNum = 2;
                }
                else if (spriteNum == 2) {
                    spriteNum = 3;
                }
                else if (spriteNum == 3) {
                    spriteNum = 1;
                }
                spriteCounter = 0;
            }
        }
    }
    public void draw(Graphics2D g2) {

        BufferedImage image = null;

        switch(direction) {
            case "up":
                if (spriteNum == 1) {
                    image = up1;
                }
                if (spriteNum == 2) {
                    image = up2;
                }
                if (spriteNum == 3) {
                    image = up3;
                }
                break;
            case "down":
                if (spriteNum == 1) {
                    image = down1;
                }
                if (spriteNum == 2) {
                    image = down2;
                }
                if (spriteNum == 3) {
                    image = down3;
                }
                break;
            case "left":
                if (spriteNum == 1) {
                    image = left1;
                }
                if (spriteNum == 2) {
                    image = left2;
                }
                if (spriteNum == 3) {
                    image = left3;
                }
                break;
            case "right":
                if (spriteNum == 1) {
                    image = right1;
                }
                if (spriteNum == 2) {
                    image = right2;
                }
                if (spriteNum == 3) {
                    image = right3;
                }
                break;
        }
        g2.drawImage(image, realX, realY, gp.tileSize, gp.tileSize, null);
    }
}
