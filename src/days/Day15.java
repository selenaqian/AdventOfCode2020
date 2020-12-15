package days;

import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;

public class Day15 extends Day {
    int[] starters;

    public Day15(String filename) throws FileNotFoundException {
        super(filename);
        String[] values = data.get(0).split(",");
        starters = new int[values.length];
        for (int i = 0; i < values.length; i++) {
            starters[i] = Integer.parseInt(values[i]);
        }
    }

    public int getNumber(int index) {
        int i = 0;
        int last = starters[starters.length-1];
        Map<Integer, Integer> previous = new HashMap<>();
        Map<Integer, Integer> secondPrev = new HashMap<>();
        while (i < starters.length) {
            previous.put(starters[i], i);
            i++;
        }
        while (i < index) {
            int current = 0;
            if (secondPrev.containsKey(last)) {
                current = previous.get(last) - secondPrev.get(last);
            }
            if (previous.containsKey(current)) secondPrev.put(current, previous.get(current));
            previous.put(current, i);
            last = current;
            i++;
        }
        return last;
    }
}
