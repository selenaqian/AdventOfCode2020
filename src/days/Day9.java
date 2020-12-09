package days;

import javafx.scene.layout.Pane;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

public class Day9 extends Day {
    List<Long> last25;
    List<Long> all;

    public Day9(String filename) throws FileNotFoundException {
        super(filename);
        last25 = new ArrayList<>();
        all = new ArrayList<>();
        for(int i = 0; i < 25; i++) {
            last25.add(Long.parseLong(data.get(i)));
        }
        for (String s : data) {
            all.add(Long.parseLong(s));
        }
    }

    public Long findNumber() {
        Long weakness = 0L;
        for (int i = 25; i < data.size(); i++) {
            Long temp = Long.parseLong(data.get(i));
            if (!twoSum(temp)) weakness = temp;
            last25.add(temp);
            last25.remove(0);
        }
        return weakness;
    }

    public void findRangeSum(Long sum) {
        List<Long> sumsUp = new ArrayList<>();
        int i = 0;
        int j = 0;
        Long current = all.get(0);
        sumsUp.add(all.get(0));
        while (j < all.size() - 1) {
            j++;
            if (current.equals(sum)) {
                break;
            }
            if (!all.get(j).equals(sum)) {
                current += all.get(j);
                sumsUp.add(all.get(j));
            }
            while (Long.compare(current, sum) > 0) {
                current -= all.get(i);
                i++;
                sumsUp.remove(0);
            }
        }
        Long max = 0L;
        Long min = Long.MAX_VALUE;

        for (Long l : sumsUp) {
            if (Long.compare(max, l) < 0) max = l;
            if (Long.compare(min, l) > 0) min = l;
            System.out.println(l);
        }
        System.out.println("max");
        System.out.println(max);
        System.out.println("min");
        System.out.println(min);
        System.out.println(max + min);
    }

    private boolean twoSum(Long sum) {
        for (int i = 0; i < 25; i++) {
            for (int j = i+1; j < 25; j++) {
                if (last25.get(i) + last25.get(j) == sum) return true;
            }
        }
        return false;
    }
}
