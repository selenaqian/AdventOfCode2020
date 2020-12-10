package days;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Day10 extends Day {
    List<Integer> values;

    public Day10(String filename) throws FileNotFoundException {
        super(filename);
        values = new ArrayList<>();
        for (String s : data) {
            values.add(Integer.parseInt(s));
        }
        Collections.sort(values);
        values.add(values.get(values.size()-1)+3);
    }

    public int findGaps(int gapSize) {
        int count = 0;
        int curr = values.get(0);
        int prev = 0;
        for (int i = 1; i <= values.size(); i++) {
            if (curr - prev == gapSize) count++;
            prev = curr;
            if (i < values.size()) curr = values.get(i);
        }
        return count;
    }

}
