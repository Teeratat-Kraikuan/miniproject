package srcs;

import java.awt.Color;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JFrame;

public class MainScreen extends JFrame implements KeyListener{

    static int WIN_WIDTH = 13 * 64;
    static int WIN_HEIGHT = (5 + 1) * 64;
    Player player = new Player(1, 1);

    public MainScreen() {
        super("My Game");

        addKeyListener(this);
        getContentPane().setBackground(Color.GREEN);
        add(player);
        setSize(WIN_WIDTH, WIN_HEIGHT);
        setLocation(80, 50);
        setVisible(true);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    @Override
    public void keyTyped(KeyEvent e) {
        // do nothing
        // System.out.println("Typed key");
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();
        if (key == KeyEvent.VK_ESCAPE)
            System.exit(0);
        else if (key == KeyEvent.VK_UP || key == KeyEvent.VK_W)
            player.goUp();
        else if (key == KeyEvent.VK_DOWN || key == KeyEvent.VK_S)
            player.goDown();
        else if (key == KeyEvent.VK_LEFT || key == KeyEvent.VK_A)
            player.goLeft();
        else if (key == KeyEvent.VK_RIGHT || key == KeyEvent.VK_D)
            player.goRight();
        add(player);
        repaint();
    }

    @Override
    public void keyReleased(KeyEvent e) {
        // do nothing
        // System.out.println("Released key");
    }
}
