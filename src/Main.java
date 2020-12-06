import day1.Day1;
import day2.Day2;
import day3.Day3;
import day4.Day4;
import day5.Day5;

import java.io.FileNotFoundException;

public class Main {

    public static void main(String[] args) {
        try {
            Day5 temp = new Day5("data/day5/input.txt");
            System.out.println(temp.yourId());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
