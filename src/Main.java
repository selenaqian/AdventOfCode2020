import days.Day10;

import java.io.FileNotFoundException;

public class Main {

    public static void main(String[] args) {
        try {
            Day10 temp = new Day10("data/day10/input.txt");
            int three = temp.findGaps(3);
            int one = temp.findGaps(1);
            System.out.printf("%d x %d = %d", one, three, one*three);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
