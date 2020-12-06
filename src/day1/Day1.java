package day1;

import read.SimpleFileReader;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

public class Day1 {
    List<Integer> values;
    SimpleFileReader fr;

    public Day1(String filename) throws FileNotFoundException {
        values = new ArrayList<>();
        fr = new SimpleFileReader(filename);
        for (String s : fr.getData()) {
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
