package srcs.entity;

import java.awt.image.BufferedImage;

public class Entity {
    
    public int x, y, realX, realY;
    public int speed;

    public BufferedImage up1, up2, up3, down1, down2, down3, left1, left2, left3, right1, right2, right3, died1, died2, died3, died4;
    public String direction;

    public int spriteCounter = 0;
    public int spriteNum = 1;
}
