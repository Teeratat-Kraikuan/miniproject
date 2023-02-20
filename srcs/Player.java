package srcs;

public class Player {
    int posX;
    int posY;

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
}
