package read;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class SimpleFileReader {
    List<String> allData;

    public SimpleFileReader(String filename) throws FileNotFoundException {
        allData = new ArrayList<>();
        readFile(filename);
    }

    private void readFile(String filename) throws FileNotFoundException {
        File myObj = new File(filename);
        Scanner myReader = new Scanner(myObj);
        while (myReader.hasNextLine()) {
            String data = myReader.nextLine();
            allData.add(data);
        }
    }

    public List<String> getData() {
        return allData;
    }
}
