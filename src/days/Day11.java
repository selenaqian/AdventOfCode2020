package days;

import java.io.FileNotFoundException;

public abstract class Day11 extends Day {
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

    protected boolean inBounds(int i, int j) {
        return i > -1 && j > -1 && i < seats.length && j < seats[0].length;
    }

    public int occupiedSeats(){
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

    protected boolean updateState() {
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
    abstract int calculateNeighbors(int i, int j);
    abstract char updateSeat(int i, int j, int neighbors);
}
