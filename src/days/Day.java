package days;

import read.SimpleFileReader;

import java.io.FileNotFoundException;
import java.util.List;

public abstract class Day {
    List<String> data;

    public Day(String filename) throws FileNotFoundException {
        SimpleFileReader fr = new SimpleFileReader(filename);
        data = fr.getData();
    }
}
