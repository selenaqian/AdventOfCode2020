package days;

import java.io.FileNotFoundException;

public class Day11Part2 extends Day11 {

    public Day11Part2(String filename) throws FileNotFoundException {
        super(filename);
    }

    @Override
    int calculateNeighbors(int i, int j) {
        int count = 0;
        int[] directions = new int[]{-1, 0, 1};
        for (int x : directions) {
            for (int y : directions) {
                if (x!=0 || y!=0) {
                    int tempI=i+x;
                    int tempJ=j+y;
                    while(inBounds(tempI, tempJ)) {
                        if (seats[tempI][tempJ] == 'L') break;
                        if (seats[tempI][tempJ] == '#') {
                            count++;
                            break;
                        }
                        tempI+=x;
                        tempJ+=y;
                    }
                }
            }
        }
        return count;
    }

    @Override
    char updateSeat(int i, int j, int neighbors) {
        char current = seats[i][j];
        if (current == 'L' && neighbors==0) current='#';
        else if (current=='#' && neighbors >= 5) current = 'L';
        return current;
    }
}
