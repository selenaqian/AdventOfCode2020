package day5;

import read.SimpleFileReader;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Day5 {
    List<String> data;
    List<Integer> ids;

    public Day5(String filename) throws FileNotFoundException {
        SimpleFileReader fr = new SimpleFileReader(filename);
        data = fr.getData();
        ids = new ArrayList<>();
    }

    public int maxId() {
        int max = 0;
        for (String s : data) {
            int id = processSeat(s);
            if (id > max) max = id;
        }
        return max;
    }

    public int yourId() {
        for (String s : data) {
            ids.add(processSeat(s));
        }
        Collections.sort(ids);
        int prev = ids.get(0);
        for (int i : ids) {
            if (i != prev) return prev;
            prev++;
        }
        return prev;
    }

    private int processSeat(String seat) {
        int row, col;
        int rowMin = 0;
        int rowMax = 127;
        int colMin = 0;
        int colMax = 7;
        for (int i = 0; i < 7; i++) {
            if (seat.charAt(i) == 'F') {
                rowMax = (rowMax - rowMin)/2 + rowMin;
            }
            if (seat.charAt(i) == 'B') {
                rowMin = (rowMax - rowMin)/2 + rowMin + 1;
            }
            System.out.println(rowMin + ", " + rowMax);
        }
        row = rowMax;
        for (int i = 7; i < seat.length(); i++) {
            if (seat.charAt(i) == 'L') {
                colMax = (colMax - colMin)/2 + colMin;
            }
            if (seat.charAt(i) == 'R') {
                colMin = (colMax - colMin)/2 + colMin + 1;
            }
            System.out.println(colMin + ", " + colMax);
        }
        col = colMax;
        System.out.printf("row: %d, col: %d\n", row, col);
        return row*8 + col;
    }
}
