
import day6.Day6;

import java.io.FileNotFoundException;

public class Main {

    public static void main(String[] args) {
        try {
            Day6 temp = new Day6("data/day6/input.txt");
            System.out.println(temp.totalYes());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
