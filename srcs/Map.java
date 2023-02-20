package srcs;

public class Map {
    Player player;
    Enemy enemy;
    Collectible collectible;
    Exit exit;
    String[] map;

    public Map(String[] m) {
        map = m;
        init_map();
        for (int i = 0; i < map.length; i++) {
            map[i] = map[i].replace('P', '0');
            map[i] = map[i].replace('C', '0');
            map[i] = map[i].replace('E', '0');
            map[i] = map[i].replace('M', '0');
        }
    }
    void init_map() {
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j< map[i].length(); j++) {
                if (map[i].charAt(j) == 'P') {
                    player = new Player(j, i);
                }
                else if (map[i].charAt(j) == 'E') {
                    exit = new Exit(j, i);
                }
            }
        }
    }
    public void drawMap() {
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[i].length(); j++) {
                if (i == player.posY && j == player.posX)
                    System.out.print("P");
                else if (i == exit.posY && j == exit.posX)
                    System.out.print("E");
                else
                    System.out.print(map[i].charAt(j));
            }
            System.out.println();
        }
    }
    public boolean reachGoal() {
        if (player.posX == exit.posX && player.posY == exit.posY)
            return true;
        return false;
    }
    public void map_change(String command) {
        if (command.equals("up"))
            player.goUp();
        else if (command.equals("down"))
            player.goDown();
        else if (command.equals("left"))
            player.goLeft();
        else if (command.equals("right"))
            player.goRight();
    }
}
