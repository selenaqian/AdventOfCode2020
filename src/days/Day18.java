package days;

import java.io.FileNotFoundException;
import java.util.Stack;

public class Day18 extends Day {
    long[] values;

    public Day18(String filename) throws FileNotFoundException {
        super(filename);
        values = new long[data.size()];
    }

    public long getTotal() {
        for (int i = 0; i < data.size(); i++) {
            values[i] = Long.parseLong(processLine(data.get(i)));
        }
        long total = 0L;
        for (long n : values) {
            total+=n;
        }
        return total;
    }

    private String processLine(String line) {
        while (line.contains(")")) {
            int close = line.indexOf(")");
            int open = line.lastIndexOf("(", close);
            line = line.substring(0, open) + calculate(line.substring(open+1, close)) + line.substring(close+1);
        }
        System.out.println(calculate(line));
        return calculate(line);
    }

    private String calculate(String expression) {
        //expression should not have parentheses in it
        String[] parts = expression.split(" ");
        long total = Long.parseLong(parts[0]);
        char op = ' ';
        for (int i = 1; i < parts.length; i++) {
            if (parts[i].equals("+")) op = '+';
            else if (parts[i].equals("*")) op = '*';
            else {
                if (op == '+') total+=Long.parseLong(parts[i]);
                else if (op == '*') total*=Long.parseLong(parts[i]);
            }
        }
        return total + "";
    }
}
