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
            values[i] = processLine(data.get(i));
        }
        long total = 0L;
        for (long n : values) {
            total+=n;
        }
        return total;
    }

    private long processLine(String line) {
        String rpn = shuntingYard(line);
        Stack<Long> values = new Stack<>();
        for (String s : rpn.split(" ")) {
            if (s.matches("[-0-9]+")) {
                values.push(Long.parseLong(s));
                //System.out.println("number: " + s);
            }
            else if (s.equals("+")) {
                long a = values.pop();
                long b = values.pop();
                //System.out.println("adding: " + (a+b));
                values.add(a+b);
            }
            else if (s.equals("*")) {
                long a = values.pop();
                long b = values.pop();
                //System.out.println("mult: " + (a*b));
                values.add(a*b);
            }
        }

        long t = values.pop();
        System.out.println(t);
        return t;
    }

    private String shuntingYard(String original) {
        StringBuilder postFix = new StringBuilder();
        String[] parts = original.split(" ");
        Stack<String> operators = new Stack<>();
        for (int j = parts.length-1; j > -1; j--) {
            String s = parts[j];
            if (s.contains("+") || s.contains("*")) operators.push(s);
            else if (s.contains(")")) {
                while (s.contains(")")) {
                    operators.push(")");
                    int i = s.indexOf(")");
                    s = s.substring(0, i) + s.substring(i+1);
                }
                postFix.append(s + " ");
            }
            else if (s.contains("(")) {
                //System.out.println(operators);
                postFix.append(s.replace("(", "") + " ");
                String op = operators.pop();
                while (s.contains("(")) {
                    while (!op.equals(")")) {
                        postFix.append(op + " ");
                        if (operators.isEmpty()) {
                            op = ")";
                        }
                        else op = operators.pop();
                    }
                    int i = s.indexOf("(");
                    s = s.substring(0, i) + s.substring(i+1);
                    if (!operators.isEmpty() && s.contains(")")) op = operators.pop();
                    //System.out.println(s + " end paren");
                }
            }
            else postFix.append(s + " ");
        }
        while (!operators.isEmpty()) {
            postFix.append(operators.pop() + " ");
        }

        System.out.println(postFix.toString().trim());
        return postFix.toString().trim();
    }
}
