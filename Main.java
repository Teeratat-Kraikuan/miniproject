import srcs.*;

import javax.swing.JFrame;

public class Main {
    public static void main(String[] args) {

        
        JFrame window = new JFrame();
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setResizable(false);
        window.setTitle("My Game");
        
        Map map = args.length == 1 ? new Map(args[0]) : new Map("simple");
        GamePanel gamePanel = new GamePanel(map);
        window.add(gamePanel);

        window.pack();

        window.setLocationRelativeTo(null);
        window.setVisible(true);

        gamePanel.startGameThread();
    }
}