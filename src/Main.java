import days.Day9;

import java.io.FileNotFoundException;

public class Main {

    public static void main(String[] args) {
        try {
            Day9 temp = new Day9("data/day9/input.txt");
            System.out.println(temp.findNumber());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
