package day6;

import read.SimpleFileReader;

import java.io.FileNotFoundException;
import java.util.*;

public class Day6 {
    List<String> data;

    public Day6(String filename) throws FileNotFoundException {
        SimpleFileReader fr = new SimpleFileReader(filename);
        data = fr.getData();
    }

    public int totalYes() {
        int sum = 0;
        Set<Character> yes = new HashSet<>(Arrays.asList('a','b','c','d','e','f','g','h','i','j','k','l','m','n','o','p',
                'q','r','s','t','u','v','w','x','y','z'));
        for (int i = 0; i < data.size(); i++) {
            System.out.println(data.get(i));
            Set<Character> temp = new HashSet<>();
            if (!data.get(i).equals("")) {
                for (char c : data.get(i).toCharArray()) {
                    temp.add(c);
                }
                yes.retainAll(temp);
            }
            if (data.get(i).equals("") || i == data.size()-1) {
                System.out.println(yes);
                sum+=yes.size();
                yes = new HashSet<>(Arrays.asList('a','b','c','d','e','f','g','h','i','j','k','l','m','n','o','p',
                        'q','r','s','t','u','v','w','x','y','z'));
            }
        }
        return sum;
    }
}
