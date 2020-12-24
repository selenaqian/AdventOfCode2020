package days;

import java.io.FileNotFoundException;

public class Day24 extends Day {

    public Day24(String filename) throws FileNotFoundException {
        super(filename);

        int minX = Integer.MAX_VALUE;
        int maxX = Integer.MIN_VALUE;
        int minY = Integer.MAX_VALUE;
        int maxY = Integer.MIN_VALUE;
        int x = 0;
        int y = 0;
        String curr = "";
        for (String s : data) {
            for (char c : s.toCharArray()) {
                curr+=c+"";
                if (c == 'w' || c == 'e') {
                    //do stuff with x and y
                    if (curr.equals("ne")) {
                        y--;
                    }
                    else if (curr.equals("e")) {
                        x++;
                    }
                    else if (curr.equals("se")) {
                        y++;
                    }
                    else if (curr.equals("sw")) {
                        x--;
                        y++;
                    }
                    else if (curr.equals("w")) {
                        x--;
                    }
                    else if (curr.equals("nw")) {
                        x--;
                        y--;
                    }
                    curr = "";
                }
            }
            if (x < minX) minX = x;
            if (y < minY) minY = y;
            if (x > maxX) maxX = x;
            if (y > maxY) maxY = y;
        }
        System.out.printf("%d %d %d %d%n", minX, minY, maxX, maxY);
    }
}
