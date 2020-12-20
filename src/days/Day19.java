package days;

import java.io.FileNotFoundException;
import java.util.*;

public class Day19 extends Day {
    Map<String, String> rules;
    List<String> messages;
    List<String> possibilities;

    public Day19(String filename) throws FileNotFoundException {
        super(filename);
        rules = new HashMap<>();
        messages = new ArrayList<>();
        possibilities = new ArrayList<>();

        for (String line : data) {
            if (line.contains(":")) {
                rules.put(line.split(": ")[0], line.split(": ")[1]);
            }
            else if (!line.equals("")) {
                messages.add(line);
            }
        }
    }

    public int checkValidMessages() {
        makeRule(rules.get("0"), "");
        int count = 0;
        for (String m : messages) {
            for (String rule : possibilities) {
                if (m.equals(rule)) count++;
            }
        }
        return count;
    }

    public int checkValid2() {
        //replacing 8 and 11 with the following:
        //8: 42 | 42 8
        //11: 42 31 | 42 11 31
        //effectively, this means if you see 8, you can have any number of 42's in a row
        //and if you see 11 you can have any number of 42's followed by the same number of 31's
        //so maybe leave 8 and 11 in the makeRule and calculate 42 and 31 as well so when checking validity can swap in stuff?
        possibilities = new ArrayList<>();

        makeRule(rules.get("42").substring(0, rules.get("42").indexOf(" | ")), "");
        makeRule(rules.get("42").substring(rules.get("42").indexOf(" | ")+3), "");
        List<String> rule42 = possibilities;
        System.out.println(rule42);
        possibilities = new ArrayList<>();

        makeRule(rules.get("31").substring(0, rules.get("31").indexOf(" | ")), "");
        makeRule(rules.get("31").substring(rules.get("31").indexOf(" | ")+3), "");
        List<String> rule31 = possibilities;
        System.out.println(rule31);
        possibilities = new ArrayList<>();

        makeRule2("0", "");
        int count = 0;
        // can tell from input that 0 is just 8 and 11 so that simplifies this a lot - don't even need the makeRule2 method but I'm going to leave it
        for (String m : messages) {
            System.out.println(m);
            boolean has42;
            int count42 = 0;
            boolean has11;
            int count11 = 0;
            while (true) {
                has11 = false;
                for (String option42 : rule42) {
                    for (String option31 : rule31) {
                        if (m.indexOf(option42) == 0 && m.lastIndexOf(option31) == m.length() - option31.length()) {
                            has11 = true;
                            count11++;
                            m = m.substring(option42.length(), m.length() - option31.length());
                            break;
                        }
                    }
                    if (has11) break;
                }
                if (!has11) break;
            }
            while (count11 > 0) {
                has42 = false;
                for (String option : rule42) {
                    if (m.indexOf(option) == 0) {
                        has42 = true;
                        count42++;
                        m = m.substring(option.length());
                        break;
                    }
                }
                if (!has42) break;
                if (count11 > 0 && count42 > 0 && m.length() == 0) {
                    count++;
                    System.out.println(count);
                }
            }
        }
        return count;
    }

    private String makeRule2(String rule, String current) {
        String[] all = rule.split(" ");
        List<String> rulesList = new ArrayList<>();
        for (String s : all) {
            rulesList.add(s);
        }
        while (!rulesList.isEmpty()) {
            String curr = rulesList.get(0);
            rulesList.remove(0);
            if (rules.get(curr).contains("\"")) {
                current+=rules.get(curr).substring(1,2);
            }
            else if (curr.equals("8")) {
                String next = "";
                for (String a : rulesList) {
                    next+=" " + a;
                }
                if (next.length() > 0) {
                    makeRule2(next.trim(), current+"8");
                }
                else {
                    possibilities.add(current+"8");
                    return current+"8";
                }
                break;
            }
            else if (curr.equals("11")) {
                String next = "";
                for (String a : rulesList) {
                    next+=" " + a;
                }
                if (next.length() > 0) {
                    makeRule2(next.trim(), current+"11");
                }
                else {
                    possibilities.add(current+"11");
                    return current+"11";
                }
                break;
            }
            else {
                String newRule1 = rules.get(curr);
                String newRule2 = null;
                if (rules.get(curr).contains("|")) {
                    newRule1 = rules.get(curr).substring(0, rules.get(curr).indexOf(" | "));
                    newRule2 = rules.get(curr).substring(rules.get(curr).indexOf(" | ")+3);
                }
                for (String a : rulesList) {
                    newRule1+=" " + a;
                    if (newRule2!= null) newRule2+=" " + a;
                }
                boolean one = true;
                for (String a : newRule1.split(" ")) {
                    if (!rules.get(a).contains("\"")) {
                        one = false;
                        break;
                    }
                }
                if (one) possibilities.add(makeRule2(newRule1, current));
                else makeRule2(newRule1, current);

                if (newRule2!= null) {
                    boolean two = true;
                    for (String a : newRule2.split(" ")) {
                        if (!rules.get(a).contains("\"")) {
                            two = false;
                            break;
                        }
                    }
                    if (two) possibilities.add(makeRule2(newRule2, current));
                    else makeRule2(newRule2, current);
                }
                return current;
            }
        }
        return current;
    }

    private String makeRule(String rule, String current) {
        String[] all = rule.split(" ");
        List<String> rulesList = new ArrayList<>();
        for (String s : all) {
            rulesList.add(s);
        }
        while (!rulesList.isEmpty()) {
            String curr = rulesList.get(0);
            rulesList.remove(0);
            if (rules.get(curr).contains("\"")) {
                current+=rules.get(curr).substring(1,2);
            }
            else {
                String newRule1 = rules.get(curr);
                String newRule2 = null;
                if (rules.get(curr).contains("|")) {
                    newRule1 = rules.get(curr).substring(0, rules.get(curr).indexOf(" | "));
                    newRule2 = rules.get(curr).substring(rules.get(curr).indexOf(" | ")+3);
                }
                for (String a : rulesList) {
                    newRule1+=" " + a;
                    if (newRule2!= null) newRule2+=" " + a;
                }
                boolean one = true;
                for (String a : newRule1.split(" ")) {
                    if (!rules.get(a).contains("\"")) {
                        one = false;
                        break;
                    }
                }
                if (one) possibilities.add(makeRule(newRule1, current));
                else makeRule(newRule1, current);

                if (newRule2!= null) {
                    boolean two = true;
                    for (String a : newRule2.split(" ")) {
                        if (!rules.get(a).contains("\"")) {
                            two = false;
                            break;
                        }
                    }
                    if (two) possibilities.add(makeRule(newRule2, current));
                    else makeRule(newRule2, current);
                }
                return current;
            }
        }
        return current;
    }

}
