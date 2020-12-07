import days.Day7;

import java.io.FileNotFoundException;

public class Main {

    public static void main(String[] args) {
        try {
            Day7 temp = new Day7("data/day7/input.txt");
            System.out.println(temp.findNumberInside("shiny gold"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
