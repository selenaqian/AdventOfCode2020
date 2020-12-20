package days;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Day20 extends Day {
    Map<String, List<String>> tileNums;
    Map<String, List<String>> allEdges;

    public Day20(String filename) throws FileNotFoundException {
        super(filename);
        tileNums = new HashMap<>();
        allEdges = new HashMap<>();

        List<String> currentTile = new ArrayList<>();
        String tileNumber = "";
        for (String s : data) {
            if (s.contains(":")) {
                tileNumber = s.replace(":", "").replace("Tile ", "");
            }
            else if (s.equals("")) {
                List<String> edges = new ArrayList<>();
                edges.add(currentTile.get(0));
                edges.add(currentTile.get(currentTile.size()-1));
                String right = "";
                String left = "";
                for (String line : currentTile) {
                    right = right + line.substring(line.length()-1);
                    left = left + line.substring(0,1);
                }
                edges.add(right);
                edges.add(left);
                tileNums.put(tileNumber, edges);
            }
            else {
                currentTile.add(s);
            }
        }

        for (String tile : tileNums.keySet()) {
            for (String edge : tileNums.get(tile)) {
                allEdges.putIfAbsent(edge, new ArrayList<>());
                allEdges.get(edge).add(tile);
            }
        }

        System.out.println(tileNums.get("3209"));
        System.out.println(allEdges.get(".##.####.."));
    }
}
