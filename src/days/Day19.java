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
                String newRule1 = rules.get(curr).substring(0, rules.get(curr).indexOf(" | "));
                String newRule2 = rules.get(curr).substring(rules.get(curr).indexOf(" | ")+3);
                for (String a : rulesList) {
                    newRule1+=" " + a;
                    newRule2+=" " + a;
                }
                boolean one = true;
                boolean two = true;
                for (String a : newRule1.split(" ")) {
                    if (!rules.get(a).contains("\"")) {
                        one = false;
                        break;
                    }
                }
                for (String a : newRule2.split(" ")) {
                    if (!rules.get(a).contains("\"")) {
                        two = false;
                        break;
                    }
                }
                if (one) possibilities.add(makeRule(newRule1, current));
                else makeRule(newRule1, current);
                if (two) possibilities.add(makeRule(newRule2, current));
                else makeRule(newRule2, current);
                return current;
            }
        }
        return current;
    }

}
