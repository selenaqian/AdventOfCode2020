package days;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
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

    public Long findWays(Long diff) {
        Long[] ways = new Long[values.size()];
        Arrays.fill(ways, 0L);
        //first one guaranteed 1
        //rest look at previous up to 3 if allowed connection and add those ways
        ways[0] = 1L;
        for (int i = 1; i < values.size(); i++) {
            for (int j = 1; j <= diff; j++) {
                if (i-j > -1 && values.get(i) - values.get(i-j) <= diff) {
                    ways[i] += ways[i-j];
                }
            }
            if (values.get(i) <= diff) ways[i]++;
        }
        return ways[values.size()-1];
    }

}
