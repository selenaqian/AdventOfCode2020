import days.Day17;

import java.io.FileNotFoundException;

public class Main {

    public static void main(String[] args) {
        try {
            Day17 temp = new Day17("data/day17/input.txt");
            System.out.println(temp.checkActive(6));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
