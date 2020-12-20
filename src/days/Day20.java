package days;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Day20 extends Day {
    Map<String, List<String>> tileNums;
    Map<String, List<String>> allEdges;
    List<String> corners;

    public Day20(String filename) throws FileNotFoundException {
        super(filename);
        tileNums = new HashMap<>();
        allEdges = new HashMap<>();
        corners = new ArrayList<>();

        List<String> currentTile = new ArrayList<>();
        String tileNumber = "";
        int i = 0;
        for (String s : data) {
            i++;
            if (s.contains(":")) {
                tileNumber = s.replace(":", "").replace("Tile ", "");
            }
            else if (s.equals("") || i==data.size()) {
                if (i==data.size()) currentTile.add(s);
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
                currentTile = new ArrayList<>();
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

        for (String e : allEdges.keySet()) {
            System.out.println(e + ": " + allEdges.get(e));
        }
    }

    //answers found: tiles 3931, 2113, 2251, 2411
    public void findCorners() {
        Map<String, Integer> edgeSeen = new HashMap<>();
        for (String tile : tileNums.keySet()) {
            int matches = 0;
            for (String edge : tileNums.get(tile)) {
                edgeSeen.putIfAbsent(edge, 0);
                String reverseEdge = new StringBuilder(edge).reverse().toString();
                if (allEdges.containsKey(edge) && (allEdges.get(edge).size() > 1 || allEdges.get(edge).size() == 1 && allEdges.get(edge).get(0)!=tile || edgeSeen.get(edge)%2==1)) {
                    allEdges.get(edge).remove(tile);
                    matches++;
                    edgeSeen.put(edge, edgeSeen.get(edge)+1);
                }
                else if (allEdges.containsKey(reverseEdge) && (allEdges.get(reverseEdge).size() > 0 || edgeSeen.get(edge) < edgeSeen.get(reverseEdge))) {
                    allEdges.get(edge).remove(tile);
                    matches++;
                    edgeSeen.put(edge, edgeSeen.get(edge)+1);
                }
            }
            if (matches==2) {
                System.out.println(tile + " " + matches);
                corners.add(tile);
            }
        }
    }
}
