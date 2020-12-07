package days;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

public class Day1 extends Day {
    List<Integer> values;

    public Day1(String filename) throws FileNotFoundException {
        super(filename);
        values = new ArrayList<>();
        for (String s : data) {
            values.add(Integer.parseInt(s));
        }
        for (int i : values) {
            System.out.println(i);
            getEntries(2020-i);
        }
    }

    private void getEntries(int sum) {
        for (int i : values) {
            if (values.contains(sum-i)) {
                System.out.printf("%d (%d), %d (%d)\n", i, values.indexOf(i), sum-i, values.indexOf(sum-i));
            }
        }
    }
}
