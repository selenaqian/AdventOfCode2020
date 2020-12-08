package days;

import java.io.FileNotFoundException;
import java.util.HashSet;
import java.util.Set;

public class Day8 extends Day {
    private String[] operations;
    private int[] values;
    private Set<Integer> visited;

    public Day8(String filename) throws FileNotFoundException {
        super(filename);
        operations = new String[data.size()];
        values = new int[data.size()];
        visited = new HashSet<>();
        for (int i = 0; i < data.size(); i++) {
            processLine(data.get(i), i);
        }
    }

    public int findAccumulatorValue() {
        int acc = 0;
        int insIndex = 0;
        while (!visited.contains(insIndex)) {
            visited.add(insIndex);
            String ins = operations[insIndex];
            int val = values[insIndex];
            if (ins.equals("nop")) {
                insIndex++;
            }
            else if (ins.equals("jmp")) {
                insIndex+=val;
            }
            else if (ins.equals("acc")) {
                insIndex++;
                acc+=val;
            }
        }
        return acc;
    }

    private void processLine(String line, int index) {
        String[] splitLine = line.split(" ");
        operations[index] = splitLine[0];
        values[index] = Integer.parseInt(splitLine[1].replace("+", ""));
    }

}
