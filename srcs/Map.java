package srcs;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Map {

    /*
     * EXAMPLE *
     * 1111111111111                1111111111111
     * 100100010C001   ______|\     1001000104001
     * 100101M00M0C1  |        \    1001015005041
     * 100C011111001  |______  /    1004011111001
     * 1P0011E000001         |/     1200113000001
     * 1111111111111                1111111111111
     * 0 -> can walk
     * 1 -> can't walk
     * 2 -> player
     * 3 -> exit
     * 4 -> collectible
     * 5 -> monster
     */
    public int[][] map;
    public int width;
    public int height;
    public int[] playerPos = new int[2];
    public int[] exitPos = new int[2];
    public int numOfItems = 0;
    public int[][] collectiblePos;
    public int numOfMonsters = 0;
    public int[][] monsterPos;
    
    public Map(String filename) {
        
        initMap(loadMapStr(filename));
    }
    void initMap(String[] m) {
        
        height = m.length;
        width = m[0].length();
        map = new int[height][width];

        for (int i = 0; i < m.length; i++) {
            if (m[i].length() != width)
                Error.invalidMap();
            for (int j = 0; j < m[i].length(); j++) {
                if (m[i].charAt(j) == 'C')
                    numOfItems++;
                else if (m[i].charAt(j) == 'M')
                    numOfMonsters++;
            }
        }

        collectiblePos = new int[numOfItems][2];
        monsterPos = new int[numOfMonsters][2];

        int cI = 0; // collectible Iterator
        int mI = 0; // monster Iterator
        for (int i = 0; i < m.length; i++) {
            for (int j = 0; j < m[i].length(); j++) {
                if (m[i].charAt(j) == '0') {
                    map[i][j] = 0;
                }
                else if (m[i].charAt(j) == '1') {
                    map[i][j] = 1;
                }
                else if (m[i].charAt(j) == 'P') {
                    map[i][j] = 2;
                    playerPos[0] = j;
                    playerPos[1] = i;
                }
                else if (m[i].charAt(j) == 'E') {
                    map[i][j] = 3;
                    exitPos[0] = j;
                    exitPos[1] = i;
                }
                else if (m[i].charAt(j) == 'C') {
                    map[i][j] = 4;
                    collectiblePos[cI][0] = j;
                    collectiblePos[cI][1] = i;
                    cI++;
                }
                else if (m[i].charAt(j) == 'M') {
                    map[i][j] = 5;
                    monsterPos[mI][0] = j;
                    monsterPos[mI][1] = i;
                    mI++;
                }
                else {
                    Error.invalidMap();
                }
            }
        }
    }
    String[] loadMapStr(String filename) {

        String str = "";
        String[] ret;
        String file_path = "maps/" + filename;

        try {
            str = new String(Files.readAllBytes(Paths.get(file_path)));
        } 
        catch (IOException e) {
            e.printStackTrace();
            System.exit(1);
        }
        ret = str.split("\n");
        return ret;
    }
}
