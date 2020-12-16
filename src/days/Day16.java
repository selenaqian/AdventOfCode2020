package days;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

public class Day16 extends Day {
    List<Integer> lows;
    List<Integer> highs;

    public Day16(String filename) throws FileNotFoundException {
        super(filename);
        lows = new ArrayList<>();
        highs = new ArrayList<>();

        for (String s : data) {
            if (s.equals("")) break;
            String[] values = (s.split(": "))[1].split(" or ");
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
                    if (!inRange) sum+=current;
                }
            }
            if (s.contains("nearby tickets:")) ticket = true;
        }
        return sum;
    }
}
