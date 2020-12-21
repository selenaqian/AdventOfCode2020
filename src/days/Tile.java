package days;

import java.util.List;

public class Tile {
    char[][] data;
    String top, right, bottom, left;

    public Tile(List<String> rows) {
        top = rows.get(0);
        bottom = rows.get(rows.size()-1);
        left = "";
        right = "";
        data = new char[rows.size()][rows.get(0).length()];

        for (int i = 0; i < rows.size(); i++) {
            for (int j = 0; j < rows.get(0).length(); j++) {
                data[i][j] = rows.get(i).charAt(j);
                if (j == 0) left = left + rows.get(i).charAt(j);
                if (j == rows.get(0).length()-1) right = right + rows.get(i).charAt(j);
            }
        }
    }

    public void flip(char direction) {
        //h = horizontal flip, v = vertical
    }

    public void rotate(int degrees) {
        //90 or 180
    }
}
