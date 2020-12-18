import days.Day18;

import java.io.FileNotFoundException;

public class Main {

    public static void main(String[] args) {
        try {
            Day18 temp = new Day18("data/day18/input.txt");
            System.out.println(temp.getTotal());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
