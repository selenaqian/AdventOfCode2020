package days;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

public class Day13 extends Day{
    int departTime;
    List<Integer> buses;

    public Day13(String filename) throws FileNotFoundException {
        super(filename);
        departTime = Integer.parseInt(data.get(0));
        String[] busIds = data.get(1).split(",");
        buses = new ArrayList<>();
        for (String s : busIds) {
            if (!s.equals("x")) buses.add(Integer.parseInt(s));
        }
    }

    public int waitTime() {
        int bus = buses.get(0);
        int minutes = Integer.MAX_VALUE;

        for (int id : buses) {
            int wait = id - departTime%id;
            if (wait < minutes) {
                minutes = wait;
                bus = id;
            }
        }
        System.out.printf("bus %d, wait %d\n", bus, minutes);
        return bus*minutes;
    }
}
