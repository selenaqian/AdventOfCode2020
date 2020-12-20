import days.Day19;

import java.io.FileNotFoundException;

public class Main {

    public static void main(String[] args) {
        try {
            Day19 temp = new Day19("data/day19/test2.txt");
            System.out.println(temp.checkValidMessages());
            System.out.println(temp.checkValid2());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
