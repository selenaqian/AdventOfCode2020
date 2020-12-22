package days;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

public class Day22 extends Day {
    List<Integer> player1;
    List<Integer> player2;

    public Day22(String filename) throws FileNotFoundException {
        super(filename);
        player1 = new ArrayList<>();
        player2 = new ArrayList<>();
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

        System.out.println(player1);
        System.out.println(player2);
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

        int score = 0;
        if (player1.size()!=0) {
            for (int i = 1; i < player1.size()+1; i++) {
                score+=i*player1.get(player1.size()-i);
            }
        }
        else {
            for (int i = 1; i < player2.size()+1; i++) {
                score+=i*player2.get(player2.size()-i);
            }
        }
        return score;
    }
}
