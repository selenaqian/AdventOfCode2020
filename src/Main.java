import days.Day22;

import java.io.FileNotFoundException;

public class Main {

    public static void main(String[] args) {
        try {
            Day22 temp = new Day22("data/day22/input.txt");
            System.out.println(temp.getScore());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
