package day6;

import read.SimpleFileReader;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Day6 {
    List<String> data;

    public Day6(String filename) throws FileNotFoundException {
        SimpleFileReader fr = new SimpleFileReader(filename);
        data = fr.getData();
    }

    public int totalYes() {
        int sum = 0;
        Set<Character> yes = new HashSet<>();
        for (int i = 0; i < data.size(); i++) {
            System.out.println(data.get(i));
            for (char c : data.get(i).toCharArray()) {
                yes.add(c);
            }
            if (data.get(i).equals("") || i == data.size()-1) {
                System.out.println(yes);
                sum+=yes.size();
                yes = new HashSet<>();
            }
        }
        return sum;
    }
}
