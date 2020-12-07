package days;

import java.io.FileNotFoundException;
import java.util.*;

public class Day7 extends Day {
    Map<String, Set<String>> canBeIn;

    public Day7(String filename) throws FileNotFoundException {
        super(filename);
        canBeIn = new HashMap<>();
        for (String s : data) {
            processRule(s);
        }
    }

    /**
     * Looks through contains map for what bag colors hold the specified color.
     * @param color specified color to look for.
     * @return number of bag colors that eventually contain a bag of specified color.
     */
    public int holds(String color) {
        Set<String> canContain = new HashSet<>();
        Set<String> canContainTemp = new HashSet<>(Arrays.asList(color));
        while (canContainTemp.size() > 0) {
            Set<String> temp = new HashSet<>();
            for (String s : canContainTemp) {
                if (canBeIn.get(s) != null) {
                    temp.addAll(canBeIn.get(s));
                }
            }
            canContainTemp = temp;
            canContainTemp.removeAll(canContain);
            canContain.addAll(temp);
        }
        //look for things that directly contain color
        //then look for things that contain those colors
        //and so on
        return canContain.size();
    }

    private void processRule(String line) {
        //put info from rule into canBeIn
        //things after contains should have canBeIn
        String[] separated = line.split(" contain ");
        separated[0] = separated[0].replaceAll(" bags", "");
        String[] contained = separated[1].split(",");
        for (String s : contained) {
            s = s.replaceAll("[0-9]", "");
            s = s.replaceAll("bags|bag|\\.", "");
            s = s.trim();
            canBeIn.putIfAbsent(s, new HashSet<>());
            canBeIn.get(s).add(separated[0]);
        }
    }
}
