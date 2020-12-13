import days.Day13;

import java.io.FileNotFoundException;

public class Main {

    public static void main(String[] args) {
        try {
            Day13 temp = new Day13("data/day13/test.txt");
            System.out.println(temp.findDeparture());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
