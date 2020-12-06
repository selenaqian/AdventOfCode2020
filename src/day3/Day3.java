package day3;

import read.SimpleFileReader;

import java.io.FileNotFoundException;
import java.util.List;

public class Day3 {
    List<String> data;

    public Day3(String filename) throws FileNotFoundException {
        SimpleFileReader fr = new SimpleFileReader(filename);
        data = fr.getData();
    }

    public int countTrees(int xSlope, int ySlope) {
        int count = 0;
        for (int i = ySlope; i < data.size(); i+=ySlope) {
            if (isTree(i, xSlope, ySlope)) count++;
        }
        return count;
    }

    private boolean isTree(int lineIndex, int xSlope, int ySlope) {
        String line = data.get(lineIndex);
        int element = lineIndex*xSlope/ySlope;
        element = element%line.length();
        if (line.charAt(element) == '#') return true;
        return false;
    }
}
