package days;

import java.io.FileNotFoundException;

public class Day3 extends Day {

    public Day3(String filename) throws FileNotFoundException {
        super(filename);
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
