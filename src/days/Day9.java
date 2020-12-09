package days;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

public class Day9 extends Day {
    List<Long> last25;

    public Day9(String filename) throws FileNotFoundException {
        super(filename);
        last25 = new ArrayList<>();
        for(int i = 0; i < 25; i++) {
            last25.add(Long.parseLong(data.get(i)));
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

    private boolean twoSum(Long sum) {
        for (int i = 0; i < 25; i++) {
            for (int j = i+1; j < 25; j++) {
                if (last25.get(i) + last25.get(j) == sum) return true;
            }
        }
        return false;
    }
}
