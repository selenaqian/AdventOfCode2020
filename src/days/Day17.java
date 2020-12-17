package days;

import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Day17 extends Day {
    Map<Integer, Character> cubes;
    int dimensions;

    public Day17(String filename, int d) throws FileNotFoundException {
        super(filename);
        cubes = new HashMap<>();
        dimensions = d;

        for (int x = 0; x < data.size(); x++) {
            String s = data.get(x);
            for (int y = 0; y < s.length(); y++) {
                char c = s.charAt(y);
                if (dimensions == 4) {
                    cubes.putIfAbsent(makeId(new int[]{0, 0, y, x}), c);
                }
                else cubes.putIfAbsent(makeId(new int[]{0, y, x}), c);
            }
        }
    }

    public int checkActive(int cycles) {
        for (int i = 0; i < cycles; i++) {
            for (int key : cubes.keySet()) {
                System.out.println(key + ": " + cubes.get(key));
            }
            System.out.println();
            updateState();
        }
        int count = 0;
        for (char c : cubes.values()) {
            if (c == '#') count++;
        }
        return count;
    }

    private int calculateNeighbors(int id) {
        int count = 0;
        int[] directions = new int[]{-1, 0, 1};
        for (int i : directions) {
            for (int j : directions) {
                for (int k : directions) {
                    if (dimensions == 4) {
                        for (int w : directions) {
                            int neighborId = id + i*1000000 + j*10000 + k*100 + w;
                            if ((i!=0 || j!=0 || k!=0 || w!=0) && cubes.get(neighborId)!=null && cubes.get(neighborId)=='#') count++;
                        }
                    }
                    else {
                        int neighborId = id + i*10000 + j*100 + k;
                        if ((i!=0 || j!=0 || k!=0) && cubes.get(neighborId)!=null && cubes.get(neighborId)=='#') count++;
                    }
                }
            }
        }
        return count;
    }

    private void updateState() {
        Map<Integer, Character> nextCubes = new HashMap<>();
        Set<Integer> idsToCheck = new HashSet<>();
        for (int i : cubes.keySet()) {
            idsToCheck.addAll(allTouchingCubes(i));
        }
        for (int i : idsToCheck) {
            int neighbors = calculateNeighbors(i);
            if (cubes.get(i) != null && cubes.get(i) == '#') {
                if (neighbors==2 || neighbors==3) nextCubes.put(i, '#');
                else nextCubes.put(i, '.');
            }
            else if (neighbors==3) nextCubes.put(i, '#');
        }
        cubes = nextCubes;
    }

    private Set allTouchingCubes(int id) {
        Set<Integer> allCubes = new HashSet<>();
        int[] directions = new int[]{-1, 0, 1};
        for (int i : directions) {
            for (int j : directions) {
                for (int k : directions) {
                    if (dimensions == 4) {
                        for (int w : directions) {
                            allCubes.add(id + i*1000000 + j*10000 + k*100 + w);
                        }
                    }
                    else allCubes.add(id + i*10000 + j*100 + k);
                }
            }
        }
        return allCubes;
    }

    private int makeId(int[] coordinates) {
        int id = 0;
        int multiplier = 1;
        for (int i : coordinates) {
            id+=i*multiplier;
            multiplier*=100;
        }
        return id;
    }
}
