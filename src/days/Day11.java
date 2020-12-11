package days;

import java.io.FileNotFoundException;

public class Day11 extends Day {
    char[][] seats;

    public Day11(String filename) throws FileNotFoundException {
        super(filename);
        seats = new char[data.size()][data.get(0).length()];
        for (int i = 0; i < data.size(); i++) {
            String s = data.get(i);
            for (int j = 0; j < s.length(); j++) {
                seats[i][j] = s.charAt(j);
            }
        }
    }

    public int occupiedSeats() {
        int count = 0;
        boolean changed = true;
        while(changed) {
            changed = updateState();
        }
        for (char[] arr : seats) {
            for (char c : arr) {
                if (c == '#') count++;
            }
        }
        return count;
    }

    private boolean updateState() {
        boolean changed = false;
        char[][] newSeats = new char[data.size()][data.get(0).length()];
        for (int i = 0; i < seats.length; i++) {
            for (int j = 0; j < seats[0].length; j++) {
                newSeats[i][j] = updateSeat(i, j, calculateNeighbors(i, j));
                if (newSeats[i][j] != seats[i][j]) changed = true;
            }
        }
        if (changed) seats = newSeats;
        return changed;
    }

    private int calculateNeighbors(int i, int j) {
        int count = 0;
        int[] directions = new int[]{-1, 0, 1};
        for (int x : directions) {
            for (int y : directions) {
                if ((x!=0 || y!=0) && inBounds(i+x, j+y) && seats[i+x][j+y]=='#') count++;
            }
        }
        return count;
    }

    private char updateSeat(int i, int j, int neighbors) {
        char current = seats[i][j];
        if (current == 'L' && neighbors==0) current='#';
        else if (current=='#' && neighbors >= 4) current = 'L';
        return current;
    }

    private boolean inBounds(int i, int j) {
        return i > -1 && j > -1 && i < seats.length && j < seats[0].length;
    }
}
