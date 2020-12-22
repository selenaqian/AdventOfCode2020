package days;

import java.io.FileNotFoundException;
import java.util.*;

public class Day21 extends Day {
    Set<Recipe> recipes;
    Set<String> allAllergens;
    Map<String, String> allergenToName;

    public Day21(String filename) throws FileNotFoundException {
        super(filename);
        recipes = new HashSet<>();
        allAllergens = new TreeSet<>();
        allergenToName = new HashMap<>();

        for (String line : data) {
            String[] parts = line.split(" \\(contains ");
            Set<String> ingredients = new HashSet<>(Arrays.asList(parts[0].split(" ")));
            Set<String> allerg = new HashSet<>(Arrays.asList(parts[1].replace(")", "").split(", ")));
            allAllergens.addAll(allerg);
            recipes.add(new Recipe(ingredients, allerg));
        }
        System.out.println(allAllergens);
    }

    public int notAllergens() {
        while (allergenToName.keySet().size() < allAllergens.size()) {
            for (String a : allAllergens) {
                if (!allergenToName.containsKey(a)) {
                    Set<String> possible = new HashSet<>();
                    boolean found = false;
                    for (Recipe r : recipes) {
                        if (r.allergens.contains(a)) {
                            if (possible.size() == 0) {
                                possible = new HashSet<>(r.ingredients);
                            } else possible.retainAll(r.ingredients);
                            if (possible.size() == 1) {
                                String allerg = "";
                                for (String i : possible) {
                                    allerg = i;
                                }
                                for (Recipe x : recipes) {
                                    x.removeIngredient(allerg);
                                }
                                System.out.println(a + ": " + allerg);
                                allergenToName.put(a, allerg);
                                found = true;
                                break;
                            }
                        }
                        if (found) break;
                    }
                    if (found) break;
                }
            }
        }

        int count = 0;
        for (Recipe r : recipes) {
            count+=r.ingredients.size();
        }
        return count;
    }

    public String allergens() {
        StringBuilder allergList = new StringBuilder();
        for (String a : allAllergens) {
            allergList.append(allergenToName.get(a) + ",");
        }
        return allergList.toString().substring(0, allergList.length()-1);
    }

    public class Recipe {
        Set<String> ingredients;
        Set<String> allergens;

        public Recipe(Set<String> ingred, Set<String> allerg) {
            ingredients = ingred;
            allergens = allerg;
        }

        public void removeIngredient(String ingred) {
            ingredients.remove(ingred);
        }
    }
}
