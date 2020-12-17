package days;

import java.io.FileNotFoundException;
import java.util.*;

public class Day16 extends Day {
    List<Integer> lows;
    List<Integer> highs;
    List<String> categories;
    List<String> validTickets;
    int[] yourTicket;

    public Day16(String filename) throws FileNotFoundException {
        super(filename);
        lows = new ArrayList<>();
        highs = new ArrayList<>();
        categories = new ArrayList<>();
        validTickets = new ArrayList<>();

        processCategories();
    }

    private void processCategories() {
        boolean isYourTicket = false;
        for (String s : data) {
            if (s.equals("")) continue;
            if (s.equals("your ticket:")) {
                isYourTicket = true;
                continue;
            }
            if (isYourTicket) {
                String[] yourValues = s.split(",");
                yourTicket = new int[yourValues.length];
                for (int i = 0; i < yourValues.length; i++) {
                    yourTicket[i] = Integer.parseInt(yourValues[i]);
                }
                break;
            }
            String[] values = (s.split(": "))[1].split(" or ");
            categories.add(s.split(":")[0]);
            for (String a : values) {
                String[] nums = a.split("-");
                int low = Integer.parseInt(nums[0]);
                int high = Integer.parseInt(nums[1]);
                lows.add(low);
                highs.add(high);
            }
        }
    }

    public int errorRate() {
        int sum = 0;
        boolean ticket = false;
        for (String s : data) {
            if (ticket) {
                boolean valid = true;
                String[] values = s.split(",");
                for (String n : values) {
                    int current = Integer.parseInt(n);
                    boolean inRange = false;
                    for (int i = 0; i < lows.size(); i++) {
                        if (current >= lows.get(i) && current <= highs.get(i)) {
                            inRange = true;
                            break;
                        }
                    }
                    if (!inRange) {
                        valid = false;
                        sum+=current;
                    }
                }
                if (valid) validTickets.add(s);
            }
            if (s.contains("nearby tickets:")) ticket = true;
        }
        return sum;
    }

    public long departureValues() {
        long total = 1L;
        Map<String, Integer> categoryPositions = determineCategories();
        for (String s : categoryPositions.keySet()) {
            if (s.contains("departure")) {
                System.out.println(total);
                total*=(yourTicket[categoryPositions.get(s)]);
            }
        }
        return total;
    }

    private Map<String, Integer> determineCategories() {
        Map<Integer, Boolean[]> possibleCategories = new HashMap<>();
        for (String s : validTickets) {
            String[] values = s.split(",");
            for (int valuePosition = 0; valuePosition < values.length; valuePosition++) {
                int ticketValue = Integer.parseInt(values[valuePosition]);
                for (int categoryPosition = 0; categoryPosition < categories.size(); categoryPosition++) {
                    possibleCategories.putIfAbsent(valuePosition, new Boolean[categories.size()]);
                    if (inRange(ticketValue, categoryPosition)) {
                        if (possibleCategories.get(valuePosition)[categoryPosition] == null) {
                            possibleCategories.get(valuePosition)[categoryPosition] = true;
                        }
                    }
                    else possibleCategories.get(valuePosition)[categoryPosition] = false;
                }
            }
        }
        Map<Integer, Integer> validCategoriesCount = new HashMap<>(); //maps number of valid categories to the position with that many
        for (int k : possibleCategories.keySet()) {
            int trues = 0;
            for (boolean b : possibleCategories.get(k)) {
                if (b) trues++;
            }
            validCategoriesCount.putIfAbsent(trues, k);
        }
        String[] allCategories = new String[categories.size()];
        categories.toArray(allCategories);

        Map<String, Integer> categoryToPosition = new HashMap<>();
        for (int i = 1; i < validCategoriesCount.keySet().size()+1; i++) {
            Boolean[] temp = possibleCategories.get(validCategoriesCount.get(i));
            for (int j = 0; j < temp.length; j++) {
                if (temp[j] == true) {
                    if (!allCategories[j].equals("")) {
                        categoryToPosition.put(allCategories[j], validCategoriesCount.get(i));
                        allCategories[j] = "";
                    }
                }
            }
        }
        return categoryToPosition;
    }

    private boolean inRange(int value, int categoryPosition) {
        if (value >= lows.get(categoryPosition*2) && value <= highs.get(categoryPosition*2)) return true;
        if (value >= lows.get(categoryPosition*2+1) && value <= highs.get(categoryPosition*2+1)) return true;
        return false;
    }
}
