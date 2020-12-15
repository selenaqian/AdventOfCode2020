import days.Day14;

import java.io.FileNotFoundException;

public class Main {

    public static void main(String[] args) {
        try {
            Day14 temp = new Day14("data/day14/test2.txt");
            System.out.println(temp.writeMemory2());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
