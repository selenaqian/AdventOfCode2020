import days.Day8;

import java.io.FileNotFoundException;

public class Main {

    public static void main(String[] args) {
        try {
            Day8 temp = new Day8("data/day8/input.txt");
            System.out.println(temp.findAccumulatorValue());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
