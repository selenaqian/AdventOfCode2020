package days;

import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

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
            tiles.putIfAbsent(x*1000000+y*1000+z, "white");
            if (tiles.get(x*1000000+y*1000+z).equals("black")) tiles.put(x*1000000+y*1000+z, "white");
            else tiles.put(x*1000000+y*1000+z, "black");
        }

        int count = 0;
        for (int i : tiles.keySet()) {
            if (tiles.get(i).equals("black")) count++;
        }
        return count;
    }

    public int countBlackTiles(int days) {
        //neighbors are: (1, 0, -1), (1, -1, 0), (0, -1, 1), (-1, 0, 1), (-1, 1, 0), (0, 1, -1)
        int[] xNeighbors = new int[]{1, 1, 0, -1, -1, 0};
        int[] yNeighbors = new int[]{0, -1, -1, 0, 1, 1};
        int[] zNeighbors = new int[]{-1, 0, 1, 1, 0, -1};

        for (int i = 0; i < days; i++) {
            //add all neighbors to tiles - forgot this initially
            Set<Integer> tileNeighbors = new HashSet<>();
            for (int tile : tiles.keySet()) {
                for (int j=0; j < xNeighbors.length; j++) {
                    int x = xNeighbors[j];
                    int y = yNeighbors[j];
                    int z = zNeighbors[j];
                    tileNeighbors.add(tile+x*1000000+y*1000+z);
                }
            }
            for (int tile : tileNeighbors) {
                tiles.putIfAbsent(tile, "white");
            }

            Map<Integer, String> nextTiles = new HashMap<>();
            for (int tile : tiles.keySet()) {
                int neighbors = 0;
                for (int j=0; j < xNeighbors.length; j++) {
                    int x = xNeighbors[j];
                    int y = yNeighbors[j];
                    int z = zNeighbors[j];
                    if (tiles.containsKey(tile+x*1000000+y*1000+z) && tiles.get(tile+x*1000000+y*1000+z).equals("black")) neighbors++;
                }

                //if black tile, flip if 0 neighbors black or more than 2 neighbors black
                //else if white tile flip if exactly 2 neighbors black
                if (tiles.get(tile).equals("black") && (neighbors == 0 || neighbors > 2)) {
                    nextTiles.put(tile, "white");
                }
                else if (tiles.get(tile).equals("white") && neighbors == 2) {
                    nextTiles.put(tile, "black");
                }
                else nextTiles.put(tile, tiles.get(tile));
            }
            tiles = nextTiles;
        }

        int count = 0;
        for (int i : tiles.keySet()) {
            if (tiles.get(i).equals("black")) count++;
        }
        return count;
    }
}
