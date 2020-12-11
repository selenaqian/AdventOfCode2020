package days;

import java.io.FileNotFoundException;

public class Day11Part1 extends Day11 {

    public Day11Part1(String filename) throws FileNotFoundException {
        super(filename);
    }

    @Override
    int calculateNeighbors(int i, int j) {
        int count = 0;
        int[] directions = new int[]{-1, 0, 1};
        for (int x : directions) {
            for (int y : directions) {
                if ((x!=0 || y!=0) && inBounds(i+x, j+y) && seats[i+x][j+y]=='#') count++;
            }
        }
        return count;
    }

    @Override
    char updateSeat(int i, int j, int neighbors) {
        char current = seats[i][j];
        if (current == 'L' && neighbors==0) current='#';
        else if (current=='#' && neighbors >= 4) current = 'L';
        return current;
    }
}
