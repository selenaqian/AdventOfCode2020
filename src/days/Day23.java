package days;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Day23 {
    private final int HIGHEST = 9;
    private final List<Boolean> ALL_FALSE = Arrays.asList(false, false, false, false, false, false, false, false, false);

    private List<Integer> values;
    private List<Boolean> pickedUp;
    private int currentIndex;
    private int current;
    private int destinationIndex;
    private int destination;

    public Day23(String input) {
        values = new ArrayList<>();
        pickedUp = new ArrayList<>();
        currentIndex = 0;

        for (char c : input.toCharArray()) {
            values.add(Integer.parseInt(c + ""));
            pickedUp.add(false);
        }
        current = values.get(currentIndex);
    }

    public void playGame(int n) {
        for (int i = 0; i < n; i++) {
            playRound();
        }
        System.out.println(values);
    }

    private void playRound() {
//        System.out.println(values);
        for (int i = 1; i < 4; i++) {
            if (currentIndex+i < pickedUp.size()) {
                pickedUp.add(currentIndex+i, true);
                pickedUp.remove(currentIndex+i+1);
            }
            else {
                pickedUp.add(currentIndex+i-9, true);
                pickedUp.remove(currentIndex+i-8);
            }
        }
//        System.out.println(pickedUp);
        destination = findDestination(current);
//        System.out.println(current);
//        System.out.println(destination);

        List<Integer> toMove = new ArrayList<>();
        for (int i = 1; i < 4; i++) {
            if (currentIndex+i < values.size()) toMove.add(values.get(currentIndex+i));
            else toMove.add(values.get(currentIndex+i-values.size()));
        }
        values.removeAll(toMove);
        destinationIndex = values.indexOf(destination);
        values.addAll(destinationIndex+1, toMove);

        pickedUp = new ArrayList<>(ALL_FALSE);
        currentIndex = values.indexOf(current);
        if (currentIndex+1 > 8) {
            currentIndex-=9;
        }
        currentIndex++;
        current = values.get(currentIndex);
    }

    private int findDestination(int n) {
        n--;
        if (n < 1) n = HIGHEST;
        while (pickedUp.get(values.indexOf(n))) {
            n--;
            if (n < 1) n = HIGHEST;
        }
        return n;
    }
}
