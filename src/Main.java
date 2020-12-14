import days.Day14;

import java.io.FileNotFoundException;

public class Main {

    public static void main(String[] args) {
        try {
            Day14 temp = new Day14("data/day14/input.txt");
            temp.writeMemory();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
