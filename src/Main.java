import days.Day15;

import java.io.FileNotFoundException;

public class Main {

    public static void main(String[] args) {
        try {
            Day15 temp = new Day15("data/day15/input.txt");
            System.out.println(temp.getNumber(30000000));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
