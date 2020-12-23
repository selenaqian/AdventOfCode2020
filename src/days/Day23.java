package days;

public class Day23 {
    private int[] values;
    private int current;

    public Day23(String input, int size) {
        values = new int[size+1];
        current = Integer.parseInt(input.substring(0, 1));

        for (int i = 0; i < input.length() - 1; i++) {
            int index = Integer.parseInt(input.substring(i, i+1));
            int next = Integer.parseInt(input.substring(i+1, i+2));
            values[index] = next; //so values array maps from value stored in index to next value in list
        }

        for (int i = input.length() + 1; i < size; i++) {
            values[i] = i+1;
        }

        if (size == input.length()) values[Integer.parseInt(input.substring(input.length() - 1))] = Integer.parseInt(input.substring(0, 1));
        else if (size > input.length()) {
            values[Integer.parseInt(input.substring(input.length() - 1))] = input.length()+1;
            values[size] = Integer.parseInt(input.substring(0,1));
        }
    }

    public void playGame(int n, int ansType) {
        for (int i = 0; i < n; i++) {
            playRound();
        }

        if (ansType == 1) {
            int i = values[1];
            String answer = "" + values[1];
            while (i != 1) {
                answer+=values[i];
                i=values[i];
            }
            System.out.println(answer);
        }
        else {
            long ans = (long) values[1] * values[values[1]];
            System.out.println(ans);
        }
    }

    private void playRound() {
        int a = values[current];
        int b = values[a];
        int c = values[b];

        int destination = findDestination(current, a, b, c);

        values[current] = values[c];
        values[c] = values[destination];
        values[destination] = a;

        current = values[current];
    }

    private int findDestination(int n, int a, int b, int c) {
        n--;
        if (n < 1) n = values.length - 1;
        while (n == a || n == b || n == c) {
            n--;
            if (n < 1) n = values.length - 1;
        }
        return n;
    }
}
