import days.Day24;

import java.io.FileNotFoundException;

public class Main {

    public static void main(String[] args) {
        try {
            Day24 temp = new Day24("data/day24/input.txt");
            System.out.println(temp.countBlackTiles());
            System.out.println(temp.countBlackTiles(100));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
