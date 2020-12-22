package days;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

public class Day22 extends Day {
    List<Integer> player1;
    List<Integer> player2;
    List<List<Integer>> seen1;
    List<List<Integer>> seen2;

    public Day22(String filename) throws FileNotFoundException {
        super(filename);
        player1 = new ArrayList<>();
        player2 = new ArrayList<>();
        seen1 = new ArrayList<>();
        seen2 = new ArrayList<>();

        setupGame();
    }

    private void setupGame() {
        boolean p1 = true;
        for (String s : data) {
            if (s.equals("Player 2:")) {
                p1 = false;
            }
            else if (s.equals("Player 1:") || s.equals("")) continue;
            else if (p1) {
                player1.add(Integer.parseInt(s));
            }
            else {
                player2.add(Integer.parseInt(s));
            }
        }
    }

    public int getScore() {
        while (player1.size()!=0 && player2.size()!=0) {
            //play a round
            int p1 = player1.get(0);
            int p2 = player2.get(0);
            if (p1 > p2) {
                player1.add(p1);
                player1.add(p2);
            }
            else if (p2 > p1) {
                player2.add(p2);
                player2.add(p1);
            }
            player1.remove(0);
            player2.remove(0);
        }

        return calculateScore(player1, player2);
    }

    public int playRecurse() {
        int[] output = recurseRound(player1, player2, seen1, seen2);
        System.out.println(player1);
        System.out.println(player2);
        return output[1];
    }

    private int[] recurseRound(List<Integer> p1, List<Integer> p2, List<List<Integer>> s1, List<List<Integer>> s2) {
        int winner = 0;
        while (p1.size() > 0 && p2.size() > 0) {
            int card1 = p1.get(0);
            int card2 = p2.get(0);
            if (s1.indexOf(p1) > -1 && s1.indexOf(p1) == s2.indexOf(p2)) {
                return new int[]{1, calculateScore(p1, p2)};
            }
            s1.add(new ArrayList<>(p1));
            s2.add(new ArrayList<>(p2));
            if (p1.size() >= card1 + 1 && p2.size() >= card2 + 1) {
                //play another game with subdecks of size based on the card number drawn
                List<Integer> temp1 = new ArrayList<>();
                List<Integer> temp2 = new ArrayList<>();
                for (int i = 0; i < card1; i++) {
                    temp1.add(p1.get(i+1));
                }
                for (int i = 0; i < card2; i++) {
                    temp2.add(p2.get(i+1));
                }
                winner = recurseRound(temp1, temp2, new ArrayList<>(), new ArrayList<>())[0];
            }
            else if (card1 > card2) {
                winner = 1;
            }
            else {
                winner = 2;
            }
            if (winner == 1) {
                p1.add(card1);
                p1.add(card2);
            }
            else {
                p2.add(card2);
                p2.add(card1);
            }
            p1.remove(0);
            p2.remove(0);
        }
        if (p1.size() > 0) return new int[]{1, calculateScore(p1, p2)};
        else return new int[]{2, calculateScore(p1, p2)};
    }

    private int calculateScore(List<Integer> p1, List<Integer> p2) {
        int score = 0;
        if (p1.size()!=0) {
            for (int i = 1; i < p1.size()+1; i++) {
                score+=i*p1.get(p1.size()-i);
            }
        }
        else {
            for (int i = 1; i < p2.size()+1; i++) {
                score+=i*p2.get(p2.size()-i);
            }
        }
        return score;
    }
}
