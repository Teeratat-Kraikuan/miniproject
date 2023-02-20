package srcs;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Utils {
    public static String[] get_map_str(String filename) {
        String str = "";
        String[] ret;
        String file_path = "maps/" + filename;
        try {
            str = new String(
                Files.readAllBytes(Paths.get(file_path)));
        } 
        catch (IOException e) {
            e.printStackTrace();
            System.exit(1);
        }
        ret = str.split("\n");
        return ret;
    }
}
