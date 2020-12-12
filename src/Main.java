import days.Day12;

import java.io.FileNotFoundException;

public class Main {

    public static void main(String[] args) {
        try {
            Day12 temp = new Day12("data/day12/input.txt");
            temp.moveWaypoint();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
