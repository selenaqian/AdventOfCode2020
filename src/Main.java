import days.Day20;

import java.io.FileNotFoundException;

public class Main {

    public static void main(String[] args) {
        try {
            Day20 temp = new Day20("data/day20/input.txt");
            temp.findCorners();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
