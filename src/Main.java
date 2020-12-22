import days.Day21;

import java.io.FileNotFoundException;

public class Main {

    public static void main(String[] args) {
        try {
            Day21 temp = new Day21("data/day21/input.txt");
            System.out.println(temp.notAllergens());
            System.out.println(temp.allergens());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
