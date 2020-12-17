import days.Day16;

import java.io.FileNotFoundException;

public class Main {

    public static void main(String[] args) {
        try {
            Day16 temp = new Day16("data/day16/input.txt");
            System.out.println(temp.errorRate());
            System.out.println(temp.departureValues());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
