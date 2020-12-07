package days;

import java.io.FileNotFoundException;
import java.util.*;

public class Day7 extends Day {
    Map<String, Set<String>> canBeIn;
    Map<String, List<String>> contains;
    Map<String, List<Integer>> containsNumber;

    public Day7(String filename) throws FileNotFoundException {
        super(filename);
        canBeIn = new HashMap<>();
        contains = new HashMap<>();
        containsNumber = new HashMap<>();
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
        return canContain.size();
    }

    public int findNumberInside(String color) {
        if(containsNumber.get(color).size() == 0) {
            System.out.println(color + " final");
            return 1;
        }
        int sum = 0;
        for (int i = 0; i < containsNumber.get(color).size(); i++) {
            System.out.println(containsNumber.get(color).get(i) + " " + contains.get(color).get(i));
            sum += containsNumber.get(color).get(i) * findNumberInside(contains.get(color).get(i));
        }
        System.out.println(sum);
        return sum;
    }

    private void processRule(String line) {
        String[] separated = line.split(" contain ");
        separated[0] = separated[0].replaceAll(" bags", "");
        contains.putIfAbsent(separated[0], new ArrayList<>());
        containsNumber.putIfAbsent(separated[0], new ArrayList<>());

        String[] contained = separated[1].split(",");
        for (String s : contained) {
            String findNum = s.replaceAll("[^0-9]", "");
            if (findNum.length() > 0) {
                containsNumber.get(separated[0]).add(Integer.parseInt(findNum));
            }

            s = s.replaceAll("[0-9]", "");
            s = s.replaceAll("bags|bag|\\.", "");
            s = s.trim();
            canBeIn.putIfAbsent(s, new HashSet<>());
            canBeIn.get(s).add(separated[0]);
            contains.get(separated[0]).add(s);
        }
    }
}
