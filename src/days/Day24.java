package days;

import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;

public class Day24 extends Day {
    Map<Integer, String> tiles;

    public Day24(String filename) throws FileNotFoundException {
        super(filename);
        tiles = new HashMap<>();

        /*int minX = Integer.MAX_VALUE;
        int maxX = Integer.MIN_VALUE;
        int minY = Integer.MAX_VALUE;
        int maxY = Integer.MIN_VALUE;
        int minZ = Integer.MAX_VALUE;
        int maxZ = Integer.MIN_VALUE;
        String curr = "";
        for (String s : data) {
            int x = 0;
            int y = 0;
            int z = 0;
            for (char c : s.toCharArray()) {
                curr+=c+"";
                if (c == 'w' || c == 'e') {
                    //do stuff with x and y
                    if (curr.equals("ne")) {
                        x++;
                        z--;
                    }
                    else if (curr.equals("e")) {
                        x++;
                        y--;
                    }
                    else if (curr.equals("se")) {
                        y--;
                        z++;
                    }
                    else if (curr.equals("sw")) {
                        x--;
                        z++;
                    }
                    else if (curr.equals("w")) {
                        x--;
                        y++;
                    }
                    else if (curr.equals("nw")) {
                        z--;
                        y++;
                    }
                    curr = "";
                }
            }
            if (x < minX) minX = x;
            if (y < minY) minY = y;
            if (z < minZ) minZ = z;
            if (x > maxX) maxX = x;
            if (y > maxY) maxY = y;
            if (z > maxZ) maxZ = z;
        }
        System.out.printf("x: (%d, %d), y: (%d, %d), z: (%d, %d)%n", minX, maxX, minY, maxY, minZ, maxZ);
        */
        //x: (-14, 14), y: (-14, 14), z: (-14, 14)
    }

    public int countBlackTiles() {
        String curr = "";
        for (String s : data) {
            int x = 0;
            int y = 0;
            int z = 0;
            for (char c : s.toCharArray()) {
                curr+=c+"";
                if (c == 'w' || c == 'e') {
                    //do stuff with x, y, z
                    if (curr.equals("ne")) {
                        x++;
                        z--;
                    }
                    else if (curr.equals("e")) {
                        x++;
                        y--;
                    }
                    else if (curr.equals("se")) {
                        y--;
                        z++;
                    }
                    else if (curr.equals("sw")) {
                        x--;
                        z++;
                    }
                    else if (curr.equals("w")) {
                        x--;
                        y++;
                    }
                    else if (curr.equals("nw")) {
                        z--;
                        y++;
                    }
                    curr = "";
                }
            }
            tiles.putIfAbsent(x*10000+y*100+z, "white");
            if (tiles.get(x*10000+y*100+z).equals("black")) tiles.put(x*10000+y*100+z, "white");
            else tiles.put(x*10000+y*100+z, "black");
        }

        int count = 0;
        for (int i : tiles.keySet()) {
            if (tiles.get(i).equals("black")) count++;
        }
        return count;
    }
}
