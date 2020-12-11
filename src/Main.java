import days.Day10;

import java.io.FileNotFoundException;

public class Main {

    public static void main(String[] args) {
        try {
            Day10 temp = new Day10("data/day10/input.txt");
            int three = temp.findGaps(3);
            int one = temp.findGaps(1);
            System.out.printf("%d x %d = %d\n", one, three, one*three);
            System.out.println(temp.findWays(3L));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
