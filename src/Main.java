import days.Day11;

import java.io.FileNotFoundException;

public class Main {

    public static void main(String[] args) {
        try {
            Day11 temp = new Day11("data/day11/input.txt");
            System.out.println(temp.occupiedSeats());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
