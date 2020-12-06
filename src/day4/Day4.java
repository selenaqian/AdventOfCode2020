package day4;

import read.SimpleFileReader;

import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Day4 {
    List<String> data;
    int total = 0;

    public Day4(String filename) throws FileNotFoundException {
        SimpleFileReader fr = new SimpleFileReader(filename);
        data = fr.getData();
    }

    public int countValid() {
        int count = 0;
        Map<String, String> info = new HashMap<>();
        int i = 0;
        while (i <= data.size()) {
            if (i == data.size() || data.get(i).equals("")) {
                if (isValid(info)) count++;
                i++;
                info = new HashMap<>();
            }
            if (i >= data.size()) break;
            if (data.get(i).contains(" ")) {
                String[] temp = data.get(i).split(" ");
                for (String s : temp) {
                    info.put(s.split(":")[0], s.split(":")[1]);
                }
            }
            else {
                info.put(data.get(i).split(":")[0], data.get(i).split(":")[1]);
            }
            i++;
        }
        System.out.println(total);
        return count;
    }

    private boolean isValid(Map info) {
        total++;
        if (info.keySet().containsAll(Arrays.asList("byr", "iyr", "eyr", "hgt", "hcl", "ecl", "pid"))) {
            System.out.println(info);
            return byrValid(Integer.parseInt((String)info.get("byr"))) && iyrValid(Integer.parseInt((String)info.get("iyr"))) &&
                    eyrValid(Integer.parseInt((String)info.get("eyr"))) && hgtValid((String)info.get("hgt")) &&
                    hclValid((String)info.get("hcl")) && eclValid((String)info.get("ecl")) &&
                    pidValid((String)info.get("pid"));
        }
        return false;
    }

    private boolean byrValid(int byr) {
        System.out.println(byr);
        return byr >= 1920 && byr <= 2002;
    }

    private boolean iyrValid(int iyr) {
        System.out.println(iyr);
        return iyr >= 2010 && iyr <= 2020;
    }

    private boolean eyrValid(int eyr) {
        System.out.println(eyr);
        return eyr >= 2020 && eyr <= 2030;
    }

    private boolean hgtValid(String hgt) {
        System.out.println(hgt);
        int hgtValue = 0;
        try {
            hgtValue = Integer.parseInt(hgt.replaceAll("[a-zA-Z]", ""));
        }
        catch(Exception e) {
            return false;
        }
        String hgtUnit = hgt.replaceAll("[0-9]", "");
        System.out.printf("%d %s\n", hgtValue, hgtUnit);
        if (hgtUnit.equals("cm")) return hgtValue >= 150 && hgtValue <= 193;
        else if (hgtUnit.equals("in")) return hgtValue >= 59 && hgtValue <= 76;
        return false;
    }

    private boolean hclValid(String hcl) {
        System.out.println(hcl);
        if (hcl.charAt(0) == '#') {
            int count = 0;
            for (int i = 1; i < hcl.length(); i++) {
                if (Character.toString(hcl.charAt(i)).matches("[a-f0-9]")) count++;
            }
            return count == hcl.length()-1;
        }
        return false;
    }

    private boolean eclValid(String ecl) {
        System.out.println(ecl);
        return ecl.matches("amb|blu|brn|gry|grn|hzl|oth");
    }

    private boolean pidValid(String pid) {
        System.out.println(pid);
        if (pid.length() == 9) {
            int count = 0;
            for (int i = 0; i < pid.length(); i++) {
                if (Character.toString(pid.charAt(i)).matches("[0-9]")) count++;
            }
            return count == 9;
        }
        return false;
    }
}
