package days;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

public class Day13 extends Day{
    int departTime;
    List<Integer> buses;
    String[] busIds;

    public Day13(String filename) throws FileNotFoundException {
        super(filename);
        departTime = Integer.parseInt(data.get(0));
        busIds = data.get(1).split(",");
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

    public long findDeparture() {
        long offset = 0L;
        List<Long> offsets = new ArrayList<>();
        for (String s : busIds) {
            if (!s.equals("x")) {
                offsets.add(offset);
            }
            offset++;
        }
        System.out.println(buses);
        System.out.println(offsets);

        long time = buses.get(0);
        boolean changed = true;
        while (changed) {
            for (int i=1; i < buses.size(); i++) {
                if (Long.compare(time%buses.get(i), buses.get(i)-offsets.get(i))==0) {
                    changed = false;
                }
                else {
                    changed = true;
                    break;
                }
            }
            if (changed) time+=buses.get(0);
        }
        return time;
    }

}
