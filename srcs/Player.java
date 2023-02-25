package srcs;

import java.awt.Canvas;
import java.awt.Graphics;
import java.awt.Toolkit;
import java.awt.Image;

public class Player extends Canvas{
    int posX;
    int posY;
    int moved;

    public Player(int x, int y) {
        posX = x;
        posY = y;
    }
    public void goUp() {
        posY -= 1;
    }
    public void goDown() {
        posY += 1;
    }
    public void goLeft() {
        posX -= 1;
    }
    public void goRight() {
        posX += 1;
    }

    public void paint(Graphics g) {
        Toolkit t = Toolkit.getDefaultToolkit();
        Image i = t.getImage("images/cele_stand_down.png");
        g.drawImage(i, posX * 64, posY * 64, this);
    }
}
