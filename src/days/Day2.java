package days;

import java.io.FileNotFoundException;

public class Day2 extends Day {
    int correct;

    public Day2(String filename) throws FileNotFoundException {
        super(filename);
        correct = 0;
        for (String s : data) {
            count(s);
        }
        System.out.println(correct);
    }

    private void count(String line) {
        String[] separated = line.split("-|: | ");
        int min = Integer.parseInt(separated[0]);
        int max = Integer.parseInt(separated[1]);
        char letter = separated[2].charAt(0);
        String password = separated[3];
        if (isCorrect(min, max, letter, password)) correct++;
    }

    private boolean isCorrect(int min, int max, char letter, String password) {
        char[] passArray = password.toCharArray();
        return passArray[min-1] == letter && passArray[max-1] != letter || passArray[min-1] != letter && passArray[max-1] == letter;
    }
}
